package com.kingja.cardpackage.entiy;

/**
 * Description:TODO
 * Create Time:2018/3/9 10:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetChargerHeartPlusByChargerId {

    /**
     * ResultCode : 0
     * ResultText : 获取充电器最后心跳成功
     * DataTypeCode : GetChargerHeartPlusByChargerId
     * TaskID : 1
     * Content : {"ChargerId":"","Current_Current":"","Current_Voltage":"","Charger_Temperature":"",
     * "Battery_Temperature":"","Current_Electricity":"","Charge_Status":0,"PlateNumber":""}
     */

    private String ResultCode;
    private String ResultText;
    private String DataTypeCode;
    private String TaskID;
    private ContentBean Content;

    public String getResultCode() {
        return ResultCode;
    }

    public void setResultCode(String ResultCode) {
        this.ResultCode = ResultCode;
    }

    public String getResultText() {
        return ResultText;
    }

    public void setResultText(String ResultText) {
        this.ResultText = ResultText;
    }

    public String getDataTypeCode() {
        return DataTypeCode;
    }

    public void setDataTypeCode(String DataTypeCode) {
        this.DataTypeCode = DataTypeCode;
    }

    public String getTaskID() {
        return TaskID;
    }

    public void setTaskID(String TaskID) {
        this.TaskID = TaskID;
    }

    public ContentBean getContent() {
        return Content;
    }

    public void setContent(ContentBean Content) {
        this.Content = Content;
    }

    public static class ContentBean {
        /**
         * ChargerId :
         * Current_Current :
         * Current_Voltage :
         * Charger_Temperature :
         * Battery_Temperature :
         * Current_Electricity :
         * Charge_Status : 0
         * PlateNumber :
         */

        private String ChargerId;
        private String Current_Current;
        private String Current_Voltage;
        private String Charger_Temperature;
        private String Battery_Temperature;
        private String Current_Electricity;
        private int Charge_Status;
        private String PlateNumber;

        public String getChargerId() {
            return ChargerId;
        }

        public void setChargerId(String ChargerId) {
            this.ChargerId = ChargerId;
        }

        public String getCurrent_Current() {
            return Current_Current;
        }

        public void setCurrent_Current(String Current_Current) {
            this.Current_Current = Current_Current;
        }

        public String getCurrent_Voltage() {
            return Current_Voltage;
        }

        public void setCurrent_Voltage(String Current_Voltage) {
            this.Current_Voltage = Current_Voltage;
        }

        public String getCharger_Temperature() {
            return Charger_Temperature;
        }

        public void setCharger_Temperature(String Charger_Temperature) {
            this.Charger_Temperature = Charger_Temperature;
        }

        public String getBattery_Temperature() {
            return Battery_Temperature;
        }

        public void setBattery_Temperature(String Battery_Temperature) {
            this.Battery_Temperature = Battery_Temperature;
        }

        public String getCurrent_Electricity() {
            return Current_Electricity;
        }

        public void setCurrent_Electricity(String Current_Electricity) {
            this.Current_Electricity = Current_Electricity;
        }

        public int getCharge_Status() {
            return Charge_Status;
        }

        public void setCharge_Status(int Charge_Status) {
            this.Charge_Status = Charge_Status;
        }

        public String getPlateNumber() {
            return PlateNumber;
        }

        public void setPlateNumber(String PlateNumber) {
            this.PlateNumber = PlateNumber;
        }
    }
}
