package com.kingja.cardpackage.ble;

import com.kingja.cardpackage.util.BleConstants;
import com.kingja.cardpackage.util.Crc16Util;

/**
 * Description:TODO
 * Create Time:2017/11/28 13:29
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BleResult83 extends BleResult {

    public BleResult83(String result) {
        super(result);
    }

    @Override
    public String getResponse() {
        String responseContent = getOrderCode() + BleConstants.ZERO_16;
        String crc16Code = Crc16Util.getCrc16Code(responseContent);
        return BleConstants.FLAG + responseContent + crc16Code;
    }

    public static String getContent(int sep) {
        String responseContent = BleConstants.ORDER_83 + BleUtil.dex2Hex(String.valueOf(sep)) + BleConstants.ZERO_15;
        String crc16Code = Crc16Util.getCrc16Code(responseContent);
        return BleConstants.FLAG + responseContent + crc16Code;
    }

}
