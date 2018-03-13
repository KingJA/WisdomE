package com.kingja.cardpackage.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.ListPopupWindow;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.kingja.cardpackage.entiy.AddUserDetails;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.CheckUtil;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.NoDoubleClickListener;
import com.kingja.cardpackage.util.TempConstants;
import com.kingja.cardpackage.util.ToastUtil;
import com.kingja.supershapeview.SuperShapeTextView;
import com.kingja.ui.wheelview.ChangeAddressDialog;
import com.tdr.wisdome.R;
import com.tdr.wisdome.util.Utils;
import com.tdr.wisdome.view.material.MaterialEditText;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Description:TODO
 * Create Time:2017/7/22 13:49
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PerfectInfoActivity extends BackTitleActivity {
    private MaterialEditText mMetUserinfoIdcard;
    private MaterialEditText mMetUserinfoBirthday;
    private MaterialEditText mMetUserinfoRealname;
    private MaterialEditText mMetUserinfoSex;
    private MaterialEditText mMetUserinfoAddress;
    private SuperShapeTextView mStvUserinfoConfirm;
    private String idCard;
    private String birthday;
    private String realName;
    private String sex;
    private String address;
    private ImageView mIvOcr;
    private ListPopupWindow genderSelector;
    private ImageView mIvUserinfoSexArrow;
    private ImageView mIvAddressDetailArrow;
    private MaterialEditText mMetAddressArea;
    private MaterialEditText mMetAddressDetail;
    private ChangeAddressDialog mChangeAddressDialog;
    private String unitId;
    private String unitName;
    private String resideaddress;
    private String addressDetail;
    private String provinceCityArea;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initContentView() {
        mIvAddressDetailArrow = (ImageView) findViewById(R.id.iv_addressDetail_arrow);
        mMetAddressArea = (MaterialEditText) findViewById(R.id.met_addressArea);
        mMetAddressDetail = (MaterialEditText) findViewById(R.id.met_addressDetail);
        mMetUserinfoIdcard = (MaterialEditText) findViewById(R.id.met_userinfo_idcard);
        mIvOcr = (ImageView) findViewById(R.id.iv_ocr);
        mMetUserinfoBirthday = (MaterialEditText) findViewById(R.id.met_userinfo_birthday);
        mMetUserinfoRealname = (MaterialEditText) findViewById(R.id.met_userinfo_realname);
        mMetUserinfoSex = (MaterialEditText) findViewById(R.id.met_userinfo_sex);
        mMetUserinfoAddress = (MaterialEditText) findViewById(R.id.met_userinfo_address);
        mStvUserinfoConfirm = (SuperShapeTextView) findViewById(R.id.stv_userinfo_confirm);
        mIvUserinfoSexArrow = (ImageView) findViewById(R.id.iv_userinfo_sex_arrow);
        createGenderSelector();

    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_perfect_info;
    }

    @Override
    protected void initNet() {
        if (!TextUtils.isEmpty(DataManager.getIdCard())) {
            mMetUserinfoIdcard.setText(DataManager.getIdCard());
            mMetUserinfoSex.setText(DataManager.getSex());
            mMetUserinfoRealname.setText(DataManager.getRealName());
//            mMetUserinfoAddress.setText(DataManager.getAddress());
            mMetUserinfoBirthday.setText(DataManager.getBirthday());
            mMetAddressDetail.setText(DataManager.getResideaddress());
            mMetAddressArea.setText(DataManager.getProvinceCityArea());
        }
    }

    @Override
    protected void initData() {
        mStvUserinfoConfirm.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                idCard = mMetUserinfoIdcard.getText().toString().trim().toUpperCase();
                birthday = mMetUserinfoBirthday.getText().toString().trim();
                realName = mMetUserinfoRealname.getText().toString().trim();
                sex = mMetUserinfoSex.getText().toString().trim();
                addressDetail = mMetAddressDetail.getText().toString().trim();
                if (CheckUtil.checkIdCard(idCard, "身份证号格式错误")
                        && CheckUtil.checkEmpty(birthday, "请输入出生年月")
                        && CheckUtil.checkEmpty(realName, "请输入真实姓名")
                        && CheckUtil.checkEmpty(unitId, "请选择所在地")
                        && CheckUtil.checkEmpty(addressDetail, "输入详细地址")
                        && CheckUtil.checkGender(sex)) {
                    perfectUserInfo();
                }

            }
        });
        mIvAddressDetailArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChangeAddressDialog = new ChangeAddressDialog(PerfectInfoActivity.this);
                mChangeAddressDialog.setAddress("浙江省", "温州市", "鹿城区", "330000", "330300", "330302");
                mChangeAddressDialog.show();
                mChangeAddressDialog.setAddresskListener(new ChangeAddressDialog.OnAddressCListener() {
                    @Override
                    public void onClick(String province, String city, String area, String provinceId, String cityId,
                                        String areaId) {
                        provinceCityArea = province + city + area;
                        mMetAddressArea.setText(province + city + area);
                        unitId = areaId;
                        unitName = area;
                    }
                });
            }
        });
        mIvOcr.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                KCamera.GoCamera(PerfectInfoActivity.this);
            }
        });
        mMetUserinfoIdcard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String idcard = mMetUserinfoIdcard.getText().toString().toUpperCase().trim();
                if (s.length() == 18) {
                    mMetUserinfoBirthday.setText(Utils.identityToBirthday(idcard));
                    mMetUserinfoSex.setText(Utils.maleOrFemale(idcard));
                }
            }
        });

        mIvUserinfoSexArrow.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                Log.e(TAG, "onNoDoubleClick: ");
                genderSelector.show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case KCamera.REQUEST_CODE_KCAMERA:
                if (Activity.RESULT_OK == resultCode) {
                    String card = data.getStringExtra("card");
                    String name = data.getStringExtra("name");
                    String birth = data.getStringExtra("birth");
                    String sex = data.getStringExtra("sex");
                    String address = data.getStringExtra("address");
                    if (card != null) {
                        mMetUserinfoIdcard.setText(card);
                    }
                    if (name != null) {
                        mMetUserinfoRealname.setText(name);
                    }
                    if (birth != null) {
                        mMetUserinfoBirthday.setText(birth);
                    }
                    if (sex != null) {
                        mMetUserinfoSex.setText(sex);
                    }
                    if (address != null) {
                        mMetUserinfoAddress.setText(address);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void perfectUserInfo() {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put(TempConstants.TaskID, TempConstants.DEFAULT_TASK_ID);
        param.put("Realname", realName);
        param.put("idcard", idCard);
        param.put("sex", sex);
        param.put("birthday", birthday);
        param.put("nationality", "");
        param.put("address", address);
        param.put("phone2", "");
        param.put("Remark", "");
        param.put("UserName", "");
        param.put("FaceBase", "");
        param.put("Resideaddress", provinceCityArea + addressDetail);
        param.put("unitid", unitId);
        param.put("unitname", unitName);


        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), "", "AddUserDetails", param)
                .setBeanType(AddUserDetails.class)
                .setCallBack(new WebServiceCallBack<AddUserDetails>() {
                    @Override
                    public void onSuccess(AddUserDetails bean) {
                        setProgressDialog(false);
                        ToastUtil.showToast("完善用户资料成功");
                        save2Local();
                        finish();
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    private void save2Local() {
        DataManager.putProvinceCityArea(provinceCityArea);
        DataManager.putResideaddress(addressDetail);
        DataManager.putUnitId(unitId);
        DataManager.putIdCard(idCard);
        DataManager.putRealName(realName);
        DataManager.putSex(sex);
        DataManager.putBirthday(birthday);
//        DataManager.putAddresse(address);
    }

    @Override
    protected void setData() {
        setTitle("用户资料");
        if (!editable()) {
            mMetAddressDetail.setEnabled(false);
            mMetUserinfoIdcard.setEnabled(false);
            mMetUserinfoRealname.setEnabled(false);
            mMetUserinfoAddress.setEnabled(false);
            mIvUserinfoSexArrow.setVisibility(View.GONE);
            mIvAddressDetailArrow.setVisibility(View.GONE);
            mIvOcr.setVisibility(View.GONE);
        } else {
            mStvUserinfoConfirm.setVisibility(View.VISIBLE);
        }
    }


    private boolean editable() {
        return "0".equals(DataManager.getCertification()) || "3".equals(DataManager.getCertification()) || "".equals
                (DataManager.getCertification());
    }

    private void createGenderSelector() {
        genderSelector = new ListPopupWindow(this);
        genderSelector.setAdapter(new ArrayAdapter(this, R.layout.item_tv_center, Arrays.asList("男", "女")));
        genderSelector.setAnchorView(mMetUserinfoSex);
        genderSelector.setModal(true);
        genderSelector.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                genderSelector.dismiss();
                mMetUserinfoSex.setText(position == 0 ? "男" : "女");
            }
        });
    }

}
