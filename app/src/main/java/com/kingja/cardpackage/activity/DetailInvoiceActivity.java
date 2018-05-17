package com.kingja.cardpackage.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.tdr.wisdome.R;

/**
 * Description:TODO
 * Create Time:2017/5/8 9:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DetailInvoiceActivity extends BackTitleActivity {

    private String url;
    private ProgressBar mProgress;
    private WebView mWebview;


    @Override
    protected void initVariables() {
        url = getIntent().getStringExtra("url");
    }

    @Override
    protected void initContentView() {
        mProgress = (ProgressBar) findViewById(R.id.progress);
        mWebview = (WebView) findViewById(R.id.webview);
    }

    @Override
    protected int getBackContentView() {
        return R.layout.single_wb;
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {
        WebSettings settings = mWebview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBlockNetworkImage(false);//解决图片加载不出来的问题
        settings.setAppCacheEnabled(false);
        settings.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgress.setProgress(newProgress);
                mProgress.setVisibility(newProgress == 100 ? View.GONE : View.VISIBLE);
            }
        });


        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Log.e(TAG, "shouldOverrideUrlLoading: "+url );
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
                Log.e(TAG, "onReceivedSslError: " );
                handler.proceed(); // 接受网站证书
            }
        });

        mWebview.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,
                                        long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        }

        );
        Log.e(TAG, "显示url: " + url);
        mWebview.loadUrl(url);
    }

    @Override
    protected void setData() {
        setTitle("电子发票");
    }

    public static void goActivity(Context context, String url) {
        Intent intent = new Intent(context, DetailInvoiceActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
}
