package com.kingja.cardpackage.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

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
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.PhoneUtil;
import com.kingja.cardpackage.util.ToastUtil;
import com.kingja.recyclerviewhelper.BaseRvAdaper;
import com.kingja.recyclerviewhelper.LayoutHelper;
import com.kingja.recyclerviewhelper.RecyclerViewHelper;
import com.tdr.wisdome.R;
import com.tdr.wisdome.actvitiy.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            nfcSendDialog.show();
        }
    };

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initContentView() {
        mLlEmpty = (LinearLayout) findViewById(R.id.ll_empty);
        mSrl = (SwipeRefreshLayout) findViewById(R.id.srl);
        mRv = (PullToBottomRecyclerView) findViewById(R.id.rv);

        mSrl.setColorSchemeResources(R.color.bg_black);
        mSrl.setProgressViewOffset(false, 0, AppUtil.dp2px(24));
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

            }
        }, new OnBtnClickL() {
            @Override
            public void onBtnClick() {

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
        mInfo.setCARDTYPE(KConstants.CARD_TYPE_SHOP);
        mInfo.setPHONEINFO(phoneInfo);
        mInfo.setSOFTVERSION(AppInfoUtil.getVersionName());
        mInfo.setTaskID("1");
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_SHOP, KConstants.User_LogInForKaBao,
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
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_SHOP, KConstants.NFCDevice_List, param)
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
        mSrl.setOnRefreshListener(this);
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

    public static final String[] MORSE_CHAR = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B",
            "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};

    @Override
    public void onOpenFlashlight(String cardId, String deviceCode) {
        String morseCode = getMorseCode(cardId, deviceCode);
        //TODO send morse code
        setProgressDialog(true, "正在发送门禁指令，请稍等");
    }
}
