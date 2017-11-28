package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.kingja.cardpackage.adapter.AutoChargerConfigAdapter;
import com.kingja.cardpackage.callback.EmptyCallback;
import com.kingja.cardpackage.callback.ErrorCallback;
import com.kingja.cardpackage.callback.LoadingCallback;
import com.kingja.cardpackage.entiy.AddChargerSetting;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetChargerSettingList;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.GoUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.tdr.wisdome.R;

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
public class AutoChargeActivity extends BackTitleActivity implements BackTitleActivity.OnMenuClickListener {
    private String chargerId;
    private List<GetChargerSettingList.ContentBean.DataBean> chargeConfigs=new ArrayList<>();
    private ListView mLvChargeConfig;
    private AutoChargerConfigAdapter mAutoChargerConfigAdapter;
    private LoadService loadService;

    @Override
    protected void initVariables() {
        chargerId = getIntent().getStringExtra("chargerId");
        Log.e(TAG, "chargerId: " + chargerId);
    }

    @Override
    protected void initContentView() {
        mLvChargeConfig = (ListView) findViewById(R.id.lv_chargeConfig);
        mAutoChargerConfigAdapter = new AutoChargerConfigAdapter(this, chargeConfigs);
        mLvChargeConfig.setAdapter(mAutoChargerConfigAdapter);
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
        loadService.showCallback(LoadingCallback.class);
        Map<String, Object> param = new HashMap<>();
        param.put("PageIndex", "1");
        param.put("PageSize", "10");
        param.put("OnlyGetRecord", false);
        param.put("ChargerId", chargerId);
        param.put("Auto_Type", 1);
        new ThreadPoolTask.Builder()
                .setGeneralParam("0506b35c7e6248fb84cd2c83afa1b300", KConstants.CARD_TYPE_EMPTY, KConstants
                                .GetChargerSettingList,
                        param)
                .setBeanType(GetChargerSettingList.class)
                .setCallBack(new WebServiceCallBack<GetChargerSettingList>() {
                    @Override
                    public void onSuccess(GetChargerSettingList bean) {
                        chargeConfigs = bean.getContent().getData();
                        if (chargeConfigs != null && chargeConfigs.size() > 0) {
                            mAutoChargerConfigAdapter.setData(chargeConfigs);
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

    }

    @Override
    protected void setData() {
        setTitle("自动充电");
        setOnMenuClickListener(this, R.drawable.bg_add);
    }

    @Override
    public void onMenuClick() {
        GoUtil.goActivity(this,ConfigAutoChargeActivity.class);
    }

    private void addChargeConfig(int count) {
        Map<String, Object> param = new HashMap<>();
        param.put("auto_type", count % 2 + 1);
        param.put("auto_start", "13:18");
        param.put("auto_end", "13:41");
        param.put("auto_operate", count % 2 + 1);
        param.put("chargerid", chargerId);
        param.put("auto_frequency", count % 2 + 1);
        param.put("seq", count);
        new ThreadPoolTask.Builder()
                .setGeneralParam("0506b35c7e6248fb84cd2c83afa1b300", KConstants.CARD_TYPE_EMPTY, KConstants
                                .AddChargerSetting,
                        param)
                .setBeanType(AddChargerSetting.class)
                .setCallBack(new WebServiceCallBack<AddChargerSetting>() {
                    @Override
                    public void onSuccess(AddChargerSetting bean) {
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                    }
                }).build().execute();
    }

    public static void goActivity(Context context, String chargerId) {
        Intent intent = new Intent(context, AutoChargeActivity.class);
        intent.putExtra("chargerId", chargerId);
        context.startActivity(intent);
    }
}
