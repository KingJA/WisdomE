package com.kingja.cardpackage.greendaobean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;


/**
 * Description：TODO
 * Create Time：2017/1/25 13:50
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

//03 SN(1,用于同步) + 起始时间(6)+结束时间(6)+结束原因(1)
//04 SN(1,用于同步) +最大电压(2)+最大电流(2)+ 总电量(2)+环境温度(2)+电池最高温度(2)+充电器最高温度(2)
@Entity
public class ChargeRecord {
    /*03*/
    @Id
    private String sn;
    private String startTime;
    private String endTime;
    private int endReason;
    /*04*/
    private double maxVoltage;
    private double maxElectricity;
    private double totlePower;
    private double environmentTemperature;
    private double maxBatteryTemperature;
    private double maxChargerTemperature;
    @Generated(hash = 717337929)
    public ChargeRecord(String sn, String startTime, String endTime, int endReason,
            double maxVoltage, double maxElectricity, double totlePower,
            double environmentTemperature, double maxBatteryTemperature,
            double maxChargerTemperature) {
        this.sn = sn;
        this.startTime = startTime;
        this.endTime = endTime;
        this.endReason = endReason;
        this.maxVoltage = maxVoltage;
        this.maxElectricity = maxElectricity;
        this.totlePower = totlePower;
        this.environmentTemperature = environmentTemperature;
        this.maxBatteryTemperature = maxBatteryTemperature;
        this.maxChargerTemperature = maxChargerTemperature;
    }
    @Generated(hash = 1910593479)
    public ChargeRecord() {
    }
    public String getSn() {
        return this.sn;
    }
    public void setSn(String sn) {
        this.sn = sn;
    }
    public String getStartTime() {
        return this.startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return this.endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public int getEndReason() {
        return this.endReason;
    }
    public void setEndReason(int endReason) {
        this.endReason = endReason;
    }
    public double getMaxVoltage() {
        return this.maxVoltage;
    }
    public void setMaxVoltage(double maxVoltage) {
        this.maxVoltage = maxVoltage;
    }
    public double getMaxElectricity() {
        return this.maxElectricity;
    }
    public void setMaxElectricity(double maxElectricity) {
        this.maxElectricity = maxElectricity;
    }
    public double getTotlePower() {
        return this.totlePower;
    }
    public void setTotlePower(double totlePower) {
        this.totlePower = totlePower;
    }
    public double getEnvironmentTemperature() {
        return this.environmentTemperature;
    }
    public void setEnvironmentTemperature(double environmentTemperature) {
        this.environmentTemperature = environmentTemperature;
    }
    public double getMaxBatteryTemperature() {
        return this.maxBatteryTemperature;
    }
    public void setMaxBatteryTemperature(double maxBatteryTemperature) {
        this.maxBatteryTemperature = maxBatteryTemperature;
    }
    public double getMaxChargerTemperature() {
        return this.maxChargerTemperature;
    }
    public void setMaxChargerTemperature(double maxChargerTemperature) {
        this.maxChargerTemperature = maxChargerTemperature;
    }


}
