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
public class BleResult04 extends BleResult {
    public BleResult04(byte[] data) {
        super(data);
    }

    public BleResult04(String result) {
        super(result);
    }

    //    SN(1,用于同步) +最大电压(2)+最大电流(2)+ 总电量(2)+环境温度(2)+电池最高温度(2)+充电器最高温度(2)
    @Override
    public String getResponse() {
        String responseContent = BleConstants.ORDER_04 + getSn() + BleConstants.ZERO_15;
        String crc16Code = Crc16Util.getCrc16Code(responseContent);
        return BleConstants.FLAG + responseContent + crc16Code;
    }

    //SN(1,用于同步)
    public String getSn() {
        return HexUtil.encodeHexStr(Arrays.copyOfRange(data, 2, 3));
    }

    //最大电压(2)
    public double getMaxVoltage() {
        String maxVoltage = BleUtil.hex2Dec(HexUtil.encodeHexStr(BleUtil.reverseByteArr(Arrays.copyOfRange
                (data, 3, 5))));
        return Double.valueOf(BleUtil.div(Double.valueOf(maxVoltage), 100, 2));
    }

    //最大电压(2)
    public double getMaxEctricity() {
        String maxEctricity = BleUtil.hex2Dec(HexUtil.encodeHexStr(BleUtil.reverseByteArr(Arrays.copyOfRange
                (data, 5, 7))));
        return Double.valueOf(BleUtil.div(Double.valueOf(maxEctricity), 100, 2));
    }

    //总电量(2)
    public double getTotlePower() {
        String totlePower = BleUtil.hex2Dec(HexUtil.encodeHexStr(BleUtil.reverseByteArr(Arrays.copyOfRange
                (data, 7, 9))));
        return Double.valueOf(BleUtil.div(Double.valueOf(totlePower), 1000, 4));
    }

    //环境温度(2)
    public double getEnvironmentTemperature() {
        String environmentTemperature = BleUtil.hex2Dec(HexUtil.encodeHexStr(BleUtil.reverseByteArr(Arrays.copyOfRange
                (data, 9, 11))));
        return Double.valueOf(BleUtil.div(Double.valueOf(environmentTemperature), 100, 2));
    }

    //电池最高温度(2)
    public double getMaxBatteryTemperature() {
        String maxBatteryTemperature = BleUtil.hex2Dec(HexUtil.encodeHexStr(BleUtil.reverseByteArr(Arrays.copyOfRange
                (data, 11, 13))));
        return Double.valueOf(BleUtil.div(Double.valueOf(maxBatteryTemperature), 100, 2));
    }

    //充电器最高温度(2)
    public double getMaxChargerTemperature() {
        String maxChargerTemperature = BleUtil.hex2Dec(HexUtil.encodeHexStr(BleUtil.reverseByteArr(Arrays.copyOfRange
                (data, 11, 13))));
        return Double.valueOf(BleUtil.div(Double.valueOf(maxChargerTemperature), 100, 2));
    }
}
