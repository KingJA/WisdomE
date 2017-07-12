package com.kingja.cardpackage.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/4/20 14:36
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetCityList {

    /**
     * ResultCode : 0
     * ResultText : 获取城市列表成功
     * DataTypeCode : null
     * TaskID : null
     * Content : [{"CityCode":"4503","CityName":"桂林市","ShortName":"GL","FirstWord":"G","FullWord":"GUILIN",
     * "ParentCode":"45","CityType":2,"Sort":6,"IsValid":1},{"CityCode":"5301","CityName":"昆明市","ShortName":"KM",
     * "FirstWord":"K","FullWord":"KUNMING","ParentCode":"53","CityType":1,"Sort":3,"IsValid":1},{"CityCode":"5302",
     * "CityName":"昆明市测试","ShortName":"KM","FirstWord":"K","FullWord":"KUNMING","ParentCode":"53","CityType":1,
     * "Sort":3,"IsValid":1},{"CityCode":"4502","CityName":"柳州市","ShortName":"LZ","FirstWord":"L",
     * "FullWord":"LIUZHOU","ParentCode":"45","CityType":2,"Sort":5,"IsValid":1},{"CityCode":"4513","CityName":"来宾市",
     * "ShortName":"LB","FirstWord":"L","FullWord":"LAIBIN","ParentCode":"45","CityType":2,"Sort":4,"IsValid":1},
     * {"CityCode":"3311","CityName":"丽水市","ShortName":"LS","FirstWord":"L","FullWord":"LISHUI","ParentCode":"33",
     * "CityType":2,"Sort":5,"IsValid":1},{"CityCode":"4501","CityName":"南宁市","ShortName":"NN","FirstWord":"N",
     * "FullWord":"NANNING","ParentCode":"45","CityType":2,"Sort":5,"IsValid":1},{"CityCode":"4507","CityName":"钦州市",
     * "ShortName":"QZ","FirstWord":"Q","FullWord":"QINZHOU","ParentCode":"45","CityType":2,"Sort":5,"IsValid":1},
     * {"CityCode":"3306","CityName":"绍兴市","ShortName":"SX","FirstWord":"S","FullWord":"SHAOXING","ParentCode":"33",
     * "CityType":2,"Sort":3,"IsValid":1},{"CityCode":"1201","CityName":"天津市","ShortName":"TJ","FirstWord":"T",
     * "FullWord":"TIANJIN","ParentCode":"12","CityType":1,"Sort":2,"IsValid":1},{"CityCode":"3303","CityName":"温州市",
     * "ShortName":"WZ","FirstWord":"W","FullWord":"WENZHOU","ParentCode":"33","CityType":2,"Sort":1,"IsValid":1},
     * {"CityCode":"4110","CityName":"许昌市","ShortName":"XC","FirstWord":"X","FullWord":"XUCHANG","ParentCode":"41",
     * "CityType":2,"Sort":5,"IsValid":1},{"CityCode":"3307","CityName":"义乌市","ShortName":"YW","FirstWord":"Y",
     * "FullWord":"YIWU","ParentCode":"33","CityType":2,"Sort":2,"IsValid":1},{"CityCode":"3308","CityName":"义乌测试",
     * "ShortName":"YW","FirstWord":"Y","FullWord":"YIWU","ParentCode":"33","CityType":2,"Sort":1,"IsValid":1},
     * {"CityCode":"4101","CityName":"郑州市","ShortName":"ZZ","FirstWord":"Z","FullWord":"ZHENGZHOU","ParentCode":"41",
     * "CityType":2,"Sort":4,"IsValid":1}]
     */

    private String ResultCode;
    private String ResultText;
    private Object DataTypeCode;
    private Object TaskID;
    private List<City> Content;

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

    public List<City> getContent() {
        return Content;
    }

    public void setContent(List<City> Content) {
        this.Content = Content;
    }

}
