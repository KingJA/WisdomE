package com.kingja.cardpackage.util;

import android.text.TextUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 项目名称：物联网城市防控(警用版)
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/5/6 10:50
 * 修改备注：
 */
public class TimeUtil {

    public static boolean isOneDay(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date lastDate = format.parse(time);
            Date currentDate = new Date();
            long between = currentDate.getTime() - lastDate.getTime();
            if (between < (24 * 60 * 60 * 1000)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static final String getNowTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static String getFormatTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(new Date());
    }

    public static String getFormatDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(new Date());
    }

    public static boolean compareDate(String startDate, String endDate) {
        if (startDate.compareTo(endDate) > 0) {
            ToastUtil.showToast("结束日期不能小于起始日期");
            return false;
        }
        return true;
    }

    public static boolean compareTime(String startTime, String endTime, String tip) {
        if (startTime.compareTo(endTime) > 0) {
            ToastUtil.showToast(tip);
            return false;
        }
        return true;
    }

    public static boolean getMeetTime(String selectedTime) {
        Log.e("时间", "选择时间: " + selectedTime + " 明天时间: " + getLaterTime(1) + " 15天时间: " + getLaterTime(15));
        Log.e("时间", "超过明天: " + (selectedTime.compareTo(getLaterTime(1)) < 0) + "15天内: " + (selectedTime.compareTo
                (getLaterTime(15)) > 0));
        if (selectedTime.compareTo(getLaterTime(1)) < 0 || selectedTime.compareTo(getLaterTime(15)) > 0) {
            ToastUtil.showToast("选择日期必须在从明天开始15天内");
            return false;
        }
        return true;
    }

    public static String getLaterTime(int laster) {
        Calendar laterCalendar = Calendar.getInstance();
        laterCalendar.add(Calendar.DAY_OF_YEAR, laster);//获取1天后的日期

        int year = laterCalendar.get(Calendar.YEAR);
        int month = laterCalendar.get(Calendar.MONTH) + 1;
        int day = laterCalendar.get(Calendar.DAY_OF_MONTH);

        return String.format("%02d-%02d-%02d", year, month, day);
    }

    /**
     * Date=>时间字符串
     *
     * @param date
     * @param timeFormat yyyy-MM-dd  yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String Date2String(Date date, String timeFormat) {

        SimpleDateFormat df = new SimpleDateFormat(timeFormat);

        return df.format(date);
    }

    /**
     * 时间字符串=>Date
     *
     * @param strDate
     * @param timeFormat yyyy-MM-dd  yyyy-MM-dd HH:mm:ss
     * @return
     * @throws Exception
     */
    public static Date string2Date(String strDate, String timeFormat) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat(timeFormat);
        return df.parse(strDate);
    }

    public static long getDuringMills(String startTime, String endTime) {
        long duringMills = 0;
        try {
            long startMills = string2Date(startTime, "yyyy-MM-dd HH:mm:ss").getTime();
            long endMills = string2Date(endTime, "yyyy-MM-dd HH:mm:ss").getTime();
            duringMills = endMills - startMills;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return duringMills;
    }

    public static String getDuringTime(String startTime, String endTime) {
        long duringMills = getDuringMills(startTime, endTime);
        int hours = (int) (duringMills / 1000 / 60 / 60);
        int minites = (int) (duringMills / 1000 / 60);
        return (hours == 0 ? "" : hours + "小时") + minites + "分钟";
    }

    public static String get2015Date(long minutes) {
        String result = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long baseSeconds = formatter.parse("2015-01-01 00:00:00").getTime();
            Date newDate = new Date(baseSeconds + minutes * 60 * 1000);
            result = formatter.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String NORMAL_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static String MIN_SEN_FORMAT = "HH:mm";

    public static String getFormatDate(String time, String fromFormat, String toFormat) {
        SimpleDateFormat fFormat = new SimpleDateFormat(fromFormat);
        Date date = null;
        try {
            date = fFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat tFormat = new SimpleDateFormat(toFormat);
        return tFormat.format(date);

    }

    private static final String[] weeks = {"周一 ", "周二 ", "周三 ", "周四 ", "周五 ", "周六 ", "周日"};

    public static String getWeekString(String weekNumber) {
        if (weekNumber == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < weekNumber.length(); i++) {
            if ("1".equals(String.valueOf(weekNumber.charAt(i)))) {
                sb.append(weeks[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 0s-30s刚刚
     * 30s-60s 1分钟前
     * 60s-60m X分钟前
     * 60m-24h X小时前
     * 24h-48h 昨天
     * 48h-30d m月d日
     * 不是今年 显示y年m月d日
     * 2016-8-1 12:15:40
     */

    public static String getTimeTip(String time) {
        String result = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d hh:mm:ss");
        long postTime = 0;
        try {
            postTime = sdf.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long distanceTime = System.currentTimeMillis() - postTime;
        if (distanceTime < 30 * 1000) {
            result = "刚刚";
        } else if (distanceTime >= 30 * 1000 && distanceTime < 60 * 1000) {
            result = "1分钟前";
        } else if (distanceTime >= 60 * 1000 && distanceTime < 60 * 60 * 1000) {
            result = distanceTime / (60 * 1000) + "分钟前";
        } else if (distanceTime >= 60 * 60 * 1000 && distanceTime < 24 * 60 * 60 * 1000) {
            result = distanceTime / (60 * 60 * 1000) + "小时前";
        } else if (distanceTime >= 24 * 60 * 60 * 1000 && distanceTime < 48 * 60 * 60 * 1000) {
            result = "昨天";
        } else if (distanceTime >= 48 * 60 * 60 * 1000) {
            result = Date2String(new Date(postTime), "M月d日");
        }
        if (!Date2String(new Date(postTime), "yyyy").equals(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))
        )) {
            result = Date2String(new Date(postTime), "yyyy年M月d日");
        }
        return result;
    }

    /**
     *  24小时内显示具体时间，如  08：16
     24-48小时内显示昨天
     2-7天内显示具体星期，    如 星期一
     7天以上显示年月日， 如  2017-02-10
     */
}
