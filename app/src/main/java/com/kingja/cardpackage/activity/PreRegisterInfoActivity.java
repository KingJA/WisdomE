package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.cardpackage.db.ECardXutils3;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetPreRateOne;
import com.kingja.cardpackage.entiy.KJBikeCode;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.CheckUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.TimeUtil;
import com.kingja.cardpackage.util.ToastUtil;
import com.kingja.ui.dialog.BaseListDialog;
import com.tdr.wisdome.R;
import com.tdr.wisdome.actvitiy.BrandActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：预登记编辑
 * Create Time：2017/1/20 9:04
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PreRegisterInfoActivity extends BackTitleActivity {

    private LinearLayout mLlCarBrand;
    private TextView mTvCarBrand;
    private LinearLayout mLlCarColor;
    private TextView mTvCarColor;
    private EditText mEtMachineNo;
    private EditText mEtCheJiaNo;
    private EditText mEtBuyerName;
    private LinearLayout mLlIdCardType;
    private TextView mTvIdCardType;
    private EditText mEtCardNo;
    private EditText mEtPhone;
    private EditText mEtReadyPhone;
    private String mBrand;
    private String mBrandId;
    private List<KJBikeCode> colorList;
    private List<KJBikeCode> cardTypeList;
    private BaseListDialog<KJBikeCode> colorDialog;
    private BaseListDialog cardTypeDialog;
    private String cardType;
    private String carColor;
    private String prerateID;
    private Map<String, String> colorMap = new HashMap<>();
    private Map<String, String> cardTypeMap = new HashMap<>();
    private boolean editable;

    @Override
    protected void initVariables() {
        prerateID = getIntent().getStringExtra("prerateID");
        editable = getIntent().getBooleanExtra("editable", false);
        colorList = ECardXutils3.getInstance().selectAllWhere(KJBikeCode.class, "type", "4");
        cardTypeList = ECardXutils3.getInstance().selectAllWhere(KJBikeCode.class, "type", "6");
        for (KJBikeCode color : colorList) {
            colorMap.put(color.getCODE(), color.getNAME());
        }
        for (KJBikeCode cardType : cardTypeList) {
            cardTypeMap.put(cardType.getCODE(), cardType.getNAME());
        }
    }

    @Override
    protected void initContentView() {
        mLlCarBrand = (LinearLayout) findViewById(R.id.ll_carBrand);
        mLlCarColor = (LinearLayout) findViewById(R.id.ll_carColor);
        mLlIdCardType = (LinearLayout) findViewById(R.id.ll_idCardType);

        mTvIdCardType = (TextView) findViewById(R.id.tv_idCardType);
        mTvCarBrand = (TextView) findViewById(R.id.tv_carBrand);
        mTvCarColor = (TextView) findViewById(R.id.tv_carColor);
        mEtBuyerName = (EditText) findViewById(R.id.et_buyerName);
        mEtCardNo = (EditText) findViewById(R.id.et_cardNo);
        mEtPhone = (EditText) findViewById(R.id.et_phone);
        mEtMachineNo = (EditText) findViewById(R.id.et_machineNo);
        mEtCheJiaNo = (EditText) findViewById(R.id.et_cheJiaNo);
        mEtReadyPhone = (EditText) findViewById(R.id.et_readyPhone);

    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_pre_register;
    }

    @Override
    protected void initNet() {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("prerateID", prerateID);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.GetPreRateOne, param)
                .setBeanType(GetPreRateOne.class)
                .setCallBack(new WebServiceCallBack<GetPreRateOne>() {
                    @Override
                    public void onSuccess(GetPreRateOne bean) {
                        GetPreRateOne.ContentBean content = bean.getContent();
                        mTvCarBrand.setText(content.getVehicleBrandName());
                        mTvCarColor.setText(colorMap.get(content.getColorID()));
                        mTvIdCardType.setText(cardTypeMap.get(content.getCardType() + ""));

                        mEtMachineNo.setText(content.getEngineno());
                        mEtCheJiaNo.setText(content.getShelvesno());
                        mEtBuyerName.setText(content.getOwnerName());
                        mEtCardNo.setText(content.getCardid());
                        mEtPhone.setText(content.getPhone1());
                        mEtReadyPhone.setText(content.getPhone2());

                        cardType = content.getCardType()+"";
                        carColor = content.getColorID();
                        mBrandId = content.getVehiclebrand();

                        setProgressDialog(false);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Override
    protected void initData() {
        if (editable) {
            mLlIdCardType.setOnClickListener(this);
            mLlCarColor.setOnClickListener(this);
            mLlCarBrand.setOnClickListener(this);
            mEtMachineNo.setEnabled(true);
            mEtCheJiaNo.setEnabled(true);
            mEtBuyerName.setEnabled(true);
            mEtCardNo.setEnabled(true);
            mEtPhone.setEnabled(true);
            mEtReadyPhone.setEnabled(true);
            setOnRightClickListener(new OnRightClickListener() {
                @Override
                public void onRightClick() {
                    doPreRecord();
                }
            }, "修改");
        }
        colorDialog = new BaseListDialog<KJBikeCode>(this, colorList) {
            @Override
            protected void fillLvData(List<KJBikeCode> list, int position, TextView tv) {
                tv.setText(list.get(position).getNAME());
            }

            @Override
            protected void onItemSelect(KJBikeCode colorBean) {
                mTvCarColor.setText(colorBean.getNAME());
                carColor = colorBean.getCODE() + "";
            }
        };

        cardTypeDialog = new BaseListDialog<KJBikeCode>(this, cardTypeList) {
            @Override
            protected void fillLvData(List<KJBikeCode> list, int position, TextView tv) {
                tv.setText(list.get(position).getNAME());
            }

            @Override
            protected void onItemSelect(KJBikeCode cardTypeBean) {
                mTvIdCardType.setText(cardTypeBean.getNAME());
                cardType = cardTypeBean.getCODE() + "";
            }
        };
    }

    @Override
    protected void setData() {



    }

    private void doPreRecord() {
        String buyerName = mEtBuyerName.getText().toString().trim();
        String idCardNo = mEtCardNo.getText().toString().trim();
        String phone = mEtPhone.getText().toString().trim();
        String machineNo = mEtMachineNo.getText().toString().trim();
        String cheJiaNo = mEtCheJiaNo.getText().toString().trim();
        String readyPhone = mEtReadyPhone.getText().toString().trim();

        if (!(CheckUtil.checkEmpty(mBrandId, "请选择车辆品牌") &&
                CheckUtil.checkEmpty(carColor, "请选择车辆主颜色") &&
                CheckUtil.checkEmpty(buyerName, "请输入购买者姓名") &&
                CheckUtil.checkEmpty(cardType, "请选择证件类型") &&
                CheckUtil.checkEmpty(idCardNo, "请输入证件号码") &&
                CheckUtil.checkPhoneFormat(phone))) {
            return;
        }

        if ("1".equals(cardType)) {
            if (!CheckUtil.checkIdCard(idCardNo, "身份证格式错误")) {
                return;
            }

        }

        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("PrerateID", prerateID);
        param.put("Vehiclebrand", mBrandId);
        param.put("Vehiclemodels", "");
        param.put("ColorID", carColor);
        param.put("Engineno", machineNo);
        param.put("Shelvesno", cheJiaNo);
        param.put("BuyDate", "2015-01-01");
        param.put("CardType", cardType);
        param.put("OwnerName", buyerName);
        param.put("Cardid", idCardNo);
        param.put("Phone1", phone);
        param.put("Phone2", readyPhone);
        param.put("CreateTime", TimeUtil.getNowTime());

        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.EditPreRate, param)
                .setBeanType(Object.class)
                .setCallBack(new WebServiceCallBack<Object>() {
                    @Override
                    public void onSuccess(Object bean) {
                        setProgressDialog(false);
                        ToastUtil.showToast("预登记信息修改成功");
                        finish();
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ll_carBrand:
                Intent intent = new Intent(this, BrandActivity.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.ll_carColor:
                colorDialog.show();
                break;
            case R.id.ll_idCardType:
                cardTypeDialog.show();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == 100) {
                mBrand = data.getStringExtra("brandName");
                mBrandId = data.getStringExtra("brandCode");
                mTvCarBrand.setText(mBrand);
            }
        }

    }

    public static void goActivity(Context context, String prerateID, boolean editable) {
        Intent intent = new Intent(context, PreRegisterInfoActivity.class);
        intent.putExtra("prerateID", prerateID);
        intent.putExtra("editable", editable);
        context.startActivity(intent);
    }
}
