package com.kingja.cardpackage.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;

import com.kingja.cardpackage.adapter.InsuranceAdapter;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetClaim;
import com.kingja.cardpackage.entiy.GetClaimInfo;
import com.kingja.cardpackage.entiy.GetClaimInfoList;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.ui.PullToBottomRecyclerView;
import com.kingja.cardpackage.util.AppUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.recyclerviewhelper.LayoutHelper;
import com.kingja.recyclerviewhelper.RecyclerViewHelper;
import com.tdr.wisdome.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:TODO
 * Create Time:2017/4/17 13:11
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class InsuranceListActivity extends BackTitleActivity {
    private LinearLayout mLlRoot;
    private LinearLayout mLlEmpty;
    private SwipeRefreshLayout mSrl;
    private PullToBottomRecyclerView mRv;
    private List<GetClaim> mClaimInfos=new ArrayList<>();
    private InsuranceAdapter mInsuranceAdapter;


    @Override
    protected void initVariables() {

    }

    @Override
    protected void initContentView() {
        mLlRoot = (LinearLayout) findViewById(R.id.ll_root);
        mLlEmpty = (LinearLayout) findViewById(R.id.ll_empty);
        mSrl = (SwipeRefreshLayout) findViewById(R.id.srl);
        mRv = (PullToBottomRecyclerView) findViewById(R.id.rv);

        mSrl.setColorSchemeResources(R.color.bg_black);
        mSrl.setProgressViewOffset(false, 0, AppUtil.dp2px(24));
        mInsuranceAdapter = new InsuranceAdapter(this, mClaimInfos);
        new RecyclerViewHelper.Builder(this)
                .setCallbackAdapter(mInsuranceAdapter)
                .setLayoutStyle(LayoutHelper.LayoutStyle.VERTICAL_LIST)
                .build()
                .attachToRecyclerView(mRv);

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
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.GetClaimInfoList, param)
                .setBeanType(GetClaimInfoList.class)
                .setCallBack(new WebServiceCallBack<GetClaimInfoList>() {
                    @Override
                    public void onSuccess(GetClaimInfoList bean) {
                        mSrl.setRefreshing(false);
                        mClaimInfos = bean.getContent();
                        if (mClaimInfos == null) {
                            mClaimInfos=new ArrayList<>();
                        }
                        mLlEmpty.setVisibility(mClaimInfos.size()>0? View.GONE:View.VISIBLE);
                        mInsuranceAdapter.setData(mClaimInfos);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        mSrl.setRefreshing(false);
                    }
                }).build().execute();
    }

    @Override
    protected void initData() {
        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSrl.setRefreshing(false);
            }
        });
    }

    @Override
    protected void setData() {
        setTitle("保险理赔");
    }
}
