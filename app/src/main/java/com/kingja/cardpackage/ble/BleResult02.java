package com.kingja.cardpackage.ble;

import com.clj.fastble.utils.HexUtil;
import com.kingja.cardpackage.util.BleConstants;
import com.kingja.cardpackage.util.Crc16Util;

import java.util.Arrays;

/**
 * Description:TODO
 * Create Time:2017/11/28 13:29
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BleResult02 extends BleResult {
    public BleResult02(byte[] data) {
        super(data);
    }

    public BleResult02(String result) {
        super(result);
    }

    @Override
    public String getResponse() {
        String responseContent = getOrderCode() + BleUtil.getCurrentHexTime() + BleConstants.ZERO_10;
        String crc16Code = Crc16Util.getCrc16Code(responseContent);
        return BleConstants.FLAG + responseContent + crc16Code;
    }
//    充电状态（1,0:空闲,1:欠压充电，2：恒流充电，3：恒压充电，4：浮充充电）
//    当前充电电压(2)+当前充电电流(2)+累计充电电量(2)+电池温度(2)+充电器温度(2)+当前电池电量(1,0-100)+充电时间(1,0.1小时)+剩余充电时间(1,0.1小时)

    //充电状态（1,0:空闲,1:欠压充电，2：恒流充电，3：恒压充电，4：浮充充电）
    public int getChargeStatus() {
        return Integer.valueOf(BleUtil.hex2Dec(HexUtil.encodeHexStr(Arrays.copyOfRange(data, 2, 3)))) ;
    }

    //当前充电电压(2)
    public String getCurrentChargeVoltage() {
        String currentChargeVoltage = BleUtil.hex2Dec(HexUtil.encodeHexStr(BleUtil.reverseByteArr(Arrays.copyOfRange
                (data, 3, 5))));
        return BleUtil.div(Double.valueOf(currentChargeVoltage), 100, 2) + BleConstants.UNIT_VOLTAGE;
    }

    //当前充电电流(2)
    public String getCurrentChargeelEctricity() {
        String currentChargeelEctricity = BleUtil.hex2Dec(HexUtil.encodeHexStr(BleUtil.reverseByteArr(Arrays.copyOfRange
                (data, 5, 7))));
        return BleUtil.div(Double.valueOf(currentChargeelEctricity), 100, 2) + BleConstants.UNIT_ECTRICITY;
    }

    //累计充电电量(2)
    public String getTotlePower() {
        String totlePower = BleUtil.hex2Dec(HexUtil.encodeHexStr(BleUtil.reverseByteArr(Arrays.copyOfRange
                (data, 7, 9))));
        return BleUtil.div(Double.valueOf(totlePower), 1000, 4) + BleConstants.UNIT_POWER;
    }

    //电池温度(2)
    public String getBatteryTemperature() {
        String batteryTemperature = BleUtil.hex2Dec(HexUtil.encodeHexStr(BleUtil.reverseByteArr(Arrays.copyOfRange
                (data, 9, 11))));
        return BleUtil.div(Double.valueOf(batteryTemperature), 100, 2) + BleConstants.UNIT_TEMPERATURE;
    }

    //充电器温度(2)
    public String getChargerTemperature() {
        String chargerTemperature = BleUtil.hex2Dec(HexUtil.encodeHexStr(BleUtil.reverseByteArr(Arrays.copyOfRange
                (data, 11, 13))));
        return BleUtil.div(Double.valueOf(chargerTemperature), 100, 2) + BleConstants.UNIT_TEMPERATURE;
    }

    //当前电池电量(1,0-100)
    public String getCurrentPower() {
        String chargerCurrentPower = BleUtil.hex2Dec(HexUtil.encodeHexStr(BleUtil.reverseByteArr(Arrays.copyOfRange
                (data, 13, 14))));
        return chargerCurrentPower + BleConstants.UNIT_POWER;
    }

    //充电时间(1,0.1小时)
    public String getChargeCost() {
        String chargerCost = BleUtil.hex2Dec(HexUtil.encodeHexStr(Arrays.copyOfRange(data, 14, 15)));
        return BleUtil.div(Double.valueOf(chargerCost), 100, 1) + BleConstants.UNIT_HOUR;
    }

    //剩余充电时间(1,0.1小时)
    public String getLeftChargeCost() {
        String leftChargeCost = BleUtil.hex2Dec(HexUtil.encodeHexStr(Arrays.copyOfRange(data, 15, 16)));
        return BleUtil.div(Double.valueOf(leftChargeCost), 100, 1) + BleConstants.UNIT_HOUR;
    }
}
