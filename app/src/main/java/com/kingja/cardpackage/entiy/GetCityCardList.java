package com.kingja.cardpackage.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/5/10 16:36
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetCityCardList {

    /**
     * ResultCode : 0
     * ResultText : 获取城市卡片成功
     * DataTypeCode : null
     * TaskID : null
     * Content : [{"ListID":"17","CityCode":"5302","CityCardLogo":"3",
     * "ApiService":"http://122.228.188.210:20070/MobileService.asmx/CardHolder","IsValid":1,"CardCode":"1003",
     * "CardName":"我的车","CardLogo":"1","CreateTime":"2017-04-14 00:00:00"}]
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
         * ListID : 17
         * CityCode : 5302
         * CityCardLogo : 3
         * ApiService : http://122.228.188.210:20070/MobileService.asmx/CardHolder
         * IsValid : 1
         * CardCode : 1003
         * CardName : 我的车
         * CardLogo : 1
         * CreateTime : 2017-04-14 00:00:00
         */

        private String ListID;
        private String CityCode;
        private String CityCardLogo;
        private String ApiService;
        private int IsValid;
        private String CardCode;
        private String CardName;
        private String CardLogo;
        private String CreateTime;

        public String getListID() {
            return ListID;
        }

        public void setListID(String ListID) {
            this.ListID = ListID;
        }

        public String getCityCode() {
            return CityCode;
        }

        public void setCityCode(String CityCode) {
            this.CityCode = CityCode;
        }

        public String getCityCardLogo() {
            return CityCardLogo;
        }

        public void setCityCardLogo(String CityCardLogo) {
            this.CityCardLogo = CityCardLogo;
        }

        public String getApiService() {
            return ApiService;
        }

        public void setApiService(String ApiService) {
            this.ApiService = ApiService;
        }

        public int getIsValid() {
            return IsValid;
        }

        public void setIsValid(int IsValid) {
            this.IsValid = IsValid;
        }

        public String getCardCode() {
            return CardCode;
        }

        public void setCardCode(String CardCode) {
            this.CardCode = CardCode;
        }

        public String getCardName() {
            return CardName;
        }

        public void setCardName(String CardName) {
            this.CardName = CardName;
        }

        public String getCardLogo() {
            return CardLogo;
        }

        public void setCardLogo(String CardLogo) {
            this.CardLogo = CardLogo;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }
    }
}
