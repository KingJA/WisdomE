package com.kingja.cardpackage.entiy;

import java.io.Serializable;
import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/12/29 10:57
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetPreRate implements Serializable{

    /**
     * ResultCode : 0
     * ResultText : 获取预登记列表成功
     * DataTypeCode : GetPreRate
     * TaskID :
     * Content : [{"ColorName":null,"VehiclebrandName":"绿驹","PrerateID":"93f3a041-9d19-4ab5-ae00-268a91cc3cca","PrerateName":"ceshi89","Vehiclebrand":"79","Vehiclemodels":null,"ColorID":"6","Engineno":"dianji","Shelvesno":"chejia","BuyDate":"2016-12-16 00:00:00","Price":"1234","OwnerName":"ceshi","CardType":4,"Cardid":"DHDHDHDJ","Phone1":"12345678909","Phone2":null,"Remark":null,"CreateTime":"2016-12-16 19:27:18","State":0,"UserID":"eaffcb9e-3bc3-4642-b01a-44de183c3bd9","InvalidTime":"2016-12-31 19:27:01","IsDelete":0,"ConfigId":"0C33A9D4289344228E9B89987C0EE64A","ReservateTime":"2016-12-21 06:19:28","Register_Config":{"ConfigId":"0C33A9D4289344228E9B89987C0EE64A","RegistersiteId":"0BF5F9D0E8D94DA2A2DE6AA343E1DDB9","OnTime":"08:00","OffTime":"12:00","InstallCnt":31,"SurplusNum":31,"InTime":"2016-12-22 05:22:55","Week":0},"Registersite":{"RegistersiteId":"0BF5F9D0E8D94DA2A2DE6AA343E1DDB9","RegistersiteName":"温州鹿城点5","DepartmentId":"37180C76-669F-845A-5E05-36801A8C0935","CodeNo":null,"Adress":"浙江省温州市龙湾区5","Lat":119,"Lng":27.032836,"Name":"徐圣国","Phone":"13500000101","Remark":"测试青年","isdel":0},"Seq":1},{"ColorName":null,"VehiclebrandName":"绿驹","PrerateID":"5eb19992-f3c1-445e-a2f0-65ea7f04280d","PrerateName":"ceshi55","Vehiclebrand":"79","Vehiclemodels":null,"ColorID":"6","Engineno":"dianji","Shelvesno":"chejia","BuyDate":"2016-12-16 00:00:00","Price":"1234","OwnerName":"ceshi","CardType":4,"Cardid":"DHDHDHDJ","Phone1":"12345678909","Phone2":null,"Remark":null,"CreateTime":"2016-12-23 11:23:34","State":0,"UserID":"eaffcb9e-3bc3-4642-b01a-44de183c3bd9","InvalidTime":"2017-01-07 11:23:34","IsDelete":0,"ConfigId":"421134F018DF4CAEB67CCB7909EA56EA","ReservateTime":"2016-12-21 06:19:28","Register_Config":{"ConfigId":"421134F018DF4CAEB67CCB7909EA56EA","RegistersiteId":"10A71228486A49F0999A020E3D050BD1","OnTime":"08:00","OffTime":"12:00","InstallCnt":53,"SurplusNum":53,"InTime":"2016-12-22 05:22:55","Week":0},"Registersite":{"RegistersiteId":"10A71228486A49F0999A020E3D050BD1","RegistersiteName":"温州鹿城点7","DepartmentId":"37180C76-669F-845A-5E05-36801A8C0935","CodeNo":null,"Adress":"浙江省温州市龙湾区7","Lat":119,"Lng":27.032836,"Name":"徐圣国","Phone":"13500000101","Remark":"测试青年","isdel":0},"Seq":1},{"ColorName":null,"VehiclebrandName":"绿驹","PrerateID":"1c4ce77b-43c8-48d9-916e-52b7f4b7917d","PrerateName":"ceshi5335","Vehiclebrand":"79","Vehiclemodels":null,"ColorID":"6","Engineno":"dianji","Shelvesno":"chejia","BuyDate":"2016-12-16 00:00:00","Price":"1234","OwnerName":"ceshi","CardType":4,"Cardid":"DHDHDHDJ","Phone1":"12345678909","Phone2":null,"Remark":null,"CreateTime":"2016-12-23 11:24:12","State":0,"UserID":"eaffcb9e-3bc3-4642-b01a-44de183c3bd9","InvalidTime":"2017-01-07 11:24:12","IsDelete":0,"ConfigId":"421134F018DF4CAEB67CCB7909EA56EA","ReservateTime":"2016-12-21 06:19:28","Register_Config":{"ConfigId":"421134F018DF4CAEB67CCB7909EA56EA","RegistersiteId":"10A71228486A49F0999A020E3D050BD1","OnTime":"08:00","OffTime":"12:00","InstallCnt":53,"SurplusNum":53,"InTime":"2016-12-22 05:22:55","Week":0},"Registersite":{"RegistersiteId":"10A71228486A49F0999A020E3D050BD1","RegistersiteName":"温州鹿城点7","DepartmentId":"37180C76-669F-845A-5E05-36801A8C0935","CodeNo":null,"Adress":"浙江省温州市龙湾区7","Lat":119,"Lng":27.032836,"Name":"徐圣国","Phone":"13500000101","Remark":"测试青年","isdel":0},"Seq":3},{"ColorName":null,"VehiclebrandName":"绿驹","PrerateID":"b3dbd9ef-b0a5-4e05-a145-8a17d83d82df","PrerateName":"ceshi85","Vehiclebrand":"79","Vehiclemodels":null,"ColorID":"6","Engineno":"dianji","Shelvesno":"chejia","BuyDate":"2016-12-16 00:00:00","Price":"1234","OwnerName":"ceshi","CardType":4,"Cardid":"DHDHDHDJ","Phone1":"12345678909","Phone2":null,"Remark":null,"CreateTime":"2016-12-23 11:23:48","State":0,"UserID":"eaffcb9e-3bc3-4642-b01a-44de183c3bd9","InvalidTime":"2017-01-07 11:23:48","IsDelete":0,"ConfigId":"421134F018DF4CAEB67CCB7909EA56EA","ReservateTime":"2016-12-21 06:19:28","Register_Config":{"ConfigId":"421134F018DF4CAEB67CCB7909EA56EA","RegistersiteId":"10A71228486A49F0999A020E3D050BD1","OnTime":"08:00","OffTime":"12:00","InstallCnt":53,"SurplusNum":53,"InTime":"2016-12-22 05:22:55","Week":0},"Registersite":{"RegistersiteId":"10A71228486A49F0999A020E3D050BD1","RegistersiteName":"温州鹿城点7","DepartmentId":"37180C76-669F-845A-5E05-36801A8C0935","CodeNo":null,"Adress":"浙江省温州市龙湾区7","Lat":119,"Lng":27.032836,"Name":"徐圣国","Phone":"13500000101","Remark":"测试青年","isdel":0},"Seq":2}]
     */

    private String ResultCode;
    private String ResultText;
    private String DataTypeCode;
    private String TaskID;
    /**
     * ColorName : null
     * VehiclebrandName : 绿驹
     * PrerateID : 93f3a041-9d19-4ab5-ae00-268a91cc3cca
     * PrerateName : ceshi89
     * Vehiclebrand : 79
     * Vehiclemodels : null
     * ColorID : 6
     * Engineno : dianji
     * Shelvesno : chejia
     * BuyDate : 2016-12-16 00:00:00
     * Price : 1234
     * OwnerName : ceshi
     * CardType : 4
     * Cardid : DHDHDHDJ
     * Phone1 : 12345678909
     * Phone2 : null
     * Remark : null
     * CreateTime : 2016-12-16 19:27:18
     * State : 0
     * UserID : eaffcb9e-3bc3-4642-b01a-44de183c3bd9
     * InvalidTime : 2016-12-31 19:27:01
     * IsDelete : 0
     * ConfigId : 0C33A9D4289344228E9B89987C0EE64A
     * ReservateTime : 2016-12-21 06:19:28
     * Register_Config : {"ConfigId":"0C33A9D4289344228E9B89987C0EE64A","RegistersiteId":"0BF5F9D0E8D94DA2A2DE6AA343E1DDB9","OnTime":"08:00","OffTime":"12:00","InstallCnt":31,"SurplusNum":31,"InTime":"2016-12-22 05:22:55","Week":0}
     * Registersite : {"RegistersiteId":"0BF5F9D0E8D94DA2A2DE6AA343E1DDB9","RegistersiteName":"温州鹿城点5","DepartmentId":"37180C76-669F-845A-5E05-36801A8C0935","CodeNo":null,"Adress":"浙江省温州市龙湾区5","Lat":119,"Lng":27.032836,"Name":"徐圣国","Phone":"13500000101","Remark":"测试青年","isdel":0}
     * Seq : 1
     */

    private List<ContentBean> Content;

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

    public List<ContentBean> getContent() {
        return Content;
    }

    public void setContent(List<ContentBean> Content) {
        this.Content = Content;
    }

    public static class ContentBean implements Serializable{
        private Object ColorName;
        private String VehiclebrandName;
        private String PrerateID;
        private String PrerateName;
        private String Vehiclebrand;
        private Object Vehiclemodels;
        private String ColorID;
        private String Engineno;
        private String Shelvesno;
        private String BuyDate;
        private String Price;
        private String OwnerName;
        private int CardType;
        private String Cardid;
        private String Phone1;
        private Object Phone2;
        private Object Remark;
        private String CreateTime;
        private int State;
        private String UserID;
        private String InvalidTime;
        private int IsDelete;
        private String ConfigId;
        private String ReservateTime;
        /**
         * ConfigId : 0C33A9D4289344228E9B89987C0EE64A
         * RegistersiteId : 0BF5F9D0E8D94DA2A2DE6AA343E1DDB9
         * OnTime : 08:00
         * OffTime : 12:00
         * InstallCnt : 31
         * SurplusNum : 31
         * InTime : 2016-12-22 05:22:55
         * Week : 0
         */

        private RegisterConfigBean Register_Config;
        /**
         * RegistersiteId : 0BF5F9D0E8D94DA2A2DE6AA343E1DDB9
         * RegistersiteName : 温州鹿城点5
         * DepartmentId : 37180C76-669F-845A-5E05-36801A8C0935
         * CodeNo : null
         * Adress : 浙江省温州市龙湾区5
         * Lat : 119
         * Lng : 27.032836
         * Name : 徐圣国
         * Phone : 13500000101
         * Remark : 测试青年
         * isdel : 0
         */

        private RegistersiteBean Registersite;
        private int Seq;

        public Object getColorName() {
            return ColorName;
        }

        public void setColorName(Object ColorName) {
            this.ColorName = ColorName;
        }

        public String getVehiclebrandName() {
            return VehiclebrandName;
        }

        public void setVehiclebrandName(String VehiclebrandName) {
            this.VehiclebrandName = VehiclebrandName;
        }

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

        public Object getVehiclemodels() {
            return Vehiclemodels;
        }

        public void setVehiclemodels(Object Vehiclemodels) {
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

        public Object getPhone2() {
            return Phone2;
        }

        public void setPhone2(Object Phone2) {
            this.Phone2 = Phone2;
        }

        public Object getRemark() {
            return Remark;
        }

        public void setRemark(Object Remark) {
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

        public static class RegisterConfigBean implements Serializable{
            private String ConfigId;
            private String RegistersiteId;
            private String OnTime;
            private String OffTime;
            private int InstallCnt;
            private int SurplusNum;
            private String InTime;
            private int Week;

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
        }

        public static class RegistersiteBean implements Serializable{
            private String RegistersiteId;
            private String RegistersiteName;
            private String DepartmentId;
            private Object CodeNo;
            private String Adress;
            private int Lat;
            private double Lng;
            private String Name;
            private String Phone;
            private String Remark;
            private int isdel;

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

            public int getLat() {
                return Lat;
            }

            public void setLat(int Lat) {
                this.Lat = Lat;
            }

            public double getLng() {
                return Lng;
            }

            public void setLng(double Lng) {
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

            public String getRemark() {
                return Remark;
            }

            public void setRemark(String Remark) {
                this.Remark = Remark;
            }

            public int getIsdel() {
                return isdel;
            }

            public void setIsdel(int isdel) {
                this.isdel = isdel;
            }
        }
    }
}
