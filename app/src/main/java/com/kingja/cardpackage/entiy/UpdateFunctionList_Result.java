package com.kingja.cardpackage.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/4/20 10:29
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class UpdateFunctionList_Result {

    /**
     * ResultCode : 0
     * ResultText : 获取城市配置成功
     * DataTypeCode : null
     * TaskID : null
     * Content : [{"ConfigId":1,"CityCode":"5302","CityName":"昆明市测试","CardCode":"1003","ColumnValue":"1",
     * "ColumnName":"委托自动布防","IsGrant":1,"IsValid":1,"LastUpdatetime":"2017-04-19 07:16:12","Remark1":null,
     * "Remark2":null,"CardName":"我的车","CardLogo":null,"CreateTime":"0001-01-01 00:00:00"}]
     */

    private String ResultCode;
    private String ResultText;
    private String DataTypeCode;
    private String TaskID;
    private List<UpdateFunctions> Content;

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

    public List<UpdateFunctions> getContent() {
        return Content;
    }

    public void setContent(List<UpdateFunctions> Content) {
        this.Content = Content;
    }

}
