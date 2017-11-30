package com.kingja.cardpackage.ble;

import android.util.Log;

import com.kingja.cardpackage.util.BleConstants;
import com.kingja.cardpackage.util.Crc16Util;

/**
 * Description:TODO
 * Create Time:2017/11/28 13:29
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BleResult82 extends BleResult {


    public BleResult82(String result) {
        super(result);
    }

    @Override
    public String getResponse() {
        String responseContent = getOrderCode() + BleConstants.ZERO_16;
        String crc16Code = Crc16Util.getCrc16Code(responseContent);
        return BleConstants.FLAG + responseContent + crc16Code;
    }

    //编号(1,0-9)+开时间(6)+关时间(6)(全0表示不设置)+有效性(1,1：一次有效，0：长期有效)
    public static String getContent(int sep, String startTime, String endTime, int repeatMode) {
        Log.e("峰谷设置", "sep: " + sep);
        Log.e("峰谷设置", "startTime: " + startTime);
        Log.e("峰谷设置", "endTime: " + endTime);
        Log.e("峰谷设置", "repeatMode: " + repeatMode);
        String responseContent = BleConstants.ORDER_82 + BleUtil.dex2Hex(String.valueOf(sep)) + BleUtil.getHexTimeStr
                (startTime) + BleUtil.getHexTimeStr(endTime) + BleUtil.dex2Hex(String.valueOf(repeatMode)) +
                BleConstants.ZERO_2;
        String crc16Code = Crc16Util.getCrc16Code(responseContent);
        return BleConstants.FLAG + responseContent + crc16Code;
    }

}
