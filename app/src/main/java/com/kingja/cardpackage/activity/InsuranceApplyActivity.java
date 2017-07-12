package com.kingja.cardpackage.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.cardpackage.Event.MainCarRefresh;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetBindingList;
import com.kingja.cardpackage.entiy.GetCodeList;
import com.kingja.cardpackage.entiy.SendCodeSms;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.CheckUtil;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.StringUtil;
import com.kingja.cardpackage.util.TimeUtil;
import com.kingja.cardpackage.util.ToastUtil;
import com.pizidea.imagepicker.AndroidImagePicker;
import com.pizidea.imagepicker.ImageUtil;
import com.pizidea.imagepicker.bean.ImageItem;
import com.tdr.wisdome.R;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：TODO
 * Create Time：2016/12/2 10:56
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class InsuranceApplyActivity extends BackTitleActivity implements View.OnClickListener {
    private EditText mEtCarNum;
    private EditText mEtOwnerName;
    private EditText mEtOwnerCardId;
    private EditText mEtOwnerPhone;
    private TextView mTvGetCheckCode;
    private EditText mEtCheckCode;
    private EditText mEtBankNum;
    private EditText mEtRemark;
    private ImageView mIvClickIdCard;
    private ImageView mIvIdCard;
    private ImageView mIvClickBankCard;
    private ImageView mIvBankCard;
    private GetBindingList.ContentBean bindCar;
    private String plateNumber;
    private String ownerName;
    private String ownerCardId;
    private String ownerPhone;
    private String checkCode;
    private String bankNum;
    private String remark;
    private String photoCard;
    private String photoBankCard;
    private String bankOwnerName;
    private EditText mEtBankOwnerName;

    @Override
    protected void initVariables() {
        bindCar = (GetBindingList.ContentBean) getIntent().getSerializableExtra("CAR_INFO");
    }

    @Override
    protected void initContentView() {
        mEtCarNum = (EditText) findViewById(R.id.et_carNum);
        mEtOwnerName = (EditText) findViewById(R.id.et_ownerName);
        mEtOwnerCardId = (EditText) findViewById(R.id.et_ownerCardId);
        mEtOwnerPhone = (EditText) findViewById(R.id.et_ownerPhone);
        mEtCheckCode = (EditText) findViewById(R.id.et_checkCode);
        mEtBankNum = (EditText) findViewById(R.id.et_bankNum);
        mEtRemark = (EditText) findViewById(R.id.et_remark);
        mEtBankOwnerName = (EditText) findViewById(R.id.et_bankOwnerName);

        mIvIdCard = (ImageView) findViewById(R.id.iv_idCard);
        mIvBankCard = (ImageView) findViewById(R.id.iv_bankCard);

        mTvGetCheckCode = (TextView) findViewById(R.id.tv_getCheckCode);
        mIvClickIdCard = (ImageView) findViewById(R.id.iv_click_idCard);
        mIvClickBankCard = (ImageView) findViewById(R.id.iv_click_bankCard);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_insurance_apply;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        mTvGetCheckCode.setOnClickListener(this);
        mIvClickIdCard.setOnClickListener(this);
        mIvClickBankCard.setOnClickListener(this);
    }

    @Override
    protected void setData() {
        setTitle("保险理赔");
        setOnRightClickListener(new OnRightClickListener() {
            @Override
            public void onRightClick() {
                checkApplyInfo();
            }
        }, "完成");
        mEtCarNum.setText(bindCar.getPlateNumber());
        mEtOwnerName.setText(bindCar.getOwnerName());
        mEtOwnerCardId.setText(bindCar.getCardID());
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_getCheckCode://获取验证码
                getCheckCode();
                break;
            case R.id.iv_click_idCard://证件照片
                takePhoto(1);
                break;
            case R.id.iv_click_bankCard://银行卡照片
                takePhoto(2);
                break;
            default:
                break;
        }
    }

    private void takePhoto(final int typeCode) {
        AndroidImagePicker.getInstance().pickSingle(this, true, new AndroidImagePicker.OnImagePickCompleteListener() {
            @Override
            public void onImagePickComplete(List<ImageItem> items) {
                if (items != null && items.size() > 0) {
                    Bitmap bitmap = ImageUtil.compressScaleFromF2B(items.get(0).path);
                    boolean raphael = ImageUtil.saveBitmap2file(InsuranceApplyActivity.this, bitmap, "CardPackage", StringUtil.getUUID());
                    Log.e(TAG, "raphael: " + raphael);

                    switch (typeCode) {
                        case 1:
                            photoCard = ImageUtil.bitmapToBase64(bitmap);
                            mIvIdCard.setImageBitmap(bitmap);
                            break;
                        case 2:
                            photoBankCard = ImageUtil.bitmapToBase64(bitmap);
                            mIvBankCard.setImageBitmap(bitmap);
                            break;
                    }
                }
            }
        });
    }

    public static void goActivity(Activity activity, GetBindingList.ContentBean bindCar) {
        Intent intent = new Intent(activity, InsuranceApplyActivity.class);
        intent.putExtra("CAR_INFO", bindCar);
        activity.startActivity(intent);

    }

    private void checkApplyInfo() {
        plateNumber = mEtCarNum.getText().toString().trim();
        ownerName = mEtOwnerName.getText().toString().trim();
        ownerCardId = mEtOwnerCardId.getText().toString().trim();
        ownerPhone = mEtOwnerPhone.getText().toString().trim();
        checkCode = mEtCheckCode.getText().toString().trim();
        bankNum = mEtBankNum.getText().toString().trim();
        remark = mEtRemark.getText().toString().trim();
        bankOwnerName = mEtBankOwnerName.getText().toString().trim();
        if (CheckUtil.checkEmpty(plateNumber, "请输入车牌号码") && CheckUtil.checkEmpty(ownerName, "请输入车主姓名")
                && CheckUtil.checkEmpty(ownerCardId, "请输入证件号码") && CheckUtil.checkPhoneFormat(ownerPhone)
                && CheckUtil.checkEmpty(checkCode, "请输入验证码") && CheckUtil.checkEmpty(bankNum, "请输入银行卡卡号")
                && CheckUtil.checkEmpty(bankOwnerName, "请输入银行卡户主") && CheckUtil.checkEmpty(photoCard, "请提供证件照片")
                && CheckUtil.checkEmpty(photoBankCard, "请提供银行卡照片")) {
            doApply();
        }


    }

    private void doApply() {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("EcID", bindCar.getEcID());
        param.put("PlateNumber", plateNumber);
        param.put("OwnerName", ownerName);
        param.put("Cardid", ownerCardId);
        param.put("Phone", ownerPhone);
        param.put("DeclareTime", TimeUtil.getFormatTime());
        param.put("BankCardNO", bankNum);
        param.put("PhotoCard", photoCard);
        param.put("PhotoBankCard", photoBankCard);
        param.put("DeclareRemark", remark);
        param.put("BankCardOwner", bankOwnerName);

        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.DeclareClaimInfo, param)
                .setBeanType(GetCodeList.class)
                .setCallBack(new WebServiceCallBack<GetCodeList>() {
                    @Override
                    public void onSuccess(GetCodeList bean) {
                        setProgressDialog(false);
                        EventBus.getDefault().post(new MainCarRefresh());
                        ToastUtil.showToast("申报成功");
                        finish();
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    private void getCheckCode() {
        ownerPhone = mEtOwnerPhone.getText().toString().trim();
        if (!CheckUtil.checkPhoneFormat(ownerPhone)) {
            return;
        }
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("phone", ownerPhone);
        param.put("CodeType", 1);

        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.SendCodeSms, param)
                .setBeanType(SendCodeSms.class)
                .setCallBack(new WebServiceCallBack<SendCodeSms>() {
                    @Override
                    public void onSuccess(SendCodeSms bean) {
                        checkCode=  bean.getContent().getVerificationCode();
                        setProgressDialog(false);

                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }
}
