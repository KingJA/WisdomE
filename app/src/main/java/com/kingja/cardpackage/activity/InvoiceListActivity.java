package com.kingja.cardpackage.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;

import com.kingja.cardpackage.adapter.InvoiceAdapter;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetInvoiceInfoList;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.ui.PullToBottomRecyclerView;
import com.kingja.cardpackage.util.AppUtil;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.recyclerviewhelper.LayoutHelper;
import com.kingja.recyclerviewhelper.RecyclerViewHelper;
import com.tdr.wisdome.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/5/8 9:25
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class InvoiceListActivity extends BackTitleActivity implements SwipeRefreshLayout.OnRefreshListener {
    private LinearLayout mLlRoot;
    private LinearLayout mLlEmpty;
    private SwipeRefreshLayout mSrl;
    private PullToBottomRecyclerView mRv;
    private List<GetInvoiceInfoList.ContentBean> invoices = new ArrayList<>();
    private InvoiceAdapter mInvoiceAdapter;


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

        mInvoiceAdapter = new InvoiceAdapter(this, invoices);

        new RecyclerViewHelper.Builder(this)
                .setCallbackAdapter(mInvoiceAdapter)
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
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.GetInvoiceInfoList, null)
                .setBeanType(GetInvoiceInfoList.class)
                .setCallBack(new WebServiceCallBack<GetInvoiceInfoList>() {
                    @Override
                    public void onSuccess(GetInvoiceInfoList bean) {
                        mSrl.setRefreshing(false);
                        invoices = bean.getContent();
                        mLlEmpty.setVisibility(invoices.size() > 0 ? View.GONE : View.VISIBLE);
                        mInvoiceAdapter.setData(invoices);
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
        setTitle("电子发票");
    }

    @Override
    public void onRefresh() {
        mSrl.setRefreshing(false);
    }
}
