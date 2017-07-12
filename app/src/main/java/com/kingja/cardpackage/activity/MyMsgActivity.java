package com.kingja.cardpackage.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;

import com.kingja.cardpackage.Event.CancleEntrustDeployEvent;
import com.kingja.cardpackage.adapter.MyMsgAdapter;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetLastUserMessage;
import com.kingja.cardpackage.entiy.GetMyMsgEvent;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.ui.PullToBottomRecyclerView;
import com.kingja.cardpackage.util.AppUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.recyclerviewhelper.BaseRvAdaper;
import com.kingja.recyclerviewhelper.LayoutHelper;
import com.kingja.recyclerviewhelper.RecyclerViewHelper;
import com.tdr.wisdome.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:我的消息
 * Create Time:2017/4/17 13:11
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MyMsgActivity extends BackTitleActivity implements SwipeRefreshLayout.OnRefreshListener{
    private LinearLayout mLlRoot;
    private LinearLayout mLlEmpty;
    private SwipeRefreshLayout mSrl;
    private PullToBottomRecyclerView mRv;
    private List<GetLastUserMessage.ContentBean> lastUserMsgs = new ArrayList<>();
    private MyMsgAdapter mMyMsgAdapter;


    @Override
    protected void initVariables() {
        EventBus.getDefault().register(this);

    }

    @Override
    protected void initContentView() {
        mLlRoot = (LinearLayout) findViewById(R.id.ll_root);
        mLlEmpty = (LinearLayout) findViewById(R.id.ll_empty);
        mSrl = (SwipeRefreshLayout) findViewById(R.id.srl);
        mRv = (PullToBottomRecyclerView) findViewById(R.id.rv);

        mSrl.setColorSchemeResources(R.color.bg_black);
        mSrl.setProgressViewOffset(false, 0, AppUtil.dp2px(24));
        mMyMsgAdapter = new MyMsgAdapter(this, lastUserMsgs);
        new RecyclerViewHelper.Builder(this)
                .setCallbackAdapter(mMyMsgAdapter)
                .setLayoutStyle(LayoutHelper.LayoutStyle.VERTICAL_LIST)
                .build()
                .attachToRecyclerView(mRv);

        mMyMsgAdapter.setOnItemClickListener(new BaseRvAdaper.OnItemClickListener<GetLastUserMessage.ContentBean>() {
            @Override
            public void onItemClick(GetLastUserMessage.ContentBean contentBean, int position) {

                CardMsgActivity.goAcivity(MyMsgActivity.this, contentBean.getCardCode());
            }
        });
    }

    @Override
    protected int getBackContentView() {
        return R.layout.single_rv;
    }

    @Override
    protected void initNet() {
        mSrl.setRefreshing(true);
        Map<String, Object> param = new HashMap<>();
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), "", KConstants.GetLastUserMessage, param)
                .setBeanType(GetLastUserMessage.class)
                .setCallBack(new WebServiceCallBack<GetLastUserMessage>() {
                    @Override
                    public void onSuccess(GetLastUserMessage bean) {
                        mSrl.setRefreshing(false);
                        lastUserMsgs = bean.getContent();
                        mLlEmpty.setVisibility(lastUserMsgs.size() > 0 ? View.GONE : View.VISIBLE);
                        mMyMsgAdapter.setData(lastUserMsgs);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        mSrl.setRefreshing(false);
                    }
                }).build().execute();
    }

    @Override
    protected void initData() {
        mSrl.setOnRefreshListener(this);

    }

    @Override
    protected void setData() {
        setTitle("我的消息");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMyMsg(GetMyMsgEvent event) {
        initNet();
    }

    @Override
    public void onRefresh() {
        mSrl.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
