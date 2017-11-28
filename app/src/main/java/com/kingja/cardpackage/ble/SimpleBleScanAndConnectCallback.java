package com.kingja.cardpackage.ble;

import android.bluetooth.BluetoothGatt;

import com.clj.fastble.callback.BleScanAndConnectCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;

/**
 * Description:TODO
 * Create Time:2017/11/28 14:27
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SimpleBleScanAndConnectCallback extends BleScanAndConnectCallback {
    @Override
    public void onScanStarted(boolean success) {

    }

    @Override
    public void onScanFinished(BleDevice scanResult) {

    }

    @Override
    public void onStartConnect() {

    }

    @Override
    public void onConnectFail(BleException exception) {

    }

    @Override
    public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt gatt, int status) {

    }

    @Override
    public void onDisConnected(boolean isActiveDisConnected, BleDevice device, BluetoothGatt gatt, int status) {

    }
}
