package com.kingja.cardpackage.ble;

import android.util.Log;

import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.utils.HexUtil;
import com.kingja.cardpackage.util.TempConstants;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
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
    private static DecimalFormat scale1 = new java.text.DecimalFormat("0.0");
    private static DecimalFormat scale2 = new java.text.DecimalFormat("0.00");
    private static DecimalFormat scale3 = new java.text.DecimalFormat("0.000");

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

    public static String getDecTime(String hexTime) {
        String year = String.format("%02d", Integer.parseInt(hexTime.substring(0, 2), 16));
        String month = String.format("%02d", Integer.parseInt(hexTime.substring(2, 4), 16));
        String day = String.format("%02d", Integer.parseInt(hexTime.substring(4, 6), 16));
        String hour = String.format("%02d", Integer.parseInt(hexTime.substring(6, 8), 16));
        String min = String.format("%02d", Integer.parseInt(hexTime.substring(8, 10), 16));
        String sec = String.format("%02d", Integer.parseInt(hexTime.substring(10, 12), 16));
        return "20" + year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;
    }

    public static String hex2Dec(String hexStr) {
        return String.valueOf(Integer.parseInt(hexStr, 16));
    }

    public static String dex2Hex(String dexStr) {
        String hex = Integer.toHexString(Integer.valueOf(dexStr));
        if (hex.length() < 2) {
            hex = "0" + hex;
        }
        return hex;
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

    public static String getNowTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = simpleDateFormat.format(new Date());
        return currentTime;
    }

    public static String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentTime = simpleDateFormat.format(new Date());
        System.out.println("yyyyMMdd " + currentTime);
        return currentTime;
    }

    public static byte[] reverseByteArr(byte[] byteArr) {
        byte[] newArr = new byte[byteArr.length];
        for (int i = 0; i < byteArr.length; i++) {
            newArr[i] = byteArr[byteArr.length - i - 1];
        }
        return newArr;
    }

    public static String div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or ");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        if (scale == 1) {
            return scale1.format(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue());
        } else if (scale == 2) {
            return scale2.format(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue());
        } else {
            return scale3.format(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
    }

    public static void sendBle(String hexStr, BleWriteCallback bleWriteCallback) {
        BleManager.getInstance().write(
                BleManager.getInstance().getAllConnectedDevice().get(0),
                TempConstants.BLE_SERVICE_UUID,
                TempConstants.BLE_OPERATE_UUID,
                HexUtil.hexStringToBytes(hexStr), bleWriteCallback);
    }

    public static Date str2Date(String str) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getCostTime(String startTime, String endTime) {
        long startMil = str2Date(startTime).getTime();
        long endMil = str2Date(endTime).getTime();
        long costMills = endMil - startMil;

        long hours = costMills / (1000 * 60 * 60);
        long mins = costMills % (1000 * 60 * 60) / (1000 * 60);
        return hours + "小时" + mins + "分钟";

    }

    public static boolean checkBle(byte[] bytes) {
        if (bytes.length != 20 || !"aa".equals(HexUtil.encodeHexStr(Arrays.copyOfRange(bytes, 0, 1)))) {
            return false;
        }
        return true;
    }

}
