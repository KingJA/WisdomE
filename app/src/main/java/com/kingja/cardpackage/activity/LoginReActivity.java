package com.kingja.cardpackage.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.cardpackage.Event.Login;
import com.kingja.cardpackage.base.App;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.CheckUtil;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.GoUtil;
import com.kingja.cardpackage.util.StringUtil;
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
 * Description：记住账号
 * Create Time：2017/1/17 10:49
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoginReActivity extends BackTitleActivity implements BackTitleActivity.OnRightClickListener, TextWatcher {
    private MaterialEditText mMetPassword;
    private TextView mTvForgetPassword;
    private TextView mTvLogin;
    private String imei;
    private TextView mTvName;
    private TextView mTvChangeUser;
    private ImageView mIvUserImg;


    @Override
    protected void initVariables() {
        PhoneInfo phoneInfo = PhoneUtil.getInfo(App.getContext());
        imei = phoneInfo.getIMEI();
    }

    @Override
    protected void initContentView() {
        mIvUserImg = (ImageView) findViewById(R.id.iv_userImg);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mMetPassword = (MaterialEditText) findViewById(R.id.met_password);
        mTvForgetPassword = (TextView) findViewById(R.id.tv_forgetPassword);
        mTvLogin = (TextView) findViewById(R.id.tv_login);
        mTvChangeUser = (TextView) findViewById(R.id.tv_changeUser);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_login_re;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        mTvForgetPassword.setOnClickListener(this);
        mTvChangeUser.setOnClickListener(this);
        mTvLogin.setOnClickListener(this);
        mMetPassword.addTextChangedListener(this);
    }

    @Override
    protected void setData() {
        mIvUserImg.setImageBitmap(Utils.stringtoBitmap(com.tdr.wisdome.util.Constants.getFaceBase()));
        mTvName.setText(StringUtil.hidePhone(com.tdr.wisdome.util.Constants.getUserPhone(), 4));
        setLeftImg(R.drawable.close);
        setOnRightClickListener(this, "注册");
    }

    @Override
    public void onRightClick() {
        GoUtil.goActivityAndFinish(this, RegisterActivity.class);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_forgetPassword:
                GoUtil.goActivityAndFinish(this, ForgetActivityPhone.class);
                break;
            case R.id.tv_login:
                checkLogin();
                break;
            case R.id.tv_changeUser:
                GoUtil.goActivityAndFinish(this, LoginActivity.class);
                break;
            default:
                break;
        }
    }

    private void checkLogin() {
        String password = mMetPassword.getText().toString().trim();
        if (CheckUtil.checkEmpty(password, "请输入密码")) {
            try {
                password = MD5.getMD5(password);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            doLogin(password);
        }
    }

    private void doLogin(String password) {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("Phone", com.tdr.wisdome.util.Constants.getUserPhone());
        param.put("UserPassword", password);
        param.put("ChannelID", JPushInterface.getRegistrationID(this));
        param.put("ChannelType", "1");
        param.put("LoginIP", "");
        param.put("IMEI", imei);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), "", KConstants.Login, param)
                .setBeanType(Login.class)
                .setCallBack(new WebServiceCallBack<Login>() {
                    @Override
                    public void onSuccess(Login bean) {
                        setProgressDialog(false);
                        save2Local(bean.getContent());
                        GoUtil.goActivityAndFinish(LoginReActivity.this, MainActivity.class);
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
//        com.tdr.wisdome.util.Constants.setFaceBase(Utils.initNullStr(content.getFaceBase()));
        com.tdr.wisdome.util.Constants.setToken(Utils.initNullStr(content.getToken()));
        com.tdr.wisdome.util.Constants.setCertification(Utils.initNullStr(content.getCertification() + ""));
        com.tdr.wisdome.util.Constants.setRealName(Utils.initNullStr(content.getRealname()));
        com.tdr.wisdome.util.Constants.setPermanentAddr(Utils.initNullStr(content.getAddress()));


        DataManager.putResideaddress(Utils.initNullStr(content.getResideaddress()));
        DataManager.putProvinceCityArea(Utils.initNullStr(content.getUnitName()));

        DataManager.putToken(Utils.initNullStr(content.getToken()));
        DataManager.putIdCard(Utils.initNullStr(content.getIDCard()));
        DataManager.putRealName(Utils.initNullStr(content.getRealname()));
        DataManager.putSex(Utils.initNullStr(content.getSex()));
        DataManager.putBirthday(Utils.initNullStr(content.getBirthday()));
        DataManager.putPhone(Utils.initNullStr(content.getPhone()));
        DataManager.putUserId(Utils.initNullStr(content.getUserID()));
        DataManager.putAddresse(Utils.initNullStr(content.getAddress()));
        DataManager.putCertification(Utils.initNullStr(content.getCertification() + ""));

        if (content.getCity() != null) {
            DataManager.putCityName(Utils.initNullStr(content.getCity().getCityName()));
            DataManager.putCityCode(Utils.initNullStr(content.getCity().getCityCode()));
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 0) {
            mTvLogin.setEnabled(true);
            mTvLogin.setBackgroundResource(R.drawable.btn_blue_match);
        }else{
            mTvLogin.setEnabled(false);
            mTvLogin.setBackgroundResource(R.drawable.btn_gray_match);
        }
    }
}
