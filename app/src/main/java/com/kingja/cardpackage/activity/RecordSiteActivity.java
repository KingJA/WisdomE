package com.kingja.cardpackage.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.kingja.cardpackage.adapter.RecordSiteAdapter;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetRegistersiteList;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.AppUtil;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.KConstants;
import com.tdr.wisdome.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/12/28 15:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RecordSiteActivity extends BackTitleActivity implements SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemClickListener{
    private SwipeRefreshLayout mSrlTopContent;
    private ListView mLvTopContent;
    private RecordSiteAdapter mRecordSiteAdapter;
    private LinearLayout mLlEmpty;
    private List<GetRegistersiteList.ContentBean> recordSiteList=new ArrayList<>();
    private String siteName;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initContentView() {
        mLlEmpty = (LinearLayout) findViewById(R.id.ll_empty);
        mSrlTopContent = (SwipeRefreshLayout) findViewById(R.id.srl);
        mLvTopContent = (ListView) findViewById(R.id.lv);

        mRecordSiteAdapter = new RecordSiteAdapter(this, recordSiteList);
        mLvTopContent.setAdapter(mRecordSiteAdapter);

        mSrlTopContent.setColorSchemeResources(R.color.bg_black);
        mSrlTopContent.setProgressViewOffset(false, 0, AppUtil.dp2px(24));
    }

    @Override
    protected int getBackContentView() {
        return R.layout.single_lv;
    }

    @Override
    protected void initNet() {
        mSrlTopContent.setRefreshing(true);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.GetRegistersiteList, null)
                .setBeanType(GetRegistersiteList.class)
                .setCallBack(new WebServiceCallBack<GetRegistersiteList>() {
                    @Override
                    public void onSuccess(GetRegistersiteList bean) {
                        mSrlTopContent.setRefreshing(false);
                        recordSiteList = bean.getContent();
                        mLlEmpty.setVisibility(recordSiteList.size() > 0 ? View.GONE : View.VISIBLE);
                        mRecordSiteAdapter.setData(recordSiteList);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        mSrlTopContent.setRefreshing(false);
                    }
                }).build().execute();
    }

    @Override
    protected void initData() {
        mLvTopContent.setOnItemClickListener(this);
        mSrlTopContent.setOnRefreshListener(this);
    }

    @Override
    protected void setData() {
        setTitle("备案登记点");
    }

    @Override
    public void onRefresh() {
        mSrlTopContent.setRefreshing(false);
    }

    private String registersiteId="";
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GetRegistersiteList.ContentBean bean= (GetRegistersiteList.ContentBean) parent.getItemAtPosition(position);
        Intent intent = new Intent();
        registersiteId=bean.getRegistersiteId();
        siteName = bean.getRegistersiteName();
        intent.putExtra("RegistersiteId",registersiteId);
        intent.putExtra("SiteName", siteName);
        setResult(RESULT_OK,intent);
        finish();
    }
}
