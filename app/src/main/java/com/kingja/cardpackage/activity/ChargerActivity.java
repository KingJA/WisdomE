package com.kingja.cardpackage.activity;

import android.bluetooth.BluetoothGatt;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.clj.fastble.utils.HexUtil;
import com.kingja.cardpackage.adapter.ChargerAlarmAdapter;
import com.kingja.cardpackage.ble.BleResult;
import com.kingja.cardpackage.ble.BleResult02;
import com.kingja.cardpackage.ble.BleResult03;
import com.kingja.cardpackage.ble.BleResult04;
import com.kingja.cardpackage.ble.BleResult05;
import com.kingja.cardpackage.ble.BleResult81;
import com.kingja.cardpackage.ble.BleResultFactory;
import com.kingja.cardpackage.ble.BleUtil;
import com.kingja.cardpackage.ble.SimpleBleIndicateCallback;
import com.kingja.cardpackage.ble.SimpleBleScanAndConnectCallback;
import com.kingja.cardpackage.callback.EmptyCallback;
import com.kingja.cardpackage.callback.ErrorCallback;
import com.kingja.cardpackage.callback.LoadingCallback;
import com.kingja.cardpackage.dao.DBManager;
import com.kingja.cardpackage.entiy.AddChargerRecord;
import com.kingja.cardpackage.entiy.AddChargerWarningInfo;
import com.kingja.cardpackage.entiy.ChargerAlarm;
import com.kingja.cardpackage.entiy.ChargerRecord;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetChargerStatistics;
import com.kingja.cardpackage.entiy.GetChargerWarningInfoList;
import com.kingja.cardpackage.greendaobean.ChargeRecord;
import com.kingja.cardpackage.greendaobean.ErrorInfo;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.BleConstants;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.TempConstants;
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
    private BleDevice bleDevice;
    private ImageView mIvBleStatus;
    private TextView mTvCurrentChargeelEctricity;
    private TextView mTvCurrentChargeVoltage;
    private TextView mTvChargerTemperature;
    private TextView mTvBatteryTemperature;
    private TextView mTvLeftCost;

    @Override
    protected void initVariables() {
        chargerId = getIntent().getStringExtra("chargerId");
        if (!(bleDevice != null && BleManager.getInstance().isConnected(bleDevice))) {
            connectBle(chargerId);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!(bleDevice != null && BleManager.getInstance().isConnected(bleDevice))) {
            setTitle("充电器(未连接)");
            ToastUtil.showToast("蓝牙已断开，请重新连接");
            mIvBleStatus.setBackgroundResource(R.mipmap.ble_disable);
            mIvBleStatus.setEnabled(true);
            mIvBleStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    connectBle(chargerId);
                }
            });
            Log.e(TAG, "检测蓝牙: 已经断开");
        } else {
            setTitle("充电器(已连接)");
            mIvBleStatus.setBackgroundResource(R.mipmap.ble_enable);
            mIvBleStatus.setEnabled(false);
            Log.e(TAG, "检测蓝牙: 连接");
        }
    }

    private void initBleListener() {
        BleManager.getInstance().indicate(
                bleDevice,
                TempConstants.BLE_SERVICE_UUID,
                TempConstants.BLE_OPERATE_UUID, new SimpleBleIndicateCallback() {
                    @Override
                    public void onCharacteristicChanged(byte[] data) {
                        if (!BleUtil.checkBle(data)) {
                            return;
                        }
                        String result = HexUtil.encodeHexStr(data);
                        Log.e(TAG, "onCharacteristicChanged:" + result);
                        BleResult bleResult = BleResultFactory.getBleResult(result);
                        switch (bleResult.getOrderCode()) {
                            case BleConstants.ORDER_02:
                                dispatchOrder02(data);
                                break;
                            case BleConstants.ORDER_03:
                                dispatchOrder03((BleResult03) bleResult);
                                break;
                            case BleConstants.ORDER_04:
                                dispatchOrder04(data);
                                break;
                            case BleConstants.ORDER_05:
                                dispatch05((BleResult05) bleResult);
                                break;
                            case BleConstants.ORDER_81:
                                Log.e(TAG, "接收到81: " + result);
                                break;
                            case BleConstants.ORDER_82:
                                Log.e(TAG, "接收到82: " + result);
                                break;
                            case BleConstants.ORDER_83:
                                Log.e(TAG, "接收到83: " + result);
                                break;
                            case BleConstants.ORDER_84:
                                Log.e(TAG, "接收到84: " + result);
                                break;
                            default:
                                break;
                        }
                    }
                });
    }

    private void dispatchOrder03(BleResult03 bleResult) {
        BleResult03 bleResult03 = bleResult;
        sendBle(bleResult03.getResponse());
        Log.e(TAG, "回复03: " + bleResult03.getResponse());
//        Log.e(TAG, "解析03===========================");
//        Log.e(TAG, "03sn: " + bleResult03.getSn());
//        Log.e(TAG, "03开始时间: " + bleResult03.getStartTime());
//        Log.e(TAG, "03结束时间: " + bleResult03.getEndTime());
//        Log.e(TAG, "03结束原因: " + bleResult03.getEndReason());
        ChargeRecord chargeRecord = new ChargeRecord();
        chargeRecord.setSn(bleResult03.getSn());
        chargeRecord.setStartTime(bleResult03.getSn());
        chargeRecord.setEndTime(bleResult03.getEndTime());
        chargeRecord.setEndReason(bleResult03.getEndReason());
        DBManager.getInstance(this).insertChargeRecord(chargeRecord);
    }

    private void dispatchOrder04(byte[] data) {
        BleResult04 bleResult04 = new BleResult04(data);
        sendBle(bleResult04.getResponse());
        Log.e(TAG, "回复04: " + bleResult04.getResponse());
//        Log.e(TAG, "解析04===========================");
//        Log.e(TAG, "04sn: " + bleResult04.getSn());
//        Log.e(TAG, "04充电电压: " + bleResult04.getMaxVoltage());
//        Log.e(TAG, "04充电电流: " + bleResult04.getMaxEctricity());
//        Log.e(TAG, "04总电量: " + bleResult04.getTotlePower());
//        Log.e(TAG, "04环境温度: " + bleResult04.getEnvironmentTemperature());
//        Log.e(TAG, "04最高电池温度: " + bleResult04.getMaxBatteryTemperature());
//        Log.e(TAG, "04最高充电器温度: " + bleResult04.getMaxChargerTemperature());
        ChargeRecord chargeRecord = DBManager.getInstance(this).getChargeRecordById(bleResult04.getSn());
        if (chargeRecord != null) {
            chargeRecord.setMaxVoltage(bleResult04.getMaxVoltage());
            chargeRecord.setMaxElectricity(bleResult04.getMaxEctricity());
            chargeRecord.setTotlePower(bleResult04.getTotlePower());
            chargeRecord.setEnvironmentTemperature(bleResult04.getEnvironmentTemperature());
            chargeRecord.setMaxBatteryTemperature(bleResult04.getMaxBatteryTemperature());
            chargeRecord.setMaxChargerTemperature(bleResult04.getMaxChargerTemperature());
            DBManager.getInstance(this).updateChargeRecords(chargeRecord);
            Log.e(TAG, "插入04成功");
        }

    }

    private void dispatch05(BleResult05 bleResult) {
        BleResult05 bleResult05 = bleResult;
        //发送回复
        sendBle(bleResult05.getResponse());
        DBManager.getInstance(this).insertErrorInfo(new ErrorInfo(bleResult05.getSn(), bleResult05.getOrderCode(),
                bleResult05.getErrorTime(), bleResult05.getErrorMsg(), bleResult05.getErrorType()));
        Log.e(TAG, "回复: " + bleResult05.getResponse());
//        Log.e(TAG, "解析05===========================");
//        Log.e(TAG, "05异常时间: " + bleResult05.getErrorTime());
//        Log.e(TAG, "05异常信息: " + bleResult05.getErrorMsg());
//        Log.e(TAG, "05异常类型: " + bleResult05.getErrorType());
    }

    private void dispatchOrder02(byte[] data) {
        BleResult02 bleResult02 = new BleResult02(data);
        sendBle(bleResult02.getResponse());
        Log.e(TAG, "回复02: " + bleResult02.getResponse());
//        Log.e(TAG, "充电状态: " + bleResult02.getChargeStatus());
//        Log.e(TAG, "当前充电电压: " + bleResult02.getCurrentChargeVoltage());
//        Log.e(TAG, "当前充电电流: " + bleResult02.getCurrentChargeelEctricity());
//        Log.e(TAG, "累计充电电量: " + bleResult02.getTotlePower());
//        Log.e(TAG, "电池温度: " + bleResult02.getBatteryTemperature());
//        Log.e(TAG, "充电器温度: " + bleResult02.getChargerTemperature());
//        Log.e(TAG, "当前电量: " + bleResult02.getCurrentPower());
//        Log.e(TAG, "充电时间: " + bleResult02.getChargeCost());
//        Log.e(TAG, "剩余充电时间: " + bleResult02.getLeftChargeCost());

        mTvCurrentChargeelEctricity.setText(bleResult02.getCurrentChargeelEctricity());
        mTvCurrentChargeVoltage.setText(bleResult02.getCurrentChargeVoltage());
        mTvChargerTemperature.setText(bleResult02.getChargerTemperature());
        mTvBatteryTemperature.setText(bleResult02.getBatteryTemperature());
        mTvLeftCost.setText("预计充满电还需" + bleResult02.getLeftChargeCost());
    }

    private void connectBle(final String deviceId) {
        setProgressDialog(true, "蓝牙连接中");
        Log.e(TAG, "getBleName: " + BleUtil.getBleName(deviceId));
        BleScanRuleConfig scanRuleConfig = new BleScanRuleConfig.Builder()
                .setDeviceName(true, BleUtil.getBleName(deviceId))
                .setAutoConnect(true)
                .build();
        BleManager.getInstance().initScanRule(scanRuleConfig);
        BleManager.getInstance().scanAndConnect(new SimpleBleScanAndConnectCallback() {
            @Override
            public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt gatt, int status) {
                Log.e(TAG, "onConnectSuccess: ");
                setProgressDialog(false);
                mIvBleStatus.setVisibility(View.VISIBLE);
                setTitle("充电器(已连接)");
                ChargerActivity.this.bleDevice = bleDevice;
                initBleListener();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendBle(BleResult81.getContent());
                    }
                }, 1000);

                //TODO 延时上传
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        sendAlarm();
//                        sendChargeRecord();
                    }
                }, 60000);
            }
        });
    }


    private void sendBle(String hexStr) {
        BleManager.getInstance().write(
                bleDevice,
                TempConstants.BLE_SERVICE_UUID,
                TempConstants.BLE_OPERATE_UUID,
                HexUtil.hexStringToBytes(hexStr),
                new BleWriteCallback() {
                    @Override
                    public void onWriteSuccess() {
                        Log.e(TAG, "onWriteSuccess: ");
                        // 发送数据到设备成功（UI线程）
                    }

                    @Override
                    public void onWriteFailure(BleException exception) {
                        Log.e(TAG, "onWriteFailure: " + exception.toString());
                        // 发送数据到设备失败（UI线程）
                    }
                });
    }

    @Override
    protected void initContentView() {
        mChargePop = new ChargePop(mRlTopMenu, this);
        mLvArarm = (ListView) findViewById(R.id.lv_ararm);
        mIvBleStatus = (ImageView) findViewById(R.id.iv_ble_status);
        mTvMoreAlarms = (TextView) findViewById(R.id.tv_moreAlarms);
        mTvChargeTotleCount = (TextView) findViewById(R.id.tv_chargeTotleCount);
        mTvChargeTotleCost = (TextView) findViewById(R.id.tv_chargeTotleCost);
        mTvChargeTotleElectricity = (TextView) findViewById(R.id.tv_chargeTotleElectricity);

        mTvCurrentChargeelEctricity = (TextView) findViewById(R.id.tv_currentChargeelEctricity);
        mTvCurrentChargeVoltage = (TextView) findViewById(R.id.tv_currentChargeVoltage);
        mTvChargerTemperature = (TextView) findViewById(R.id.tv_chargerTemperature);
        mTvBatteryTemperature = (TextView) findViewById(R.id.tv_batteryTemperature);
        mTvLeftCost = (TextView) findViewById(R.id.tv_leftCost);

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
        setTitle("充电器(未连接)");
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
                if (!(bleDevice != null && BleManager.getInstance().isConnected(bleDevice))) {
                    connectBle(chargerId);
                } else {
                    ToastUtil.showToast("蓝牙已连接");
                }
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
        List<ErrorInfo> errorInfos = DBManager.getInstance(this).getErrorInfos();
        if (errorInfos == null || errorInfos.size() == 0) {
            return;
        }
        List<ChargerAlarm> chargerAlarms = new ArrayList<>();
        for (int i = 0; i < errorInfos.size(); i++) {
            ChargerAlarm chargerAlarm = new ChargerAlarm();
            chargerAlarm.setChargerid(chargerId);
            chargerAlarm.setWarn_msg(errorInfos.get(i).getErrorMsg());
            chargerAlarm.setWarn_time(errorInfos.get(i).getErrorTime());
            chargerAlarm.setWarn_type(errorInfos.get(i).getErrorType());
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
                        DBManager.getInstance(ChargerActivity.this).deleteAllErrorInfos();
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                    }
                }).build().execute();
    }

    private void sendChargeRecord() {
        List<ChargeRecord> chargeRecords = DBManager.getInstance(this).getChargeRecords();
        if (chargeRecords == null || chargeRecords.size() == 0) {
            return;
        }
        List<ChargerRecord> chargerRecords = new ArrayList<>();
        for (ChargeRecord chargeRecord : chargeRecords) {
            ChargerRecord chargerRecord = new ChargerRecord();
            chargerRecord.setCharger_starttime(chargeRecord.getStartTime());
            chargerRecord.setCharger_endtime(chargeRecord.getEndTime());
            chargerRecord.setCharger_end_reason(chargeRecord.getEndReason());
            chargerRecord.setRecordtime(BleUtil.getNowTime());
            chargerRecord.setChargerid(chargerId);
            chargerRecord.setVoltage(chargeRecord.getMaxVoltage());
            chargerRecord.setCurrents(chargeRecord.getMaxElectricity());
            chargerRecord.setElectricity_total(chargeRecord.getTotlePower());
            chargerRecord.setAmbient_temperature(chargeRecord.getEnvironmentTemperature());
            chargerRecord.setBattery_temperature(chargeRecord.getMaxBatteryTemperature());
            chargerRecord.setCharger_temperature(chargeRecord.getMaxChargerTemperature());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BleManager.getInstance().disconnectAllDevice();
    }
}
