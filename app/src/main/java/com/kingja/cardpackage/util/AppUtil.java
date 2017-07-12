package com.kingja.cardpackage.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.util.DisplayMetrics;

import com.kingja.cardpackage.base.App;

import java.util.List;


/**
 * 项目名称：物联网城市防控(警用版)
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/3/28 11:13
 * 修改备注：
 */
public class AppUtil {
    public static Context getContext() {
        return App.getContext();
    }

    /**
     * dp转换为px
     *
     * @param dp
     * @return
     */
    public static int dp2px(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }

    /**
     * px转换为dp
     *
     * @param px
     * @return
     */
    public static int px2dp(int px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getScreenHeight() {
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     *判断当前应用程序处于前台还是后台
     */
    public static boolean isAppForeground() {
        android.app.ActivityManager am = (android.app.ActivityManager)getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (topActivity.getPackageName().equals(App.getContext().getPackageName())) {
                return true;
            }
        }
        return false;
    }
}
