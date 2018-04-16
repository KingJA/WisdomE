package com.kingja.cardpackage.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.kingja.cardpackage.adapter.ChargersAdapter;
import com.kingja.cardpackage.callback.EmptyCallback;
import com.kingja.cardpackage.callback.ErrorCallback;
import com.kingja.cardpackage.callback.LoadingCallback;
import com.kingja.cardpackage.db.ECardXutils3;
import com.kingja.cardpackage.entiy.DelBindCharger;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetBindChargerList;
import com.kingja.cardpackage.entiy.GetCodeList;
import com.kingja.cardpackage.entiy.KJBikeCode;
import com.kingja.cardpackage.entiy.RefleshChargeList;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.DialogUtil;
import com.kingja.cardpackage.util.GoUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.TempConstants;
import com.kingja.cardpackage.util.TimeUtil;
import com.kingja.cardpackage.util.ToastUtil;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.tdr.wisdome.R;
import com.tdr.wisdome.zbar.CaptureActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
    public static byte BIND_DEVICE = 0x02;
    private ListView mLvCharges;
    private ChargersAdapter mChargersAdapter;
    private List<GetBindChargerList.ContentBean.DataBean> chargers = new ArrayList<>();
    private LoadService loadService;
    private NormalDialog bingdDialog;
    private NormalDialog unbingdDialog;

    @Override
    protected void initVariables() {
        EventBus.getDefault().register(this);
        bingdDialog = DialogUtil.getDoubleDialog(this, "确定要绑定该设备吗？", "取消", "确定");
        unbingdDialog = DialogUtil.getDoubleDialog(this, "确定要解绑该设备吗？", "取消", "确定");
    }

    private void showUnbindDialog(final String chargeId, final String ecId, final int position) {
        unbingdDialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                unbingdDialog.dismiss();
            }
        }, new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                unbingdDialog.dismiss();
                unbindDevice(chargeId, ecId, position);
            }
        });
        unbingdDialog.show();
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
            public void onUnbindDevice(String chargeId, String ecId, int position) {
                showUnbindDialog(chargeId, ecId, position);
            }

            @Override
            public void onBinidDevice(final String deviceId) {
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
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CHARGER, KConstants
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
                            downloadDB();
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

    protected void downloadDB() {
        setProgressDialog(true, "检测电动车数据更新");
        String updateTime;
        if (TextUtils.isEmpty(DataManager.getLastCity()) || !(DataManager.getCityName().equals(DataManager
                .getLastCity()))) {
            updateTime = "1990-01-01 00:00:01";
            DataManager.putLastCity(DataManager.getCityName());
        } else {
            updateTime = DataManager.getLastUpdateCarBrand();
        }
        Map<String, Object> param = new HashMap<>();
        param.put("updatetime", updateTime);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CHARGER, KConstants.GetCodeList, param)
                .setBeanType(GetCodeList.class)
                .setCallBack(new WebServiceCallBack<GetCodeList>() {
                    @Override
                    public void onSuccess(final GetCodeList bean) {
                        List<KJBikeCode> carInfoList = bean.getContent();
                        Log.e(TAG, "更新车辆品牌数据: " + carInfoList.size());
                        if (carInfoList.size() > 0) {
                            ECardXutils3.getInstance().deleteAll(KJBikeCode.class);
                            ECardXutils3.getInstance().saveDate(carInfoList);
                            DataManager.putLastUpdateCarBrand(TimeUtil.getNowTime());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    setProgressDialog(false);
                                }
                            }, 10000);
                        } else {
                            setProgressDialog(false);
                        }
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Override
    protected void initData() {
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
            if (requestCode == RQ_QCODE) {
                Bundle bundle = data.getExtras();
                String url = bundle.getString("result");
                Log.e(TAG, "url: " + url);
                if (!url.startsWith("8681")) {
                    ToastUtil.showToast("不是指定类型的二维码");
                    return;
                }
                ChargerBindActivity.goActivity(this, url, BIND_DEVICE);
            } else if (requestCode == BIND_DEVICE) {
                String chargeId = data.getStringExtra("chargeId");
                String plateNumber = data.getStringExtra("plateNumber");
                mChargersAdapter.addItem(chargeId, plateNumber);

            }

        }
    }

    protected void unbindDevice(String chargeId, String ecId, final int position) {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("ChargerId", chargeId);
        param.put("EcId", ecId);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CHARGER, KConstants
                                .DelBindCharger,
                        param)
                .setBeanType(DelBindCharger.class)
                .setCallBack(new WebServiceCallBack<DelBindCharger>() {
                    @Override
                    public void onSuccess(DelBindCharger bean) {
                        mChargersAdapter.removeItem(position);
                        setProgressDialog(false);
                        ToastUtil.showToast("解绑成功");
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Subscribe
    public void refleshChargeList(RefleshChargeList event) {
        initNet();
    }
}
