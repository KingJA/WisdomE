package com.kingja.cardpackage.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.cardpackage.Event.GetPreRegisterListEvent;
import com.kingja.cardpackage.entiy.EditPreRatePlus;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetPreRate;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.CheckUtil;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.GoUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.TimeUtil;
import com.kingja.cardpackage.util.ToastUtil;
import com.tdr.wisdome.R;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Description：TODO
 * Create Time：2016/12/29 10:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PreRecordInfoActivity extends BackTitleActivity implements BackTitleActivity.OnRightClickListener {

    private LinearLayout mLlMeetTime;
    private LinearLayout mLlMeetFrom;
    private LinearLayout mLlSiteName;
    private TextView mTvMeetTime;
    private TextView mTvSiteName;
    private TextView mTvMeetFrom;
    private GetPreRate.ContentBean preRateBean;
    private String meetFrom;
    private String meetTime;
    private String registersiteId;
    private String prerateID;
    private String configId;
    private int year;
    private int month;
    private int day;
    private TextView mTvPreNum;
    private boolean editable;


    @Override
    protected void initVariables() {
        preRateBean = (GetPreRate.ContentBean) getIntent().getSerializableExtra("PreRateBean");
        editable = getIntent().getBooleanExtra("editable", false);
        Log.e(TAG, "editable: "+editable );
        initCalendar();
    }

    @Override
    protected void initContentView() {
        mLlMeetTime = (LinearLayout) findViewById(R.id.ll_meetTime);
        mLlSiteName = (LinearLayout) findViewById(R.id.ll_siteName);
        mLlMeetFrom = (LinearLayout) findViewById(R.id.ll__meetFrom);

        mTvMeetTime = (TextView) findViewById(R.id.tv_meetTime);
        mTvSiteName = (TextView) findViewById(R.id.tv_siteName);
        mTvMeetFrom = (TextView) findViewById(R.id.tv_meetFrom);
        mTvPreNum = (TextView) findViewById(R.id.tv_preNum);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_pre_record;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        if (editable) {
            mLlMeetTime.setOnClickListener(this);
            mLlSiteName.setOnClickListener(this);
            mLlMeetFrom.setOnClickListener(this);
        }
        fillData();

    }

    private void fillData() {
        meetTime = preRateBean.getReservateTime().substring(0, "xxxx-xx-xx".length());
        prerateID = preRateBean.getPrerateID();
        mTvMeetTime.setText(meetTime);
        mTvPreNum.setText(preRateBean.getSeq() + "");

        GetPreRate.ContentBean.RegisterConfigBean register_config = preRateBean.getRegister_Config();
        if (register_config != null) {
            configId = register_config.getConfigId();
            registersiteId = register_config.getRegistersiteId();
            meetFrom = register_config.getOnTime() + "-" + register_config.getOffTime();
            mTvMeetFrom.setText(meetFrom);
        }
        GetPreRate.ContentBean.RegistersiteBean registersite = preRateBean.getRegistersite();
        if (registersite != null) {
            mTvSiteName.setText(registersite.getRegistersiteName());
        }


    }

    @Override
    protected void setData() {
        setTitle("预约信息");
        if (editable) {
            setOnRightClickListener(this, "保存");
        }

    }

    public void editRecordInfo() {
        if (!CheckUtil.checkEmpty(configId, "请选择预约时段")) {
            return;
        }
        Map<String, Object> mParam = new HashMap<>();
        mParam.put("RegistersiteId", registersiteId);
        mParam.put("ReservateTime", meetTime);
        mParam.put("ConfigId", configId);
        mParam.put("PrerateID", prerateID);
        setProgressDialog(true);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.EditPreRatePlus, mParam)
                .setBeanType(EditPreRatePlus.class)
                .setCallBack(new WebServiceCallBack<EditPreRatePlus>() {
                    @Override
                    public void onSuccess(EditPreRatePlus bean) {
                        EventBus.getDefault().post(new GetPreRegisterListEvent());
                        setProgressDialog(false);
                        ToastUtil.showToast("修改成功");
                        finish();
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Override
    public void onRightClick() {
        editRecordInfo();
    }

    private void initCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);//获取明天的日期
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);

    }

    public static void goActivityAndFinish(Activity activity, GetPreRate.ContentBean bean, boolean editable) {
        Intent intent = new Intent(activity, PreRecordInfoActivity.class);
        intent.putExtra("PreRateBean", bean);
        intent.putExtra("editable", editable);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_meetTime:
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (TimeUtil.getMeetTime(String.format("%02d-%02d-%02d", year, monthOfYear + 1, dayOfMonth))) {
                            meetTime = String.format("%02d-%02d-%02d", year, monthOfYear + 1, dayOfMonth);
                            mTvMeetTime.setText(meetTime);
                            mTvMeetFrom.setText("");
                            configId = "";
                        }
                    }
                }, year, month - 1, day).show();
                break;
            case R.id.ll_siteName:
                GoUtil.goActivityForResult(this, RecordSiteActivity.class, 200);
                break;
            case R.id.ll__meetFrom:
                MeetFromActivity.goActivity(this, registersiteId, meetTime);
                break;
            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == 200) {//选择备案登记点
                registersiteId = data.getStringExtra("RegistersiteId");
                mTvSiteName.setText(data.getStringExtra("SiteName"));
                //清空预约时段信息
                configId = "";
                mTvMeetFrom.setText("");
            }
            if (requestCode == 300) {//选择预约时间段
                configId = data.getStringExtra("configId");
                mTvMeetFrom.setText(data.getStringExtra("onTime") + "-" + data.getStringExtra("offTime"));
            }
        }
    }
}
