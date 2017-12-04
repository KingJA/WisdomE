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
public class BleResult05 extends BleResult {
    private final String[] errorMsgs = {"过压", "过流", "电池过温", "充电器过温", "短路", "反接"};

    public BleResult05(String result) {
        super(result);
    }

    @Override
    public String getResponse() {
        String responseContent = getOrderCode() + getSn() + BleConstants.ZERO_15;
        String crc16Code = Crc16Util.getCrc16Code(responseContent);
        return BleConstants.FLAG + responseContent + crc16Code;
    }
//    SN(1,用于同步)+异常时间(6)+异常原因(1，1代表异常，0正常，
//            bit0：过压，bit1:过流，bit2:电池过温，bit3:充电器过温,bit4:短路,bit5:反接)


    //SN(1,用于同步)
    public String getSn() {
        return  result.substring(4, 6);
    }

    //异常时间(6)
    public String getErrorTime() {
        String year = String.format("%02d", Integer.parseInt(result.substring(6, 8), 16));
        String month = String.format("%02d", Integer.parseInt(result.substring(8, 10), 16));
        String day = String.format("%02d", Integer.parseInt(result.substring(10, 12), 16));
        String hour = String.format("%02d", Integer.parseInt(result.substring(12, 14), 16));
        String min = String.format("%02d", Integer.parseInt(result.substring(14, 16), 16));
        String sec = String.format("%02d", Integer.parseInt(result.substring(16, 18), 16));
        return "20" + year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;
    }


    //异常原因(1，1代表异常，0正常，bit0：过压，bit1:过流，bit2:电池过温，bit3:充电器过温,bit4:短路,bit5:反接)
    public String getErrorMsg() {
        String binStr = new StringBuffer(hexString2binaryString(result.substring(18, 20)).substring(2)).reverse()
                .toString();
        Log.e("异常二进制", "异常二进制: " + binStr);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < binStr.length(); i++) {
            if ("1".equals(String.valueOf(binStr.charAt(i)))) {
                if (i == binStr.length()) {
                    sb.append(errorMsgs[i]);
                } else {
                    sb.append(errorMsgs[i] + ",");
                }
            }
        }
        if (sb.toString().endsWith(",")) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public int getErrorType() {
        return Integer.parseInt(result.substring(18, 20), 16);
    }

    public static String hexString2binaryString(String hexString) {
        if (hexString == null || hexString.length() % 2 != 0) {
            return null;
        }
        String bString = "", tmp;
        for (int i = 0; i < hexString.length(); i++) {
            tmp = "0000"
                    + Integer.toBinaryString(Integer.parseInt(hexString
                    .substring(i, i + 1), 16));
            bString += tmp.substring(tmp.length() - 4);
        }
        return bString;
    }

}
