package com.kingja.cardpackage.activity;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.kingja.cardpackage.adapter.NfcDeviceAdapter;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.LoginInfo;
import com.kingja.cardpackage.entiy.NFCDevice_List;
import com.kingja.cardpackage.entiy.PhoneInfo;
import com.kingja.cardpackage.entiy.User_LogInForKaBao;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.ui.PullToBottomRecyclerView;
import com.kingja.cardpackage.util.AppInfoUtil;
import com.kingja.cardpackage.util.AppUtil;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.DialogUtil;
import com.kingja.cardpackage.util.FlashSir;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.PhoneUtil;
import com.kingja.cardpackage.util.ToastUtil;
import com.kingja.recyclerviewhelper.BaseRvAdaper;
import com.kingja.recyclerviewhelper.LayoutHelper;
import com.kingja.recyclerviewhelper.RecyclerViewHelper;
import com.tdr.wisdome.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kingja.cardpackage.util.FlashSir.MORSE_CHAR;

/**
 * Description:TODO
 * Create Time:2017/9/11 9:32
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NfcRoomActivity extends BackTitleActivity implements SwipeRefreshLayout.OnRefreshListener,
        NfcDeviceAdapter.OnOpenFlashlightListener {
    private LinearLayout mLlEmpty;
    private SwipeRefreshLayout mSrl;
    private PullToBottomRecyclerView mRv;
    private List<NFCDevice_List.ContentBean> nfcDevices = new ArrayList<>();
    private NfcDeviceAdapter mNfcDeviceAdapter;
    private NormalDialog nfcSendDialog;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mProgressDialog.dismiss();
            nfcSendDialog.show();
        }
    };
    private String morseCode;
    private ProgressDialog mProgressDialog;

    @Override
    protected void initVariables() {
        if (!hasFlash()) {
            ToastUtil.showToast("该设备不支持闪光灯");
            finish();
        }
        FlashSir.getInstance().createCamera(this, handler);
    }

    @Override
    protected void initContentView() {
        mLlEmpty = (LinearLayout) findViewById(R.id.ll_empty);
        mSrl = (SwipeRefreshLayout) findViewById(R.id.srl);
        mRv = (PullToBottomRecyclerView) findViewById(R.id.rv);

        mSrl.setColorSchemeResources(R.color.bg_black);
        mSrl.setProgressViewOffset(false, 0, AppUtil.dp2px(24));
        mSrl.setOnRefreshListener(this);
        mNfcDeviceAdapter = new NfcDeviceAdapter(this, nfcDevices);
        new RecyclerViewHelper.Builder(this)
                .setCallbackAdapter(mNfcDeviceAdapter)
                .setLayoutStyle(LayoutHelper.LayoutStyle.VERTICAL_LIST)
                .build()
                .attachToRecyclerView(mRv);

        mNfcDeviceAdapter.setOnItemClickListener(new BaseRvAdaper.OnItemClickListener<NFCDevice_List.ContentBean>() {
            @Override
            public void onItemClick(NFCDevice_List.ContentBean contentBean, int position) {

            }
        });
        mNfcDeviceAdapter.setOnOpenFlashlight(this);
        nfcSendDialog = DialogUtil.getDoubleDialog(this, "指定发送完毕", "重试", "关闭");

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("正在发送门禁指令，请稍等");
    }

    @Override
    protected int getBackContentView() {
        return R.layout.single_rv;
    }

    @Override
    protected void initNet() {
        cardLogin();
    }

    @Override
    protected void initData() {
        nfcSendDialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                if (!TextUtils.isEmpty(morseCode)) {
                    sendMorse(morseCode);
                }
            }
        }, new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                nfcSendDialog.dismiss();
            }
        });
    }

    @Override
    protected void setData() {
        setTitle("NFC门禁卡");
    }

    private void cardLogin() {
        setProgressDialog(true);
        LoginInfo mInfo = new LoginInfo();
        PhoneInfo phoneInfo = new PhoneUtil(this).getInfo();
        mInfo.setTaskID("1");
        mInfo.setREALNAME(DataManager.getRealName());
        mInfo.setIDENTITYCARD(DataManager.getIdCard());
        mInfo.setPHONENUM(DataManager.getUserPhone());
        mInfo.setSOFTVERSION(AppInfoUtil.getVersionName());
        mInfo.setSOFTTYPE(4);
        mInfo.setCARDTYPE(KConstants.CARD_TYPE_NFC);
        mInfo.setPHONEINFO(phoneInfo);
        mInfo.setSOFTVERSION(AppInfoUtil.getVersionName());
        mInfo.setTaskID("1");
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_NFC, KConstants.User_LogInForKaBao,
                        mInfo)
                .setBeanType(User_LogInForKaBao.class)
                .setCallBack(new WebServiceCallBack<User_LogInForKaBao>() {
                    @Override
                    public void onSuccess(User_LogInForKaBao bean) {
                        setProgressDialog(false);
                        doNet(0);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                        finish();
                        ToastUtil.showToast("卡包登录失败");
                    }
                }).build().execute();
    }

    private void doNet(final int index) {
        mSrl.setRefreshing(true);
        Map<String, Object> param = new HashMap<>();
        param.put("TaskID", "1");
        param.put("PageSize", 50);
        param.put("PageIndex", index);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_NFC, KConstants.NFCDevice_List, param)
                .setBeanType(NFCDevice_List.class)
                .setCallBack(new WebServiceCallBack<NFCDevice_List>() {
                    @Override
                    public void onSuccess(NFCDevice_List bean) {
                        mSrl.setRefreshing(false);
                        nfcDevices = bean.getContent();
                        mLlEmpty.setVisibility(nfcDevices.size() > 0 ? View.GONE : View.VISIBLE);
                        mNfcDeviceAdapter.setData(nfcDevices);
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        mSrl.setRefreshing(false);
                    }
                }).build().execute();
    }

    @Override
    public void onRefresh() {
        mSrl.setRefreshing(false);
    }

    private int getChatInt(String subCardIdHex, String devcieCodeHex) {
        Integer subCardIdInt = Integer.valueOf(subCardIdHex, 16);
        Integer cardCodeInt = Integer.valueOf(devcieCodeHex, 16);
        return (subCardIdInt + cardCodeInt) % 36;
    }

    public String getMorseCode(String cardIdHex, String deviceCodeHex) {
        String subTop = cardIdHex.substring(0, 4);
        String subMid = cardIdHex.substring(4, 10);
        String subEnd = cardIdHex.substring(10, 16);
        String firstLetter = MORSE_CHAR[getChatInt(subEnd, deviceCodeHex)];
        String secondLetter = MORSE_CHAR[getChatInt(subMid, deviceCodeHex)];
        String thirdLetter = MORSE_CHAR[getChatInt(subTop, deviceCodeHex)];
        return firstLetter + secondLetter + thirdLetter;
    }


    @Override
    public void onOpenFlashlight(String cardId, String deviceCode) {
        morseCode = getMorseCode(cardId, deviceCode);
        Log.e(TAG, "待发送字符: " + morseCode);
        morseCode = "{" + morseCode + "}";
        sendMorse(morseCode);
    }

    private void sendMorse(String morseCode) {
        mProgressDialog.show();
        FlashSir.getInstance().sendWordTimes(morseCode, 3, 600);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FlashSir.getInstance().closeCamera();
    }

    private boolean hasFlash() {
        return getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.camera.flash");
    }
}
