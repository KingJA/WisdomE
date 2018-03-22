package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.exception.BleException;
import com.kingja.cardpackage.Event.RefreshAutoChargers;
import com.kingja.cardpackage.ble.BleResult82;
import com.kingja.cardpackage.ble.BleUtil;
import com.kingja.cardpackage.entiy.AddChargerSetting;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetChargerSettingList;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.NoDoubleClickListener;
import com.kingja.cardpackage.util.ToastUtil;
import com.kingja.ui.wheelview.TimeSelector;
import com.tdr.wisdome.R;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:TODO
 * Create Time:2017/11/25 10:16
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ModifyAutoChargeActivity extends BackTitleActivity implements BackTitleActivity.OnRightClickListener {

    private TextView mTvSelectTime;
    private TimeSelector selectTimeSelector;
    private String selectTime = "09:30";
    private int autoFrequency;
    private AppCompatRadioButton mRbOnce;
    private AppCompatRadioButton mRbRepeat;
    private AppCompatRadioButton mRbAutoStart;
    private AppCompatRadioButton mRbAutoEnd;
    private String chargerId;
    private int sep;
    private int bleAutoFrequency;
    private int autoOperate;
    private String autoStartTime;
    private String autoEndTime;
    private GetChargerSettingList.ContentBean.DataBean config;

    @Override
    protected void initVariables() {
        config = (GetChargerSettingList.ContentBean.DataBean) getIntent().getSerializableExtra("config");
    }

    @Override
    protected void initContentView() {
        mTvSelectTime = (TextView) findViewById(R.id.tv_selectTime);
        mRbOnce = (AppCompatRadioButton) findViewById(R.id.rb_once);
        mRbRepeat = (AppCompatRadioButton) findViewById(R.id.rb_repeat);
        mRbAutoStart = (AppCompatRadioButton) findViewById(R.id.rb_autoStart);
        mRbAutoEnd = (AppCompatRadioButton) findViewById(R.id.rb_autoEnd);
        mRbAutoEnd.setChecked(true);
        mRbOnce.setChecked(true);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_config_auto_detail;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        selectTime = config.getAuto_operate() == 1 ? config.getAuto_start() : config.getAuto_end();
        mTvSelectTime.setText(selectTime);
        autoOperate = config.getAuto_operate();
        autoFrequency = config.getAuto_frequency();
        if (autoOperate == 1) {
            mRbAutoStart.setChecked(true);
        } else {
            mRbAutoEnd.setChecked(true);
        }

        if (autoFrequency == 1) {
            mRbOnce.setChecked(true);
        } else {
            mRbRepeat.setChecked(true);
        }

        mTvSelectTime.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                selectTimeSelector = new TimeSelector(ModifyAutoChargeActivity.this, ModifyAutoChargeActivity.this
                        .selectTime);
                selectTimeSelector.setOnTimeSelectListener(new TimeSelector.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(String hour, String second) {
                        ModifyAutoChargeActivity.this.selectTime = hour + ":" + second;
                        mTvSelectTime.setText(ModifyAutoChargeActivity.this.selectTime);
                    }
                });
                selectTimeSelector.show();
            }
        });
    }

    @Override
    protected void setData() {
        setTitle("自动充电修改");
        setOnRightClickListener(this, "保存");
    }

    public static void goActivity(Context context, GetChargerSettingList.ContentBean.DataBean config) {
        Intent intent = new Intent(context, ModifyAutoChargeActivity.class);
        intent.putExtra("config", config);
        context.startActivity(intent);
    }

    @Override
    public void onRightClick() {
        if (mRbAutoStart.isChecked()) {
            autoOperate = 1;
            autoFrequency = 1;
            bleAutoFrequency = 1;
        } else if (mRbAutoEnd.isChecked()) {
            autoOperate = 2;
            autoFrequency = 2;
            bleAutoFrequency = 0;
        } else {
            ToastUtil.showToast("请选择自动类型");
            return;
        }

        if (mRbOnce.isChecked()) {
            autoFrequency = 1;
            bleAutoFrequency = 1;
        } else if (mRbRepeat.isChecked()) {
            autoFrequency = 2;
            bleAutoFrequency = 0;
        } else {
            ToastUtil.showToast("请选择充电频率");
            return;
        }
        uploadConfig();
    }

    private void uploadConfig() {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("auto_start", autoOperate == 1 ? selectTime : "");
        param.put("auto_end", autoOperate == 2 ? selectTime : "");
        param.put("auto_operate", autoOperate);
        param.put("auto_frequency", autoFrequency);
        param.put("autoid", config.getAutoid());
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CHARGER, KConstants
                                .EditChargerSetting,
                        param)
                .setBeanType(AddChargerSetting.class)
                .setCallBack(new WebServiceCallBack<AddChargerSetting>() {
                    @Override
                    public void onSuccess(AddChargerSetting bean) {
                        EventBus.getDefault().post(new RefreshAutoChargers());
                        setProgressDialog(false);
                        ToastUtil.showToast("设置成功");
                        finish();
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);

                    }
                }).build().execute();
    }
}
