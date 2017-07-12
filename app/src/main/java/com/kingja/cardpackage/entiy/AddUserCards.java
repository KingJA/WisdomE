package com.kingja.cardpackage.entiy;

/**
 * Description:TODO
 * Create Time:2017/4/21 10:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AddUserCards {

    /**
     * ResultCode : 0
     * ResultText : 增加卡片成功
     * DataTypeCode : null
     * TaskID : null
     * Content : {"ListID":"1a7cc486-6c8b-4f90-8807-62fd68d228fb","UserID":"5f1289f6-74f2-46d0-9d72-31ae3e519b65",
     * "UserCityID":"1c275067-e6f8-46d4-a942-2cbd90560bc9","CityCode":"5302","CardCode":"1004","CardName":null,
     * "CardLogo":null,"CreateTime":"2017-04-21 10:57:07","IsDelete":0}
     */

    private String ResultCode;
    private String ResultText;
    private Object DataTypeCode;
    private Object TaskID;
    private UserCard Content;

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

    public UserCard getContent() {
        return Content;
    }

    public void setContent(UserCard Content) {
        this.Content = Content;
    }

}
