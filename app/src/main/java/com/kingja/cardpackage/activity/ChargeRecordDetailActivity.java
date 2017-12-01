package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.kingja.cardpackage.ble.BleUtil;
import com.kingja.cardpackage.entiy.GetChargerRecordList;
import com.kingja.cardpackage.util.BleConstants;
import com.tdr.wisdome.R;

/**
 * Description:TODO
 * Create Time:2017/11/23 14:21
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ChargeRecordDetailActivity extends BackTitleActivity {

    private GetChargerRecordList.ContentBean.DataBean chargeRecord;
    private TextView mTvStartTime;
    private TextView mTvEndTime;
    private TextView mTvChargeCost;
    private TextView mTvChargePower;
    private TextView mTvChargerTemperature;
    private TextView mTvBatteryTemperature;


    @Override
    protected void initVariables() {
        chargeRecord = (GetChargerRecordList.ContentBean.DataBean) getIntent().getSerializableExtra("chargeRecord");
    }

    @Override
    protected void initContentView() {
        mTvStartTime = (TextView) findViewById(R.id.tv_startTime);
        mTvEndTime = (TextView) findViewById(R.id.tv_endTime);
        mTvChargeCost = (TextView) findViewById(R.id.tv_chargeCost);
        mTvChargePower = (TextView) findViewById(R.id.tv_chargePower);
        mTvChargerTemperature = (TextView) findViewById(R.id.tv_chargerTemperature);
        mTvBatteryTemperature = (TextView) findViewById(R.id.tv_batteryTemperature);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_charge_detail;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        mTvStartTime.setText(chargeRecord.getCharger_starttime());
        mTvEndTime.setText(chargeRecord.getCharger_endtime());
        mTvChargeCost.setText(BleUtil.getCostTime(chargeRecord.getCharger_starttime(), chargeRecord
                .getCharger_endtime()));
        mTvChargePower.setText(chargeRecord.getElectricity_total() + BleConstants.UNIT_POWER);
        mTvChargerTemperature.setText(chargeRecord.getCharger_temperature() + BleConstants.UNIT_TEMPERATURE);
        mTvBatteryTemperature.setText(chargeRecord.getBattery_temperature() + BleConstants.UNIT_TEMPERATURE);
    }

    @Override
    protected void setData() {
        setTitle("充电详情");
    }

    public static void goActivity(Context context, GetChargerRecordList.ContentBean.DataBean chargeRecord) {
        Intent intent = new Intent(context, ChargeRecordDetailActivity.class);
        intent.putExtra("chargeRecord", chargeRecord);
        context.startActivity(intent);
    }
}
