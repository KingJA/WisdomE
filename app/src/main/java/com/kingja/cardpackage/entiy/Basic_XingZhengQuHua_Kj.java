package com.kingja.cardpackage.entiy;


import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 项目名称：物联网城市防控(警用版)
 * 类描述：登录
 * 创建人：KingJA
 * 创建时间：2016/3/28 14:58
 * 修改备注：
 */
@Table(name = "Basic_XingZhengQuHua")
public class Basic_XingZhengQuHua_Kj {
    @Column(name = "DMZM", isId = true)
    private String DMZM;
    @Column(name = "DMMC")
    private String DMMC;
    @Column(name = "SORT")
    private String SORT;

    public String getDMZM() {
        return DMZM;
    }

    public void setDMZM(String DMZM) {
        this.DMZM = DMZM;
    }

    public String getDMMC() {
        return DMMC;
    }

    public void setDMMC(String DMMC) {
        this.DMMC = DMMC;
    }

    public String getSORT() {
        return SORT;
    }

    public void setSORT(String SORT) {
        this.SORT = SORT;
    }
}
