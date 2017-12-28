package com.kingja.cardpackage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.clj.fastble.BleManager;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.kingja.cardpackage.adapter.ChargersAdapter;
import com.kingja.cardpackage.callback.EmptyCallback;
import com.kingja.cardpackage.callback.ErrorCallback;
import com.kingja.cardpackage.callback.LoadingCallback;
import com.kingja.cardpackage.entiy.BindCharger;
import com.kingja.cardpackage.entiy.DelBindCharger;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetBindChargerList;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.DialogUtil;
import com.kingja.cardpackage.util.GoUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.TempConstants;
import com.kingja.cardpackage.util.ToastUtil;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.tdr.wisdome.R;
import com.tdr.wisdome.zbar.CaptureActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:TODO
 * Create Time:2017/11/24 9:19
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ChargeListActivity extends BackTitleActivity implements BackTitleActivity.OnMenuClickListener {
    private byte RQ_QCODE = 0x01;
    private ListView mLvCharges;
    private ChargersAdapter mChargersAdapter;
    private List<GetBindChargerList.ContentBean.DataBean> chargers = new ArrayList<>();
    private LoadService loadService;
    private NormalDialog bingdDialog;
    private NormalDialog unbingdDialog;

    @Override
    protected void initVariables() {
        bingdDialog = DialogUtil.getDoubleDialog(this, "确定要绑定该设备吗？", "取消", "确定");
        unbingdDialog = DialogUtil.getDoubleDialog(this, "确定要解绑该设备吗？", "取消", "确定");
    }

    private void showUnbindDialog(final String userId, final String deviceId) {
        unbingdDialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                unbingdDialog.dismiss();
            }
        }, new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                unbingdDialog.dismiss();
                unbindDevice(userId, deviceId);
            }
        });
        unbingdDialog.show();
    }

    private void showBindDialog(final String deviceId) {
        bingdDialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                bingdDialog.dismiss();
            }
        }, new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                bingdDialog.dismiss();
                bindDevice(deviceId);
            }
        });
        bingdDialog.show();
    }

    @Override
    protected void initContentView() {
        mLvCharges = (ListView) findViewById(R.id.lv_charges);
        mChargersAdapter = new ChargersAdapter(this, chargers);
        mLvCharges.setAdapter(mChargersAdapter);
        loadService = LoadSir.getDefault().register(mLvCharges, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                initNet();
            }
        });
        mChargersAdapter.setOnChargeOperListener(new ChargersAdapter.OnChargeOperListener() {
            @Override
            public void onUnbindDevice(String userId, String deviceId) {
                showUnbindDialog(userId, deviceId);
            }

            @Override
            public void onConnectDevice(final String deviceId) {
                ChargerActivity.goActivity(ChargeListActivity.this, deviceId);
            }
        });
    }


    @Override
    protected int getBackContentView() {
        return R.layout.activity_charge_list;
    }

    @Override
    protected void initNet() {
        loadService.showCallback(LoadingCallback.class);
        Map<String, Object> param = new HashMap<>();
        param.put(TempConstants.PageIndex, 1);
        param.put(TempConstants.PageSize, 50);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_EMPTY, KConstants
                                .GetBindChargerList,
                        param)
                .setBeanType(GetBindChargerList.class)
                .setCallBack(new WebServiceCallBack<GetBindChargerList>() {
                    @Override
                    public void onSuccess(GetBindChargerList bean) {
                        chargers = bean.getContent().getData();
                        if (chargers != null && chargers.size() > 0) {
                            mChargersAdapter.setData(chargers);
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
        if (!BleManager.getInstance().isBlueEnable()) {
            ToastUtil.showToast("该设备不支持蓝牙Ble，无法正常连接蓝牙");
        }else{
            Log.e(TAG, "支持Ble");
            BleManager.getInstance().enableBluetooth();
        }
    }

    @Override
    protected void setData() {
        setTitle("充电器");
        setOnMenuClickListener(this, R.drawable.bg_add);
    }

    @Override
    public void onMenuClick() {
        GoUtil.goActivityForResult(this, CaptureActivity.class, RQ_QCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            String url = bundle.getString("result");
            Log.e(TAG, "url: " + url);
            showBindDialog(url);
        }
    }

    protected void bindDevice(String deviceId) {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("binding_objectid", deviceId);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_EMPTY, KConstants.BindCharger,
                        param)
                .setBeanType(BindCharger.class)
                .setCallBack(new WebServiceCallBack<BindCharger>() {
                    @Override
                    public void onSuccess(BindCharger bean) {
                        setProgressDialog(false);
                        ToastUtil.showToast("绑定成功");
                        initNet();
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    protected void unbindDevice(String userId, String deviceId) {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("userid", userId);
        param.put("binding_objectid", deviceId);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_EMPTY, KConstants
                                .DelBindCharger,
                        param)
                .setBeanType(DelBindCharger.class)
                .setCallBack(new WebServiceCallBack<DelBindCharger>() {
                    @Override
                    public void onSuccess(DelBindCharger bean) {
                        setProgressDialog(false);
                        ToastUtil.showToast("解绑成功");
                        initNet();
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }
}
