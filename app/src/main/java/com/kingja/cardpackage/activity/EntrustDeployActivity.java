package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kingja.cardpackage.Event.CancleEntrustDeployEvent;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.SetEntrustDeploy;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.NoDoubleClickListener;
import com.kingja.cardpackage.util.ToastUtil;
import com.tdr.wisdome.R;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:TODO
 * Create Time:2017/4/18 11:12
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class EntrustDeployActivity extends BackTitleActivity {
    private WebView mWbEntrustDeploy;
    private AppCompatCheckBox mCbEntrustDeploy;
    private TextView mTvEntrustDeploy;
    private String url;
    private ProgressBar mProgressBar;


    @Override
    protected void initVariables() {
        url = getIntent().getStringExtra("url");
    }

    @Override
    protected void initContentView() {
        mWbEntrustDeploy = (WebView) findViewById(R.id.wb_entrust_deploy);
        mCbEntrustDeploy = (AppCompatCheckBox) findViewById(R.id.cb_entrust_deploy);
        mTvEntrustDeploy = (TextView) findViewById(R.id.tv_entrust_deploy);
        mProgressBar = (ProgressBar) findViewById(R.id.pb);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.activity_entrust_deploy;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        mTvEntrustDeploy.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (mCbEntrustDeploy.isChecked()) {
                    setEntrustDeploy();
                } else {
                    ToastUtil.showToast("请仔细阅读委托布防协议并勾选");
                }
            }
        });
        WebSettings settings = mWbEntrustDeploy.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(false);
        mWbEntrustDeploy.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWbEntrustDeploy.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgressBar.setProgress(newProgress);
                mProgressBar.setVisibility(newProgress == 100 ? View.GONE : View.VISIBLE);
            }
        });
        mWbEntrustDeploy.loadUrl(url);
    }

    private void setEntrustDeploy() {
        setProgressDialog(true);
        Map<String, Object> param = new HashMap<>();
        param.put("IsDeploy", 1);
        new ThreadPoolTask.Builder()
                .setGeneralParam(DataManager.getToken(), KConstants.CARD_TYPE_CAR, KConstants.SetEntrustDeploy, param)
                .setBeanType(SetEntrustDeploy.class)
                .setCallBack(new WebServiceCallBack<SetEntrustDeploy>() {
                    @Override
                    public void onSuccess(SetEntrustDeploy bean) {
                        setProgressDialog(false);
                        ToastUtil.showToast("委托布防设置成功");
                        finish();
                    }

                    @Override
                    public void onErrorResult(ErrorResult errorResult) {
                        setProgressDialog(false);
                    }
                }).build().execute();
    }

    @Override
    protected void setData() {
        setTitle("委托布防");
    }

    @Override
    protected void onClickBack() {
        EventBus.getDefault().post(new CancleEntrustDeployEvent());
        super.onClickBack();
    }

    @Override
    public void onBackPressed() {
        EventBus.getDefault().post(new CancleEntrustDeployEvent());
        super.onBackPressed();
    }

    public static void goActivity(Context context, String url) {
        Intent intent = new Intent(context, EntrustDeployActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
}
