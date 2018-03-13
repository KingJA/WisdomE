package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.clj.fastble.data.BleDevice;
import com.kingja.cardpackage.adapter.ChargerAlarmAdapter;
import com.kingja.cardpackage.callback.EmptyCallback;
import com.kingja.cardpackage.callback.ErrorCallback;
import com.kingja.cardpackage.callback.LoadingCallback;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetChargerHeartPlusByChargerId;
import com.kingja.cardpackage.entiy.GetChargerStatistics;
import com.kingja.cardpackage.entiy.GetChargerWarningInfoList;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.AppUtil;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.superindicator.SuperIndicator;
import com.tdr.wisdome.R;
import com.tdr.wisdome.view.popupwindow.ChargePop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lib.kingja.progress.KJProgressRound;


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
    private LoadService alarmLoadService;
    private TextView mTvChargeTotleElectricity;
    private TextView mTvChargeTotleCost;
    private TextView mTvChargeTotleCount;
    private LinearLayout mLlCharge_statistics;
    private LoadService statisticsLoadService;
    private BleDevice bleDevice;
    private ImageView mIvBleStatus;
    private TextView mTvCurrentChargeelEctricity;
    private TextView mTvCurrentChargeVoltage;
    private TextView mTvChargerTemperature;
    private TextView mTvBatteryTemperature;
    private TextView mTvLeftCost;
    private SuperIndicator mSuperIndicator;
    private KJProgressRound mProgressPower;
    private SwipeRefreshLayout mSwpChargerInfo;

    @Override
    protected void initVariables() {
        chargerId = getIntent().getStringExtra("chargerId");
    }


    //充电状态（1,0:空闲,01:欠压充电，02：恒流充电，03：恒压充电，04：浮充充电，05：完成充电）
    public int getChargeStatusProgress(int chargeStatus) {
        int result = 0;
        switch (chargeStatus) {
            case 1:
            case 2:
                result = 0;
                break;
            case 3:
                result = 1;
                break;
            case 4:
                result = 2;
                break;
            case 5:
                result = 3;
                break;
            default:
                break;
        }
        return result;
    }


    @Override
    protected void initContentView() {
        mChargePop = new ChargePop(mRlTopMenu, this);
        mSwpChargerInfo = (SwipeRefreshLayout) findViewById(R.id.swp_charger_info);
        mProgressPower = (KJProgressRound) findViewById(R.id.progress_power);
        mLvArarm = (ListView) findViewById(R.id.lv_ararm);
        mIvBleStatus = (ImageView) findViewById(R.id.iv_ble_status);
        mTvMoreAlarms = (TextView) findViewById(R.id.tv_moreAlarms);
        mTvChargeTotleCount = (TextView) findViewById(R.id.tv_chargeTotleCount);
        mTvChargeTotleCost = (TextView) findViewById(R.id.tv_chargeTotleCost);
        mTvChargeTotleElectricity = (TextView) findViewById(R.id.tv_chargeTotleElectricity);
        mSuperIndicator = (SuperIndicator) findViewById(R.id.superIndicator);
        mTvCurrentChargeelEctricity = (TextView) findViewById(R.id.tv_currentChargeelEctricity);
        mTvCurrentChargeVoltage = (TextView) findViewById(R.id.tv_currentChargeVoltage);
        mTvChargerTemperature = (TextView) findViewById(R.id.tv_chargerTemperature);
        mTvBatteryTemperature = (TextView) findViewById(R.id.tv_batteryTemperature);
        mTvLeftCost = (TextView) findViewById(R.id.tv_leftCost);
        mLlCharge_statistics = (LinearLayout) findViewById(R.id.ll_charge_statistics);
        mChargerAlarmAdapter = new ChargerAlarmAdapter(this, chargerAlarms);
        mLvArarm.setAdapter(mChargerAlarmAdapter);
        alarmLoadService = LoadSir.getDefault().register(mLvArarm, new Callback.OnReloadListener() {
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
        mSwpChargerInfo.setColorSchemeResources(R.color.bg_black);
        mSwpChargerInfo.setProgressViewOffset(false, 0, AppUtil.dp2px(24));
    }

    private void loadStatistics() {
        statisticsLoadService.showCallback(LoadingCallback.class);
        Map<String, Object> param = new HashMap<>();
        param.put("ChargerId", chargerId);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CHARGER, KConstants
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
                            mTvChargeTotleElectricity.setText(chargeInfo.getELECTRICITY_TOTAL() + "KWh");
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
        loadChargeHeart();
        loadAlarms();
        loadStatistics();
    }

    private void loadChargeHeart() {
        mSwpChargerInfo.setRefreshing(true);
        Map<String, Object> param = new HashMap<>();
        param.put("ChargerId", chargerId);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CHARGER, KConstants
                                .GetChargerHeartPlusByChargerId,
                        param)
                .setBeanType(GetChargerHeartPlusByChargerId.class)
                .setCallBack(new WebServiceCallBack<GetChargerHeartPlusByChargerId>() {
                    @Override
                    public void onSuccess(GetChargerHeartPlusByChargerId bean) {
                        mSwpChargerInfo.setRefreshing(false);
                        GetChargerHeartPlusByChargerId.ContentBean chargerHeart = bean.getContent();
                        mSuperIndicator.setProgress(getChargeStatusProgress(chargerHeart.getCharge_Status()));
                        mTvCurrentChargeelEctricity.setText(chargerHeart.getCurrent_Current()+"A");
                        mTvCurrentChargeVoltage.setText(chargerHeart.getCurrent_Voltage()+"V");
                        mTvChargerTemperature.setText(chargerHeart.getCharger_Temperature()+"℃");
                        mTvBatteryTemperature.setText(chargerHeart.getBattery_Temperature()+"℃");
                        mProgressPower.setProgress(Integer.valueOf(chargerHeart.getCurrent_Electricity()));
//                        mTvLeftCost.setText("预计充满电还需" + chargerHeart.get());
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        mSwpChargerInfo.setRefreshing(false);
                    }
                }).build().execute();
    }

    private void loadAlarms() {
        alarmLoadService.showCallback(LoadingCallback.class);
        Map<String, Object> param = new HashMap<>();
        param.put("PageIndex", "1");
        param.put("PageSize", "3");
        param.put("OnlyGetRecord", false);
        param.put("ChargerId", chargerId);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CHARGER, KConstants
                                .GetChargerWarningInfoList,
                        param)
                .setBeanType(GetChargerWarningInfoList.class)
                .setCallBack(new WebServiceCallBack<GetChargerWarningInfoList>() {
                    @Override
                    public void onSuccess(GetChargerWarningInfoList bean) {
                        chargerAlarms = bean.getContent().getData();
                        if (chargerAlarms != null && chargerAlarms.size() > 0) {
                            mChargerAlarmAdapter.setData(chargerAlarms);
                            alarmLoadService.showSuccess();
                        } else {
                            alarmLoadService.showCallback(EmptyCallback.class);
                        }
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        alarmLoadService.showCallback(ErrorCallback.class);
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
        mSuperIndicator.setTabs(Arrays.asList("快速充电", "连续充电", "涓流充电", "完成充电"));
        mSwpChargerInfo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadChargeHeart();
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

}
