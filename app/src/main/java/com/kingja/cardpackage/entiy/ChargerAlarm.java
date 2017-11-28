package com.kingja.cardpackage.entiy;

/**
 * Description:TODO
 * Create Time:2017/11/24 15:33
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ChargerAlarm {

    /**
     * chargerid : 38CBF510-6EF2-49AB-A3CF-EB7782EF1BA7
     * warn_type : 1
     * warn_time : 2017-11-23 11:24:50
     * warn_msg : 预警信息
     */

    private String chargerid;
    private int warn_type;
    private String warn_time;
    private String warn_msg;

    public String getChargerid() {
        return chargerid;
    }

    public void setChargerid(String chargerid) {
        this.chargerid = chargerid;
    }

    public int getWarn_type() {
        return warn_type;
    }

    public void setWarn_type(int warn_type) {
        this.warn_type = warn_type;
    }

    public String getWarn_time() {
        return warn_time;
    }

    public void setWarn_time(String warn_time) {
        this.warn_time = warn_time;
    }

    public String getWarn_msg() {
        return warn_msg;
    }

    public void setWarn_msg(String warn_msg) {
        this.warn_msg = warn_msg;
    }
}
