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
public class BleResult03 extends BleResult {
    public BleResult03(String result) {
        super(result);
    }

    @Override
    public String getResponse() {
        String responseContent = getOrderCode() + BleConstants.ZERO_17;
        String crc16Code = Crc16Util.getCrc16Code(responseContent);
        return BleConstants.FLAG + responseContent + crc16Code;
    }
//    SN(1,用于同步) + 起始时间(6)+结束时间(6)+结束原因(1,0正常充电结束，1过压结束，
//            2过流结束，3电池过温结束，4充电器过温结束，5断电结束)

    //SN(1,用于同步)
    public String getSn() {
        return null;
    }

    //起始时间(6)
    public String getStartTime() {
        return null;
    }

    //结束时间(6)
    public String getEndTime() {
        return null;
    }

    //结束原因(1,0正常充电结束，1过压结束，2过流结束，3电池过温结束，4充电器过温结束，5断电结束)
    public String getEndReason() {
        return null;
    }
}
