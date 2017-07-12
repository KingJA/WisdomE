package com.kingja.cardpackage.entiy;

import java.io.Serializable;
import java.util.List;

/**
 * Description：TODO
 * Create Time：2017/1/24 10:52
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetUserMessagePager {

    /**
     * ResultCode : 0
     * ResultText : 获取用户消息列表成功
     * DataTypeCode : null
     * TaskID : null
     * Content : {"IsError":false,"Count":877,"Data":[{"MessageID":"a9aef1f38cfa4645b7ba2a4790b2988a","CardCode":"1004","CityCode":"3303","CMD":null,"Message":"景山演示1,智慧商铺门戒A型（2101）,触发报警,请确认安全","UserID":"1b4e0d9c-41dc-4024-a71d-f0fe354295d7","URL":"122.228.89.67","CreateTime":"2016-12-27 15:23:34","OperateTime":"2016-12-27 15:23:34","IsRead":0,"MessageType":2,"CityName":"温州市","PageIndex":0,"PageSize":0,"OnlyGetRecord":false},{"MessageID":"e4572d0f4f794917a4f3a216cda4f8f0","CardCode":"1004","CityCode":"3303","CMD":null,"Message":"景山演示1,智慧商铺门戒A型（2110）,触发报警,请确认安全","UserID":"1b4e0d9c-41dc-4024-a71d-f0fe354295d7","URL":"122.228.89.67","CreateTime":"2016-12-27 07:00:18","OperateTime":"2016-12-27 09:25:52","IsRead":1,"MessageType":2,"CityName":"温州市","PageIndex":0,"PageSize":0,"OnlyGetRecord":false}],"ErrorMsg":null}
     */

    private String ResultCode;
    private String ResultText;
    private Object DataTypeCode;
    private Object TaskID;
    /**
     * IsError : false
     * Count : 877
     * Data : [{"MessageID":"a9aef1f38cfa4645b7ba2a4790b2988a","CardCode":"1004","CityCode":"3303","CMD":null,"Message":"景山演示1,智慧商铺门戒A型（2101）,触发报警,请确认安全","UserID":"1b4e0d9c-41dc-4024-a71d-f0fe354295d7","URL":"122.228.89.67","CreateTime":"2016-12-27 15:23:34","OperateTime":"2016-12-27 15:23:34","IsRead":0,"MessageType":2,"CityName":"温州市","PageIndex":0,"PageSize":0,"OnlyGetRecord":false},{"MessageID":"e4572d0f4f794917a4f3a216cda4f8f0","CardCode":"1004","CityCode":"3303","CMD":null,"Message":"景山演示1,智慧商铺门戒A型（2110）,触发报警,请确认安全","UserID":"1b4e0d9c-41dc-4024-a71d-f0fe354295d7","URL":"122.228.89.67","CreateTime":"2016-12-27 07:00:18","OperateTime":"2016-12-27 09:25:52","IsRead":1,"MessageType":2,"CityName":"温州市","PageIndex":0,"PageSize":0,"OnlyGetRecord":false}]
     * ErrorMsg : null
     */

    private ContentBean Content;

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

    public ContentBean getContent() {
        return Content;
    }

    public void setContent(ContentBean Content) {
        this.Content = Content;
    }

    public static class ContentBean implements Serializable{
        private boolean IsError;
        private int Count;
        private Object ErrorMsg;
        /**
         * MessageID : a9aef1f38cfa4645b7ba2a4790b2988a
         * CardCode : 1004
         * CityCode : 3303
         * CMD : null
         * Message : 景山演示1,智慧商铺门戒A型（2101）,触发报警,请确认安全
         * UserID : 1b4e0d9c-41dc-4024-a71d-f0fe354295d7
         * URL : 122.228.89.67
         * CreateTime : 2016-12-27 15:23:34
         * OperateTime : 2016-12-27 15:23:34
         * IsRead : 0
         * MessageType : 2
         * CityName : 温州市
         * PageIndex : 0
         * PageSize : 0
         * OnlyGetRecord : false
         */

        private List<DataBean> Data;

        public boolean isIsError() {
            return IsError;
        }

        public void setIsError(boolean IsError) {
            this.IsError = IsError;
        }

        public int getCount() {
            return Count;
        }

        public void setCount(int Count) {
            this.Count = Count;
        }

        public Object getErrorMsg() {
            return ErrorMsg;
        }

        public void setErrorMsg(Object ErrorMsg) {
            this.ErrorMsg = ErrorMsg;
        }

        public List<DataBean> getData() {
            return Data;
        }

        public void setData(List<DataBean> Data) {
            this.Data = Data;
        }

        public static class DataBean implements Serializable{
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
}
