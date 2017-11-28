package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kingja.cardpackage.adapter.ChargerAlarmAdapter;
import com.kingja.cardpackage.callback.EmptyCallback;
import com.kingja.cardpackage.callback.ErrorCallback;
import com.kingja.cardpackage.callback.LoadingCallback;
import com.kingja.cardpackage.entiy.AddChargerRecord;
import com.kingja.cardpackage.entiy.AddChargerWarningInfo;
import com.kingja.cardpackage.entiy.ChargerAlarm;
import com.kingja.cardpackage.entiy.ChargerRecord;
import com.kingja.cardpackage.entiy.DelBindCharger;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetChargerStatistics;
import com.kingja.cardpackage.entiy.GetChargerWarningInfoList;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.GoUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.ToastUtil;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.tdr.wisdome.R;
import com.tdr.wisdome.view.popupwindow.ChargePop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:TODO
 * Create Time:2017/11/23 10:52
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ChargerActivity extends BackTitleActivity implements BackTitleActivity.OnMenuClickListener, ChargePop
        .OnItemClickListener {

    private ChargePop mChargePop;
    private String chargerId;
    private ListView mLvArarm;
    private TextView mTvMoreAlarms;
    private List<GetChargerWarningInfoList.ContentBean.DataBean> chargerAlarms = new ArrayList<>();
    private ChargerAlarmAdapter mChargerAlarmAdapter;
    private LoadService loadService;
    private TextView mTvChargeTotleElectricity;
    private TextView mTvChargeTotleCost;
    private TextView mTvChargeTotleCount;
    private LinearLayout mLlCharge_statistics;
    private LoadService statisticsLoadService;

    @Override
    protected void initVariables() {
        chargerId = getIntent().getStringExtra("chargerId");

    }

    @Override
    protected void initContentView() {
        mChargePop = new ChargePop(mRlTopMenu, this);
        mLvArarm = (ListView) findViewById(R.id.lv_ararm);
        mTvMoreAlarms = (TextView) findViewById(R.id.tv_moreAlarms);
        mTvChargeTotleCount = (TextView) findViewById(R.id.tv_chargeTotleCount);
        mTvChargeTotleCost = (TextView) findViewById(R.id.tv_chargeTotleCost);
        mTvChargeTotleElectricity = (TextView) findViewById(R.id.tv_chargeTotleElectricity);
        mLlCharge_statistics = (LinearLayout) findViewById(R.id.ll_charge_statistics);
        mChargerAlarmAdapter = new ChargerAlarmAdapter(this, chargerAlarms);
        mLvArarm.setAdapter(mChargerAlarmAdapter);
        loadService = LoadSir.getDefault().register(mLvArarm, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                loadAlarms();
            }
        });
        statisticsLoadService = LoadSir.getDefault().register(mLlCharge_statistics, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                loadStatistics();
            }
        });
    }

    private void loadStatistics() {
        statisticsLoadService.showCallback(LoadingCallback.class);
        Map<String, Object> param = new HashMap<>();
        param.put("ChargerId", chargerId);
        new ThreadPoolTask.Builder()
                .setGeneralParam("0506b35c7e6248fb84cd2c83afa1b300", KConstants.CARD_TYPE_EMPTY, KConstants
                                .GetChargerStatistics,
                        param)
                .setBeanType(GetChargerStatistics.class)
                .setCallBack(new WebServiceCallBack<GetChargerStatistics>() {
                    @Override
                    public void onSuccess(GetChargerStatistics bean) {
                        GetChargerStatistics.ContentBean chargeInfo = bean.getContent();
                        if (chargeInfo != null) {
                            statisticsLoadService.showSuccess();
                            mTvChargeTotleCount.setText(chargeInfo.getCHARGERTIMES() + "次");
                            mTvChargeTotleCost.setText(chargeInfo.getCHANGERTLONG() + "小时");
                            mTvChargeTotleElectricity.setText(chargeInfo.getELECTRICITY_TOTAL() + "A");
                        }

                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        statisticsLoadService.showCallback(ErrorCallback.class);
                    }
                }).build().execute();
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_charge;
    }

    @Override
    protected void initNet() {
        loadAlarms();
        loadStatistics();
    }

    private void loadAlarms() {
        loadService.showCallback(LoadingCallback.class);
        Map<String, Object> param = new HashMap<>();
        param.put("PageIndex", "1");
        param.put("PageSize", "3");
        param.put("OnlyGetRecord", false);
        param.put("ChargerId", chargerId);
        new ThreadPoolTask.Builder()
                .setGeneralParam("0506b35c7e6248fb84cd2c83afa1b300", KConstants.CARD_TYPE_EMPTY, KConstants
                                .GetChargerWarningInfoList,
                        param)
                .setBeanType(GetChargerWarningInfoList.class)
                .setCallBack(new WebServiceCallBack<GetChargerWarningInfoList>() {
                    @Override
                    public void onSuccess(GetChargerWarningInfoList bean) {
                        chargerAlarms = bean.getContent().getData();
                        if (chargerAlarms != null && chargerAlarms.size() > 0) {
                            mChargerAlarmAdapter.setData(chargerAlarms);
                            loadService.showSuccess();
                        } else {
                            loadService.showCallback(EmptyCallback.class);
                        }
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        loadService.showCallback(ErrorCallback.class);
                    }
                }).build().execute();
    }

    @Override
    protected void initData() {
        mChargePop.setOnItemClickListener(this);
        mTvMoreAlarms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChargeAlarmActivity.goActivity(ChargerActivity.this, chargerId);
            }
        });
    }

    @Override
    protected void setData() {
        setTitle("充电器");
        setOnMenuClickListener(this, R.drawable.bg_add);
    }

    @Override
    public void onMenuClick() {
        mChargePop.showPopupWindowDown();
    }

    @Override
    public void onPopItemClick(int position) {
        switch (position) {
            case 0:
                GoUtil.goActivity(this, BluetoothSearchActivity.class);
                break;
            case 1:
                ChargeRecordActivity.goActivity(this, chargerId);
                break;
            case 2:
                AutoChargeActivity.goActivity(this, chargerId);
                break;
            case 3:
                TopChargeActivity.goActivity(this, chargerId);
                break;

            default:
                break;
        }
    }

    public static void goActivity(Context context, String chargerId) {
        Intent intent = new Intent(context, ChargerActivity.class);
        intent.putExtra("chargerId", chargerId);
        context.startActivity(intent);
    }

    private void sendAlarm() {
        List<ChargerAlarm> chargerAlarms = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            ChargerAlarm chargerAlarm = new ChargerAlarm();
            chargerAlarm.setChargerid(chargerId);
            chargerAlarm.setWarn_msg("电池过温度");
            chargerAlarm.setWarn_time("2017-11-23 11:24:0" + i);
            chargerAlarm.setWarn_type(i % 2);
            chargerAlarms.add(chargerAlarm);
        }
        new ThreadPoolTask.Builder()
                .setGeneralParam("0506b35c7e6248fb84cd2c83afa1b300", KConstants.CARD_TYPE_EMPTY, KConstants
                                .AddChargerWarningInfo,
                        chargerAlarms)
                .setBeanType(AddChargerWarningInfo.class)
                .setCallBack(new WebServiceCallBack<AddChargerWarningInfo>() {
                    @Override
                    public void onSuccess(AddChargerWarningInfo bean) {

                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                    }
                }).build().execute();
    }

    private void sendChargeRecord() {
        List<ChargerRecord> chargerRecords = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            ChargerRecord chargerRecord = new ChargerRecord();
            chargerRecord.setCharger_starttime("2017-11-23 11:24:0" + i);
            chargerRecord.setCharger_endtime("2017-11-23 11:24:0" + i);
            chargerRecord.setCharger_end_reason(i % 2);
            chargerRecord.setRecordtime("2017-11-23 11:24:0" + i);
            chargerRecord.setChargerid(chargerId);
            chargerRecord.setVoltage(220);
            chargerRecord.setCurrents(300);
            chargerRecord.setElectricity_total(5000);
            chargerRecord.setAmbient_temperature(20);
            chargerRecord.setBattery_temperature(50);
            chargerRecord.setCharger_temperature(i * 2);
            chargerRecords.add(chargerRecord);
        }
        new ThreadPoolTask.Builder()
                .setGeneralParam("0506b35c7e6248fb84cd2c83afa1b300", KConstants.CARD_TYPE_EMPTY, KConstants
                                .AddChargerRecord,
                        chargerRecords)
                .setBeanType(AddChargerRecord.class)
                .setCallBack(new WebServiceCallBack<AddChargerRecord>() {
                    @Override
                    public void onSuccess(AddChargerRecord bean) {

                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                    }
                }).build().execute();
    }
}
