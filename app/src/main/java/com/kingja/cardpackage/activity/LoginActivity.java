package com.kingja.cardpackage.activity;

import android.util.Log;
import android.view.View;
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
 * Description：TODO
 * Create Time：2017/1/17 10:49
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoginActivity extends BackTitleActivity implements BackTitleActivity.OnRightClickListener {
    private MaterialEditText mMetName;
    private MaterialEditText mMetPassword;
    private TextView mTvForgetPassword;
    private TextView mTvLogin;
    private String imei;


    @Override
    protected void initVariables() {
        PhoneInfo phoneInfo = PhoneUtil.getInfo(App.getContext());
        imei = phoneInfo.getIMEI();
    }

    @Override
    protected void initContentView() {
        mMetName = (MaterialEditText) findViewById(R.id.met_name);
        mMetPassword = (MaterialEditText) findViewById(R.id.met_password);
        mTvForgetPassword = (TextView) findViewById(R.id.tv_forgetPassword);
        mTvLogin = (TextView) findViewById(R.id.tv_login);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_login_new;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        mTvForgetPassword.setOnClickListener(this);
        mTvLogin.setOnClickListener(this);
    }

    @Override
    protected void setData() {
        setTitle("登录");
        setLeftImg(-1);
        setOnRightClickListener(this, "注册");
    }

    @Override
    public void onRightClick() {
        GoUtil.goActivity(this, RegisterActivity.class);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_forgetPassword:
                GoUtil.goActivity(this, ForgetActivityPhone.class);
                break;
            case R.id.tv_login:
                checkLogin();
                break;
            default:
                break;
        }
    }

    private void checkLogin() {
        String name = mMetName.getText().toString().trim();
        String password = mMetPassword.getText().toString().trim();
        if (CheckUtil.checkEmpty(name, "请输入用户名") && CheckUtil.checkEmpty(password, "请输入密码")) {
            try {
                password = MD5.getMD5(password);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            doLogin(name, password);
        }
    }

    private void doLogin(String name, String password) {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("Phone", name);
        param.put("UserPassword", password);
        param.put("ChannelID", JPushInterface.getRegistrationID(this));
        Log.e(TAG, "RegistrationID: " + JPushInterface.getRegistrationID(this));
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
                        GoUtil.goActivityAndFinish(LoginActivity.this, MainActivity.class);
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

        DataManager.putToken(content.getToken());
        DataManager.putIdCard(content.getIDCard());
        DataManager.putRealName(content.getRealname());
        DataManager.putSex(content.getSex());
        DataManager.putBirthday(content.getBirthday());
        DataManager.putPhone(content.getPhone());
        DataManager.putUserId(content.getUserID());
        DataManager.putAddresse(content.getAddress());
        DataManager.putCertification(content.getCertification()+"");

        if (content.getCity() != null) {
            DataManager.putCityName(content.getCity().getCityName());
            DataManager.putCityCode(content.getCity().getCityCode());
        }

    }
}
