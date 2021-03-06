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
import com.kingja.cardpackage.Event.RefreshTopChargers;
import com.kingja.cardpackage.ble.BleResult82;
import com.kingja.cardpackage.ble.BleUtil;
import com.kingja.cardpackage.entiy.AddChargerSetting;
import com.kingja.cardpackage.entiy.ErrorResult;
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
public class AddAutoChargeActivity extends BackTitleActivity implements BackTitleActivity.OnRightClickListener {

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

    @Override
    protected void initVariables() {
        chargerId = getIntent().getStringExtra("chargerId");
        sep = getIntent().getIntExtra("sep", 0);
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
        mTvSelectTime.setText(selectTime);
        mTvSelectTime.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                selectTimeSelector = new TimeSelector(AddAutoChargeActivity.this, selectTime);
                selectTimeSelector.setOnTimeSelectListener(new TimeSelector.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(String hour, String second) {
                        selectTime = hour + ":" + second;
                        mTvSelectTime.setText(selectTime);
                    }
                });
                selectTimeSelector.show();
            }
        });
    }

    @Override
    protected void setData() {
        setTitle("自动充电设置");
        setOnRightClickListener(this, "保存");
    }

    public static void goActivity(Context context, String chargerId, int sep) {
        Intent intent = new Intent(context, AddAutoChargeActivity.class);
        intent.putExtra("chargerId", chargerId);
        intent.putExtra("sep", sep);
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

        String currentDate = BleUtil.getCurrentDate();
        if (autoOperate == 1) {
            autoEndTime = currentDate + (selectTime.replace(":", "")) + "00";
            autoStartTime = "00000000000000";
        } else {
            autoStartTime = currentDate + (selectTime.replace(":", "")) + "00";
            autoEndTime = "00000000000000";
        }
        uploadConfig();
    }

    private void uploadConfig() {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
//        param.put("auto_start", autoOperate == 1 ? selectTime : "");
//        param.put("auto_end", autoOperate == 2 ? selectTime : "");
        param.put("auto_start", selectTime);
        param.put("auto_end",  "");
        param.put("auto_operate", 1);
        param.put("auto_frequency", autoFrequency);
        param.put("auto_type", "1");
        param.put("chargerid", chargerId);
        param.put("seq", sep);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CHARGER, KConstants
                                .AddChargerSetting,
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
