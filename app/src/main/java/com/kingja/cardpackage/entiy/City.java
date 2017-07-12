package com.kingja.cardpackage.entiy;



import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Description：TODO
 * Create Time：2016/8/15 13:51
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Table(name = "city_db")
public class City implements Serializable {
    /**
     * CityCode : 123
     * CityName : 123
     * ShortName : 123
     * FirstWord : W
     * ParentCode : 123
     * CityType : 1
     * Sort : 123
     * IsValid : 1
     */
    @Column(name = "CityCode", isId = true)
    private String CityCode;//城市的行政区划代码;PK
    @Column(name = "CityName")
    private String CityName;//城市名称
    @Column(name = "FullWord")
    private String FullWord;//城市全拼
    @Column(name = "ShortName")
    private String ShortName;//城市名称简称，如温州的简称WZ
    @Column(name = "FirstWord")
    private String FirstWord;//名称汉字拼音首字母
    @Column(name = "ParentCode")
    private String ParentCode;//上级行政区划代码
    @Column(name = "CityType")
    private int CityType;//城市类型（0：一级 1：二级 2：三级 3：四级)
    @Column(name = "Sort")
    private int Sort;//顺序（排序）
    @Column(name = "IsValid")
    private int IsValid;//是否有效（0:无效 1有效）


    public City(String CityName, String FullWord) {
        this.CityName = CityName;
        this.FullWord = FullWord;
    }

    public City() {
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String CityCode) {
        this.CityCode = CityCode;
    }

    public String getCityPinYin() {
        return FullWord;
    }

    public void setCityPinYin(String FullWord) {
        this.FullWord = FullWord;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String ShortName) {
        this.ShortName = ShortName;
    }

    public String getFirstWord() {
        return FirstWord;
    }

    public void setFirstWord(String FirstWord) {
        this.FirstWord = FirstWord;
    }

    public String getParentCode() {
        return ParentCode;
    }

    public void setParentCode(String ParentCode) {
        this.ParentCode = ParentCode;
    }

    public int getCityType() {
        return CityType;
    }

    public void setCityType(int CityType) {
        this.CityType = CityType;
    }

    public int getSort() {
        return Sort;
    }

    public void setSort(int Sort) {
        this.Sort = Sort;
    }

    public int getIsValid() {
        return IsValid;
    }

    public void setIsValid(int IsValid) {
        this.IsValid = IsValid;
    }
}

