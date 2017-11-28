package com.kingja.cardpackage.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/11/24 10:52
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetBindChargerList {

    /**
     * ResultCode : 0
     * ResultText : 获取充电预警信息成功
     * DataTypeCode : null
     * TaskID : null
     * Content : {"IsError":false,"Count":15,"Data":[{"userid":"21581727-8290-4d84-b7af-06df3527863f",
     * "binding_objectid":"d0338f29-0e26-4d51-b087-8030e378b745"}],"ErrorMsg":null}
     */

    private String ResultCode;
    private String ResultText;
    private Object DataTypeCode;
    private Object TaskID;
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

    public static class ContentBean {
        /**
         * IsError : false
         * Count : 15
         * Data : [{"userid":"21581727-8290-4d84-b7af-06df3527863f",
         * "binding_objectid":"d0338f29-0e26-4d51-b087-8030e378b745"}]
         * ErrorMsg : null
         */

        private boolean IsError;
        private int Count;
        private Object ErrorMsg;
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

        public static class DataBean {
            /**
             * userid : 21581727-8290-4d84-b7af-06df3527863f
             * binding_objectid : d0338f29-0e26-4d51-b087-8030e378b745
             */

            private String userid;
            private String binding_objectid;

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getBinding_objectid() {
                return binding_objectid;
            }

            public void setBinding_objectid(String binding_objectid) {
                this.binding_objectid = binding_objectid;
            }
        }
    }
}
