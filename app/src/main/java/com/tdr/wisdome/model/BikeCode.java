package com.tdr.wisdome.model;

import net.tsz.afinal.annotation.sqlite.Id;
import net.tsz.afinal.annotation.sqlite.Table;

/**
 * Created by Linus_Xie on 2016/8/27.
 */
@Table(name = "bikeInfo_db")
public class BikeCode {
    @Id(column = "id")
    private int id;
    private String CODEID;
    private String CODE;
    private String NAME;
    private int TYPE;// 电动车品牌 1,电动车类型 2,登记点类型 3,颜色 4,基站类型 5
    private String REMARK;
    private int SORT;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCODEID() {
        return CODEID;
    }

    public void setCODEID(String CODEID) {
        this.CODEID = CODEID;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setName(String name) {
        this.NAME = name;
    }

    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE(int type) {
        this.TYPE = type;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public int getSORT() {
        return SORT;
    }

    public void setSORT(int sort) {
        this.SORT = sort;
    }

}
