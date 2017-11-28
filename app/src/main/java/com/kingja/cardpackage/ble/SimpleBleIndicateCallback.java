package com.kingja.cardpackage.ble;

import com.clj.fastble.callback.BleIndicateCallback;
import com.clj.fastble.exception.BleException;

/**
 * Description:TODO
 * Create Time:2017/11/28 14:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SimpleBleIndicateCallback extends BleIndicateCallback {
    @Override
    public void onIndicateSuccess() {

    }

    @Override
    public void onIndicateFailure(BleException exception) {

    }

    @Override
    public void onCharacteristicChanged(byte[] data) {

    }
}
