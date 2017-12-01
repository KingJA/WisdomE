package com.kingja.cardpackage.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import com.clj.fastble.BleManager;
import com.kingja.cardpackage.callback.EmptyCallback;
import com.kingja.cardpackage.callback.ErrorCallback;
import com.kingja.cardpackage.callback.LoadingAboveCallback;
import com.kingja.cardpackage.callback.LoadingCallback;
import com.kingja.cardpackage.util.CrashHandler;
import com.kingja.loadsir.core.LoadSir;

import org.xutils.BuildConfig;
import org.xutils.x;

import cn.jpush.android.api.JPushInterface;

/**
 * Description：TODO
 * Create Time：2017/4/19 13:57
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class App extends Application {
    private static Context mAppContext;
    private static SharedPreferences mSharedPreferences;


    @Override
    public void onCreate() {
        super.onCreate();
//        CrashHandler.getInstance().init(this);
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
        JPushInterface.setDebugMode(true);// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);
        mAppContext = getApplicationContext();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(mAppContext);
        initXutils3();

        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingAboveCallback())
                .addCallback(new LoadingCallback())
                .commit();

        BleManager.getInstance().enableLog(false).init(this);
    }


    private void initXutils3() {
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }

    public static Context getContext() {
        return mAppContext;
    }

    public static SharedPreferences getSP() {
        return mSharedPreferences;
    }

}
