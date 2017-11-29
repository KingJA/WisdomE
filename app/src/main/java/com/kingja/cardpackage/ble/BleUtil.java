package com.kingja.cardpackage.ble;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Description:TODO
 * Create Time:2017/11/28 14:36
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BleUtil {

    public static String getBleName(String deviceId) {
        String deviceType = deviceId.substring(0, 4);
        String deviceNo = deviceId.substring(4);
        return "TDR-" + deviceType + "-" + deviceNo;
    }

    //20   171125112525
    public static String getHexTimeStr(String time) {
        time = time.substring(2);
        String year = dex2Hex(time.substring(0, 2));
        String month = dex2Hex(time.substring(2, 4));
        String day = dex2Hex(time.substring(4, 6));
        String hour = dex2Hex(time.substring(6, 8));
        String min = dex2Hex(time.substring(8, 10));
        String sec = dex2Hex(time.substring(10, 12));
        return year + month + day + hour + min + sec;
    }

    public static String hex2Dec(String hexStr) {
        return String.valueOf(Integer.parseInt(hexStr, 16));
    }

    public static String dex2Hex(String dexStr) {
        return Integer.toHexString(Integer.valueOf(dexStr));
    }

    public static String getCurrentHexTime() {
        return getHexTimeStr(getCurrentTime());
    }

    public static String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentTime = simpleDateFormat.format(new Date());
        System.out.println("yyyyMMddHHmmss " + currentTime);
        return currentTime;
    }

    public static byte[] reverseByteArr(byte[] byteArr) {
        byte[] newArr = new byte[byteArr.length];
        for (int i = 0; i < byteArr.length; i++) {
            newArr[i] = byteArr[byteArr.length - i - 1];
        }
        return newArr;
    }

    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or ");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

}
