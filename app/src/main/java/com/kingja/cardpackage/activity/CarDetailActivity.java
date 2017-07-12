package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.kingja.cardpackage.Event.GetCarDataEvent;
import com.kingja.cardpackage.db.DbDaoXutils3;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.IndexData;
import com.kingja.cardpackage.entiy.KJBikeCode;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.DialogUtil;
import com.tdr.wisdome.R;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * Description：车辆详情
 * Create Time：2017/1/20 11:25
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CarDetailActivity extends BackTitleActivity implements BackTitleActivity.OnRightClickListener {
    private TextView mTvCarNo;
    private TextView mTvCarBrand;
    private TextView mTvCarColor;
    private TextView mTvMachineNo;
    private TextView mTvCheJiaNo;
    private TextView mTvIdCarType;
    private TextView mTvIdCardNo;
    private TextView mTvPhone;
    private TextView mTvBuyerName;
    private TextView mTvBuyerTime;
    private TextView mTvBuyerPrice;
    private TextView mTvInsuranceBuyTime;
    private TextView mTvInsuranceEndTime;
    private TextView mTvPayStatus;


    private IndexData.ContentBean.BindingListBean bindBean;
    private KJBikeCode colorBean;
    private KJBikeCode brandBean;
    private KJBikeCode cardTypeBean;
    private NormalDialog unBindDialog;

    @Override
    protected void initVariables() {
        bindBean = (IndexData.ContentBean.BindingListBean) getIntent().getSerializableExtra("BindBean");
        colorBean = (KJBikeCode) DbDaoXutils3.getInstance("WisdomE.db").selectFirst(KJBikeCode.class, "TYPE", "4", "CODE", bindBean.getElectricCar().getColorID());
        brandBean = (KJBikeCode) DbDaoXutils3.getInstance("WisdomE.db").selectFirst(KJBikeCode.class, "TYPE", "1", "CODE", bindBean.getElectricCar().getVehiclebrand());
        cardTypeBean = (KJBikeCode) DbDaoXutils3.getInstance("WisdomE.db").selectFirst(KJBikeCode.class, "TYPE", "6", "CODE", bindBean.getElectricCar().getCardType() + "");
    }

    @Override
    protected void initContentView() {
        mTvCarNo = (TextView) findViewById(R.id.tv_carNo);
        mTvCarBrand = (TextView) findViewById(R.id.tv_carBrand);
        mTvCarColor = (TextView) findViewById(R.id.tv_carColor);
        mTvMachineNo = (TextView) findViewById(R.id.tv_machineNo);
        mTvCheJiaNo = (TextView) findViewById(R.id.tv_cheJiaNo);
        mTvIdCarType = (TextView) findViewById(R.id.tv_idCarType);
        mTvIdCardNo = (TextView) findViewById(R.id.et_cardNo);
        mTvPhone = (TextView) findViewById(R.id.et_phone);
        mTvBuyerName = (TextView) findViewById(R.id.tv_buyerName);
        mTvBuyerTime = (TextView) findViewById(R.id.tv_buyerTime);
        mTvBuyerPrice = (TextView) findViewById(R.id.tv_buyerPrice);
        mTvInsuranceBuyTime = (TextView) findViewById(R.id.tv_insuranceBuyTime);
        mTvInsuranceEndTime = (TextView) findViewById(R.id.tv_insuranceEndTime);
        mTvPayStatus = (TextView) findViewById(R.id.tv_payStatus);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_car_detail;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        unBindDialog = DialogUtil.getDoubleDialog(this, "您是否解绑电子车牌+电动车", "取消", "解绑");
        unBindDialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                unBindDialog.dismiss();
            }
        }, new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                unBindDialog.dismiss();
                unBind();
            }
        });
    }

    @Override
    protected void setData() {
        mTvCarNo.setText(bindBean.getPlateNumber());
        mTvCarBrand.setText(brandBean.getNAME());
        mTvCarColor.setText(colorBean.getNAME());
        mTvMachineNo.setText(bindBean.getElectricCar().getEngineno());
        mTvCheJiaNo.setText(bindBean.getElectricCar().getShelvesno());
        mTvIdCarType.setText(cardTypeBean.getNAME());
        mTvIdCardNo.setText(bindBean.getElectricCar().getCardid());
        mTvPhone.setText(bindBean.getElectricCar().getPhone1());
        mTvBuyerName.setText(bindBean.getElectricCar().getOwnerName());
        mTvBuyerTime.setText(bindBean.getElectricCar().getBuyDate());
        mTvBuyerPrice.setText(bindBean.getElectricCar().getPrice());
        mTvInsuranceBuyTime.setText(bindBean.getBuyDate());
        mTvInsuranceEndTime.setText(bindBean.getPolicyEndTime());
        mTvPayStatus.setText(getClaimStatus(bindBean.getClaimState() + ""));
        setTitle("车辆详情");
        setOnRightClickListener(this, "解绑");
    }

    private void unBind() {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("BindingID", bindBean.getBindingID());
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.UnBinding, param)
                .setBeanType(Object.class)
                .setCallBack(new WebServiceCallBack<Object>() {
                    @Override
                    public void onSuccess(Object bean) {
                        setOnRightClickGone();
                        EventBus.getDefault().post(new GetCarDataEvent());
                        setProgressDialog(false);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    public static void goActivity(Context context, IndexData.ContentBean.BindingListBean bindBean) {
        Intent intent = new Intent(context, CarDetailActivity.class);
        intent.putExtra("BindBean", bindBean);
        context.startActivity(intent);
    }

    public String getClaimStatus(String statusCode) {
        String claimStatus = "进行中";
        if ("2".equals(statusCode)) {
            claimStatus = "已申报理赔";
        } else if ("3".equals(statusCode)) {
            claimStatus = "超期未理赔";
        } else if ("4".equals(statusCode)) {
            claimStatus = "已理赔";
        }
        return claimStatus;
    }

    @Override
    public void onRightClick() {
        unBindDialog.show();

    }
}
