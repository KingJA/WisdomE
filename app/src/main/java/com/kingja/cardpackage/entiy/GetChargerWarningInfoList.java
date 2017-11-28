package com.kingja.cardpackage.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/11/24 15:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetChargerWarningInfoList {

    /**
     * ResultCode : 0
     * ResultText : 获取充电预警信息成功
     * DataTypeCode : null
     * TaskID : null
     * Content : {"IsError":false,"Count":15,"Data":[{"warnid":1,"chargerid":"d0338f29-0e26-4d51-b087-8030e378b745",
     * "warn_type":1,"warn_time":"2017-11-23 10:49:48","warn_msg":"预警信息","isread":0,"read_time":"2017-11-23
     * 10:49:48","intime":"2017-11-23 10:49:48","reserve1":"","reserve2":"","reserve3":"",
     * "userid":"ec1afa7a-69a0-42a2-b3da-b827ac20d8ef"}],"ErrorMsg":null}
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
         * Data : [{"warnid":1,"chargerid":"d0338f29-0e26-4d51-b087-8030e378b745","warn_type":1,
         * "warn_time":"2017-11-23 10:49:48","warn_msg":"预警信息","isread":0,"read_time":"2017-11-23 10:49:48",
         * "intime":"2017-11-23 10:49:48","reserve1":"","reserve2":"","reserve3":"",
         * "userid":"ec1afa7a-69a0-42a2-b3da-b827ac20d8ef"}]
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
             * warnid : 1
             * chargerid : d0338f29-0e26-4d51-b087-8030e378b745
             * warn_type : 1
             * warn_time : 2017-11-23 10:49:48
             * warn_msg : 预警信息
             * isread : 0
             * read_time : 2017-11-23 10:49:48
             * intime : 2017-11-23 10:49:48
             * reserve1 :
             * reserve2 :
             * reserve3 :
             * userid : ec1afa7a-69a0-42a2-b3da-b827ac20d8ef
             */

            private int warnid;
            private String chargerid;
            private int warn_type;
            private String warn_time;
            private String warn_msg;
            private int isread;
            private String read_time;
            private String intime;
            private String reserve1;
            private String reserve2;
            private String reserve3;
            private String userid;

            public int getWarnid() {
                return warnid;
            }

            public void setWarnid(int warnid) {
                this.warnid = warnid;
            }

            public String getChargerid() {
                return chargerid;
            }

            public void setChargerid(String chargerid) {
                this.chargerid = chargerid;
            }

            public int getWarn_type() {
                return warn_type;
            }

            public void setWarn_type(int warn_type) {
                this.warn_type = warn_type;
            }

            public String getWarn_time() {
                return warn_time;
            }

            public void setWarn_time(String warn_time) {
                this.warn_time = warn_time;
            }

            public String getWarn_msg() {
                return warn_msg;
            }

            public void setWarn_msg(String warn_msg) {
                this.warn_msg = warn_msg;
            }

            public int getIsread() {
                return isread;
            }

            public void setIsread(int isread) {
                this.isread = isread;
            }

            public String getRead_time() {
                return read_time;
            }

            public void setRead_time(String read_time) {
                this.read_time = read_time;
            }

            public String getIntime() {
                return intime;
            }

            public void setIntime(String intime) {
                this.intime = intime;
            }

            public String getReserve1() {
                return reserve1;
            }

            public void setReserve1(String reserve1) {
                this.reserve1 = reserve1;
            }

            public String getReserve2() {
                return reserve2;
            }

            public void setReserve2(String reserve2) {
                this.reserve2 = reserve2;
            }

            public String getReserve3() {
                return reserve3;
            }

            public void setReserve3(String reserve3) {
                this.reserve3 = reserve3;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }
        }
    }
}
