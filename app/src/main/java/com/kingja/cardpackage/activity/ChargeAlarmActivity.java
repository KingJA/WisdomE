package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.kingja.cardpackage.adapter.ChargerAlarmDetailAdapter;
import com.kingja.cardpackage.callback.EmptyCallback;
import com.kingja.cardpackage.callback.ErrorCallback;
import com.kingja.cardpackage.callback.LoadingCallback;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetChargerWarningInfoList;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.ToastUtil;
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
 * Create Time:2017/11/23 13:39
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ChargeAlarmActivity extends BackTitleActivity implements BackTitleActivity.OnRightClickListener {
    private String chargerId;
    private ListView mLvChargeAlarm;
    private List<GetChargerWarningInfoList.ContentBean.DataBean> chargerAlarms = new ArrayList<>();
    private ChargerAlarmDetailAdapter mChargerAlarmDetailAdapter;
    private LoadService loadService;

    @Override
    protected void initVariables() {
        chargerId = getIntent().getStringExtra("chargerId");
        Log.e(TAG, "chargerId: " + chargerId);
    }

    @Override
    protected void initContentView() {
        mLvChargeAlarm = (ListView) findViewById(R.id.lv_chargeAlarm);
        mChargerAlarmDetailAdapter = new ChargerAlarmDetailAdapter(this, chargerAlarms);
        mLvChargeAlarm.setAdapter(mChargerAlarmDetailAdapter);
        loadService = LoadSir.getDefault().register(mLvChargeAlarm, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                initNet();
            }
        });
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_charge_alarm;
    }

    @Override
    protected void initNet() {
        loadService.showCallback(LoadingCallback.class);
        Map<String, Object> param = new HashMap<>();
        param.put("PageIndex", "1");
        param.put("PageSize", "50");
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
                            mChargerAlarmDetailAdapter.setData(chargerAlarms);
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
        setTitle("充电器预警");
        setOnRightClickListener(this, "全部已读");
    }

    @Override
    public void onRightClick() {
        ToastUtil.showToast("全部已读");
    }

    public static void goActivity(Context context, String chargerId) {
        Intent intent = new Intent(context, ChargeAlarmActivity.class);
        intent.putExtra("chargerId", chargerId);
        context.startActivity(intent);
    }
}
