package com.kingja.cardpackage.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import com.kingja.cardpackage.activity.LoginActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 项目名称：
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/15 9:54
 * 修改备注：
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler mCrashHandler;
    public static final String LOG_DIR = "WLogs";
    public static final String LOG_FILENAME = "WisdomE.txt";
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Context context;
    private File mLogDir;

    private CrashHandler() {

    }

    public static CrashHandler getInstance() {
        if (mCrashHandler == null) {
            synchronized (CrashHandler.class) {
                if (mCrashHandler == null) {
                    mCrashHandler = new CrashHandler();
                }
            }
        }
        return mCrashHandler;
    }

    public void init(Context context) {
        this.context = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(final Thread thread, final Throwable ex) {
        Log.e("###CrashHandler###", "全局异常捕捉");
        ex.printStackTrace();
        savaToSdCard(ex);
        uploadToService(ex);
        ToastUtil.showToast("很抱歉，程序遭遇异常，即将重启");
        try {
            thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        skipToActivity();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    private void skipToActivity() {
        ActivityManager.getAppManager().finishAllActivity();//避免前台的其他APP被关闭
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    /**
     * 上传异常信息到服务器
     *
     * @param ex
     */
    private void uploadToService(Throwable ex) {

    }

    /**
     * 保存异常信息到本地
     *
     * @param ex
     */
    private void savaToSdCard(Throwable ex) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            mLogDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + LOG_DIR);
        } else {
            mLogDir = new File(context.getFilesDir().getAbsolutePath() + File.separator + LOG_DIR);
        }
        if (!mLogDir.exists()) {
            mLogDir.mkdirs();
        }
        File logFile = new File(mLogDir, LOG_FILENAME);
        PrintWriter pw;
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(logFile, true)));
            pw.println(getFormaTime());
            pw.println("===================");
            pw.println("Crash Thread:     " + Thread.currentThread().getName());
            pw.println("Phone Info:       " + android.os.Build.MODEL + ","
                    + android.os.Build.VERSION.SDK_INT + ","
                    + android.os.Build.VERSION.RELEASE + ","
                    + android.os.Build.CPU_ABI);
            pw.println(getVersionInfo(context));
            ex.printStackTrace(pw);
            pw.close();
        } catch (IOException e) {
            Log.e("CrashHandler", "save file failed...  ", e.getCause());

        }
    }


    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    private String getVersionInfo(Context context) {
        PackageInfo pi;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            return "Application Info: " + pi.packageName + ",VersionCode:" + pi.versionCode + ",VersionName:" + pi.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "版本号:未知\t版本号:未知";
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public String getFormaTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

}
