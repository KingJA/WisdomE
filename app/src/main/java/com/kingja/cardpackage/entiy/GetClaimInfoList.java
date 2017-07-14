package com.kingja.cardpackage.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/4/17 14:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetClaimInfoList {

    /**
     * ResultCode : 0
     * ResultText : 获取理赔信息成功
     * DataTypeCode : GetClaimInfoList
     * TaskID : 1
     * Content : [{"ListId":"646AA4CF-8386-4966-B3EF-1ADE6CBC78F9","EcId":"5A05F952-1A88-4FC4-AA5C-AD6D5BB07458",
     * "PlateNumber":"8889999","OwnerName":"ZHANGSAN","CARDTYPE":"1","CardId":"330326198710110734",
     * "Phone":"13736350002","InsurerType":"2","BuyDate":"2017/4/17 0:00:00","LoseDate":"2017/4/17 0:00:00",
     * "UploadCertTime":"2017/4/17 10:21:35","DeclareTime":"2017/4/17 10:22:49","ClaimTime":null,"PCSPhotoCERT":"",
     * "PCSPhotoId":"2","CityCode":"5301","XQName":"昆明市","PCSName":null,"PCSOperator":"TEST","CERTRemark":null,
     * "Bank":"中国工商银行","BankCardNo":"6222022502019454522","PhotoCardId":null,"PhotoCard":"","PhotoBankCardId":null,
     * "PhotoBankCard":"","PhotoCertId":null,"PhotoCert":"","ClaimMoney":"89","ClaimType":"1","ClaimState":1,
     * "InTime":"2017/4/17 10:22:49","LastOperator":null,"LastUpdateTime":"2017/4/17 10:22:49","ClaimOperator":null,
     * "PhotoClaim":"","PhotoClaimId":null,"DeclareRemark":null,"OperatorUnitName":"昆明市","IsoutTime":null,"IsFind":0,
     * "BankCardOwner":"TEST","ClaimUserId":null,"PolicyType":"2","PolicyBeginTime":"2017/4/17 17:29:55",
     * "PolicyEndTime":"2018/4/14 17:29:55","PolicyAmount":"100","PicTure":null}]
     */

    private String ResultCode;
    private String ResultText;
    private String DataTypeCode;
    private String TaskID;
    private List<GetClaim> Content;

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

    public List<GetClaim> getContent() {
        return Content;
    }

    public void setContent(List<GetClaim> Content) {
        this.Content = Content;
    }

}
