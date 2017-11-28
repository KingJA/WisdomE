package com.kingja.cardpackage.entiy;

/**
 * Description:TODO
 * Create Time:2017/11/24 17:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ChargerRecord {

    /**
     * charger_starttime : 2017-11-23 13:00:50
     * charger_endtime : 2017-11-23 13:18:50
     * charger_end_reason : 0
     * recordtime : 2017-11-23 13:19:50
     * chargerid : 38CBF510-6EF2-49AB-A3CF-EB7782EF1BA7
     * voltage : 1
     * currents : 1
     * electricity_total : 1
     * ambient_temperature : 1
     * battery_temperature : 1
     * charger_temperature : 1
     */

    private String charger_starttime;
    private String charger_endtime;
    private int charger_end_reason;
    private String recordtime;
    private String chargerid;
    private int voltage;
    private int currents;
    private int electricity_total;
    private int ambient_temperature;
    private int battery_temperature;
    private int charger_temperature;

    public String getCharger_starttime() {
        return charger_starttime;
    }

    public void setCharger_starttime(String charger_starttime) {
        this.charger_starttime = charger_starttime;
    }

    public String getCharger_endtime() {
        return charger_endtime;
    }

    public void setCharger_endtime(String charger_endtime) {
        this.charger_endtime = charger_endtime;
    }

    public int getCharger_end_reason() {
        return charger_end_reason;
    }

    public void setCharger_end_reason(int charger_end_reason) {
        this.charger_end_reason = charger_end_reason;
    }

    public String getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(String recordtime) {
        this.recordtime = recordtime;
    }

    public String getChargerid() {
        return chargerid;
    }

    public void setChargerid(String chargerid) {
        this.chargerid = chargerid;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    public int getCurrents() {
        return currents;
    }

    public void setCurrents(int currents) {
        this.currents = currents;
    }

    public int getElectricity_total() {
        return electricity_total;
    }

    public void setElectricity_total(int electricity_total) {
        this.electricity_total = electricity_total;
    }

    public int getAmbient_temperature() {
        return ambient_temperature;
    }

    public void setAmbient_temperature(int ambient_temperature) {
        this.ambient_temperature = ambient_temperature;
    }

    public int getBattery_temperature() {
        return battery_temperature;
    }

    public void setBattery_temperature(int battery_temperature) {
        this.battery_temperature = battery_temperature;
    }

    public int getCharger_temperature() {
        return charger_temperature;
    }

    public void setCharger_temperature(int charger_temperature) {
        this.charger_temperature = charger_temperature;
    }
}
