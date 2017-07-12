package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.kingja.cardpackage.Event.GetCarDataEvent;
import com.kingja.cardpackage.adapter.CarMsgAdapter;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetMessagePager;
import com.kingja.cardpackage.entiy.GetMsgCountEvent;
import com.kingja.cardpackage.entiy.GetUserMessagePager;
import com.kingja.cardpackage.entiy.KB_IsRead;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.AppUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.ToastUtil;
import com.tdr.wisdome.R;
import com.tdr.wisdome.util.Constants;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：我的车报警记录
 * Create Time：2017/1/24 9:14
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CarMsgActivity extends BackTitleActivity implements AdapterView.OnItemClickListener, SwipeRefreshLayout
        .OnRefreshListener {
    private LinearLayout mLlRoot;
    private SwipeRefreshLayout mSrl;
    private ListView mLv;
    private LinearLayout mLlEmpty;
    private int LOADSIZE = 20;
    private int loadIndex = 0;
    private boolean hasMore;
    private CarMsgAdapter mCarMsgAdapter;
    private String platenumber;
    private List<GetMessagePager.ContentBean> carAlarms = new ArrayList();

    @Override
    protected void initVariables() {
        platenumber = getIntent().getStringExtra("platenumber");
    }

    @Override
    protected void initContentView() {
        mLlRoot = (LinearLayout) findViewById(R.id.ll_root);
        mSrl = (SwipeRefreshLayout) findViewById(R.id.srl);
        mLv = (ListView) findViewById(R.id.lv);
        mLlEmpty = (LinearLayout) findViewById(R.id.ll_empty);

        mCarMsgAdapter = new CarMsgAdapter(this, carAlarms);
        mLv.setAdapter(mCarMsgAdapter);

        mSrl.setColorSchemeResources(R.color.bg_black);
        mSrl.setProgressViewOffset(false, 0, AppUtil.dp2px(24));
    }

    @Override
    protected int getBackContentView() {
        return R.layout.single_lv;
    }

    @Override
    protected void initNet() {
        loadNet(0);
    }

    @Override
    protected void initData() {
        mLv.setOnItemClickListener(this);
        mSrl.setOnRefreshListener(this);
        mLv.setOnScrollListener(onScrollListener);
    }

    @Override
    protected void setData() {
        setTitle("报警记录");
    }

    private void loadNet(final int index) {
        mSrl.setRefreshing(true);
        Map<String, Object> param = new HashMap<>();
        param.put("platenumber", platenumber);
        param.put("pageNum", index);
        param.put("pageSize", LOADSIZE);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.GetMessagePager, param)
                .setBeanType(GetMessagePager.class)
                .setCallBack(new WebServiceCallBack<GetMessagePager>() {
                    @Override
                    public void onSuccess(GetMessagePager bean) {
                        mSrl.setRefreshing(false);
                        carAlarms = bean.getContent();
                        if (index == 0) {
                            mCarMsgAdapter.reset();
                            mLlEmpty.setVisibility(carAlarms.size() > 0 ? View.GONE : View.VISIBLE);
                        }
                        hasMore = carAlarms.size() == LOADSIZE;
                        Log.e(TAG, "hasMore" + hasMore);
                        Log.e(TAG, "加载数据条数" + carAlarms.size());
                        mCarMsgAdapter.addData(carAlarms);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        mSrl.setRefreshing(false);
                    }
                }).build().execute();
    }


    private AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            switch (scrollState) {
                case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                    if (mLv.getLastVisiblePosition() == (mLv.getCount() - 1)) {
                        Log.e("log", "滑到底部");
                        if (mSrl.isRefreshing()) {
                            return;
                        }
                        if (hasMore) {
                            loadNet(++loadIndex);
                        } else {
                            ToastUtil.showToast("已经没有更多数据");
                        }
                    }
                    break;
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GetMessagePager.ContentBean msgBean = (GetMessagePager.ContentBean) parent
                .getItemAtPosition(position);
        CarMsgDetailActivity.goActivity(CarMsgActivity.this, msgBean);
        setMsgReaded(msgBean.getLISTID(), position);
    }

    @Override
    public void onRefresh() {
        loadNet(1);
    }

    private void setMsgReaded(String listid, final int position) {
        mSrl.setRefreshing(true);
        Map<String, Object> param = new HashMap<>();
        param.put("listid", listid);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.KB_IsRead, param)
                .setBeanType(KB_IsRead.class)
                .setCallBack(new WebServiceCallBack<KB_IsRead>() {
                    @Override
                    public void onSuccess(KB_IsRead bean) {
                        mSrl.setRefreshing(false);
                        mCarMsgAdapter.setReadedStatus(position);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        mSrl.setRefreshing(false);
                    }
                }).build().execute();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new GetMsgCountEvent());
        EventBus.getDefault().post(new GetCarDataEvent());
    }

    public static void goAcivity(Context context, String platenumber) {
        Intent intent = new Intent(context, CarMsgActivity.class);
        intent.putExtra("platenumber", platenumber);
        context.startActivity(intent);
    }
}
