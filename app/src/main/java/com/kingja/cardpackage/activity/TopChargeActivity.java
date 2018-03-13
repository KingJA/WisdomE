package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.exception.BleException;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.kingja.cardpackage.Event.RefreshTopChargers;
import com.kingja.cardpackage.adapter.TopChargerConfigAdapter;
import com.kingja.cardpackage.ble.BleResult83;
import com.kingja.cardpackage.ble.BleUtil;
import com.kingja.cardpackage.callback.EmptyCallback;
import com.kingja.cardpackage.callback.ErrorCallback;
import com.kingja.cardpackage.callback.LoadingAboveCallback;
import com.kingja.cardpackage.entiy.DelChargerSetting;
import com.kingja.cardpackage.entiy.DisableChargerSetting;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetChargerSettingList;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.DialogUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.TempConstants;
import com.kingja.cardpackage.util.ToastUtil;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.tdr.wisdome.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:TODO
 * Create Time:2017/11/23 13:19
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TopChargeActivity extends BackTitleActivity implements BackTitleActivity.OnMenuClickListener,
        TopChargerConfigAdapter.OnConfigOperListener {
    private String chargerId;
    private List<GetChargerSettingList.ContentBean.DataBean> chargeConfigs = new ArrayList<>();
    private ListView mLvChargeConfig;
    private LoadService loadService;
    private TopChargerConfigAdapter mTopChargerConfigAdapter;
    private NormalDialog deleteConfigDialog;

    @Override
    protected void initVariables() {
        EventBus.getDefault().register(this);
        chargerId = getIntent().getStringExtra("chargerId");
        deleteConfigDialog = DialogUtil.getDoubleDialog(this, "是否确定要删除该充电设置?", "取消", "确定");
    }

    @Override
    protected void initContentView() {
        mLvChargeConfig = (ListView) findViewById(R.id.lv_chargeConfig);
        mTopChargerConfigAdapter = new TopChargerConfigAdapter(this, chargeConfigs);
        mLvChargeConfig.setAdapter(mTopChargerConfigAdapter);
        loadService = LoadSir.getDefault().register(mLvChargeConfig, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                initNet();
            }
        });
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_charge_config;
    }

    @Override
    protected void initNet() {
        getTopChargers();
    }

    private void getTopChargers() {
        loadService.showCallback(LoadingAboveCallback.class);
        Map<String, Object> param = new HashMap<>();
        param.put("PageIndex", "1");
        param.put("PageSize", "10");
        param.put("OnlyGetRecord", false);
        param.put("ChargerId", chargerId);
        param.put("Auto_Type", 2);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CHARGER, KConstants
                                .GetChargerSettingList,
                        param)
                .setBeanType(GetChargerSettingList.class)
                .setCallBack(new WebServiceCallBack<GetChargerSettingList>() {
                    @Override
                    public void onSuccess(GetChargerSettingList bean) {
                        chargeConfigs = bean.getContent().getData();
                        if (chargeConfigs != null && chargeConfigs.size() > 0) {
                            mTopChargerConfigAdapter.setData(chargeConfigs);
                            loadService.showSuccess();
                            hideMenu();
                        } else {
                            loadService.showCallback(EmptyCallback.class);
                            setOnMenuClickListener(TopChargeActivity.this, R.drawable.bg_add);
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
        mTopChargerConfigAdapter.setOnConfigOperListener(this);
    }

    @Override
    protected void setData() {
        setTitle("峰谷充电");

    }

    @Override
    public void onMenuClick() {
//        if (mTopChargerConfigAdapter.getCount() < TempConstants.CHARGER_CONFIG_TOP.length) {
//            Log.e(TAG, "Sep: " + getSep());
//            AddTopChargeActivity.goActivity(this, chargerId, getSep());
//        } else {
//            ToastUtil.showToast("配置数量超过上限，请修改或者删除");
//        }
        AddTopChargeActivity.goActivity(this, chargerId, 0);
    }

    public int getSep() {
        List<GetChargerSettingList.ContentBean.DataBean> configs = mTopChargerConfigAdapter.getData();
        List<Integer> chaergerSeps = new ArrayList<>();
        for (int i = 0; i < configs.size(); i++) {
            chaergerSeps.add(configs.get(i).getSeq());
        }
        for (int j = 0; j < TempConstants.CHARGER_CONFIG_TOP.length; j++) {
            if (!chaergerSeps.contains(TempConstants.CHARGER_CONFIG_TOP[j])) {
                return TempConstants.CHARGER_CONFIG_TOP[j];
            }
        }
        return TempConstants.CHARGER_CONFIG_TOP[0];
    }

    public static void goActivity(Context context, String chargerId) {
        Intent intent = new Intent(context, TopChargeActivity.class);
        intent.putExtra("chargerId", chargerId);
        context.startActivity(intent);
    }

    @Override
    public void onConfigEdit(int position, GetChargerSettingList.ContentBean.DataBean config) {
        ModifyTopChargeActivity.goActivity(this, config);
    }

    @Override
    public void onConfigDelete(final int position, final GetChargerSettingList.ContentBean.DataBean config) {
        deleteConfigDialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                deleteConfigDialog.dismiss();
            }
        }, new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                deleteConfigDialog.dismiss();
                onDelete(position, config.getAutoid());
            }
        });
        deleteConfigDialog.show();
    }

    @Override
    public void onConfigDisable(final int position, final int isdisable, String autoid) {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("isdisable", isdisable);
        param.put("autoid", autoid);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CHARGER, KConstants
                                .DisableChargerSetting,
                        param)
                .setBeanType(DisableChargerSetting.class)
                .setCallBack(new WebServiceCallBack<DisableChargerSetting>() {
                    @Override
                    public void onSuccess(DisableChargerSetting bean) {
                        setProgressDialog(false);
                        ToastUtil.showToast("设置成功");
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                        mTopChargerConfigAdapter.setIsdisable(position,isdisable==1?0:1);
                    }
                }).build().execute();
    }


    private void onDelete(final int position, String autoid) {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("autoid", autoid);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CHARGER, KConstants
                                .DelChargerSetting,
                        param)
                .setBeanType(DelChargerSetting.class)
                .setCallBack(new WebServiceCallBack<DelChargerSetting>() {
                    @Override
                    public void onSuccess(DelChargerSetting bean) {
                        setProgressDialog(false);
                        mTopChargerConfigAdapter.removeItem(position);
                        ToastUtil.showToast("删除成功");
                        setOnMenuClickListener(TopChargeActivity.this, R.drawable.bg_add);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshTopChargers(RefreshTopChargers refreshTopChargers) {
        getTopChargers();
    }
}
