package com.kingja.cardpackage.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.cardpackage.Event.GetCarDataEvent;
import com.kingja.cardpackage.activity.MeetFromActivity;
import com.kingja.cardpackage.activity.PreRecordSuccessActivity;
import com.kingja.cardpackage.activity.PreRegisterActivity;
import com.kingja.cardpackage.activity.RecordSiteActivity;
import com.kingja.cardpackage.base.BaseFragment;
import com.kingja.cardpackage.db.ECardXutils3;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.KJBikeCode;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.CheckUtil;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.GoUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.TimeUtil;
import com.kingja.ui.dialog.BaseListDialog;
import com.tdr.wisdome.R;
import com.tdr.wisdome.actvitiy.BrandActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


/**
 * Description：预登记
 * Create Time：2016/8/19 14:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PreRegisterFragment extends BaseFragment implements PreRegisterActivity.OnSaveClickListener, View
        .OnClickListener {

    private LinearLayout mLlCarBrand;
    private TextView mTvCarBrand;
    private LinearLayout mLlCarColor;
    private TextView mTvCarColor;
    private EditText mEtMachineNo;
    private EditText mEtCheJiaNo;
    private EditText mEtBuyerName;
    private LinearLayout mLlIdCardType;
    private TextView mTvIdCardType;
    private EditText mTvIdCardNo;
    private EditText mTvPhone;
    private EditText mTvReadyPhone;
    private LinearLayout mLlPre;
    private LinearLayout mLlMeetTime;
    private TextView mTvMeetTime;
    private LinearLayout mLlRecordSite;
    private TextView mTvRecordSite;
    private LinearLayout mLlMeetFrom;
    private TextView mTvMeetFrom;
    private PreRegisterActivity mPreRegisterActivity;
    private List<KJBikeCode> colorList;
    private List<KJBikeCode> cardTypeList;
    private BaseListDialog<KJBikeCode> cardTypeDialog;
    private BaseListDialog<KJBikeCode> colorDialog;
    private String mBrand;
    private String mBrandId;
    private String registersiteId;
    private String siteName;
    private String configId;
    private String onTime;
    private String offTime;
    private String meetTime;
    private String carColor;
    private String cardType;
    private int year;
    private int month;
    private int day;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_pre_register;
    }

    @Override
    public void initFragmentView(View view, Bundle savedInstanceState) {
        mPreRegisterActivity = (PreRegisterActivity) getActivity();
        mTvCarBrand = (TextView) view.findViewById(R.id.tv_carBrand);
        mTvCarColor = (TextView) view.findViewById(R.id.tv_carColor);
        mEtMachineNo = (EditText) view.findViewById(R.id.et_machineNo);
        mEtCheJiaNo = (EditText) view.findViewById(R.id.et_cheJiaNo);
        mEtBuyerName = (EditText) view.findViewById(R.id.et_buyerName);
        mTvIdCardType = (TextView) view.findViewById(R.id.tv_idCardType);
        mTvIdCardNo = (EditText) view.findViewById(R.id.et_cardNo);
        mTvPhone = (EditText) view.findViewById(R.id.et_phone);
        mTvReadyPhone = (EditText) view.findViewById(R.id.et_readyPhone);
        mLlPre = (LinearLayout) view.findViewById(R.id.ll_pre);
        mTvMeetTime = (TextView) view.findViewById(R.id.tv_meetTime);
        mTvRecordSite = (TextView) view.findViewById(R.id.tv_recordSite);
        mTvMeetFrom = (TextView) view.findViewById(R.id.tv_meetFrom);

        mLlCarBrand = (LinearLayout) view.findViewById(R.id.ll_carBrand);
        mLlCarColor = (LinearLayout) view.findViewById(R.id.ll_carColor);
        mLlIdCardType = (LinearLayout) view.findViewById(R.id.ll_idCardType);
        mLlMeetTime = (LinearLayout) view.findViewById(R.id.ll_meetTime);
        mLlRecordSite = (LinearLayout) view.findViewById(R.id.ll_recordSite);
        mLlMeetFrom = (LinearLayout) view.findViewById(R.id.ll_meetFrom);
    }

    @Override
    public void initFragmentVariables() {
        colorList = ECardXutils3.getInstance().selectAllWhere(KJBikeCode.class, "type", "4");

        cardTypeList = ECardXutils3.getInstance().selectAllWhere(KJBikeCode.class, "type", "6");

        Log.e(TAG, "colorList: "+colorList.size() );
        Log.e(TAG, "cardTypeList: "+cardTypeList.size() );
    }

    @Override
    public void initFragmentNet() {

    }

    @Override
    public void initFragmentData() {
        initCalendar();
        mPreRegisterActivity.setOnSaveClickListener(this);
        mLlCarBrand.setOnClickListener(this);
        mLlCarColor.setOnClickListener(this);
        mLlIdCardType.setOnClickListener(this);
        mLlMeetTime.setOnClickListener(this);
        mLlMeetFrom.setOnClickListener(this);
        mLlRecordSite.setOnClickListener(this);

        colorDialog = new BaseListDialog<KJBikeCode>(getActivity(), colorList) {
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

        cardTypeDialog = new BaseListDialog<KJBikeCode>(getActivity(), cardTypeList) {
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
    public void setFragmentData() {
        mLlPre.setVisibility(CheckUtil.checkCity("天津市") ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onSaveClick() {
        String buyerName = mEtBuyerName.getText().toString().trim();
        String idCardNo = mTvIdCardNo.getText().toString().trim();
        String phone = mTvPhone.getText().toString().trim();
        String machineNo = mEtMachineNo.getText().toString().trim();
        String cheJiaNo = mEtCheJiaNo.getText().toString().trim();
        String readyPhone = mTvReadyPhone.getText().toString().trim();

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
        /*天津市*/
        if (CheckUtil.checkCity("天津市")) {
            if (!(CheckUtil.checkEmpty(meetTime, "请选择预约时间") &&
                    CheckUtil.checkEmpty(registersiteId, "请选择备案登记点") &&
                    CheckUtil.checkEmpty(configId, "请选择预约时间段"))) {
                return;
            }
        }
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
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
        param.put("RegistersiteId", registersiteId);
        param.put("ConfigId", configId);
        param.put("ReservateTime", meetTime);

        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.AddPreRate, param)
                .setBeanType(Object.class)
                .setCallBack(new WebServiceCallBack<Object>() {
                    @Override
                    public void onSuccess(Object bean) {
                        setProgressDialog(false);
                        EventBus.getDefault().post(new GetCarDataEvent());
                        PreRecordSuccessActivity.goActivity(getActivity(), siteName);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_carBrand:
                Intent intent = new Intent(getActivity(), BrandActivity.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.ll_carColor:
                colorDialog.show();
                break;
            case R.id.ll_idCardType:
                cardTypeDialog.show();
                break;
            case R.id.ll_meetTime:
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (TimeUtil.getMeetTime(String.format("%02d-%02d-%02d", year, monthOfYear + 1, dayOfMonth))) {
                            meetTime = String.format("%02d-%02d-%02d", year, monthOfYear + 1, dayOfMonth);
                            mTvMeetTime.setText(meetTime);
                        }
                    }
                }, year, month - 1, day).show();
                break;
            case R.id.ll_recordSite:
                GoUtil.goActivityForResult(getActivity(), RecordSiteActivity.class, 200);
                break;
            case R.id.ll_meetFrom:
                if (CheckUtil.checkEmpty(meetTime, "请选择预约时间") && CheckUtil.checkEmpty(registersiteId, "请选择备案登记点")) {
                    MeetFromActivity.goActivity(getActivity(), registersiteId, meetTime);
                    configId = "";
                    mTvMeetFrom.setText("");
                }
                break;

        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == 100) {
                mBrand = data.getStringExtra("brandName");
                mBrandId = data.getStringExtra("brandCode");
                mTvCarBrand.setText(mBrand);
            }

            if (requestCode == 200) {//选择备案登记点
                registersiteId = data.getStringExtra("RegistersiteId");
                siteName = data.getStringExtra("SiteName");
                mTvRecordSite.setText(siteName);
                configId = "";
                mTvMeetFrom.setText("");
            }
            if (requestCode == 300) {//选择预约时间段
                onTime = data.getStringExtra("onTime");
                offTime = data.getStringExtra("offTime");
                configId = data.getStringExtra("configId");
                mTvMeetFrom.setText(onTime + "-" + offTime);
            }
        }
    }

    private void initCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);//获取明天的日期
        year = calendar.get(Calendar.YEAR);
        //月份从0开始
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }


}
