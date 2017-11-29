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
        BleResult bleResult = new BleResult03(result);
        switch (orderCode) {
            case "02":
                bleResult = new BleResult02(result);
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
            case "80":
                bleResult = new BleResult80(result);
                break;
            case "81":
                bleResult = new BleResult81(result);
                break;
            case "82":
                bleResult = new BleResult82(result);
                break;
            case "83":
                bleResult = new BleResult83(result);
                break;
            case "84":
                bleResult = new BleResult84(result);
                break;
            default:
                break;
        }
        return bleResult;
    }
}
