package com.kingja.cardpackage.entiy;

/**
 * Description:TODO
 * Create Time:2017/4/26 10:13
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetPreRateOne {

    /**
     * ResultCode : 0
     * ResultText : 获取预登记成功
     * DataTypeCode : GetPreRateOne
     * TaskID : 1
     * Content : {"PrerateID":"792d3b84-050f-4318-a5d4-352e7796d4ef","PrerateName":"487","Vehiclebrand":"1202",
     * "Vehiclemodels":null,"ColorID":"16","Engineno":null,"Shelvesno":null,"BuyDate":"2015-01-01 00:00:00",
     * "Price":null,"OwnerName":"沈","CardType":6,"Cardid":"123456","Phone1":"13646494491","Phone2":null,
     * "Remark":null,"CreateTime":"2017-04-26 09:52:43","State":0,"UserID":"1d913f7f-eb5b-4fc4-b0b2-9804184780fa",
     * "InvalidTime":"2017-05-10 12:00:00","IsDelete":0,"RegistersiteId":"8236CD36-36C1-4A7F-AE2B-58FB7EAC3BE3",
     * "ConfigId":"93313ba1df9149b793751066c31b92c4","ReservateTime":"2017-05-10 00:00:00",
     * "Register_Config":{"ConfigId":"93313ba1df9149b793751066c31b92c4",
     * "RegistersiteId":"8236CD36-36C1-4A7F-AE2B-58FB7EAC3BE3","OnTime":"08:00","OffTime":"12:00","InstallCnt":50,
     * "SurplusNum":50,"InTime":"2017-01-03 10:51:57","Week":3,"WeekName":"星期三"},
     * "Registersite":{"RegistersiteId":"8236CD36-36C1-4A7F-AE2B-58FB7EAC3BE3","RegistersiteName":"长虹派出所备案登记点",
     * "DepartmentId":"3F1CC371-13D0-45BB-8E05-36901A8C0543","CodeNo":null,"Adress":"南开分局长虹派出所","Lat":null,
     * "Lng":null,"Name":"杜旭东","Phone":"13820478700","Remark":null,"isdel":0,"UnitNo":""},"Seq":2,"ColorName":null,
     * "VehicleBrandName":"金宇"}
     */

    private String ResultCode;
    private String ResultText;
    private String DataTypeCode;
    private String TaskID;
    private ContentBean Content;

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

    public ContentBean getContent() {
        return Content;
    }

    public void setContent(ContentBean Content) {
        this.Content = Content;
    }

    public static class ContentBean {
        /**
         * PrerateID : 792d3b84-050f-4318-a5d4-352e7796d4ef
         * PrerateName : 487
         * Vehiclebrand : 1202
         * Vehiclemodels : null
         * ColorID : 16
         * Engineno : null
         * Shelvesno : null
         * BuyDate : 2015-01-01 00:00:00
         * Price : null
         * OwnerName : 沈
         * CardType : 6
         * Cardid : 123456
         * Phone1 : 13646494491
         * Phone2 : null
         * Remark : null
         * CreateTime : 2017-04-26 09:52:43
         * State : 0
         * UserID : 1d913f7f-eb5b-4fc4-b0b2-9804184780fa
         * InvalidTime : 2017-05-10 12:00:00
         * IsDelete : 0
         * RegistersiteId : 8236CD36-36C1-4A7F-AE2B-58FB7EAC3BE3
         * ConfigId : 93313ba1df9149b793751066c31b92c4
         * ReservateTime : 2017-05-10 00:00:00
         * Register_Config : {"ConfigId":"93313ba1df9149b793751066c31b92c4",
         * "RegistersiteId":"8236CD36-36C1-4A7F-AE2B-58FB7EAC3BE3","OnTime":"08:00","OffTime":"12:00",
         * "InstallCnt":50,"SurplusNum":50,"InTime":"2017-01-03 10:51:57","Week":3,"WeekName":"星期三"}
         * Registersite : {"RegistersiteId":"8236CD36-36C1-4A7F-AE2B-58FB7EAC3BE3","RegistersiteName":"长虹派出所备案登记点",
         * "DepartmentId":"3F1CC371-13D0-45BB-8E05-36901A8C0543","CodeNo":null,"Adress":"南开分局长虹派出所","Lat":null,
         * "Lng":null,"Name":"杜旭东","Phone":"13820478700","Remark":null,"isdel":0,"UnitNo":""}
         * Seq : 2
         * ColorName : null
         * VehicleBrandName : 金宇
         */

        private String PrerateID;
        private String PrerateName;
        private String Vehiclebrand;
        private String Vehiclemodels;
        private String ColorID;
        private String Engineno;
        private String Shelvesno;
        private String BuyDate;
        private String Price;
        private String OwnerName;
        private int CardType;
        private String Cardid;
        private String Phone1;
        private String Phone2;
        private String Remark;
        private String CreateTime;
        private int State;
        private String UserID;
        private String InvalidTime;
        private int IsDelete;
        private String RegistersiteId;
        private String ConfigId;
        private String ReservateTime;
        private RegisterConfigBean Register_Config;
        private RegistersiteBean Registersite;
        private int Seq;
        private String ColorName;
        private String VehicleBrandName;

        public String getPrerateID() {
            return PrerateID;
        }

        public void setPrerateID(String PrerateID) {
            this.PrerateID = PrerateID;
        }

        public String getPrerateName() {
            return PrerateName;
        }

        public void setPrerateName(String PrerateName) {
            this.PrerateName = PrerateName;
        }

        public String getVehiclebrand() {
            return Vehiclebrand;
        }

        public void setVehiclebrand(String Vehiclebrand) {
            this.Vehiclebrand = Vehiclebrand;
        }

        public String getVehiclemodels() {
            return Vehiclemodels;
        }

        public void setVehiclemodels(String Vehiclemodels) {
            this.Vehiclemodels = Vehiclemodels;
        }

        public String getColorID() {
            return ColorID;
        }

        public void setColorID(String ColorID) {
            this.ColorID = ColorID;
        }

        public String getEngineno() {
            return Engineno;
        }

        public void setEngineno(String Engineno) {
            this.Engineno = Engineno;
        }

        public String getShelvesno() {
            return Shelvesno;
        }

        public void setShelvesno(String Shelvesno) {
            this.Shelvesno = Shelvesno;
        }

        public String getBuyDate() {
            return BuyDate;
        }

        public void setBuyDate(String BuyDate) {
            this.BuyDate = BuyDate;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public String getOwnerName() {
            return OwnerName;
        }

        public void setOwnerName(String OwnerName) {
            this.OwnerName = OwnerName;
        }

        public int getCardType() {
            return CardType;
        }

        public void setCardType(int CardType) {
            this.CardType = CardType;
        }

        public String getCardid() {
            return Cardid;
        }

        public void setCardid(String Cardid) {
            this.Cardid = Cardid;
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

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public String getInvalidTime() {
            return InvalidTime;
        }

        public void setInvalidTime(String InvalidTime) {
            this.InvalidTime = InvalidTime;
        }

        public int getIsDelete() {
            return IsDelete;
        }

        public void setIsDelete(int IsDelete) {
            this.IsDelete = IsDelete;
        }

        public String getRegistersiteId() {
            return RegistersiteId;
        }

        public void setRegistersiteId(String RegistersiteId) {
            this.RegistersiteId = RegistersiteId;
        }

        public String getConfigId() {
            return ConfigId;
        }

        public void setConfigId(String ConfigId) {
            this.ConfigId = ConfigId;
        }

        public String getReservateTime() {
            return ReservateTime;
        }

        public void setReservateTime(String ReservateTime) {
            this.ReservateTime = ReservateTime;
        }

        public RegisterConfigBean getRegister_Config() {
            return Register_Config;
        }

        public void setRegister_Config(RegisterConfigBean Register_Config) {
            this.Register_Config = Register_Config;
        }

        public RegistersiteBean getRegistersite() {
            return Registersite;
        }

        public void setRegistersite(RegistersiteBean Registersite) {
            this.Registersite = Registersite;
        }

        public int getSeq() {
            return Seq;
        }

        public void setSeq(int Seq) {
            this.Seq = Seq;
        }

        public String getColorName() {
            return ColorName;
        }

        public void setColorName(String ColorName) {
            this.ColorName = ColorName;
        }

        public String getVehicleBrandName() {
            return VehicleBrandName;
        }

        public void setVehicleBrandName(String VehicleBrandName) {
            this.VehicleBrandName = VehicleBrandName;
        }

        public static class RegisterConfigBean {
            /**
             * ConfigId : 93313ba1df9149b793751066c31b92c4
             * RegistersiteId : 8236CD36-36C1-4A7F-AE2B-58FB7EAC3BE3
             * OnTime : 08:00
             * OffTime : 12:00
             * InstallCnt : 50
             * SurplusNum : 50
             * InTime : 2017-01-03 10:51:57
             * Week : 3
             * WeekName : 星期三
             */

            private String ConfigId;
            private String RegistersiteId;
            private String OnTime;
            private String OffTime;
            private int InstallCnt;
            private int SurplusNum;
            private String InTime;
            private int Week;
            private String WeekName;

            public String getConfigId() {
                return ConfigId;
            }

            public void setConfigId(String ConfigId) {
                this.ConfigId = ConfigId;
            }

            public String getRegistersiteId() {
                return RegistersiteId;
            }

            public void setRegistersiteId(String RegistersiteId) {
                this.RegistersiteId = RegistersiteId;
            }

            public String getOnTime() {
                return OnTime;
            }

            public void setOnTime(String OnTime) {
                this.OnTime = OnTime;
            }

            public String getOffTime() {
                return OffTime;
            }

            public void setOffTime(String OffTime) {
                this.OffTime = OffTime;
            }

            public int getInstallCnt() {
                return InstallCnt;
            }

            public void setInstallCnt(int InstallCnt) {
                this.InstallCnt = InstallCnt;
            }

            public int getSurplusNum() {
                return SurplusNum;
            }

            public void setSurplusNum(int SurplusNum) {
                this.SurplusNum = SurplusNum;
            }

            public String getInTime() {
                return InTime;
            }

            public void setInTime(String InTime) {
                this.InTime = InTime;
            }

            public int getWeek() {
                return Week;
            }

            public void setWeek(int Week) {
                this.Week = Week;
            }

            public String getWeekName() {
                return WeekName;
            }

            public void setWeekName(String WeekName) {
                this.WeekName = WeekName;
            }
        }

        public static class RegistersiteBean {
            /**
             * RegistersiteId : 8236CD36-36C1-4A7F-AE2B-58FB7EAC3BE3
             * RegistersiteName : 长虹派出所备案登记点
             * DepartmentId : 3F1CC371-13D0-45BB-8E05-36901A8C0543
             * CodeNo : null
             * Adress : 南开分局长虹派出所
             * Lat : null
             * Lng : null
             * Name : 杜旭东
             * Phone : 13820478700
             * Remark : null
             * isdel : 0
             * UnitNo :
             */

            private String RegistersiteId;
            private String RegistersiteName;
            private String DepartmentId;
            private Object CodeNo;
            private String Adress;
            private Object Lat;
            private Object Lng;
            private String Name;
            private String Phone;
            private Object Remark;
            private int isdel;
            private String UnitNo;

            public String getRegistersiteId() {
                return RegistersiteId;
            }

            public void setRegistersiteId(String RegistersiteId) {
                this.RegistersiteId = RegistersiteId;
            }

            public String getRegistersiteName() {
                return RegistersiteName;
            }

            public void setRegistersiteName(String RegistersiteName) {
                this.RegistersiteName = RegistersiteName;
            }

            public String getDepartmentId() {
                return DepartmentId;
            }

            public void setDepartmentId(String DepartmentId) {
                this.DepartmentId = DepartmentId;
            }

            public Object getCodeNo() {
                return CodeNo;
            }

            public void setCodeNo(Object CodeNo) {
                this.CodeNo = CodeNo;
            }

            public String getAdress() {
                return Adress;
            }

            public void setAdress(String Adress) {
                this.Adress = Adress;
            }

            public Object getLat() {
                return Lat;
            }

            public void setLat(Object Lat) {
                this.Lat = Lat;
            }

            public Object getLng() {
                return Lng;
            }

            public void setLng(Object Lng) {
                this.Lng = Lng;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String Phone) {
                this.Phone = Phone;
            }

            public Object getRemark() {
                return Remark;
            }

            public void setRemark(Object Remark) {
                this.Remark = Remark;
            }

            public int getIsdel() {
                return isdel;
            }

            public void setIsdel(int isdel) {
                this.isdel = isdel;
            }

            public String getUnitNo() {
                return UnitNo;
            }

            public void setUnitNo(String UnitNo) {
                this.UnitNo = UnitNo;
            }
        }
    }
}
