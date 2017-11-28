package com.kingja.cardpackage.ble;

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
}
