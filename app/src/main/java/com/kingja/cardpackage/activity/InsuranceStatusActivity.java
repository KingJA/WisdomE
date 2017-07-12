package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetClaimInfo;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.KConstants;
import com.pizidea.imagepicker.ImageUtil;
import com.tdr.wisdome.R;
import com.tdr.wisdome.actvitiy.BigImageActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Description：TODO
 * Create Time：2016/12/2 13:39
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class InsuranceStatusActivity extends BackTitleActivity {
    private TextView mTvCarNum;
    private TextView mTvOwnerName;
    private TextView mTvOwnerCardId;
    private TextView mTvOwnerPhone;
    private TextView mTvBankName;
    private TextView mTvBankNum;
    private ImageView mIvCardId;
    private ImageView mIvBankCard;
    private TextView mTvCompanyName;
    private TextView mTvClaimType;
    private TextView mTvClaimCost;
    private TextView mTvStartTime;
    private TextView mTvEndTime;
    private TextView mTvStolenTime;
    private TextView mTvUploadTime;
    private TextView mTvApplyTime;
    private TextView mTvPayTime;
    private TextView mTvStatus;
    private TextView mTvRemark;
    private String ecId;
    private RelativeLayout mRlCardId;
    private RelativeLayout mRlBankNum;
    private String photoCard;
    private String photoBankCard;
    private String photoCert;
    private RelativeLayout mRlCert;
    private ImageView mIvCert;
    private TextView mTvBankOwnerName;


    @Override
    protected void initVariables() {
        ecId = getIntent().getStringExtra("ecId");
    }

    @Override
    protected void initContentView() {
        mTvCarNum = (TextView) findViewById(R.id.tv_carNum);
        mTvOwnerName = (TextView) findViewById(R.id.tv_ownerName);
        mTvOwnerCardId = (TextView) findViewById(R.id.tv_ownerCardId);
        mTvOwnerPhone = (TextView) findViewById(R.id.tv_ownerPhone);
        mTvBankName = (TextView) findViewById(R.id.tv_bankName);
        mTvBankNum = (TextView) findViewById(R.id.tv_bankNum);
        mTvCompanyName = (TextView) findViewById(R.id.tv_companyName);
        mTvClaimType = (TextView) findViewById(R.id.tv_claimType);
        mTvClaimCost = (TextView) findViewById(R.id.tv_claimCost);
        mTvStartTime = (TextView) findViewById(R.id.tv_startTime);
        mTvEndTime = (TextView) findViewById(R.id.tv_endTime);
        mTvStolenTime = (TextView) findViewById(R.id.tv_stolenTime);
        mTvUploadTime = (TextView) findViewById(R.id.tv_uploadTime);
        mTvApplyTime = (TextView) findViewById(R.id.tv_applyTime);
        mTvPayTime = (TextView) findViewById(R.id.tv_payTime);
        mTvStatus = (TextView) findViewById(R.id.tv_status);
        mTvRemark = (TextView) findViewById(R.id.tv_remark);
        mTvBankOwnerName = (TextView) findViewById(R.id.tv_bankOwnerName);

        mIvCardId = (ImageView) findViewById(R.id.iv_cardId);
        mIvBankCard = (ImageView) findViewById(R.id.iv_bankCard);
        mIvCert = (ImageView) findViewById(R.id.iv_cert);

        mRlCardId = (RelativeLayout) findViewById(R.id.rl_cardId);
        mRlBankNum = (RelativeLayout) findViewById(R.id.rl_bankNum);
        mRlCert = (RelativeLayout) findViewById(R.id.rl_cert);
    }


    @Override
    protected int getBackContentView() {
        return R.layout.activity_insurance_status;
    }

    @Override
    protected void initNet() {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("EcID", ecId);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.GetClaimInfo, param)
                .setBeanType(GetClaimInfo.class)
                .setCallBack(new WebServiceCallBack<GetClaimInfo>() {
                    @Override
                    public void onSuccess(GetClaimInfo bean) {
                        setClaimInfo(bean.getContent());
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
        mRlCardId.setOnClickListener(this);
        mRlBankNum.setOnClickListener(this);
        mRlCert.setOnClickListener(this);
    }

    @Override
    protected void setData() {
        setTitle("理赔状态");
    }

    private void setClaimInfo(GetClaimInfo.ContentBean claimInfo) {
        mTvCarNum.setText(claimInfo.getPlateNumber());
        mTvOwnerName.setText(claimInfo.getOwnerName());
        mTvOwnerCardId.setText(claimInfo.getCardId());
        mTvOwnerPhone.setText(claimInfo.getPhone());
        mTvBankName.setText(claimInfo.getBank());
        mTvBankOwnerName.setText(claimInfo.getBankCardOwner());
        mTvBankNum.setText(claimInfo.getBankCardNo());
        mTvCompanyName.setText("1".equals(claimInfo.getInsurerType()) ? "大地" : "人保");
        mTvClaimType.setText(getClaimType(claimInfo.getPolicyType()));
        mTvClaimCost.setText(claimInfo.getPolicyAmount());
        mTvStartTime.setText(claimInfo.getPolicyBeginTime());
        mTvEndTime.setText(claimInfo.getPolicyEndTime());
        mTvStolenTime.setText(claimInfo.getLoseDate());
        mTvUploadTime.setText(claimInfo.getUploadCertTime());
        mTvApplyTime.setText(claimInfo.getDeclareTime());
        mTvPayTime.setText(claimInfo.getClaimTime());
        mTvStatus.setText(getClaimStatus(claimInfo.getClaimState()));
        mTvRemark.setText(claimInfo.getDeclareRemark());
        photoCard = claimInfo.getPhotoCert();
        photoBankCard = claimInfo.getPhotoBankCard();
        photoCert = claimInfo.getPCSPhotoCERT();
        mIvCardId.setImageBitmap(ImageUtil.base64ToBitmap(photoCard));
        mIvBankCard.setImageBitmap(ImageUtil.base64ToBitmap(photoBankCard));
        mIvCert.setImageBitmap(ImageUtil.base64ToBitmap(photoCert));
    }

    public static void goActivity(Context context, String ECID) {
        Intent intent = new Intent(context, InsuranceStatusActivity.class);
        intent.putExtra("ecId", ECID);
        context.startActivity(intent);

    }

    public String getClaimStatus(int statusCode) {
        String claimStatus = "进行中";
        Log.e(TAG, "statusCode: "+statusCode );
        if (statusCode==1) {
            claimStatus = "已上传证明";
        } else if (statusCode==2) {
            claimStatus = "已申报理赔";
        } else if (statusCode==3) {
            claimStatus = "超期未理赔";
        } else if (statusCode==4) {
            claimStatus = "已理赔";
        }
        return claimStatus;
    }

    //    保单类型（1 意外险 2 盗抢险 3责任险）
    public String getClaimType(String typeCode) {
        String claimType = "未知";
        if ("1".equals(typeCode)) {
            claimType = "意外险";
        } else if ("2".equals(typeCode)) {
            claimType = "盗抢险";
        } else if ("3".equals(typeCode)) {
            claimType = "责任险";
        }
        return claimType;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rl_cardId:
                BigImageActivity.goActivity(this, photoCard);
                break;
            case R.id.rl_bankNum:
                BigImageActivity.goActivity(this, photoBankCard);
                break;
            case R.id.rl_cert:
                BigImageActivity.goActivity(this, photoCert);
                break;

        }
    }
}
