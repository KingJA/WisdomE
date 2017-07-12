package com.kingja.cardpackage.entiy;

import java.io.Serializable;

/**
 * Description：TODO
 * Create Time：2016/12/5 9:56
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetClaimInfo {

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

    private ContentBean Content;

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

    public ContentBean getContent() {
        return Content;
    }

    public void setContent(ContentBean Content) {
        this.Content = Content;
    }

    public static class ContentBean implements Serializable{
        private String EcId;
        private String ClaimTime;

        public String getBankCardOwner() {
            return BankCardOwner;
        }

        public void setBankCardOwner(String bankCardOwner) {
            BankCardOwner = bankCardOwner;
        }

        private String BankCardOwner;
        private String Phone;
        private String DeclareRemark;
        private String PhotoBankCardId;
        private String LastOperator;
        private String InsurerType;
        private String LastUpdateTime;
        private String PhotoCertId;
        private String UploadCertTime;
        private String CalimMoney;
        private String OperatorUnitName;
        private String CalimType;
        private String DeclareTime;
        private String ListId;
        private String PhotoClaimId;
        private String CARDTYPE;
        private String OwnerName;
        private String CardId;
        private int ClaimState;
        private String PCSPhotoCERT;
        private String PhotoCard;
        private String BuyDate;
        private String PhotoCardId;
        private String Bank;
        private String XQName;
        private String CERTRemark;
        private String CityCode;
        private String PCSName;
        private String LoseDate;
        private String PhotoBankCard;
        private String BankCardNo;
        private String PCSPhotoId;
        private String ClaimOperator;
        private String PlateNumber;
        private String InTime;
        private String PCSOperator;
        private String PhotoClaim;
        private String PolicyType;
        private String PolicyAmount;
        private String PolicyBeginTime;
        private String PolicyEndTime;
        private String PhotoCert;

        public String getPolicyType() {
            return PolicyType;
        }

        public void setPolicyType(String policyType) {
            PolicyType = policyType;
        }

        public String getPolicyAmount() {
            return PolicyAmount;
        }

        public void setPolicyAmount(String policyAmount) {
            PolicyAmount = policyAmount;
        }

        public String getPolicyBeginTime() {
            return PolicyBeginTime;
        }

        public void setPolicyBeginTime(String policyBeginTime) {
            PolicyBeginTime = policyBeginTime;
        }

        public String getPolicyEndTime() {
            return PolicyEndTime;
        }

        public void setPolicyEndTime(String policyEndTime) {
            PolicyEndTime = policyEndTime;
        }

        public String getEcId() {
            return EcId;
        }

        public void setEcId(String EcId) {
            this.EcId = EcId;
        }

        public String getClaimTime() {
            return ClaimTime;
        }

        public void setClaimTime(String ClaimTime) {
            this.ClaimTime = ClaimTime;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getDeclareRemark() {
            return DeclareRemark;
        }

        public void setDeclareRemark(String DeclareRemark) {
            this.DeclareRemark = DeclareRemark;
        }

        public String getPhotoBankCardId() {
            return PhotoBankCardId;
        }

        public void setPhotoBankCardId(String PhotoBankCardId) {
            this.PhotoBankCardId = PhotoBankCardId;
        }

        public String getLastOperator() {
            return LastOperator;
        }

        public void setLastOperator(String LastOperator) {
            this.LastOperator = LastOperator;
        }

        public String getInsurerType() {
            return InsurerType;
        }

        public void setInsurerType(String InsurerType) {
            this.InsurerType = InsurerType;
        }

        public String getLastUpdateTime() {
            return LastUpdateTime;
        }

        public void setLastUpdateTime(String LastUpdateTime) {
            this.LastUpdateTime = LastUpdateTime;
        }

        public String getPhotoCertId() {
            return PhotoCertId;
        }

        public void setPhotoCertId(String PhotoCertId) {
            this.PhotoCertId = PhotoCertId;
        }

        public String getUploadCertTime() {
            return UploadCertTime;
        }

        public void setUploadCertTime(String UploadCertTime) {
            this.UploadCertTime = UploadCertTime;
        }

        public String getCalimMoney() {
            return CalimMoney;
        }

        public void setCalimMoney(String CalimMoney) {
            this.CalimMoney = CalimMoney;
        }

        public String getOperatorUnitName() {
            return OperatorUnitName;
        }

        public void setOperatorUnitName(String OperatorUnitName) {
            this.OperatorUnitName = OperatorUnitName;
        }

        public String getCalimType() {
            return CalimType;
        }

        public void setCalimType(String CalimType) {
            this.CalimType = CalimType;
        }

        public String getDeclareTime() {
            return DeclareTime;
        }

        public void setDeclareTime(String DeclareTime) {
            this.DeclareTime = DeclareTime;
        }

        public String getListId() {
            return ListId;
        }

        public void setListId(String ListId) {
            this.ListId = ListId;
        }

        public String getPhotoClaimId() {
            return PhotoClaimId;
        }

        public void setPhotoClaimId(String PhotoClaimId) {
            this.PhotoClaimId = PhotoClaimId;
        }

        public String getCARDTYPE() {
            return CARDTYPE;
        }

        public void setCARDTYPE(String CARDTYPE) {
            this.CARDTYPE = CARDTYPE;
        }

        public String getOwnerName() {
            return OwnerName;
        }

        public void setOwnerName(String OwnerName) {
            this.OwnerName = OwnerName;
        }

        public String getCardId() {
            return CardId;
        }

        public void setCardId(String CardId) {
            this.CardId = CardId;
        }

        public int getClaimState() {
            return ClaimState;
        }

        public void setClaimState(int CalimState) {
            this.ClaimState = CalimState;
        }

        public String getPCSPhotoCERT() {
            return PCSPhotoCERT;
        }

        public void setPCSPhotoCERT(String PCSPhotoCERT) {
            this.PCSPhotoCERT = PCSPhotoCERT;
        }

        public String getPhotoCard() {
            return PhotoCard;
        }

        public void setPhotoCard(String PhotoCard) {
            this.PhotoCard = PhotoCard;
        }

        public String getBuyDate() {
            return BuyDate;
        }

        public void setBuyDate(String BuyDate) {
            this.BuyDate = BuyDate;
        }

        public String getPhotoCardId() {
            return PhotoCardId;
        }

        public void setPhotoCardId(String PhotoCardId) {
            this.PhotoCardId = PhotoCardId;
        }

        public String getBank() {
            return Bank;
        }

        public void setBank(String Bank) {
            this.Bank = Bank;
        }

        public String getXQName() {
            return XQName;
        }

        public void setXQName(String XQName) {
            this.XQName = XQName;
        }

        public String getCERTRemark() {
            return CERTRemark;
        }

        public void setCERTRemark(String CERTRemark) {
            this.CERTRemark = CERTRemark;
        }

        public String getCityCode() {
            return CityCode;
        }

        public void setCityCode(String CityCode) {
            this.CityCode = CityCode;
        }

        public String getPCSName() {
            return PCSName;
        }

        public void setPCSName(String PCSName) {
            this.PCSName = PCSName;
        }

        public String getLoseDate() {
            return LoseDate;
        }

        public void setLoseDate(String LoseDate) {
            this.LoseDate = LoseDate;
        }

        public String getPhotoCert() {
            return PhotoCert;
        }

        public void setPhotoCert(String PhotoCert) {
            this.PhotoCert = PhotoCert;
        }

        public String getPhotoBankCard() {
            return PhotoBankCard;
        }

        public void setPhotoBankCard(String PhotoBankCard) {
            this.PhotoBankCard = PhotoBankCard;
        }

        public String getBankCardNo() {
            return BankCardNo;
        }

        public void setBankCardNo(String BankCardNo) {
            this.BankCardNo = BankCardNo;
        }

        public String getPCSPhotoId() {
            return PCSPhotoId;
        }

        public void setPCSPhotoId(String PCSPhotoId) {
            this.PCSPhotoId = PCSPhotoId;
        }

        public String getClaimOperator() {
            return ClaimOperator;
        }

        public void setClaimOperator(String ClaimOperator) {
            this.ClaimOperator = ClaimOperator;
        }

        public String getPlateNumber() {
            return PlateNumber;
        }

        public void setPlateNumber(String PlateNumber) {
            this.PlateNumber = PlateNumber;
        }

        public String getInTime() {
            return InTime;
        }

        public void setInTime(String InTime) {
            this.InTime = InTime;
        }

        public String getPCSOperator() {
            return PCSOperator;
        }

        public void setPCSOperator(String PCSOperator) {
            this.PCSOperator = PCSOperator;
        }

        public String getPhotoClaim() {
            return PhotoClaim;
        }

        public void setPhotoClaim(String PhotoClaim) {
            this.PhotoClaim = PhotoClaim;
        }
    }
}
