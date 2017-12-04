package com.kingja.cardpackage.greendaobean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;


/**
 * Description：TODO
 * Create Time：2017/1/25 13:50
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Entity
public class ErrorInfo {
    @Id
    private String sn;
    private String errorTime;
    private String errorMsg;
    private int errorType;
    @Generated(hash = 35098913)
    public ErrorInfo(String sn, String errorTime, String errorMsg, int errorType) {
        this.sn = sn;
        this.errorTime = errorTime;
        this.errorMsg = errorMsg;
        this.errorType = errorType;
    }
    @Generated(hash = 918440259)
    public ErrorInfo() {
    }
    public String getSn() {
        return this.sn;
    }
    public void setSn(String sn) {
        this.sn = sn;
    }
    public String getErrorTime() {
        return this.errorTime;
    }
    public void setErrorTime(String errorTime) {
        this.errorTime = errorTime;
    }
    public String getErrorMsg() {
        return this.errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public int getErrorType() {
        return this.errorType;
    }
    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }


}
