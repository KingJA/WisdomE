package com.kingja.cardpackage.ble;

import com.clj.fastble.utils.HexUtil;

import java.util.Arrays;

/**
 * Description:TODO
 * Create Time:2017/11/28 13:25
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BleResult {

    protected String result;
    protected byte[] data;

    public BleResult() {
    }

    public BleResult(String result) {
        this.result = result;
    }

    public BleResult(byte[] data) {
        this.data = data;
    }

    public String getOrderCode() {
        if (result != null) {
            return result.substring(2, 4);
        } else {
            return HexUtil.encodeHexStr(Arrays.copyOfRange(data,2,3));
        }

    }

    public abstract String getResponse();

}
