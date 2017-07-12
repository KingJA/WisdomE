package com.kingja.cardpackage.entiy;

/**
 * Description：TODO
 * Create Time：2016/12/29 10:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class EditPreRatePlus {

    /**
     * ResultCode : 0
     * ResultText : 修改预约成功！您的预约序号为 1 .
     * DataTypeCode : EditPreRatePlus
     * TaskID :
     * Content : null
     */

    private String ResultCode;
    private String ResultText;
    private String DataTypeCode;
    private String TaskID;
    private Object Content;

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

    public Object getContent() {
        return Content;
    }

    public void setContent(Object Content) {
        this.Content = Content;
    }
}
