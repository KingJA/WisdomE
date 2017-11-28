package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.kingja.cardpackage.adapter.ChargerRecordAdapter;
import com.kingja.cardpackage.callback.EmptyCallback;
import com.kingja.cardpackage.callback.ErrorCallback;
import com.kingja.cardpackage.callback.LoadingCallback;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetChargerRecordList;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
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
 * Create Time:2017/11/23 14:19
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ChargeRecordActivity extends BackTitleActivity {
    private String chargerId;
    private ListView mLvChargeRecord;
    private List<GetChargerRecordList.ContentBean.DataBean> chargerRecords=new ArrayList<>();
    private LoadService loadService;
    private ChargerRecordAdapter mChargerRecordAdapter;

    @Override
    protected void initVariables() {
        chargerId = getIntent().getStringExtra("chargerId");
        Log.e(TAG, "chargerId: " + chargerId);
    }

    @Override
    protected void initContentView() {
        mLvChargeRecord = (ListView) findViewById(R.id.lv_charge_record);
        mChargerRecordAdapter = new ChargerRecordAdapter(this, chargerRecords);
        mLvChargeRecord.setAdapter(mChargerRecordAdapter);
        loadService = LoadSir.getDefault().register(mLvChargeRecord, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                initNet();
            }
        });

    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_charge_record;
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
                                .GetChargerRecordList,
                        param)
                .setBeanType(GetChargerRecordList.class)
                .setCallBack(new WebServiceCallBack<GetChargerRecordList>() {
                    @Override
                    public void onSuccess(GetChargerRecordList bean) {
                        chargerRecords = bean.getContent().getData();
                        if (chargerRecords != null && chargerRecords.size() > 0) {
                            mChargerRecordAdapter.setData(chargerRecords);
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
        setTitle("充电记录");
    }

    public static void goActivity(Context context, String chargerId) {
        Intent intent = new Intent(context, ChargeRecordActivity.class);
        intent.putExtra("chargerId", chargerId);
        context.startActivity(intent);
    }
}
