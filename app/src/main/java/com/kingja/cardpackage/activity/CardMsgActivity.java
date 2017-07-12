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

import com.kingja.cardpackage.adapter.CardMsgAdapter;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetMsgCountEvent;
import com.kingja.cardpackage.entiy.GetMyMsgEvent;
import com.kingja.cardpackage.entiy.GetUserMessagePager;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.AppUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.ToastUtil;
import com.tdr.wisdome.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：TODO
 * Create Time：2017/1/24 9:14
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CardMsgActivity extends BackTitleActivity implements AdapterView.OnItemClickListener, SwipeRefreshLayout
        .OnRefreshListener {
    private LinearLayout mLlRoot;
    private SwipeRefreshLayout mSrl;
    private ListView mLv;
    private LinearLayout mLlEmpty;
    private int LOADSIZE = 50;
    private int loadIndex = 1;
    private boolean hasMore;
    private List<GetUserMessagePager.ContentBean.DataBean> msgList = new ArrayList<>();
    private CardMsgAdapter cardMsgAdapter;
    private String cardCode;

    @Override
    protected void initVariables() {
        cardCode = getIntent().getStringExtra("cardCode");
    }

    @Override
    protected void initContentView() {
        mLlRoot = (LinearLayout) findViewById(R.id.ll_root);
        mSrl = (SwipeRefreshLayout) findViewById(R.id.srl);
        mLv = (ListView) findViewById(R.id.lv);
        mLlEmpty = (LinearLayout) findViewById(R.id.ll_empty);

        cardMsgAdapter = new CardMsgAdapter(this, msgList);
        mLv.setAdapter(cardMsgAdapter);

        mSrl.setColorSchemeResources(R.color.bg_black);
        mSrl.setProgressViewOffset(false, 0, AppUtil.dp2px(24));
    }

    @Override
    protected int getBackContentView() {
        return R.layout.single_lv;
    }

    @Override
    protected void initNet() {
        loadNet(1);
    }

    @Override
    protected void initData() {
        mLv.setOnItemClickListener(this);
        mSrl.setOnRefreshListener(this);
        mLv.setOnScrollListener(onScrollListener);

    }

    @Override
    protected void setData() {
        setTitle(getCardType(cardCode));
    }

    private void loadNet(final int index) {
        mSrl.setRefreshing(true);
        Map<String, Object> param = new HashMap<>();
        param.put("CardCode", cardCode);
        param.put("PageIndex", index);
        param.put("PageSize", LOADSIZE);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), "", KConstants.GetUserMessagePager, param)
                .setBeanType(GetUserMessagePager.class)
                .setCallBack(new WebServiceCallBack<GetUserMessagePager>() {
                    @Override
                    public void onSuccess(GetUserMessagePager bean) {
                        mSrl.setRefreshing(false);
                        msgList = bean.getContent().getData();
                        checkHasNoReaded(msgList);
                        if (index == 1) {
                            cardMsgAdapter.reset();
                        }
                        hasMore = msgList.size() == LOADSIZE;
                        Log.e(TAG, "hasMore" + hasMore);
                        Log.e(TAG, "加载数据条数" + msgList.size());
                        mLlEmpty.setVisibility(msgList.size() > 0 ? View.GONE : View.VISIBLE);
                        cardMsgAdapter.addData(msgList);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        mSrl.setRefreshing(false);
                    }
                }).build().execute();
    }

    private void checkHasNoReaded(List<GetUserMessagePager.ContentBean.DataBean> msgList) {
        for (GetUserMessagePager.ContentBean.DataBean msg : msgList) {
            if (msg.getIsRead() == 0) {
                setOnRightClickListener(new OnRightClickListener() {
                    @Override
                    public void onRightClick() {
                        setAllMsgReaded();
                    }
                }, "全部已读");
                return;
            }
        }
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
        GetUserMessagePager.ContentBean.DataBean msgBean = (GetUserMessagePager.ContentBean.DataBean) parent
                .getItemAtPosition(position);
        CardMsgDetailActivity.goActivity(CardMsgActivity.this, msgBean);
        setMsgReaded(msgBean.getMessageID(), position);
    }

    @Override
    public void onRefresh() {
        loadNet(1);
    }

    private void setMsgReaded(String messageId, final int position) {
        mSrl.setRefreshing(true);
        Map<String, Object> param = new HashMap<>();
        param.put("Messageid", messageId);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), "", KConstants.SetUserMessage, param)
                .setBeanType(Object.class)
                .setCallBack(new WebServiceCallBack<Object>() {
                    @Override
                    public void onSuccess(Object bean) {
                        mSrl.setRefreshing(false);
                        checkHasNoReaded(msgList);
                        cardMsgAdapter.setReadedStatus(position);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        mSrl.setRefreshing(false);
                    }
                }).build().execute();
    }

    private void setAllMsgReaded() {
        mSrl.setRefreshing(true);
        Map<String, Object> param = new HashMap<>();
        param.put("CardCode", cardCode);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), "", KConstants.SetUserMessageAll, param)
                .setBeanType(Object.class)
                .setCallBack(new WebServiceCallBack<Object>() {
                    @Override
                    public void onSuccess(Object bean) {
                        mSrl.setRefreshing(false);
                        cardMsgAdapter.setAllReadedStatus();
                        setOnRightClickGone();

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
        EventBus.getDefault().post(new GetMyMsgEvent());
        EventBus.getDefault().post(new GetMsgCountEvent());
    }

    public static void goAcivity(Context context, String cardCode) {
        Intent intent = new Intent(context, CardMsgActivity.class);
        intent.putExtra("cardCode", cardCode);
        context.startActivity(intent);
    }

    private String getCardType(String cardCode) {
        String cardType = "未知类型";
        switch (cardCode) {
            case "1001":
                cardType = "我的住房";
                break;
            case "1002":
                cardType = "我的出租房";
                break;
            case "1003":
                cardType = "我的车";
                break;
            case "1004":
                cardType = "我的店";
                break;
            case "1005":
                cardType = "亲情关爱";
                break;
            case "1006":
                cardType = "服务商城";
                break;
            case "1007":
                cardType = "出租房代管";
                break;
        }
        return cardType;
    }
}
