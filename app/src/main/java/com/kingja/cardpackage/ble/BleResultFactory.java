package com.kingja.cardpackage.ble;

/**
 * Description:TODO
 * Create Time:2017/11/28 13:32
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BleResultFactory {


    public static BleResult getBleResult(String result) {
        String orderCode = result.substring(2, 4);
        BleResult bleResult = null;
        switch (orderCode) {
            case "02":
                break;
            case "03":
                bleResult = new BleResult03(result);
                break;
            case "04":
                bleResult = new BleResult04(result);
                break;
            case "05":
                bleResult = new BleResult05(result);
                break;
            default:
                break;
        }
        return bleResult;
    }
}
