package com.kingja.cardpackage.entiy;

import com.tdr.wisdome.model.BikeCode;

import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.Table;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/12/2 15:36
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetCodeList {


    /**
     * ResultCode : 0
     * ResultText : 获取列表成功
     * DataTypeCode : GetCodeList
     * TaskID :
     * Content : [{"CODEID":"FA9C3BF8-8F65-4849-A445-743310162804","CODE":"0","NAME":"其他","REMARK":"其他品牌","TYPE":1,"SORT":-1,"INTIME":"2015-08-27 11:18:00","UPDATETIME":"2015-08-27 11:18:00"},{"CODEID":"7C087D7F-2A17-430F-A59E-9778B5BEC7BC","CODE":"1280","NAME":"风速","REMARK":null,"TYPE":1,"SORT":0,"INTIME":"2015-08-27 11:18:00","UPDATETIME":"2015-08-27 11:18:00"},{"CODEID":"cf36517c-e5bf-45ad-a4b6-8eb3ec57732e","CODE":"1281","NAME":"平安","REMARK":"电动车品牌","TYPE":1,"SORT":0,"INTIME":"2015-08-27 11:18:00","UPDATETIME":"2015-08-27 11:18:00"}]
     */

    private String ResultCode;
    private String ResultText;
    private String DataTypeCode;
    private String TaskID;
    /**
     * CODEID : FA9C3BF8-8F65-4849-A445-743310162804
     * CODE : 0
     * NAME : 其他
     * REMARK : 其他品牌
     * TYPE : 1
     * SORT : -1
     * INTIME : 2015-08-27 11:18:00
     * UPDATETIME : 2015-08-27 11:18:00
     */

    private List<KJBikeCode> Content;

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

    public List<KJBikeCode> getContent() {
        return Content;
    }

    public void setContent(List<KJBikeCode> Content) {
        this.Content = Content;
    }
}
