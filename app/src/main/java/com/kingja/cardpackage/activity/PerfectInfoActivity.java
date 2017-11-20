package com.kingja.cardpackage.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.ListPopupWindow;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
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

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initContentView() {
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
            mMetUserinfoAddress.setText(DataManager.getAddress());
            mMetUserinfoBirthday.setText(DataManager.getBirthday());
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
                address = mMetUserinfoAddress.getText().toString().trim();
                if (CheckUtil.checkIdCard(idCard, "身份证号格式错误")
                        && CheckUtil.checkEmpty(birthday, "请输入出生年月")
                        && CheckUtil.checkEmpty(realName, "请输入真实姓名")
                        && CheckUtil.checkGender(sex)
                        && CheckUtil.checkEmpty(address, "请输入详细地址")) {
                    perfectUserInfo();
                }

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
        DataManager.putIdCard(idCard);
        DataManager.putRealName(realName);
        DataManager.putSex(sex);
        DataManager.putBirthday(birthday);
        DataManager.putAddresse(address);
    }

    @Override
    protected void setData() {
        setTitle("用户资料");
        if (!editable()) {
            mMetUserinfoIdcard.setEnabled(false);
            mMetUserinfoRealname.setEnabled(false);
            mMetUserinfoAddress.setEnabled(false);
            mIvUserinfoSexArrow.setVisibility(View.GONE);
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
