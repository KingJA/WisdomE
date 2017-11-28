package com.kingja.cardpackage.ble;

/**
 * Description:TODO
 * Create Time:2017/11/28 13:25
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BleResult {
    protected String result;

    public BleResult(String result) {
        this.result = result;
    }

    public String getOrderCode() {
        return result.substring(2, 4);
    }

    public abstract String getResponse();

}
