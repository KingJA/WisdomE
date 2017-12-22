package com.kingja.cardpackage.activity;

import android.widget.TextView;

import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.KConstants;
import com.tdr.wisdome.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Description:TODO
 * Create Time:2017/12/19 9:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BindCodeActivity extends BackTitleActivity {

    private TextView mTvBindCode;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initContentView() {
        mTvBindCode = (TextView) findViewById(R.id.tv_bindCode);

    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_bind_code;
    }

    @Override
    protected void initNet() {
        setProgressDialog(true, "验证码生成中");
        final String randomNum = 1000 + new Random().nextInt(9000) + "";
        Map<String, Object> param = new HashMap<>();
        param.put("VerificationCode", randomNum);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), "", KConstants.SetBindWechatValidCode, param)
                .setBeanType(Object.class)
                .setCallBack(new WebServiceCallBack<Object>() {
                    @Override
                    public void onSuccess(Object bean) {
                        setProgressDialog(false);
                        mTvBindCode.setText(randomNum);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();

    }

    @Override
    protected void initData() {


    }

    @Override
    protected void setData() {
        setTitle("绑定验证码");

    }
}
