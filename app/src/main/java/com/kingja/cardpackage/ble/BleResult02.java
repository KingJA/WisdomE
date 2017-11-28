package com.kingja.cardpackage.ble;

import com.kingja.cardpackage.util.BleConstants;
import com.kingja.cardpackage.util.Crc16Util;

/**
 * Description:TODO
 * Create Time:2017/11/28 13:29
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BleResult02 extends BleResult {
    public BleResult02(String result) {
        super(result);
    }

    @Override
    public String getResponse() {
        String responseContent = getOrderCode() + BleConstants.ZERO_17;
        String crc16Code = Crc16Util.getCrc16Code(responseContent);
        return BleConstants.FLAG + responseContent + crc16Code;
    }
//    充电状态（1,0:空闲,1:欠压充电，2：恒流充电，3：恒压充电，4：浮充充电）
//    当前充电电压(2)+当前充电电流(2)+累计充电电量(2)+电池温度(2)+充电器温度(2)+当前电池电量(1,0-100)+充电时间(1,0.1小时)+剩余充电时间(1,0.1小时)

    //充电状态（1,0:空闲,1:欠压充电，2：恒流充电，3：恒压充电，4：浮充充电）
    public String getChargeStatus() {
        return result.substring(4, 6);
    }

    //当前充电电压(2)
    public String getCurrentChargeVoltage() {
        return result.substring(6, 10);
    }

    //当前充电电流(2)
    public String getCurrentChargeelEctricity() {
        return result.substring(10, 14);
    }

    //累计充电电量(2)
    public String getTotlePower() {
        return result.substring(14, 18);
    }

    //电池温度(2)
    public String getBatteryTemperature() {
        return null;
    }

    //充电器温度(2)
    public String getChargerTemperature() {
        return null;
    }

    //    当前电池电量(1,0-100)
    public String getCurrentPower() {
        return null;
    }

    //    充电时间(1,0.1小时)
    public String getChargeCost() {
        return null;
    }

    //剩余充电时间(1,0.1小时)
    public String getLeftChargeCost() {
        return null;
    }
}
