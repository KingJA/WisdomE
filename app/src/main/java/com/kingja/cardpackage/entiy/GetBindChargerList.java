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
     * ResultText : 获取充电器绑定信息成功
     * DataTypeCode : null
     * TaskID : null
     * Content : {"IsError":false,"Count":15,"Data":[{"ChargerId":"21581727-8290-4d84-b7af-06df3527863f",
     * "Charge_Spec":"SDFWERWER","IsValid":"1","CreateTime":"2018-03-06 15:02:12","PlateNumber":"0123456"}],
     * "ErrorMsg":null}
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
         * Data : [{"ChargerId":"21581727-8290-4d84-b7af-06df3527863f","Charge_Spec":"SDFWERWER","IsValid":"1",
         * "CreateTime":"2018-03-06 15:02:12","PlateNumber":"0123456"}]
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
             * ChargerId : 21581727-8290-4d84-b7af-06df3527863f
             * Charge_Spec : SDFWERWER
             * IsValid : 1
             * CreateTime : 2018-03-06 15:02:12
             * PlateNumber : 0123456
             */

            private String ChargerId;

            public String getEcId() {
                return EcId;
            }

            public void setEcId(String ecId) {
                EcId = ecId;
            }

            private String EcId;
            private String Charge_Spec;
            private String IsValid;
            private String CreateTime;
            private String PlateNumber;

            public String getChargerId() {
                return ChargerId;
            }

            public void setChargerId(String ChargeId) {
                this.ChargerId = ChargeId;
            }

            public String getCharge_Spec() {
                return Charge_Spec;
            }

            public void setCharge_Spec(String Charge_Spec) {
                this.Charge_Spec = Charge_Spec;
            }

            public String getIsValid() {
                return IsValid;
            }

            public void setIsValid(String IsValid) {
                this.IsValid = IsValid;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getPlateNumber() {
                return PlateNumber;
            }

            public void setPlateNumber(String PlateNumber) {
                this.PlateNumber = PlateNumber;
            }
        }
    }
}
