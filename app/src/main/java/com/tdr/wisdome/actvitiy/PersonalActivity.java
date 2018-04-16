package com.tdr.wisdome.actvitiy;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.kingja.cardpackage.activity.BackTitleActivity;
import com.kingja.cardpackage.activity.LoginReActivity;
import com.kingja.cardpackage.activity.PerfectInfoActivity;
import com.kingja.cardpackage.util.ActivityManager;
import com.kingja.cardpackage.util.AppInfoUtil;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.DialogUtil;
import com.kingja.cardpackage.util.GoUtil;
import com.tdr.wisdome.R;
import com.tdr.wisdome.util.Constants;
import com.tdr.wisdome.util.Utils;
import com.tdr.wisdome.util.WebServiceUtils;

import java.util.HashMap;

import lib.king.kupdate.strategy.WebServiceStrategy;

/**
 * Description：个人中心
 * Create Time：2017/1/17 14:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PersonalActivity extends BackTitleActivity {
    private NormalDialog quitAccountDialog;
    private NormalDialog perfectDialog;
    private RelativeLayout mRlUserInfo;
    private ImageView mIvUserImg;
    private TextView mTvUserName;
    private ImageView mIvState;
    private TextView mTvLocaltion;
    private TextView mTextCoinNum;
    private RelativeLayout mRlCoinStore;
    private RelativeLayout mRlPerfectInfo;
    private RelativeLayout mRlRealName;
    private TextView mTvRealName;
    private RelativeLayout mRlMyCard;
    private Button mBtnQuit;
    private TextView mTvVersion;
    private ImageView mIvNewVersion;
    private RelativeLayout mRlVersion;


    @Override
    protected void initVariables() {

    }

    @Override
    protected void initContentView() {
        mIvUserImg = (ImageView) findViewById(R.id.iv_user_img);
        mTvUserName = (TextView) findViewById(R.id.tv_userName);
        mIvState = (ImageView) findViewById(R.id.iv_state);
        mIvNewVersion = (ImageView) findViewById(R.id.iv_newVersion);
        mTvLocaltion = (TextView) findViewById(R.id.tv_localtion);
        mTextCoinNum = (TextView) findViewById(R.id.tv_coinNum);
        mTvRealName = (TextView) findViewById(R.id.tv_realName);
        mTvVersion = (TextView) findViewById(R.id.tv_version);

        mRlVersion = (RelativeLayout) findViewById(R.id.rl_version);
        mRlUserInfo = (RelativeLayout) findViewById(R.id.rl_userInfo);
        mRlMyCard = (RelativeLayout) findViewById(R.id.rl_myCard);
        mRlCoinStore = (RelativeLayout) findViewById(R.id.rl_coinStore);
        mRlPerfectInfo = (RelativeLayout) findViewById(R.id.rl_perfectInfo);
        mRlRealName = (RelativeLayout) findViewById(R.id.rl_realName);
        mBtnQuit = (Button) findViewById(R.id.btn_quit);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_personal;
    }

    @Override
    protected void initNet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int serverVersion = new WebServiceStrategy().getVersionCode(lib.king.kupdate.Constants.APK_NAME);
                Log.e(TAG, "serverVersion: "+serverVersion+  " currentVersion: "+AppInfoUtil.getVersionCode());
                if (serverVersion > AppInfoUtil.getVersionCode()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mIvNewVersion.setVisibility(View.VISIBLE);
                            mRlVersion.setOnClickListener(PersonalActivity.this);
                        }
                    });
                }
            }
        }).start();

    }

    @Override
    protected void initData() {
        mRlUserInfo.setOnClickListener(this);
        mRlMyCard.setOnClickListener(this);
        mRlPerfectInfo.setOnClickListener(this);
        mRlRealName.setOnClickListener(this);
        mBtnQuit.setOnClickListener(this);
    }

    @Override
    protected void setData() {
        setTitle("个人中心");
        mTvVersion.setText(AppInfoUtil.getVersionName());

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rl_userInfo:
                GoUtil.goActivity(this, PersonInfoActivity.class);
                break;
            case R.id.rl_coinStore:
                break;
            case R.id.rl_perfectInfo:
                GoUtil.goActivity(this, PerfectInfoActivity.class);
                break;
            case R.id.rl_realName:
                GoUtil.goActivity(this, RealNameActivity.class);
                break;
            case R.id.rl_myCard:
                GoUtil.goActivity(this, CardManagerActivity.class);
                break;
            case R.id.rl_version:
//                ToastUtil.showToast("点击升级");
                break;
            case R.id.btn_quit:
                showQuitDialog();
                break;
        }
    }

    private void showPerfectDialog() {
        perfectDialog = DialogUtil.getDoubleDialog(this, "资料不完整,现在就去完善?", "取消", "确定");
        perfectDialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                perfectDialog.dismiss();
            }
        }, new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                perfectDialog.dismiss();
                GoUtil.goActivity(PersonalActivity.this, PerfectInfoActivity.class);

            }
        });
        perfectDialog.show();
    }

    protected void showQuitDialog() {
        quitAccountDialog = DialogUtil.getDoubleDialog(this, "是否退出当前账户", "取消", "确定");
        quitAccountDialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                quitAccountDialog.dismiss();
            }
        }, new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                quitAccountDialog.dismiss();
                loginOut();
            }
        });
        quitAccountDialog.show();
    }


    private void loginOut() {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", Constants.getToken());
        map.put("cardType", "");
        map.put("taskId", "");
        map.put("DataTypeCode", "LoginOut");
        map.put("content", "");

        WebServiceUtils.callWebService(Constants.WEBSERVER_URL, Constants.WEBSERVER_CARDHOLDER, map, new WebServiceUtils.WebServiceCallBack() {
            @Override
            public void callBack(String result) {
                Constants.setToken("");
                Constants.setRealName("");
                Constants.setUserIdentitycard("");
                Constants.setUserName("");
//                KConstants.setUserPhone("");
                Constants.setCardCode("");
                ActivityManager.getAppManager().finishAllActivity();
                GoUtil.goActivity(PersonalActivity.this, LoginReActivity.class);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(DataManager.getIdCard())) {
            //弹出对话框，完善资料
            showPerfectDialog();
        }
        mTvUserName.setText(Constants.getUserName().equals("") ? Constants.getUserPhone() : Constants.getUserName());
        mTvLocaltion.setText(DataManager.getCityName());
        mIvUserImg.setImageBitmap(Utils.stringtoBitmap(Constants.getFaceBase()));
        if (Constants.getCertification().equals("0")) {//未认证
            mIvState.setImageResource(R.mipmap.image_unauthorized);
        } else if (Constants.getCertification().equals("1")) {
            mIvState.setImageResource(R.mipmap.image_authorized);
            mTvRealName.setText("实名认证(已认证)");
            mRlRealName.setClickable(false);
        } else if (Constants.getCertification().equals("2")) {
            mIvState.setImageResource(R.mipmap.image_authorizeding);
        }
    }
}
