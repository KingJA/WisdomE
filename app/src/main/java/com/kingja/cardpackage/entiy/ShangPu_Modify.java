package com.kingja.cardpackage.entiy;

/**
 * Description：TODO
 * Create Time：2016/9/1 10:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ShangPu_Modify {

    /**
     * ResultCode : 0
     * ResultText : 操作成功
     * DataTypeCode : ShangPu_Modify
     * TaskID :  1
     * Content : {"SHOPID":"XXX"}
     */

    private int ResultCode;
    private String ResultText;
    private String DataTypeCode;
    private String TaskID;
    /**
     * SHOPID : XXX
     */

    private ContentBean Content;

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

    public ContentBean getContent() {
        return Content;
    }

    public void setContent(ContentBean Content) {
        this.Content = Content;
    }

    public static class ContentBean {
        private String SHOPID;

        public String getSHOPID() {
            return SHOPID;
        }

        public void setSHOPID(String SHOPID) {
            this.SHOPID = SHOPID;
        }
    }
}
