package com.kingja.cardpackage.entiy;

/**
 * Description:TODO
 * Create Time:2017/11/25 14:09
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetChargerStatistics {


    /**
     * ResultCode : 0
     * ResultText : 获取充电统计成功
     * DataTypeCode : null
     * TaskID : null
     * Content : {"CHARGERTIMES":"45","CHANGERTLONG":"0","ELECTRICITY_TOTAL":"225000"}
     */

    private String ResultCode;
    private String ResultText;
    private Object DataTypeCode;
    private Object TaskID;
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

    public Object getDataTypeCode() {
        return DataTypeCode;
    }

    public void setDataTypeCode(Object DataTypeCode) {
        this.DataTypeCode = DataTypeCode;
    }

    public Object getTaskID() {
        return TaskID;
    }

    public void setTaskID(Object TaskID) {
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
         * CHARGERTIMES : 45
         * CHANGERTLONG : 0
         * ELECTRICITY_TOTAL : 225000
         */

        private String CHARGERTIMES;
        private String CHANGERTLONG;
        private String ELECTRICITY_TOTAL;

        public String getCHARGERTIMES() {
            return CHARGERTIMES;
        }

        public void setCHARGERTIMES(String CHARGERTIMES) {
            this.CHARGERTIMES = CHARGERTIMES;
        }

        public String getCHANGERTLONG() {
            return CHANGERTLONG;
        }

        public void setCHANGERTLONG(String CHANGERTLONG) {
            this.CHANGERTLONG = CHANGERTLONG;
        }

        public String getELECTRICITY_TOTAL() {
            return ELECTRICITY_TOTAL;
        }

        public void setELECTRICITY_TOTAL(String ELECTRICITY_TOTAL) {
            this.ELECTRICITY_TOTAL = ELECTRICITY_TOTAL;
        }
    }
}
