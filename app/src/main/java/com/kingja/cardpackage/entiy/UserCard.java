package com.kingja.cardpackage.entiy;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Description:TODO
 * Create Time:2017/4/20 15:42
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Table(name = "UserCard")
public class UserCard {
    @Column(name = "CardCode", isId = true)
    private String CardCode;
    @Column(name = "ListID")
    private String ListID;
    @Column(name = "UserID")
    private String UserID;
    @Column(name = "UserCityID")
    private String UserCityID;
    @Column(name = "CityCode")
    private String CityCode;
    @Column(name = "CardName")
    private String CardName;
    @Column(name = "CardLogo")
    private String CardLogo;
    @Column(name = "CreateTime")
    private String CreateTime;
    @Column(name = "IsDelete")
    private int IsDelete;
    public String getCardCode() {
        return CardCode;
    }

    public void setCardCode(String cardCode) {
        CardCode = cardCode;
    }

    public String getListID() {
        return ListID;
    }

    public void setListID(String listID) {
        ListID = listID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserCityID() {
        return UserCityID;
    }

    public void setUserCityID(String userCityID) {
        UserCityID = userCityID;
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String cityCode) {
        CityCode = cityCode;
    }

    public String getCardName() {
        return CardName;
    }

    public void setCardName(String cardName) {
        CardName = cardName;
    }

    public String getCardLogo() {
        return CardLogo;
    }

    public void setCardLogo(String cardLogo) {
        CardLogo = cardLogo;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public int getIsDelete() {
        return IsDelete;
    }

    public void setIsDelete(int isDelete) {
        IsDelete = isDelete;
    }


}
