package com.tdr.wisdome.actvitiy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.kingja.cardpackage.util.CheckUtil;
import com.kingja.cardpackage.util.DialogUtil;
import com.tdr.wisdome.R;
import com.tdr.wisdome.util.Constants;
import com.tdr.wisdome.util.Utils;
import com.tdr.wisdome.util.WebServiceUtils;
import com.tdr.wisdome.view.ClearEditTextView;
import com.tdr.wisdome.view.ZProgressHUD;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 设置昵称
 * Created by Linus_Xie on 2016/9/2.
 */
public class SetNickNameActivity extends Activity implements View.OnClickListener {

    private Context mContext;

    private ZProgressHUD mProgressHUD;
    private NormalDialog editNameDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setnickname);
        mContext = this;
        initView();
    }

    private ImageView image_back;
    private TextView text_title;
    private TextView text_deal;

    private ClearEditTextView clear_nickName;

    private void initView() {
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        text_title = (TextView) findViewById(R.id.text_title);
        text_title.setText("设置昵称");
        text_deal = (TextView) findViewById(R.id.text_deal);
        text_deal.setText("完成");
        text_deal.setVisibility(View.VISIBLE);
        text_deal.setOnClickListener(this);

        clear_nickName = (ClearEditTextView) findViewById(R.id.clear_nickName);

        mProgressHUD = new ZProgressHUD(this);
        mProgressHUD.setMessage("保存中...");
        mProgressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
        editNameDialog = DialogUtil.getDoubleDialog(this, "您只能修改一次昵称", "取消", "修改");
        editNameDialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                editNameDialog.dismiss();
            }
        }, new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                editNameDialog.dismiss();
                saveNickname();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                finish();
                break;

            case R.id.text_deal:
                editNameDialog.show();
                break;
        }
    }

    private void saveNickname() {
         final String nickName = clear_nickName.getText().toString().trim();
        if (!CheckUtil.checkEmpty(nickName, "请输入昵称")) {
            return;
        }
        mProgressHUD.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", nickName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("token", Constants.getToken());
        map.put("cardType", "");
        map.put("taskId", "");
        map.put("Encryption", "");
        map.put("DataTypeCode", "EditUserName");
        map.put("content", jsonObject.toString());
        WebServiceUtils.callWebService(Constants.WEBSERVER_URL, Constants.WEBSERVER_CARDHOLDER, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                if (result != null) {
                    try {
                        JSONObject object = new JSONObject(result);
                        int resultCode = object.getInt("ResultCode");
                        String resultText = Utils.initNullStr(object.getString("ResultText"));
                        if (resultCode == 0) {
                            Constants.setUserName(nickName);
                            Intent intent = new Intent();
                            intent.setClass(SetNickNameActivity.this, PersonInfoActivity.class);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            mProgressHUD.dismiss();
                            Utils.myToast(mContext, resultText);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        mProgressHUD.dismiss();
                        Utils.myToast(mContext, "JSON解析出错");
                    }
                } else {
                    mProgressHUD.dismiss();
                    Utils.myToast(mContext, "获取数据错误，请稍后重试！");
                }
            }
        });
    }
}
