package com.kingja.cardpackage.ble;

import com.kingja.cardpackage.util.BleConstants;
import com.kingja.cardpackage.util.Crc16Util;

/**
 * Description:TODO
 * Create Time:2017/11/28 13:29
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BleResult04 extends BleResult {
    public BleResult04(String result) {
        super(result);
    }

    @Override
    public String getResponse() {
        String responseContent = getOrderCode() +getSn()+ BleConstants.ZERO_15;
        String crc16Code = Crc16Util.getCrc16Code(responseContent);
        return BleConstants.FLAG + responseContent + crc16Code;
    }
    //SN(1,用于同步)
    public String getSn() {
        return result.substring(4, 6);
    }

}
