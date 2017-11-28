package com.kingja.cardpackage.entiy;

import java.io.Serializable;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/11/25 9:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetChargerSettingList implements Serializable{


    /**
     * ResultCode : 0
     * ResultText : 获取充电设置信息成功
     * DataTypeCode :
     * TaskID : null
     * Content : {"IsError":false,"Count":2,"Data":[{"autoid":"b5d55182-d6e3-44a7-a95c-90290142b537",
     * "chargerid":"86A011223344","userid":"dea3b307-907e-4dcb-859b-c7b603f793b3","auto_type":"2",
     * "auto_time":"2017-11-25 14:39:12","auto_start":"14:20","auto_end":"15:40","auto_operate":"2",
     * "auto_frequency":"1","isvalid":"1","isdelete":"0","delete_time":null,"intime":"2017/11/25 14:39:31","seq":9,
     * "isdisable":0},{"autoid":"537ee216-e3d7-44ce-9368-3f9d110437c3","chargerid":"86A011223344",
     * "userid":"dea3b307-907e-4dcb-859b-c7b603f793b3","auto_type":"2","auto_time":"2017-11-25 14:39:12",
     * "auto_start":"16:20","auto_end":"20:45","auto_operate":"2","auto_frequency":"1","isvalid":"1","isdelete":"0",
     * "delete_time":null,"intime":"2017/11/25 14:39:31","seq":7,"isdisable":0}],"ErrorMsg":""}
     */

    private String ResultCode;
    private String ResultText;
    private String DataTypeCode;
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

    public String getDataTypeCode() {
        return DataTypeCode;
    }

    public void setDataTypeCode(String DataTypeCode) {
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
        /**
         * IsError : false
         * Count : 2
         * Data : [{"autoid":"b5d55182-d6e3-44a7-a95c-90290142b537","chargerid":"86A011223344",
         * "userid":"dea3b307-907e-4dcb-859b-c7b603f793b3","auto_type":"2","auto_time":"2017-11-25 14:39:12",
         * "auto_start":"14:20","auto_end":"15:40","auto_operate":"2","auto_frequency":"1","isvalid":"1",
         * "isdelete":"0","delete_time":null,"intime":"2017/11/25 14:39:31","seq":9,"isdisable":0},
         * {"autoid":"537ee216-e3d7-44ce-9368-3f9d110437c3","chargerid":"86A011223344",
         * "userid":"dea3b307-907e-4dcb-859b-c7b603f793b3","auto_type":"2","auto_time":"2017-11-25 14:39:12",
         * "auto_start":"16:20","auto_end":"20:45","auto_operate":"2","auto_frequency":"1","isvalid":"1",
         * "isdelete":"0","delete_time":null,"intime":"2017/11/25 14:39:31","seq":7,"isdisable":0}]
         * ErrorMsg :
         */

        private boolean IsError;
        private int Count;
        private String ErrorMsg;
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

        public String getErrorMsg() {
            return ErrorMsg;
        }

        public void setErrorMsg(String ErrorMsg) {
            this.ErrorMsg = ErrorMsg;
        }

        public List<DataBean> getData() {
            return Data;
        }

        public void setData(List<DataBean> Data) {
            this.Data = Data;
        }

        public static class DataBean implements Serializable{
            /**
             * autoid : b5d55182-d6e3-44a7-a95c-90290142b537
             * chargerid : 86A011223344
             * userid : dea3b307-907e-4dcb-859b-c7b603f793b3
             * auto_type : 2
             * auto_time : 2017-11-25 14:39:12
             * auto_start : 14:20
             * auto_end : 15:40
             * auto_operate : 2
             * auto_frequency : 1
             * isvalid : 1
             * isdelete : 0
             * delete_time : null
             * intime : 2017/11/25 14:39:31
             * seq : 9
             * isdisable : 0
             */

            private String autoid;
            private String chargerid;
            private String userid;
            private int auto_type;
            private String auto_time;
            private String auto_start;
            private String auto_end;
            private int auto_operate;
            private int auto_frequency;
            private String isvalid;
            private String isdelete;
            private Object delete_time;
            private String intime;
            private int seq;
            private int isdisable;

            public String getAutoid() {
                return autoid;
            }

            public void setAutoid(String autoid) {
                this.autoid = autoid;
            }

            public String getChargerid() {
                return chargerid;
            }

            public void setChargerid(String chargerid) {
                this.chargerid = chargerid;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public int getAuto_type() {
                return auto_type;
            }

            public void setAuto_type(int auto_type) {
                this.auto_type = auto_type;
            }

            public String getAuto_time() {
                return auto_time;
            }

            public void setAuto_time(String auto_time) {
                this.auto_time = auto_time;
            }

            public String getAuto_start() {
                return auto_start;
            }

            public void setAuto_start(String auto_start) {
                this.auto_start = auto_start;
            }

            public String getAuto_end() {
                return auto_end;
            }

            public void setAuto_end(String auto_end) {
                this.auto_end = auto_end;
            }

            public int getAuto_operate() {
                return auto_operate;
            }

            public void setAuto_operate(int auto_operate) {
                this.auto_operate = auto_operate;
            }

            public int getAuto_frequency() {
                return auto_frequency;
            }

            public void setAuto_frequency(int auto_frequency) {
                this.auto_frequency = auto_frequency;
            }

            public String getIsvalid() {
                return isvalid;
            }

            public void setIsvalid(String isvalid) {
                this.isvalid = isvalid;
            }

            public String getIsdelete() {
                return isdelete;
            }

            public void setIsdelete(String isdelete) {
                this.isdelete = isdelete;
            }

            public Object getDelete_time() {
                return delete_time;
            }

            public void setDelete_time(Object delete_time) {
                this.delete_time = delete_time;
            }

            public String getIntime() {
                return intime;
            }

            public void setIntime(String intime) {
                this.intime = intime;
            }

            public int getSeq() {
                return seq;
            }

            public void setSeq(int seq) {
                this.seq = seq;
            }

            public int getIsdisable() {
                return isdisable;
            }

            public void setIsdisable(int isdisable) {
                this.isdisable = isdisable;
            }
        }
    }
}
