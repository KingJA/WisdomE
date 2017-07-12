package com.kingja.cardpackage.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/5/8 9:27
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetInvoiceInfoList {

    /**
     * ResultCode : 0
     * ResultText : 获取绑定车辆电子发票列表成功
     * DataTypeCode : GetInvoiceInfoList
     * TaskID : 1
     * Content : [{"LISTID":"3258FE94CDD742E3A2C68E47DC7211E2","ECID":"5A05F952-1A88-4FC4-AA5C-AD6D5BB07458",
     * "PLATENUMBER":"8888999","FPDM":"140110930000","FPHM":"10017361","KPTYPE":"",
     * "PDF_URL":"http://115.236.64.125/group1/M00/01/CE/wKjScFkP2GqAX2p4AABscwumdi4574.pdf",
     * "JPG_URL":"115.236.64.125:8800/DZFPJK/mKX?p=43ADB","REMARK1":null,"REMARK2":null}]
     */

    private String ResultCode;
    private String ResultText;
    private String DataTypeCode;
    private String TaskID;
    private List<ContentBean> Content;

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

    public List<ContentBean> getContent() {
        return Content;
    }

    public void setContent(List<ContentBean> Content) {
        this.Content = Content;
    }

    public static class ContentBean {
        /**
         * LISTID : 3258FE94CDD742E3A2C68E47DC7211E2
         * ECID : 5A05F952-1A88-4FC4-AA5C-AD6D5BB07458
         * PLATENUMBER : 8888999
         * FPDM : 140110930000
         * FPHM : 10017361
         * KPTYPE :
         * PDF_URL : http://115.236.64.125/group1/M00/01/CE/wKjScFkP2GqAX2p4AABscwumdi4574.pdf
         * JPG_URL : 115.236.64.125:8800/DZFPJK/mKX?p=43ADB
         * REMARK1 : null
         * REMARK2 : null
         */

        private String LISTID;
        private String ECID;
        private String PLATENUMBER;
        private String FPDM;
        private String FPHM;
        private String KPTYPE;
        private String PDF_URL;
        private String JPG_URL;
        private String REMARK1;
        private String REMARK2;

        public String getLISTID() {
            return LISTID;
        }

        public void setLISTID(String LISTID) {
            this.LISTID = LISTID;
        }

        public String getECID() {
            return ECID;
        }

        public void setECID(String ECID) {
            this.ECID = ECID;
        }

        public String getPLATENUMBER() {
            return PLATENUMBER;
        }

        public void setPLATENUMBER(String PLATENUMBER) {
            this.PLATENUMBER = PLATENUMBER;
        }

        public String getFPDM() {
            return FPDM;
        }

        public void setFPDM(String FPDM) {
            this.FPDM = FPDM;
        }

        public String getFPHM() {
            return FPHM;
        }

        public void setFPHM(String FPHM) {
            this.FPHM = FPHM;
        }

        public String getKPTYPE() {
            return KPTYPE;
        }

        public void setKPTYPE(String KPTYPE) {
            this.KPTYPE = KPTYPE;
        }

        public String getPDF_URL() {
            return PDF_URL;
        }

        public void setPDF_URL(String PDF_URL) {
            this.PDF_URL = PDF_URL;
        }

        public String getJPG_URL() {
            return JPG_URL;
        }

        public void setJPG_URL(String JPG_URL) {
            this.JPG_URL = JPG_URL;
        }

        public String getREMARK1() {
            return REMARK1;
        }

        public void setREMARK1(String REMARK1) {
            this.REMARK1 = REMARK1;
        }

        public String getREMARK2() {
            return REMARK2;
        }

        public void setREMARK2(String REMARK2) {
            this.REMARK2 = REMARK2;
        }
    }
}
