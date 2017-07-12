package com.kingja.cardpackage.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/4/17 17:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetLastUserMessage {

    /**
     * ResultCode : 0
     * ResultText : 获取用户消息列表成功
     * DataTypeCode : null
     * TaskID : null
     * Content : [{"MessageID":"d0ea437d620043eb95e08f4b7ceaf871","CardCode":"1003","CityCode":"5301","CMD":null,
     * "Message":"车牌8889999产生预警信息，请确认是否有异常移动","UserID":"5f1289f6-74f2-46d0-9d72-31ae3e519b65","URL":"122.228.89.67",
     * "CreateTime":"2017-04-17 00:00:00","OperateTime":"2017-04-17 10:00:19","IsRead":0,"MessageType":2,
     * "CityName":"昆明市","PageIndex":0,"PageSize":0,"OnlyGetRecord":false},
     * {"MessageID":"d0ea437d620043eb95e08f4b7ceaf874","CardCode":"1007","CityCode":"5301","CMD":null,
     * "Message":"这是出租房测试message2","UserID":"5f1289f6-74f2-46d0-9d72-31ae3e519b65","URL":"122.228.89.67",
     * "CreateTime":"2017-04-17 00:00:00","OperateTime":"2017-04-17 10:00:19","IsRead":0,"MessageType":2,
     * "CityName":"昆明市","PageIndex":0,"PageSize":0,"OnlyGetRecord":false}]
     */

    private String ResultCode;
    private String ResultText;
    private Object DataTypeCode;
    private Object TaskID;
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

    public List<ContentBean> getContent() {
        return Content;
    }

    public void setContent(List<ContentBean> Content) {
        this.Content = Content;
    }

    public static class ContentBean {
        /**
         * MessageID : d0ea437d620043eb95e08f4b7ceaf871
         * CardCode : 1003
         * CityCode : 5301
         * CMD : null
         * Message : 车牌8889999产生预警信息，请确认是否有异常移动
         * UserID : 5f1289f6-74f2-46d0-9d72-31ae3e519b65
         * URL : 122.228.89.67
         * CreateTime : 2017-04-17 00:00:00
         * OperateTime : 2017-04-17 10:00:19
         * IsRead : 0
         * MessageType : 2
         * CityName : 昆明市
         * PageIndex : 0
         * PageSize : 0
         * OnlyGetRecord : false
         */

        private String MessageID;
        private String CardCode;
        private String CityCode;
        private Object CMD;
        private String Message;
        private String UserID;
        private String URL;
        private String CreateTime;
        private String OperateTime;
        private int IsRead;
        private int MessageType;
        private String CityName;
        private int PageIndex;
        private int PageSize;
        private boolean OnlyGetRecord;

        public String getMessageID() {
            return MessageID;
        }

        public void setMessageID(String MessageID) {
            this.MessageID = MessageID;
        }

        public String getCardCode() {
            return CardCode;
        }

        public void setCardCode(String CardCode) {
            this.CardCode = CardCode;
        }

        public String getCityCode() {
            return CityCode;
        }

        public void setCityCode(String CityCode) {
            this.CityCode = CityCode;
        }

        public Object getCMD() {
            return CMD;
        }

        public void setCMD(Object CMD) {
            this.CMD = CMD;
        }

        public String getMessage() {
            return Message;
        }

        public void setMessage(String Message) {
            this.Message = Message;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public String getURL() {
            return URL;
        }

        public void setURL(String URL) {
            this.URL = URL;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getOperateTime() {
            return OperateTime;
        }

        public void setOperateTime(String OperateTime) {
            this.OperateTime = OperateTime;
        }

        public int getIsRead() {
            return IsRead;
        }

        public void setIsRead(int IsRead) {
            this.IsRead = IsRead;
        }

        public int getMessageType() {
            return MessageType;
        }

        public void setMessageType(int MessageType) {
            this.MessageType = MessageType;
        }

        public String getCityName() {
            return CityName;
        }

        public void setCityName(String CityName) {
            this.CityName = CityName;
        }

        public int getPageIndex() {
            return PageIndex;
        }

        public void setPageIndex(int PageIndex) {
            this.PageIndex = PageIndex;
        }

        public int getPageSize() {
            return PageSize;
        }

        public void setPageSize(int PageSize) {
            this.PageSize = PageSize;
        }

        public boolean isOnlyGetRecord() {
            return OnlyGetRecord;
        }

        public void setOnlyGetRecord(boolean OnlyGetRecord) {
            this.OnlyGetRecord = OnlyGetRecord;
        }
    }
}
