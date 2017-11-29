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
import com.kingja.cardpackage.entiy.AddChargerRecord;
import com.kingja.cardpackage.entiy.AddChargerWarningInfo;
import com.kingja.cardpackage.entiy.ChargerAlarm;
import com.kingja.cardpackage.entiy.ChargerRecord;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetChargerStatistics;
import com.kingja.cardpackage.entiy.GetChargerWarningInfoList;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.BleConstants;
import com.kingja.cardpackage.util.Crc16Util;
import com.kingja.cardpackage.util.GoUtil;
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

import static com.kingja.cardpackage.util.BleConstants.ORDER_81;

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
                        String result = HexUtil.encodeHexStr(data);
                        Log.e(TAG, "onCharacteristicChanged:" + result);

                        BleResult bleResult = BleResultFactory.getBleResult(result);
                        switch (bleResult.getOrderCode()) {
                            case BleConstants.ORDER_02:
                                BleResult02 bleResult02 = new BleResult02(data);
                                sendBle(bleResult02.getResponse());
                                Log.e(TAG, "发送02: " + bleResult02.getResponse());
                                Log.e(TAG, "充电状态: " + bleResult02.getChargeStatus());
                                Log.e(TAG, "当前充电电压: " + bleResult02.getCurrentChargeVoltage());
                                Log.e(TAG, "当前充电电流: " + bleResult02.getCurrentChargeelEctricity());
                                Log.e(TAG, "累计充电电量: " + bleResult02.getTotlePower());
                                Log.e(TAG, "电池温度: " + bleResult02.getBatteryTemperature());
                                Log.e(TAG, "充电器温度: " + bleResult02.getChargerTemperature());
                                break;
                            case BleConstants.ORDER_03:
                                BleResult03 bleResult03 = (BleResult03) bleResult;
                                sendBle(bleResult03.getResponse());
                                Log.e(TAG, "发送03: " + bleResult03.getResponse());
                                break;
                            case BleConstants.ORDER_04:
                                BleResult04 bleResult04 = (BleResult04) bleResult;
                                sendBle(bleResult04.getResponse());
                                Log.e(TAG, "发送04: " + bleResult04.getResponse());
                                break;
                            case BleConstants.ORDER_05:
                                BleResult05 bleResult05 = (BleResult05) bleResult;
                                sendBle(bleResult05.getResponse());
                                Log.e(TAG, "发送05: " + bleResult05.getResponse());
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
                ChargerActivity.this.bleDevice = bleDevice;
                initBleListener();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendBle(BleResult81.getContent());
                    }
                }, 1000);

//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        String responseContent82 = BleConstants.ORDER_82 + "01" + "110b1d000000" + "110b1d000101" +
//                                "01" + BleConstants.ZERO_2;
//                        String crc16Code82 = Crc16Util.getCrc16Code(responseContent82);
//                        Log.e(TAG, "82发送: " + (BleConstants.FLAG + responseContent82 + crc16Code82));
//                        sendBle(BleConstants.FLAG + responseContent82 + crc16Code82);
//                    }
//                }, 1500);


//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
////                        84读取
//                        String responseContent83 = BleConstants.ORDER_83 + "01" + BleConstants.ZERO_15;
//                        String crc16Code84 = Crc16Util.getCrc16Code(responseContent83);
//                        sendBle(BleConstants.FLAG + responseContent83 + crc16Code84);
//                    }
//                }, 1500);
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
////                        84读取
//                        String responseContent84 = BleConstants.ORDER_84 + "01" + BleConstants.ZERO_15;
//                        String crc16Code84 = Crc16Util.getCrc16Code(responseContent84);
//                        sendBle(BleConstants.FLAG + responseContent84 + crc16Code84);
//                    }
//                }, 2000);
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
//        loadAlarms();
//        loadStatistics();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BleManager.getInstance().disconnectAllDevice();
    }
}
