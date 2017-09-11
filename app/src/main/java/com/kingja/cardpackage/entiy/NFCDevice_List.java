package com.kingja.cardpackage.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/9/11 9:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NFCDevice_List {

    /**
     * ResultCode : 0
     * ResultText : 操作成功
     * DataTypeCode :  NFCDevice_List
     * TaskID :  1
     * Content : [{"DEVICEID":"33D591C2EC39477D892F59B8A615B57F","DEVICECODE":"1425","DEVICETYPE":"1062",
     * "XQCODE":"330303","PCSCODE":"330303510000","JWHCODE":"","STANDARDADDRCODE":"330303201307003219000013",
     * "STANDARDADDR":"龙湾区蒲州街道河头路175号","CARDTYPE":"9","CARDID":"40D99B01890A8C7C"}]
     */

    private int ResultCode;
    private String ResultText;
    private String DataTypeCode;
    private String TaskID;
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
        /**
         * DEVICEID : 33D591C2EC39477D892F59B8A615B57F
         * DEVICECODE : 1425
         * DEVICETYPE : 1062
         * XQCODE : 330303
         * PCSCODE : 330303510000
         * JWHCODE :
         * STANDARDADDRCODE : 330303201307003219000013
         * STANDARDADDR : 龙湾区蒲州街道河头路175号
         * CARDTYPE : 9
         * CARDID : 40D99B01890A8C7C
         */

        private String DEVICEID;
        private String DEVICECODE;
        private String DEVICETYPE;
        private String XQCODE;
        private String PCSCODE;
        private String JWHCODE;
        private String STANDARDADDRCODE;
        private String STANDARDADDR;
        private String CARDTYPE;
        private String CARDID;

        public String getDEVICEID() {
            return DEVICEID;
        }

        public void setDEVICEID(String DEVICEID) {
            this.DEVICEID = DEVICEID;
        }

        public String getDEVICECODE() {
            return DEVICECODE;
        }

        public void setDEVICECODE(String DEVICECODE) {
            this.DEVICECODE = DEVICECODE;
        }

        public String getDEVICETYPE() {
            return DEVICETYPE;
        }

        public void setDEVICETYPE(String DEVICETYPE) {
            this.DEVICETYPE = DEVICETYPE;
        }

        public String getXQCODE() {
            return XQCODE;
        }

        public void setXQCODE(String XQCODE) {
            this.XQCODE = XQCODE;
        }

        public String getPCSCODE() {
            return PCSCODE;
        }

        public void setPCSCODE(String PCSCODE) {
            this.PCSCODE = PCSCODE;
        }

        public String getJWHCODE() {
            return JWHCODE;
        }

        public void setJWHCODE(String JWHCODE) {
            this.JWHCODE = JWHCODE;
        }

        public String getSTANDARDADDRCODE() {
            return STANDARDADDRCODE;
        }

        public void setSTANDARDADDRCODE(String STANDARDADDRCODE) {
            this.STANDARDADDRCODE = STANDARDADDRCODE;
        }

        public String getSTANDARDADDR() {
            return STANDARDADDR;
        }

        public void setSTANDARDADDR(String STANDARDADDR) {
            this.STANDARDADDR = STANDARDADDR;
        }

        public String getCARDTYPE() {
            return CARDTYPE;
        }

        public void setCARDTYPE(String CARDTYPE) {
            this.CARDTYPE = CARDTYPE;
        }

        public String getCARDID() {
            return CARDID;
        }

        public void setCARDID(String CARDID) {
            this.CARDID = CARDID;
        }
    }
}
