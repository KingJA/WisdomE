package com.kingja.cardpackage.entiy;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/12/28 15:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetRegistersiteList {

    /**
     * ResultCode : 0
     * ResultText : 获取备案登记点列表成功
     * DataTypeCode : GetRegistersiteList
     * TaskID :
     * Content : [{"RegistersiteId":"017F9BDED31F4FFF9C08E774B5B8B202","RegistersiteName":"温州鹿城点","DepartmentId":"3303001","CodeNo":null,"Adress":"浙江省温州市龙湾区蒲州派出所","Lat":119,"Lng":27.032836,"Name":"徐圣国","Phone":"13500000101","Remark":"测试青年"},{"RegistersiteId":"EC80188AAEA0413DAFAF900443427046","RegistersiteName":"温州鹿城点2","DepartmentId":"3303002","CodeNo":null,"Adress":"浙江省温州市龙湾区2","Lat":119,"Lng":27.032836,"Name":"徐圣国","Phone":"13500000101","Remark":"测试青年"}]
     */

    private String ResultCode;
    private String ResultText;
    private String DataTypeCode;
    private String TaskID;
    /**
     * RegistersiteId : 017F9BDED31F4FFF9C08E774B5B8B202
     * RegistersiteName : 温州鹿城点
     * DepartmentId : 3303001
     * CodeNo : null
     * Adress : 浙江省温州市龙湾区蒲州派出所
     * Lat : 119
     * Lng : 27.032836
     * Name : 徐圣国
     * Phone : 13500000101
     * Remark : 测试青年
     */

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
        private String RegistersiteId;
        private String RegistersiteName;
        private String DepartmentId;
        private Object CodeNo;
        private String Adress;
        private int Lat;
        private double Lng;
        private String Name;
        private String Phone;
        private String Remark;

        public String getRegistersiteId() {
            return RegistersiteId;
        }

        public void setRegistersiteId(String RegistersiteId) {
            this.RegistersiteId = RegistersiteId;
        }

        public String getRegistersiteName() {
            return RegistersiteName;
        }

        public void setRegistersiteName(String RegistersiteName) {
            this.RegistersiteName = RegistersiteName;
        }

        public String getDepartmentId() {
            return DepartmentId;
        }

        public void setDepartmentId(String DepartmentId) {
            this.DepartmentId = DepartmentId;
        }

        public Object getCodeNo() {
            return CodeNo;
        }

        public void setCodeNo(Object CodeNo) {
            this.CodeNo = CodeNo;
        }

        public String getAdress() {
            return Adress;
        }

        public void setAdress(String Adress) {
            this.Adress = Adress;
        }

        public int getLat() {
            return Lat;
        }

        public void setLat(int Lat) {
            this.Lat = Lat;
        }

        public double getLng() {
            return Lng;
        }

        public void setLng(double Lng) {
            this.Lng = Lng;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }
    }
}
