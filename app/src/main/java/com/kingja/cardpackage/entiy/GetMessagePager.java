package com.kingja.cardpackage.entiy;

import java.io.Serializable;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/4/17 16:02
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetMessagePager {

    /**
     * ResultCode : 0
     * ResultText : 获取报警列表成功
     * DataTypeCode : GetMessagePager
     * TaskID : 1
     * Content : [{"LISTID":"83AA6D0F-F341-949C-5BF3-100E39004826","PLATENUMBER":"8888999","THEFTNO":null,
     * "WEICHATID":null,"DEVICEID":1,"MONITORTIME":"2017-04-11 00:00:00","MESSAGE":"您的电动车1234546已于2017-04-11
     * 13:00:00发生移动。如果是您本人驾驶，请迅速撤防。若已发生被盗，请立即拨打110报警。","MSGTYPE":null,"ISSUCCESS":null,"PUSHTIME":null,
     * "FAILREASON":null,"REMARK":null,"RESERVED1":null,"RESERVED2":null,"RESERVED3":null,"IsRead":0,
     * "WECHATLISTID":null}]
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

    public static class ContentBean implements Serializable{
        /**
         * LISTID : 83AA6D0F-F341-949C-5BF3-100E39004826
         * PLATENUMBER : 8888999
         * THEFTNO : null
         * WEICHATID : null
         * DEVICEID : 1.0
         * MONITORTIME : 2017-04-11 00:00:00
         * MESSAGE : 您的电动车1234546已于2017-04-11 13:00:00发生移动。如果是您本人驾驶，请迅速撤防。若已发生被盗，请立即拨打110报警。
         * MSGTYPE : null
         * ISSUCCESS : null
         * PUSHTIME : null
         * FAILREASON : null
         * REMARK : null
         * RESERVED1 : null
         * RESERVED2 : null
         * RESERVED3 : null
         * IsRead : 0
         * WECHATLISTID : null
         */

        private String LISTID;
        private String PLATENUMBER;
        private String THEFTNO;
        private String WEICHATID;
        private double DEVICEID;
        private String MONITORTIME;
        private String MESSAGE;
        private String MSGTYPE;
        private String ISSUCCESS;
        private String PUSHTIME;
        private String FAILREASON;
        private String REMARK;
        private String RESERVED1;
        private String RESERVED2;
        private String RESERVED3;
        private int IsRead;
        private String WECHATLISTID;

        public String getLISTID() {
            return LISTID;
        }

        public void setLISTID(String LISTID) {
            this.LISTID = LISTID;
        }

        public String getPLATENUMBER() {
            return PLATENUMBER;
        }

        public void setPLATENUMBER(String PLATENUMBER) {
            this.PLATENUMBER = PLATENUMBER;
        }

        public String getTHEFTNO() {
            return THEFTNO;
        }

        public void setTHEFTNO(String THEFTNO) {
            this.THEFTNO = THEFTNO;
        }

        public String getWEICHATID() {
            return WEICHATID;
        }

        public void setWEICHATID(String WEICHATID) {
            this.WEICHATID = WEICHATID;
        }

        public double getDEVICEID() {
            return DEVICEID;
        }

        public void setDEVICEID(double DEVICEID) {
            this.DEVICEID = DEVICEID;
        }

        public String getMONITORTIME() {
            return MONITORTIME;
        }

        public void setMONITORTIME(String MONITORTIME) {
            this.MONITORTIME = MONITORTIME;
        }

        public String getMESSAGE() {
            return MESSAGE;
        }

        public void setMESSAGE(String MESSAGE) {
            this.MESSAGE = MESSAGE;
        }

        public String getMSGTYPE() {
            return MSGTYPE;
        }

        public void setMSGTYPE(String MSGTYPE) {
            this.MSGTYPE = MSGTYPE;
        }

        public String getISSUCCESS() {
            return ISSUCCESS;
        }

        public void setISSUCCESS(String ISSUCCESS) {
            this.ISSUCCESS = ISSUCCESS;
        }

        public String getPUSHTIME() {
            return PUSHTIME;
        }

        public void setPUSHTIME(String PUSHTIME) {
            this.PUSHTIME = PUSHTIME;
        }

        public String getFAILREASON() {
            return FAILREASON;
        }

        public void setFAILREASON(String FAILREASON) {
            this.FAILREASON = FAILREASON;
        }

        public String getREMARK() {
            return REMARK;
        }

        public void setREMARK(String REMARK) {
            this.REMARK = REMARK;
        }

        public String getRESERVED1() {
            return RESERVED1;
        }

        public void setRESERVED1(String RESERVED1) {
            this.RESERVED1 = RESERVED1;
        }

        public String getRESERVED2() {
            return RESERVED2;
        }

        public void setRESERVED2(String RESERVED2) {
            this.RESERVED2 = RESERVED2;
        }

        public String getRESERVED3() {
            return RESERVED3;
        }

        public void setRESERVED3(String RESERVED3) {
            this.RESERVED3 = RESERVED3;
        }

        public int getIsRead() {
            return IsRead;
        }

        public void setIsRead(int IsRead) {
            this.IsRead = IsRead;
        }

        public String getWECHATLISTID() {
            return WECHATLISTID;
        }

        public void setWECHATLISTID(String WECHATLISTID) {
            this.WECHATLISTID = WECHATLISTID;
        }
    }
}
