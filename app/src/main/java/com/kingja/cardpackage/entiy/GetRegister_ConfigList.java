package com.kingja.cardpackage.entiy;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/12/29 9:36
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetRegister_ConfigList {

    /**
     * ResultCode : 0
     * ResultText : 获取安装点列表成功
     * DataTypeCode : GetRegister_ConfigList
     * TaskID :
     * Content : [{"ConfigId":"8CD600E3DA36497AB49E770FC31E5D3A","RegistersiteId":"456B54AA0AE648AD89685D66AED85180","OnTime":"08:00","OffTime":"12:00","InstallCnt":50,"SurplusNum":50,"InTime":"2016-12-22 05:22:55"},{"ConfigId":"ADA5681239304EAC8178138F196A8D83","RegistersiteId":"456B54AA0AE648AD89685D66AED85180","OnTime":"13:00","OffTime":"17:30","InstallCnt":50,"SurplusNum":50,"InTime":"2016-12-22 05:22:55"}]
     */

    private String ResultCode;
    private String ResultText;
    private String DataTypeCode;
    private String TaskID;
    /**
     * ConfigId : 8CD600E3DA36497AB49E770FC31E5D3A
     * RegistersiteId : 456B54AA0AE648AD89685D66AED85180
     * OnTime : 08:00
     * OffTime : 12:00
     * InstallCnt : 50
     * SurplusNum : 50
     * InTime : 2016-12-22 05:22:55
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
        private String ConfigId;
        private String RegistersiteId;
        private String OnTime;
        private String OffTime;
        private int InstallCnt;
        private int SurplusNum;
        private String InTime;

        public String getConfigId() {
            return ConfigId;
        }

        public void setConfigId(String ConfigId) {
            this.ConfigId = ConfigId;
        }

        public String getRegistersiteId() {
            return RegistersiteId;
        }

        public void setRegistersiteId(String RegistersiteId) {
            this.RegistersiteId = RegistersiteId;
        }

        public String getOnTime() {
            return OnTime;
        }

        public void setOnTime(String OnTime) {
            this.OnTime = OnTime;
        }

        public String getOffTime() {
            return OffTime;
        }

        public void setOffTime(String OffTime) {
            this.OffTime = OffTime;
        }

        public int getInstallCnt() {
            return InstallCnt;
        }

        public void setInstallCnt(int InstallCnt) {
            this.InstallCnt = InstallCnt;
        }

        public int getSurplusNum() {
            return SurplusNum;
        }

        public void setSurplusNum(int SurplusNum) {
            this.SurplusNum = SurplusNum;
        }

        public String getInTime() {
            return InTime;
        }

        public void setInTime(String InTime) {
            this.InTime = InTime;
        }
    }
}
