package com.kingja.cardpackage.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/4/17 17:19
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetUserCards {

    /**
     * ResultCode : 0
     * ResultText : 获取用户卡片成功
     * DataTypeCode : null
     * TaskID : null
     * Content : {"cardcodes":null,"cardlist":[{"ListID":"593e7954-bb29-48f5-acf0-94676e58cdcf","UserID":null,
     * "UserCityID":"1c275067-e6f8-46d4-a942-2cbd90560bc9","CityCode":null,"CardCode":"1003","CardName":"我的车",
     * "CardLogo":"1","CreateTime":"2017-04-15 17:28:38","IsDelete":0}]}
     */

    private String ResultCode;
    private String ResultText;
    private String DataTypeCode;
    private String TaskID;
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

    public String getTaskID() {
        return TaskID;
    }

    public void setTaskID(String TaskID) {
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
         * cardcodes : null
         * cardlist : [{"ListID":"593e7954-bb29-48f5-acf0-94676e58cdcf","UserID":null,
         * "UserCityID":"1c275067-e6f8-46d4-a942-2cbd90560bc9","CityCode":null,"CardCode":"1003","CardName":"我的车",
         * "CardLogo":"1","CreateTime":"2017-04-15 17:28:38","IsDelete":0}]
         */

        private Object cardcodes;
        private List<UserCard> cardlist;

        public Object getCardcodes() {
            return cardcodes;
        }

        public void setCardcodes(Object cardcodes) {
            this.cardcodes = cardcodes;
        }

        public List<UserCard> getCardlist() {
            return cardlist;
        }

        public void setCardlist(List<UserCard> cardlist) {
            this.cardlist = cardlist;
        }

    }
}
