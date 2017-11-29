package com.kingja.cardpackage.ble;

import com.kingja.cardpackage.util.BleConstants;
import com.kingja.cardpackage.util.Crc16Util;

/**
 * Description:TODO
 * Create Time:2017/11/28 13:29
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BleResult81 extends BleResult {

    public BleResult81(String result) {
        super(result);
    }

    @Override
    public String getResponse() {
        String responseContent = getOrderCode() + BleConstants.ZERO_16;
        String crc16Code = Crc16Util.getCrc16Code(responseContent);
        return BleConstants.FLAG + responseContent + crc16Code;
    }

    //设备类型(2)+设备ID(4)+设备时间(6)
    public static String getContent() {
        String responseContent = BleConstants.ORDER_81 + BleUtil.getCurrentHexTime() + BleConstants.ZERO_10;
        String crc16Code = Crc16Util.getCrc16Code(responseContent);
        return BleConstants.FLAG + responseContent + crc16Code;
    }

    //设备类型(2)
    public String getDeviceType() {
        return result.substring(4, 8);
    }

    //设备ID(4)
    public String getDeviceId() {
        return result.substring(8, 16);
    }

    //设备时间(6)
    public String getDeviceTime() {
        return result.substring(16, 28);
    }


}
