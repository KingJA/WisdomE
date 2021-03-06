package com.kingja.cardpackage.entiy;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/9/1 14:16
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ShangPu_DeviceLists {

    /**
     * ResultCode : 0
     * ResultText : 操作成功
     * DataTypeCode :  ShangPu_DeviceLists
     * TaskID :  1
     * Content : [{"DEVICEID":"XXX","DEVICETYPE":"1088","DEVICECODE":"1234","DEVICENAME":"门戒","PARAM1":"XXX","PARAM2":"XXX","PARAM3":"XXX","PARAM4":"XXX","PARAM5":"XXX","PARAM6":"XXX","PARAM7":"XXX","PARAM8":"XXX","PARAM9":"XXX","PARAM10":"XXX"},{"DEVICEID":"XXX","DEVICETYPE":"1088","DEVICECODE":"1234","DEVICENAME":"门戒","PARAM1":"XXX","PARAM2":"XXX","PARAM3":"XXX","PARAM4":"XXX","PARAM5":"XXX","PARAM6":"XXX","PARAM7":"XXX","PARAM8":"XXX","PARAM9":"XXX","PARAM10":"XXX"}]
     */

    private int ResultCode;
    private String ResultText;
    private String DataTypeCode;
    private String TaskID;
    /**
     * DEVICEID : XXX
     * DEVICETYPE : 1088
     * DEVICECODE : 1234
     * DEVICENAME : 门戒
     * PARAM1 : XXX
     * PARAM2 : XXX
     * PARAM3 : XXX
     * PARAM4 : XXX
     * PARAM5 : XXX
     * PARAM6 : XXX
     * PARAM7 : XXX
     * PARAM8 : XXX
     * PARAM9 : XXX
     * PARAM10 : XXX
     */

    private List<ContentBean> Content;

    public int getResultCode() {
        return ResultCode;
    }

    public void setResultCode(int ResultCode) {
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

    public List<ContentBean> getContent() {
        return Content;
    }

    public void setContent(List<ContentBean> Content) {
        this.Content = Content;
    }

    public static class ContentBean {
        private String DEVICEID;
        private String DEVICETYPE;
        private String DEVICECODE;
        private String DEVICENAME;
        private String PARAM1;
        private String PARAM2;
        private String PARAM3;
        private String PARAM4;
        private String PARAM5;
        private String PARAM6;
        private String PARAM7;
        private String PARAM8;
        private String PARAM9;
        private String PARAM10;

        public String getDEVICEID() {
            return DEVICEID;
        }

        public void setDEVICEID(String DEVICEID) {
            this.DEVICEID = DEVICEID;
        }

        public String getDEVICETYPE() {
            return DEVICETYPE;
        }

        public void setDEVICETYPE(String DEVICETYPE) {
            this.DEVICETYPE = DEVICETYPE;
        }

        public String getDEVICECODE() {
            return DEVICECODE;
        }

        public void setDEVICECODE(String DEVICECODE) {
            this.DEVICECODE = DEVICECODE;
        }

        public String getDEVICENAME() {
            return DEVICENAME;
        }

        public void setDEVICENAME(String DEVICENAME) {
            this.DEVICENAME = DEVICENAME;
        }

        public String getPARAM1() {
            return PARAM1;
        }

        public void setPARAM1(String PARAM1) {
            this.PARAM1 = PARAM1;
        }

        public String getPARAM2() {
            return PARAM2;
        }

        public void setPARAM2(String PARAM2) {
            this.PARAM2 = PARAM2;
        }

        public String getPARAM3() {
            return PARAM3;
        }

        public void setPARAM3(String PARAM3) {
            this.PARAM3 = PARAM3;
        }

        public String getPARAM4() {
            return PARAM4;
        }

        public void setPARAM4(String PARAM4) {
            this.PARAM4 = PARAM4;
        }

        public String getPARAM5() {
            return PARAM5;
        }

        public void setPARAM5(String PARAM5) {
            this.PARAM5 = PARAM5;
        }

        public String getPARAM6() {
            return PARAM6;
        }

        public void setPARAM6(String PARAM6) {
            this.PARAM6 = PARAM6;
        }

        public String getPARAM7() {
            return PARAM7;
        }

        public void setPARAM7(String PARAM7) {
            this.PARAM7 = PARAM7;
        }

        public String getPARAM8() {
            return PARAM8;
        }

        public void setPARAM8(String PARAM8) {
            this.PARAM8 = PARAM8;
        }

        public String getPARAM9() {
            return PARAM9;
        }

        public void setPARAM9(String PARAM9) {
            this.PARAM9 = PARAM9;
        }

        public String getPARAM10() {
            return PARAM10;
        }

        public void setPARAM10(String PARAM10) {
            this.PARAM10 = PARAM10;
        }
    }
}
