package com.kingja.cardpackage.activity;

import android.os.Handler;

import com.kingja.cardpackage.base.BaseActivity;
import com.kingja.cardpackage.db.DatebaseManager;
import com.kingja.cardpackage.net.PoolManager;
import com.kingja.cardpackage.util.GoUtil;
import com.tdr.wisdome.R;

/**
 * Description：TODO
 * Create Time：2016/10/21 9:32
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SplashActivity extends BaseActivity {
    private final long DELAYED_MILLS = 2000;
    private Handler handler=new Handler();
    @Override
    protected void initVariables() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setData() {
        copyDb();
        handler.postDelayed(skipRunnable,DELAYED_MILLS);
    }

    private void copyDb() {
        PoolManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                DatebaseManager.getInstance(getApplicationContext()).copyDataBase("citypolice_wz.db");
            }
        });
    }

    private Runnable skipRunnable = new Runnable() {
        @Override
        public void run() {
            GoUtil.goActivityAndFinish(SplashActivity.this, LoginActivity.class);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(skipRunnable);
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.bg_blue;
    }
}
