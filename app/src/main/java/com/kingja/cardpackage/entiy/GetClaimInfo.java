package com.kingja.cardpackage.entiy;

import java.io.Serializable;

/**
 * Description：TODO
 * Create Time：2016/12/5 9:56
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetClaimInfo implements Serializable{

    /**
     * DataTypeCode : GetClaimInfo
     * ResultCode : 0
     * TaskID : 1
     * ResultText : 获取理赔信息成功
     * Content : {"EcId":"1B28FC2C-3D50-40D3-BC08-1482AC667012","ClaimTime":"","Phone":"18868269007","DeclareRemark":"","PhotoBankCardId":"","LastOperator":"","InsurerType":"2","LastUpdateTime":"","PhotoCertId":"","UploadCertTime":"2016/12/6 13:09:22","CalimMoney":"","OperatorUnitName":"绍兴市公安局","CalimType":"","DeclareTime":"","ListId":"2C01DF3D-BF05-4283-A926-C70A196965D8","PhotoClaimId":"","CARDTYPE":"1","OwnerName":"丁辰","CardId":"542231197501123354","ClaimState":"1","PCSPhotoCERT":"","PhotoCard":"","BuyDate":"2016/12/6 0:00:00","PhotoCardId":"","Bank":"","XQName":"绍兴市公安局","CERTRemark":"备注信息","CityCode":"3306","PCSName":"","LoseDate":"2016/12/6 0:00:00","PhotoCert":"","PhotoBankCard":"","BankCardNo":"","PCSPhotoId":"B0032886-B724-4983-B699-878A77742C79","ClaimOperator":"","PlateNumber":"YC870494","InTime":"2016/12/6 13:09:36","PCSOperator":"天地人","PhotoClaim":""}
     */

    private String DataTypeCode;
    private String ResultCode;
    private String TaskID;
    private String ResultText;
    /**
     * EcId : 1B28FC2C-3D50-40D3-BC08-1482AC667012
     * ClaimTime :
     * Phone : 18868269007
     * DeclareRemark :
     * PhotoBankCardId :
     * LastOperator :
     * InsurerType : 2
     * LastUpdateTime :
     * PhotoCertId :
     * UploadCertTime : 2016/12/6 13:09:22
     * CalimMoney :
     * OperatorUnitName : 绍兴市公安局
     * CalimType :
     * DeclareTime :
     * ListId : 2C01DF3D-BF05-4283-A926-C70A196965D8
     * PhotoClaimId :
     * CARDTYPE : 1
     * OwnerName : 丁辰
     * CardId : 542231197501123354
     * ClaimState : 1
     * PCSPhotoCERT :
     * PhotoCard :
     * BuyDate : 2016/12/6 0:00:00
     * PhotoCardId :
     * Bank :
     * XQName : 绍兴市公安局
     * CERTRemark : 备注信息
     * CityCode : 3306
     * PCSName :
     * LoseDate : 2016/12/6 0:00:00
     * PhotoCert :
     * PhotoBankCard :
     * BankCardNo :
     * PCSPhotoId : B0032886-B724-4983-B699-878A77742C79
     * ClaimOperator :
     * PlateNumber : YC870494
     * InTime : 2016/12/6 13:09:36
     * PCSOperator : 天地人
     * PhotoClaim :
     */

    private GetClaim Content;

    public String getDataTypeCode() {
        return DataTypeCode;
    }

    public void setDataTypeCode(String DataTypeCode) {
        this.DataTypeCode = DataTypeCode;
    }

    public String getResultCode() {
        return ResultCode;
    }

    public void setResultCode(String ResultCode) {
        this.ResultCode = ResultCode;
    }

    public String getTaskID() {
        return TaskID;
    }

    public void setTaskID(String TaskID) {
        this.TaskID = TaskID;
    }

    public String getResultText() {
        return ResultText;
    }

    public void setResultText(String ResultText) {
        this.ResultText = ResultText;
    }

    public GetClaim getContent() {
        return Content;
    }

    public void setContent(GetClaim Content) {
        this.Content = Content;
    }

}
