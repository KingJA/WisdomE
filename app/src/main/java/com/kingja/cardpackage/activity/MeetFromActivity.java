package com.kingja.cardpackage.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.kingja.cardpackage.adapter.MeetFromAdapter;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetRegister_ConfigList;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.AppUtil;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.ToastUtil;
import com.tdr.wisdome.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：预约时间段
 * Create Time：2016/12/28 15:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MeetFromActivity extends BackTitleActivity implements SwipeRefreshLayout.OnRefreshListener,AdapterView.OnItemClickListener{
    private SwipeRefreshLayout mSrlTopContent;
    private ListView mLvTopContent;
    private MeetFromAdapter mMeetFromAdapter;
    private LinearLayout mLlEmpty;
    private List<GetRegister_ConfigList.ContentBean> meetFromList=new ArrayList<>();
    private String onTime="";
    private String offTime="";
    private String registersiteId;
    private String reservateTime;
    private String configId="";

    @Override
    protected void initVariables() {
        registersiteId = getIntent().getStringExtra("registersiteId");
        reservateTime = getIntent().getStringExtra("reservateTime");
    }

    @Override
    protected void initContentView() {
        mLlEmpty = (LinearLayout) findViewById(R.id.ll_empty);
        mSrlTopContent = (SwipeRefreshLayout) findViewById(R.id.srl);
        mLvTopContent = (ListView) findViewById(R.id.lv);

        mMeetFromAdapter = new MeetFromAdapter(this, meetFromList);
        mLvTopContent.setAdapter(mMeetFromAdapter);

        mSrlTopContent.setColorSchemeResources(R.color.bg_black);
        mSrlTopContent.setProgressViewOffset(false, 0, AppUtil.dp2px(24));
    }

    @Override
    protected int getBackContentView() {
        return R.layout.single_lv;
    }

    @Override
    protected void initNet() {
        Map<String,Object> mParam = new HashMap<>();
        mParam.put("RegistersiteId", registersiteId);
        mParam.put("ReservateTime",reservateTime);
        mSrlTopContent.setRefreshing(true);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.GetRegister_ConfigList, mParam)
                .setBeanType(GetRegister_ConfigList.class)
                .setCallBack(new WebServiceCallBack<GetRegister_ConfigList>() {
                    @Override
                    public void onSuccess(GetRegister_ConfigList bean) {
                        mSrlTopContent.setRefreshing(false);
                        meetFromList = bean.getContent();
                        mLlEmpty.setVisibility(meetFromList.size() > 0 ? View.GONE : View.VISIBLE);
                        mMeetFromAdapter.setData(meetFromList);
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
        setTitle("预约时间段");
    }

    @Override
    public void onRefresh() {
        mSrlTopContent.setRefreshing(false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GetRegister_ConfigList.ContentBean bean= (GetRegister_ConfigList.ContentBean) parent.getItemAtPosition(position);

        if (bean.getSurplusNum() == 0) {
            ToastUtil.showToast("安装数为0");
            return;
        }
        Intent intent = new Intent();
        onTime = bean.getOnTime();
        offTime = bean.getOffTime();
        configId = bean.getConfigId();
        intent.putExtra("onTime",onTime);
        intent.putExtra("offTime", offTime);
        intent.putExtra("configId", configId);
        setResult(RESULT_OK,intent);
        finish();
    }

    public static void goActivity(Activity activity, String registersiteId,String reservateTime) {
        Intent intent = new Intent(activity, MeetFromActivity.class);
        intent.putExtra("registersiteId", registersiteId);
        intent.putExtra("reservateTime", reservateTime);
        activity.startActivityForResult(intent,300);
    }
}
