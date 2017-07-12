package com.tdr.wisdome.actvitiy;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.cardpackage.Event.GetCarDataEvent;
import com.kingja.cardpackage.db.ECardXutils3;
import com.kingja.cardpackage.entiy.KJBikeCode;
import com.kingja.cardpackage.util.CheckUtil;
import com.kingja.ui.dialog.BaseListDialog;
import com.tdr.wisdome.R;
import com.tdr.wisdome.util.Constants;
import com.tdr.wisdome.util.TextCountUtil;
import com.tdr.wisdome.util.Utils;
import com.tdr.wisdome.util.WebServiceUtils;
import com.tdr.wisdome.view.ZProgressHUD;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * 车辆绑定
 * Created by Linus_Xie on 2016/8/19.
 */
public class CarBindingActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "CarBindingActivity";

    private Context mContext;
    private List<KJBikeCode> cardTypeList;
    private ZProgressHUD mProgressHUD;
    private BaseListDialog cardTypeDialog;
    private String cardType;
    private TextView tv_cardType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carbinding);
        cardTypeList = ECardXutils3.getInstance().selectAllWhere(KJBikeCode.class, "type", "6");
        Log.e(TAG, "证件类型: "+cardTypeList );

        mContext = this;

        initView();

        mProgressHUD = new ZProgressHUD(mContext);
        mProgressHUD.setMessage("车辆绑定中...");
        mProgressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
    }

    private ImageView image_back;
    private TextView text_title;
    private TextView text_deal;

    private EditText edit_carNum, edit_ownerIdentity, edit_phone, edit_carCode;
    private TextView text_code;

    private void initView() {
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        text_title = (TextView) findViewById(R.id.text_title);
        text_title.setText("车辆绑定");
        text_deal = (TextView) findViewById(R.id.text_deal);
        text_deal.setOnClickListener(this);
        text_deal.setText("完成");
        text_deal.setVisibility(View.VISIBLE);

        tv_cardType = (TextView) findViewById(R.id.tv_cardType);
        edit_carNum = (EditText) findViewById(R.id.edit_carNum);
        edit_ownerIdentity = (EditText) findViewById(R.id.edit_ownerIdentity);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        edit_carCode = (EditText) findViewById(R.id.edit_carCode);
        text_code = (TextView) findViewById(R.id.text_code);
        text_code.setOnClickListener(this);
        tv_cardType.setOnClickListener(this);

        cardTypeDialog = new BaseListDialog<KJBikeCode>(this, cardTypeList) {
            @Override
            protected void fillLvData(List<KJBikeCode> list, int position, TextView tv) {
                tv.setText(list.get(position).getNAME());
            }

            @Override
            protected void onItemSelect(KJBikeCode cardTypeBean) {
                tv_cardType.setText(cardTypeBean.getNAME());
                cardType = cardTypeBean.getCODE() + "";
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.tv_cardType:
                cardTypeDialog.show();
                break;

            case R.id.text_code:
                String phone = edit_phone.getText().toString().trim();
                if (!CheckUtil.checkPhoneFormat(phone)) {
                    break;
                }
                TextCountUtil textCountUtilt1 = new TextCountUtil(CarBindingActivity.this, 60000, 1000, text_code);
                textCountUtilt1.start();
                HashMap<String, String> map = new HashMap<>();
                JSONObject obj = new JSONObject();
                try {
                    obj.put("phone", phone);
                    obj.put("CodeType", "1");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                map.put("token", Constants.getToken());
                map.put("cardType", "1003");
                map.put("taskId", "");
                map.put("DataTypeCode", "SendCodeSms");
                map.put("content", obj.toString());
                WebServiceUtils.callWebService(Constants.WEBSERVER_URL, Constants.WEBSERVER_CARDHOLDER, map, new
                        WebServiceUtils.WebServiceCallBack() {
                            @Override
                            public void callBack(String result) {
                                if (result != null) {
                                    Log.e(TAG, result);
                                    try {
                                        JSONObject object = new JSONObject(result);
                                        int resultCode = object.getInt("ResultCode");
                                        String resultText = Utils.initNullStr(object.getString("ResultText"));
                                        if (resultCode == 0) {
                                            String content = object.getString("Content");
                                            JSONObject obj = new JSONObject(content);
                                            String VerificationCodeID = obj.getString("VerificationCodeID");
                                            String VerificationCode = obj.getString("VerificationCode");
                                            Utils.myToast(mContext, "获取验证码成功");
                                        } else {
                                            Utils.myToast(mContext, resultText);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Utils.myToast(mContext, "JSON解析出错");
                                    }

                                } else {
                                    Utils.myToast(mContext, "获取数据错误，请稍后重试！");
                                }
                            }
                        });
                break;

            case R.id.text_deal:
                String carNum = edit_carNum.getText().toString().trim().toUpperCase();
                if (carNum.equals("")) {
                    Utils.myToast(mContext, "请输入车牌号码");
                    break;
                }
                String ownerIdentity = edit_ownerIdentity.getText().toString().toUpperCase().trim();

                if (!CheckUtil.checkEmpty(cardType, "请选择证件类型")) {
                    break;
                }


                if (ownerIdentity.equals("")) {
                    Utils.myToast(mContext, "请输入车主证件号码");
                    break;
                }
                if ("1".equals(cardType)) {
                    if (!CheckUtil.checkIdCard(ownerIdentity, "身份证格式错误")) {
                        return;
                    }

                }


                String phone1 = edit_phone.getText().toString().trim();

                if (!CheckUtil.checkPhoneFormat(phone1)) {
                    break;
                }
                String code = edit_carCode.getText().toString().trim();
                if (code.equals("")) {
                    Utils.myToast(mContext, "请输入验证码");
                    break;
                }

                mProgressHUD.show();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("platenumber", carNum);
                    jsonObject.put("CardID", ownerIdentity);
                    jsonObject.put("Phone", phone1);
                    jsonObject.put("Code", code);
                    jsonObject.put("codetype", "1");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                HashMap<String, String> map1 = new HashMap<>();
                map1.put("token", Constants.getToken());
                map1.put("cardType", "1003");
                map1.put("taskId", "");
                map1.put("Encryption", "");
                map1.put("DataTypeCode", "Binding");
                map1.put("content", jsonObject.toString());
                WebServiceUtils.callWebService(Constants.WEBSERVER_URL, Constants.WEBSERVER_CARDHOLDER, map1, new
                        WebServiceUtils.WebServiceCallBack() {
                            @Override
                            public void callBack(String result) {
                                if (result != null) {
                                    Log.e(TAG, result);
                                    try {
                                        JSONObject object = new JSONObject(result);
                                        int resultCode = object.getInt("ResultCode");
                                        String resultText = Utils.initNullStr(object.getString("ResultText"));
                                        if (resultCode == 0) {
                                            mProgressHUD.dismiss();
                                            Utils.myToast(mContext, resultText);
                                            EventBus.getDefault().post(new GetCarDataEvent());
                                            finish();
                                        } else {
                                            mProgressHUD.dismiss();
                                            Utils.myToast(mContext, resultText);
                                        }
                                    } catch (JSONException e) {
                                        mProgressHUD.dismiss();
                                        e.printStackTrace();
                                        Utils.myToast(mContext, "JSON解析出错");
                                    }

                                } else {
                                    mProgressHUD.dismiss();
                                    Utils.myToast(mContext, "获取数据错误，请稍后重试！");
                                }
                            }
                        });
                break;
        }
    }

}
