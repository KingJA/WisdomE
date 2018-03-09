package com.kingja.cardpackage.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/3/7 16:11
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BindChargerParam {

    /**
     * ChargerId : 86A000000001
     * Charge_Spec : 86A000000001
     * Remark : 备注
     * VehicleBrand :
     * VehicleType : 38CBF510-6EF2-49AB-A3CF-EB7782EF1BA7
     * PlateNumber : 38CBF510-6EF2-49AB-A3CF-EB7782EF1BA7
     * ColorId : 38CBF510-6EF2-49AB-A3CF-EB7782EF1BA7
     * EngineNo : 38CBF510-6EF2-49AB-A3CF-EB7782EF1BA7
     * ShelvesNo : 38CBF510-6EF2-49AB-A3CF-EB7782EF1BA7
     * OwnerName : 38CBF510-6EF2-49AB-A3CF-EB7782EF1BA7
     * CardId : 38CBF510-6EF2-49AB-A3CF-EB7782EF1BA7
     * Address : 38CBF510-6EF2-49AB-A3CF-EB7782EF1BA7
     * ResidentAddress : 38CBF510-6EF2-49AB-A3CF-EB7782EF1BA7
     * Phone1 : 38CBF510-6EF2-49AB-A3CF-EB7782EF1BA7
     * Phone2 : 38CBF510-6EF2-49AB-A3CF-EB7782EF1BA7
     * UnitId : 38CBF510-6EF2-49AB-A3CF-EB7782EF1BA7
     * PhotoListFile : [{"INDEX":5,"Photo":"38CBF510-6EF2-49AB-A3CF-EB7782EF1BA7","PhotoFile":"base64"},{"INDEX":6,
     * "Photo":"38CBF510-6EF2-49AB-A3CF-EB7782EF1BA7","PhotoFile":"base64"},{"INDEX":3,"Photo":"38CBF510-6EF2-49AB
     * -A3CF-EB7782EF1BA7","PhotoFile":"base64"}]
     */

    private String ChargerId;
    private String Charge_Spec;
    private String Remark;
    private String VehicleBrand;
    private String VehicleType;
    private String PlateNumber;
    private String ColorId;
    private String EngineNo;
    private String ShelvesNo;
    private String OwnerName;
    private String CardId;
    private String Address;
    private String ResidentAddress;
    private String Phone1;
    private String Phone2;
    private String UnitId;

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }

    private String CardType;
    private List<PhotoListFileBean> PhotoListFile;

    public String getChargerId() {
        return ChargerId;
    }

    public void setChargerId(String ChargeId) {
        this.ChargerId = ChargeId;
    }

    public String getCharge_Spec() {
        return Charge_Spec;
    }

    public void setCharge_Spec(String Charge_Spec) {
        this.Charge_Spec = Charge_Spec;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getVehicleBrand() {
        return VehicleBrand;
    }

    public void setVehicleBrand(String VehicleBrand) {
        this.VehicleBrand = VehicleBrand;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(String VehicleType) {
        this.VehicleType = VehicleType;
    }

    public String getPlateNumber() {
        return PlateNumber;
    }

    public void setPlateNumber(String PlateNumber) {
        this.PlateNumber = PlateNumber;
    }

    public String getColorId() {
        return ColorId;
    }

    public void setColorId(String ColorId) {
        this.ColorId = ColorId;
    }

    public String getEngineNo() {
        return EngineNo;
    }

    public void setEngineNo(String EngineNo) {
        this.EngineNo = EngineNo;
    }

    public String getShelvesNo() {
        return ShelvesNo;
    }

    public void setShelvesNo(String ShelvesNo) {
        this.ShelvesNo = ShelvesNo;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getResidentAddress() {
        return ResidentAddress;
    }

    public void setResidentAddress(String ResidentAddress) {
        this.ResidentAddress = ResidentAddress;
    }

    public String getPhone1() {
        return Phone1;
    }

    public void setPhone1(String Phone1) {
        this.Phone1 = Phone1;
    }

    public String getPhone2() {
        return Phone2;
    }

    public void setPhone2(String Phone2) {
        this.Phone2 = Phone2;
    }

    public String getUnitId() {
        return UnitId;
    }

    public void setUnitId(String UnitId) {
        this.UnitId = UnitId;
    }

    public List<PhotoListFileBean> getPhotoListFile() {
        return PhotoListFile;
    }

    public void setPhotoListFile(List<PhotoListFileBean> PhotoListFile) {
        this.PhotoListFile = PhotoListFile;
    }

    public static class PhotoListFileBean {
        /**
         * INDEX : 5
         * Photo : 38CBF510-6EF2-49AB-A3CF-EB7782EF1BA7
         * PhotoFile : base64
         */

        private int INDEX;
        private String Photo;
        private String PhotoFile;

        public int getINDEX() {
            return INDEX;
        }

        public void setINDEX(int INDEX) {
            this.INDEX = INDEX;
        }

        public String getPhoto() {
            return Photo;
        }

        public void setPhoto(String Photo) {
            this.Photo = Photo;
        }

        public String getPhotoFile() {
            return PhotoFile;
        }

        public void setPhotoFile(String PhotoFile) {
            this.PhotoFile = PhotoFile;
        }
    }
}
