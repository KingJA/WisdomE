package com.kingja.cardpackage.entiy;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Description:TODO
 * Create Time:2017/4/21 11:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Table(name = "UpdateFunction")
public class UpdateFunctions {
    @Column(name = "ColumnValue")
    private String ColumnValue;
    @Column(name = "ConfigId", isId = true)
    private String ConfigId;
    @Column(name = "CityCode")
    private String CityCode;
    @Column(name = "CityName")
    private String CityName;
    @Column(name = "CardCode")
    private String CardCode;
    @Column(name = "ColumnName")
    private String ColumnName;
    @Column(name = "IsGrant")
    private int IsGrant;
    @Column(name = "IsValid")
    private int IsValid;
    @Column(name = "LastUpdatetime")
    private String LastUpdatetime;
    @Column(name = "Remark1")
    private String Remark1;
    @Column(name = "Remark2")
    private String Remark2;
    @Column(name = "CardName")
    private String CardName;
    @Column(name = "CardLogo")
    private String CardLogo;
    @Column(name = "CreateTime")
    private String CreateTime;
    public String getConfigId() {
        return ConfigId;
    }

    public void setConfigId(String configId) {
        ConfigId = configId;
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String cityCode) {
        CityCode = cityCode;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getCardCode() {
        return CardCode;
    }

    public void setCardCode(String cardCode) {
        CardCode = cardCode;
    }

    public String getColumnValue() {
        return ColumnValue;
    }

    public void setColumnValue(String columnValue) {
        ColumnValue = columnValue;
    }

    public String getColumnName() {
        return ColumnName;
    }

    public void setColumnName(String columnName) {
        ColumnName = columnName;
    }

    public int getIsGrant() {
        return IsGrant;
    }

    public void setIsGrant(int isGrant) {
        IsGrant = isGrant;
    }

    public int getIsValid() {
        return IsValid;
    }

    public void setIsValid(int isValid) {
        IsValid = isValid;
    }

    public String getLastUpdatetime() {
        return LastUpdatetime;
    }

    public void setLastUpdatetime(String lastUpdatetime) {
        LastUpdatetime = lastUpdatetime;
    }

    public String getRemark1() {
        return Remark1;
    }

    public void setRemark1(String remark1) {
        Remark1 = remark1;
    }

    public String getRemark2() {
        return Remark2;
    }

    public void setRemark2(String remark2) {
        Remark2 = remark2;
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
}
