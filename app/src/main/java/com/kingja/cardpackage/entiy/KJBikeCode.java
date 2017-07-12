package com.kingja.cardpackage.entiy;


import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Description：TODO
 * Create Time：2017/1/21 9:07
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Table(name = "bikeInfo_db")
public class KJBikeCode {
    @Column(name = "id", isId = true)
    private int id;
    @Column(name = "CODEID")
    private String CODEID;
    @Column(name = "CODE")
    private String CODE;
    @Column(name = "NAME")
    private String NAME;
    @Column(name = "TYPE")
    private int TYPE;// 电动车品牌 1,电动车类型 2,登记点类型 3,颜色 4,基站类型 5
    @Column(name = "REMARK")
    private String REMARK;
    @Column(name = "SORT")
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
