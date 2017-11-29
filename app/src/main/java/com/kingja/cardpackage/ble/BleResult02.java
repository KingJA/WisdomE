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
    public String getChargeStatus() {
        return HexUtil.encodeHexStr(Arrays.copyOfRange(data, 2, 3));
    }

    //当前充电电压(2)
    public double getCurrentChargeVoltage() {
        String currentChargeVoltage = BleUtil.hex2Dec(HexUtil.encodeHexStr(BleUtil.reverseByteArr(Arrays.copyOfRange
                (data, 3, 5))));
        return BleUtil.div(Double.valueOf(currentChargeVoltage), 100, 3);
    }

    //当前充电电流(2)
    public double getCurrentChargeelEctricity() {
        String currentChargeelEctricity = BleUtil.hex2Dec(HexUtil.encodeHexStr(BleUtil.reverseByteArr(Arrays.copyOfRange
                (data, 5, 7))));
        return BleUtil.div(Double.valueOf(currentChargeelEctricity), 100, 3);
    }

    //累计充电电量(2)
    public double getTotlePower() {
        String totlePower = BleUtil.hex2Dec(HexUtil.encodeHexStr(BleUtil.reverseByteArr(Arrays.copyOfRange
                (data, 7, 9))));
        return BleUtil.div(Double.valueOf(totlePower), 1000, 4);
    }

    //电池温度(2)
    public double getBatteryTemperature() {
        String batteryTemperature = BleUtil.hex2Dec(HexUtil.encodeHexStr(BleUtil.reverseByteArr(Arrays.copyOfRange
                (data, 9, 11))));
        return BleUtil.div(Double.valueOf(batteryTemperature), 100, 3);
    }

    //充电器温度(2)
    public double getChargerTemperature() {
        String chargerTemperature = BleUtil.hex2Dec(HexUtil.encodeHexStr(BleUtil.reverseByteArr(Arrays.copyOfRange
                (data, 11, 13))));
        return BleUtil.div(Double.valueOf(chargerTemperature), 100, 3);
    }

    //    当前电池电量(1,0-100)
    public String getCurrentPower() {
        return result.substring(26, 28);
    }

    //    充电时间(1,0.1小时)
    public String getChargeCost() {
        return result.substring(28, 30);
    }

    //剩余充电时间(1,0.1小时)
    public String getLeftChargeCost() {
        return result.substring(30, 32);
    }
}
