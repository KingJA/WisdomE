package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.GetChargerSettingList;
import com.kingja.cardpackage.util.NoDoubleClickListener;
import com.kingja.cardpackage.util.ToastUtil;
import com.kingja.ui.wheelview.TimeSelector;
import com.tdr.wisdome.R;

/**
 * Description:TODO
 * Create Time:2017/11/25 10:16
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ConfigAutoChargeActivity extends BackTitleActivity {

    private TextView mTvSelectTime;
    private TimeSelector endTimeSelector;
    private String startTime = "09:30";
    private GetChargerSettingList.ContentBean.DataBean config;

    @Override
    protected void initVariables() {
        config = (GetChargerSettingList.ContentBean.DataBean) getIntent().getSerializableExtra("config");
    }

    @Override
    protected void initContentView() {
        mTvSelectTime = (TextView) findViewById(R.id.tv_selectTime);

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
        mTvSelectTime.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                endTimeSelector = new TimeSelector(ConfigAutoChargeActivity.this, startTime);
                endTimeSelector.setOnTimeSelectListener(new TimeSelector.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(String hour, String second) {
                        startTime = hour + ":" + second;
                        mTvSelectTime.setText(startTime);
                    }
                });
                endTimeSelector.show();
            }
        });

    }

    @Override
    protected void setData() {
        setTitle("自动充电设置");
        setOnRightClickListener(new OnRightClickListener() {
            @Override
            public void onRightClick() {
                ToastUtil.showToast("保存设置");
            }
        }, "保存");
    }

    public static void goActivity(Context context, GetChargerSettingList.ContentBean.DataBean config) {
        Intent intent = new Intent(context, ConfigAutoChargeActivity.class);
        intent.putExtra("config", config);
        context.startActivity(intent);
    }
}
