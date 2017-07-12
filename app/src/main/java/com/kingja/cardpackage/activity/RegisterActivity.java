package com.kingja.cardpackage.activity;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.kingja.cardpackage.Event.Login;
import com.kingja.cardpackage.entiy.AddUsers;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.IsRegisterPhone;
import com.kingja.cardpackage.entiy.SendCodeSms;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.ActivityManager;
import com.kingja.cardpackage.util.CheckUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.GoUtil;
import com.kingja.cardpackage.util.ToastUtil;
import com.tdr.wisdome.R;
import com.tdr.wisdome.actvitiy.MainActivity;
import com.tdr.wisdome.model.PhoneInfo;
import com.tdr.wisdome.util.MD5;
import com.tdr.wisdome.util.PhoneUtil;
import com.tdr.wisdome.util.Utils;
import com.tdr.wisdome.view.material.MaterialEditText;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

/**
 * Description：注册页面
 * Create Time：2017/1/17 14:52
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RegisterActivity extends BackTitleActivity implements TextWatcher {
    private MaterialEditText mMetPhone;
    private MaterialEditText mMetCode;
    private MaterialEditText mMetPassword;
    private TextView mTvConfirm;
    private TextView mTvGetCode;
    private TextView mTvGoLogin;
    private String verificationCode;
    private String verificationCodeID;
    private CountDownTimer timer;
    private TextView mTvCheckRegister;
    private boolean mIsSending;
    private String phone;
    private String checkCode;
    private String password;
    private String imei;


    @Override
    protected void initVariables() {
        PhoneInfo phoneInfo = new PhoneUtil(this).getInfo();
        imei = phoneInfo.getIMEI();
    }

    @Override
    protected void initContentView() {
        mMetPhone = (MaterialEditText) findViewById(R.id.met_phone);
        mMetCode = (MaterialEditText) findViewById(R.id.met_code);
        mMetPassword = (MaterialEditText) findViewById(R.id.met_password);
        mTvConfirm = (TextView) findViewById(R.id.tv_confirm);
        mTvGetCode = (TextView) findViewById(R.id.tv_getCode);
        mTvGoLogin = (TextView) findViewById(R.id.tv_goLogin);
        mTvCheckRegister = (TextView) findViewById(R.id.tv_checkRegister);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_register_new;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        mMetPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkEnable();
                mTvCheckRegister.setText("");
                if (mIsSending) {
                    return;
                }
                if (s.length() == 11) {
                    mTvCheckRegister.setText("检验中");
                    mTvCheckRegister.setTextColor(getResources().getColor(R.color.font_9));
                    checkRegistered(s.toString());
                } else {
                    mTvGetCode.setEnabled(false);
                    mTvGetCode.setTextColor(getResources().getColor(R.color.font_9));
                }
            }
        });
        mMetCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkEnable();
            }
        });
        mMetPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkEnable();
            }
        });

        mTvGetCode.setOnClickListener(this);
        mTvConfirm.setOnClickListener(this);
        mTvGoLogin.setOnClickListener(this);
    }


    private void checkEnable() {
        phone = mMetPhone.getText().toString().trim();
        checkCode = mMetCode.getText().toString().trim();
        password = mMetPassword.getText().toString().trim();
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(checkCode) && !TextUtils.isEmpty(password)) {
            mTvConfirm.setBackgroundResource(R.drawable.btn_blue_match);
        } else {
            mTvConfirm.setBackgroundResource(R.drawable.btn_gray_match);
        }
    }

    @Override
    protected void setData() {
        setTitle("注册");
        mTvGetCode.setOnClickListener(this);
        mTvGoLogin.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_getCode:
                getCheckCode();
                break;
            case R.id.tv_confirm:
                doRegister();
                break;
            case R.id.tv_goLogin:
                finish();
                break;

        }
    }

    private void doRegister() {
        final String phone = mMetPhone.getText().toString().trim();
        String checkCode = mMetCode.getText().toString().trim();
        String password = mMetPassword.getText().toString().trim();
        if (!(CheckUtil.checkPhoneFormat(phone) &&
                CheckUtil.checkEmpty(checkCode, "请输入验证码") &&
                CheckUtil.checkEquals(checkCode, verificationCode, "验证码不正确") &&
                CheckUtil.checkPasswordFormat(password))) {
            return;
        }
        try {
            password = MD5.getMD5(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("Phone", phone);
        param.put("UserName", "");
        param.put("UserPassword", password);
        param.put("VerificationCode", verificationCode);
        param.put("VerificationCodeID", verificationCodeID);
        param.put("IsValid", "1");
        final String finalPassword = password;
        new ThreadPoolTask.Builder()
                .setGeneralParam("", "", KConstants.AddUsers, param)
                .setBeanType(AddUsers.class)
                .setCallBack(new WebServiceCallBack<AddUsers>() {
                    @Override
                    public void onSuccess(AddUsers bean) {
                        setProgressDialog(false);
                        ToastUtil.showToast("注册成功");
                        doLogin(phone, finalPassword);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    private void getCheckCode() {
        String phone = mMetPhone.getText().toString().trim();
        if (!CheckUtil.checkPhoneFormat(phone)) {
            return;
        }

        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("phone", phone);
        param.put("CodeType", "2");
        new ThreadPoolTask.Builder()
                .setGeneralParam("", "", KConstants.SendCodeSms, param)
                .setBeanType(SendCodeSms.class)
                .setCallBack(new WebServiceCallBack<SendCodeSms>() {
                    @Override
                    public void onSuccess(SendCodeSms bean) {
                        startCountDownTime(60);
                        setProgressDialog(false);
                        verificationCode = bean.getContent().getVerificationCode();
                        verificationCodeID = bean.getContent().getVerificationCodeID();
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    private void startCountDownTime(long time) {
        mIsSending = true;
        mTvGetCode.setEnabled(false);
        timer = new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTvGetCode.setText(millisUntilFinished / 1000 + "秒重新发送");
            }

            @Override
            public void onFinish() {
                mTvGetCode.setText("重新获取验证码");
                mTvGetCode.setEnabled(true);
                mIsSending = false;
            }
        };
        timer.start();// 开始计时
    }

    private void checkRegistered(final String phone) {
        Map<String, Object> param = new HashMap<>();
        param.put("phone", phone);
        new ThreadPoolTask.Builder()
                .setGeneralParam("", "", KConstants.IsRegisterPhone, param)
                .setBeanType(IsRegisterPhone.class)
                .setCallBack(new WebServiceCallBack<IsRegisterPhone>() {
                    @Override
                    public void onSuccess(IsRegisterPhone bean) {
                        if (bean.getContent().getCode() == 0) {//未注册过
                            mTvGetCode.setTextColor(getResources().getColor(R.color.bg_red));
                            mTvGetCode.setEnabled(true);
                            mTvCheckRegister.setTextColor(getResources().getColor(R.color.bg_blue));
                            mTvCheckRegister.setText("可注册");
                        } else {
                            mTvCheckRegister.setTextColor(getResources().getColor(R.color.bg_red));
                            mTvCheckRegister.setText("已注册");
                        }
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                    }
                }).build().execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }

    }

    private void doLogin(String name, String password) {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("Phone", name);
        param.put("UserPassword", password);
        param.put("ChannelID", JPushInterface.getRegistrationID(this));
        param.put("ChannelType", "1");
        param.put("LoginIP", "");
        param.put("IMEI", imei);
        new ThreadPoolTask.Builder()
                .setGeneralParam("", "", KConstants.Login, param)
                .setBeanType(Login.class)
                .setCallBack(new WebServiceCallBack<Login>() {
                    @Override
                    public void onSuccess(Login bean) {
                        setProgressDialog(false);
                        save2Local(bean.getContent());
                        ActivityManager.getAppManager().finishAllActivity();
                        GoUtil.goActivityAndFinish(RegisterActivity.this, MainActivity.class);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    private void save2Local(Login.ContentBean content) {
        com.tdr.wisdome.util.Constants.setUserId(Utils.initNullStr(content.getUserID()));
        com.tdr.wisdome.util.Constants.setUserPhone(Utils.initNullStr(content.getPhone()));
        com.tdr.wisdome.util.Constants.setUserName(Utils.initNullStr(content.getUserName()));
        com.tdr.wisdome.util.Constants.setUserIdentitycard(Utils.initNullStr(content.getIDCard()));
        com.tdr.wisdome.util.Constants.setFaceId(Utils.initNullStr(content.getFaceID()));
        com.tdr.wisdome.util.Constants.setFaceBase(Utils.initNullStr(content.getFaceBase()));
        com.tdr.wisdome.util.Constants.setToken(Utils.initNullStr(content.getToken()));
        com.tdr.wisdome.util.Constants.setCertification(Utils.initNullStr(content.getCertification() + ""));
        com.tdr.wisdome.util.Constants.setRealName(Utils.initNullStr(content.getRealname()));
        com.tdr.wisdome.util.Constants.setPermanentAddr(Utils.initNullStr(content.getAddress()));
        if (content.getCity() != null) {
            com.tdr.wisdome.util.Constants.setCityName(Utils.initNullStr(content.getCity().getCityName()));
            com.tdr.wisdome.util.Constants.setCityCode(Utils.initNullStr(content.getCity().getCityCode()));
        }
    }
}
