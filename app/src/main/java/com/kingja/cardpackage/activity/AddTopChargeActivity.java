package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.TextView;

import com.kingja.cardpackage.Event.RefreshTopChargers;
import com.kingja.cardpackage.entiy.AddChargerSetting;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
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
public class AddTopChargeActivity extends BackTitleActivity implements BackTitleActivity.OnRightClickListener {

    private TextView mTvStartTime;
    private TextView mTvEndTime;
    private TimeSelector endTimeSelector;
    private TimeSelector startTimeSelector;
    private String startTime = "09:30";
    private String endTime = "14:30";
    private int autoFrequency;
    private AppCompatRadioButton mRbOnce;
    private AppCompatRadioButton mRbRepeat;
    private String chargerId;

    @Override
    protected void initVariables() {
        chargerId = getIntent().getStringExtra("chargerId");
    }

    @Override
    protected void initContentView() {
        mTvStartTime = (TextView) findViewById(R.id.tv_startTime);
        mTvEndTime = (TextView) findViewById(R.id.tv_endTime);
        mRbOnce = (AppCompatRadioButton) findViewById(R.id.rb_once);
        mRbRepeat = (AppCompatRadioButton) findViewById(R.id.rb_repeat);
        mRbOnce.setChecked(true);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_config_top_detail;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        mTvStartTime.setText(startTime);
        mTvEndTime.setText(endTime);

        mTvStartTime.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                startTimeSelector = new TimeSelector(AddTopChargeActivity.this, startTime);
                startTimeSelector.setOnTimeSelectListener(new TimeSelector.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(String hour, String second) {
                        startTime = hour + ":" + second;
                        mTvStartTime.setText(startTime);
                    }
                });
                startTimeSelector.show();
            }
        });

        mTvEndTime.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                endTimeSelector = new TimeSelector(AddTopChargeActivity.this, endTime);
                endTimeSelector.setOnTimeSelectListener(new TimeSelector.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(String hour, String second) {
                        endTime = hour + ":" + second;
                        mTvEndTime.setText(endTime);
                    }
                });
                endTimeSelector.show();
            }
        });

    }

    @Override
    protected void setData() {
        setTitle("峰谷充电设置");
        setOnRightClickListener(this, "保存");
    }

    public static void goActivity(Context context, String chargerId) {
        Intent intent = new Intent(context, AddTopChargeActivity.class);
        intent.putExtra("chargerId", chargerId);
        context.startActivity(intent);
    }

    @Override
    public void onRightClick() {
        if (mRbOnce.isChecked()) {
            autoFrequency = 1;
        } else if (mRbRepeat.isChecked()) {
            autoFrequency = 2;
        } else {
            ToastUtil.showToast("请选择充电频率");
            return;
        }

        uploadConfig();
    }
    private void uploadConfig() {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("auto_start", startTime);
        param.put("auto_end", endTime);
        param.put("auto_operate", "");
        param.put("auto_frequency", autoFrequency);
        param.put("auto_type", "2");
        param.put("chargerid", chargerId);
        param.put("seq", "0");
        new ThreadPoolTask.Builder()
                .setGeneralParam("0506b35c7e6248fb84cd2c83afa1b300", KConstants.CARD_TYPE_EMPTY, KConstants
                                .AddChargerSetting,
                        param)
                .setBeanType(AddChargerSetting.class)
                .setCallBack(new WebServiceCallBack<AddChargerSetting>() {
                    @Override
                    public void onSuccess(AddChargerSetting bean) {
                        EventBus.getDefault().post(new RefreshTopChargers());
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
