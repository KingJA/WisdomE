package com.kingja.cardpackage.entiy;

import java.io.Serializable;
import java.util.List;

/**
 * Description：TODO
 * Create Time：2017/1/19 10:29
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class IndexData {


    /**
     * ResultCode : 0
     * ResultText : 获取数据成功
     * DataTypeCode : IndexData
     * TaskID : 1
     * Content : {"BindingList":[{"BindingID":"fc703e4b-ab24-4cee-90c9-c6c0c9e55869","UserID":"b9d87f78-5661-4bd7-ba6a-f8d3150a1433","EcID":"082EAED7-A234-41FF-95FD-5C8DC15FA10E","CreateTime":"2016-12-10 13:57:44","PlateNumber":"ZY444444","CardID":"33032419950929211X","CardType":1,"OwnerName":"叶仁杰","Phone":null,"Code":null,"Auditstate":1,"Status":1,"IsRead":1,"ElectricCar":{"PrerateID":null,"PrerateName":null,"Vehiclebrand":"1245","Vehiclemodels":null,"ColorID":"3","Engineno":"*","Shelvesno":"*","BuyDate":"2016-12-10 00:00:00","Price":null,"OwnerName":"叶仁杰","CardType":1,"Cardid":"33032419950929211X","Phone1":"15058703629","Phone2":null,"Remark":null,"CreateTime":"2016-12-10 13:57:44","State":0,"UserID":"b9d87f78-5661-4bd7-ba6a-f8d3150a1433","InvalidTime":"0001-01-01 00:00:00","IsDelete":0,"RegistersiteId":null,"ConfigId":null,"ReservateTime":null,"Register_Config":null,"Registersite":null,"Seq":null},"ClaimState":0,"PolicyEndTime":null,"BuyDate":"2016/12/10 0:00:00"},{"BindingID":"4ccdeda3-8677-4ec4-ae0c-fde7946801d1","UserID":"b9d87f78-5661-4bd7-ba6a-f8d3150a1433","EcID":"C7B78760-43E6-4D0D-BEB8-3B4B91369178","CreateTime":"2017-01-12 13:44:32","PlateNumber":"XZ444557","CardID":"33032419950929211X","CardType":1,"OwnerName":"测试","Phone":null,"Code":null,"Auditstate":0,"Status":0,"IsRead":1,"ElectricCar":{"PrerateID":null,"PrerateName":null,"Vehiclebrand":"419","Vehiclemodels":null,"ColorID":"4","Engineno":"*","Shelvesno":"*","BuyDate":"2016-01-08 00:00:00","Price":"213","OwnerName":"测试","CardType":1,"Cardid":"33032419950929211X","Phone1":"15058703629","Phone2":null,"Remark":null,"CreateTime":"2017-01-12 13:44:32","State":0,"UserID":"b9d87f78-5661-4bd7-ba6a-f8d3150a1433","InvalidTime":"0001-01-01 00:00:00","IsDelete":0,"RegistersiteId":null,"ConfigId":null,"ReservateTime":null,"Register_Config":null,"Registersite":null,"Seq":null},"ClaimState":0,"PolicyEndTime":null,"BuyDate":"2016/1/8 0:00:00"}],"PreRate":[{"ColorName":null,"VehiclebrandName":"绿驹","PrerateID":"97bfb454-3e31-44df-b42c-012bda502c68","PrerateName":"10081","Vehiclebrand":"79","Vehiclemodels":null,"ColorID":"2","Engineno":"DJH","Shelvesno":"CHH","BuyDate":"2015-01-01 00:00:00","Price":null,"OwnerName":"test","CardType":3,"Cardid":"123","Phone1":"13163240531","Phone2":null,"Remark":null,"CreateTime":"2017-01-19 14:12:45","State":0,"UserID":"b9d87f78-5661-4bd7-ba6a-f8d3150a1433","InvalidTime":"2017-02-03 14:12:45","IsDelete":0,"RegistersiteId":null,"ConfigId":null,"ReservateTime":null,"Register_Config":null,"Registersite":null,"Seq":null},{"ColorName":null,"VehiclebrandName":"绿驹","PrerateID":"ad7b6f66-6368-4873-865a-0233bf95cddf","PrerateName":"10080","Vehiclebrand":"79","Vehiclemodels":null,"ColorID":"1","Engineno":"DJH","Shelvesno":"CJH","BuyDate":"2015-01-01 00:00:00","Price":null,"OwnerName":"gwq","CardType":3,"Cardid":"123","Phone1":"13163240531","Phone2":null,"Remark":null,"CreateTime":"2017-01-19 14:01:37","State":0,"UserID":"b9d87f78-5661-4bd7-ba6a-f8d3150a1433","InvalidTime":"2017-02-03 14:01:37","IsDelete":0,"RegistersiteId":null,"ConfigId":null,"ReservateTime":null,"Register_Config":null,"Registersite":null,"Seq":null}]}
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
         * BindingID : fc703e4b-ab24-4cee-90c9-c6c0c9e55869
         * UserID : b9d87f78-5661-4bd7-ba6a-f8d3150a1433
         * EcID : 082EAED7-A234-41FF-95FD-5C8DC15FA10E
         * CreateTime : 2016-12-10 13:57:44
         * PlateNumber : ZY444444
         * CardID : 33032419950929211X
         * CardType : 1
         * OwnerName : 叶仁杰
         * Phone : null
         * Code : null
         * Auditstate : 1
         * Status : 1
         * IsRead : 1
         * ElectricCar : {"PrerateID":null,"PrerateName":null,"Vehiclebrand":"1245","Vehiclemodels":null,"ColorID":"3","Engineno":"*","Shelvesno":"*","BuyDate":"2016-12-10 00:00:00","Price":null,"OwnerName":"叶仁杰","CardType":1,"Cardid":"33032419950929211X","Phone1":"15058703629","Phone2":null,"Remark":null,"CreateTime":"2016-12-10 13:57:44","State":0,"UserID":"b9d87f78-5661-4bd7-ba6a-f8d3150a1433","InvalidTime":"0001-01-01 00:00:00","IsDelete":0,"RegistersiteId":null,"ConfigId":null,"ReservateTime":null,"Register_Config":null,"Registersite":null,"Seq":null}
         * ClaimState : 0
         * PolicyEndTime : null
         * BuyDate : 2016/12/10 0:00:00
         */

        private List<BindingListBean> BindingList;
        /**
         * ColorName : null
         * VehiclebrandName : 绿驹
         * PrerateID : 97bfb454-3e31-44df-b42c-012bda502c68
         * PrerateName : 10081
         * Vehiclebrand : 79
         * Vehiclemodels : null
         * ColorID : 2
         * Engineno : DJH
         * Shelvesno : CHH
         * BuyDate : 2015-01-01 00:00:00
         * Price : null
         * OwnerName : test
         * CardType : 3
         * Cardid : 123
         * Phone1 : 13163240531
         * Phone2 : null
         * Remark : null
         * CreateTime : 2017-01-19 14:12:45
         * State : 0
         * UserID : b9d87f78-5661-4bd7-ba6a-f8d3150a1433
         * InvalidTime : 2017-02-03 14:12:45
         * IsDelete : 0
         * RegistersiteId : null
         * ConfigId : null
         * ReservateTime : null
         * Register_Config : null
         * Registersite : null
         * Seq : null
         */

        private List<PreRateBean> PreRate;

        public List<BindingListBean> getBindingList() {
            return BindingList;
        }

        public void setBindingList(List<BindingListBean> BindingList) {
            this.BindingList = BindingList;
        }

        public List<PreRateBean> getPreRate() {
            return PreRate;
        }

        public void setPreRate(List<PreRateBean> PreRate) {
            this.PreRate = PreRate;
        }

        public static class BindingListBean implements Serializable {
            private String BindingID;
            private String UserID;
            private String EcID;
            private String CreateTime;
            private String PlateNumber;
            private String CardID;
            private int CardType;
            private String OwnerName;
            private Object Phone;
            private Object Code;
            private int Auditstate;
            private int Status;
            private int IsRead;
            /**
             * PrerateID : null
             * PrerateName : null
             * Vehiclebrand : 1245
             * Vehiclemodels : null
             * ColorID : 3
             * Engineno : *
             * Shelvesno : *
             * BuyDate : 2016-12-10 00:00:00
             * Price : null
             * OwnerName : 叶仁杰
             * CardType : 1
             * Cardid : 33032419950929211X
             * Phone1 : 15058703629
             * Phone2 : null
             * Remark : null
             * CreateTime : 2016-12-10 13:57:44
             * State : 0
             * UserID : b9d87f78-5661-4bd7-ba6a-f8d3150a1433
             * InvalidTime : 0001-01-01 00:00:00
             * IsDelete : 0
             * RegistersiteId : null
             * ConfigId : null
             * ReservateTime : null
             * Register_Config : null
             * Registersite : null
             * Seq : null
             */

            private ElectricCarBean ElectricCar;
            private int ClaimState;
            private String PolicyEndTime;
            private String BuyDate;

            public String getBindingID() {
                return BindingID;
            }

            public void setBindingID(String BindingID) {
                this.BindingID = BindingID;
            }

            public String getUserID() {
                return UserID;
            }

            public void setUserID(String UserID) {
                this.UserID = UserID;
            }

            public String getEcID() {
                return EcID;
            }

            public void setEcID(String EcID) {
                this.EcID = EcID;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getPlateNumber() {
                return PlateNumber;
            }

            public void setPlateNumber(String PlateNumber) {
                this.PlateNumber = PlateNumber;
            }

            public String getCardID() {
                return CardID;
            }

            public void setCardID(String CardID) {
                this.CardID = CardID;
            }

            public int getCardType() {
                return CardType;
            }

            public void setCardType(int CardType) {
                this.CardType = CardType;
            }

            public String getOwnerName() {
                return OwnerName;
            }

            public void setOwnerName(String OwnerName) {
                this.OwnerName = OwnerName;
            }

            public Object getPhone() {
                return Phone;
            }

            public void setPhone(Object Phone) {
                this.Phone = Phone;
            }

            public Object getCode() {
                return Code;
            }

            public void setCode(Object Code) {
                this.Code = Code;
            }

            public int getAuditstate() {
                return Auditstate;
            }

            public void setAuditstate(int Auditstate) {
                this.Auditstate = Auditstate;
            }

            public int getStatus() {
                return Status;
            }

            public void setStatus(int Status) {
                this.Status = Status;
            }

            public int getIsRead() {
                return IsRead;
            }

            public void setIsRead(int IsRead) {
                this.IsRead = IsRead;
            }

            public ElectricCarBean getElectricCar() {
                return ElectricCar;
            }

            public void setElectricCar(ElectricCarBean ElectricCar) {
                this.ElectricCar = ElectricCar;
            }

            public int getClaimState() {
                return ClaimState;
            }

            public void setClaimState(int ClaimState) {
                this.ClaimState = ClaimState;
            }

            public String getPolicyEndTime() {
                return PolicyEndTime;
            }

            public void setPolicyEndTime(String PolicyEndTime) {
                this.PolicyEndTime = PolicyEndTime;
            }

            public String getBuyDate() {
                return BuyDate;
            }

            public void setBuyDate(String BuyDate) {
                this.BuyDate = BuyDate;
            }

            public static class ElectricCarBean implements Serializable{
                private Object PrerateID;
                private Object PrerateName;
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
                private Object RegistersiteId;
                private Object ConfigId;
                private Object ReservateTime;
                private Object Register_Config;
                private Object Registersite;
                private Object Seq;

                public Object getPrerateID() {
                    return PrerateID;
                }

                public void setPrerateID(Object PrerateID) {
                    this.PrerateID = PrerateID;
                }

                public Object getPrerateName() {
                    return PrerateName;
                }

                public void setPrerateName(Object PrerateName) {
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

                public Object getRegistersiteId() {
                    return RegistersiteId;
                }

                public void setRegistersiteId(Object RegistersiteId) {
                    this.RegistersiteId = RegistersiteId;
                }

                public Object getConfigId() {
                    return ConfigId;
                }

                public void setConfigId(Object ConfigId) {
                    this.ConfigId = ConfigId;
                }

                public Object getReservateTime() {
                    return ReservateTime;
                }

                public void setReservateTime(Object ReservateTime) {
                    this.ReservateTime = ReservateTime;
                }

                public Object getRegister_Config() {
                    return Register_Config;
                }

                public void setRegister_Config(Object Register_Config) {
                    this.Register_Config = Register_Config;
                }

                public Object getRegistersite() {
                    return Registersite;
                }

                public void setRegistersite(Object Registersite) {
                    this.Registersite = Registersite;
                }

                public Object getSeq() {
                    return Seq;
                }

                public void setSeq(Object Seq) {
                    this.Seq = Seq;
                }
            }
        }

        public static class PreRateBean implements Serializable{
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
            private Object Price;
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
            private Object RegistersiteId;
            private Object ConfigId;
            private String ReservateTime;
            private Object Seq;

            public RegisterConfigBean getRegister_Config() {
                return Register_Config;
            }

            public void setRegister_Config(RegisterConfigBean register_Config) {
                Register_Config = register_Config;
            }

            public RegistersiteBean getRegistersite() {
                return Registersite;
            }

            public void setRegistersite(RegistersiteBean registersite) {
                Registersite = registersite;
            }

            private RegisterConfigBean Register_Config;
            private RegistersiteBean Registersite;
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

            public Object getPrice() {
                return Price;
            }

            public void setPrice(Object Price) {
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

            public Object getRegistersiteId() {
                return RegistersiteId;
            }

            public void setRegistersiteId(Object RegistersiteId) {
                this.RegistersiteId = RegistersiteId;
            }

            public Object getConfigId() {
                return ConfigId;
            }

            public void setConfigId(Object ConfigId) {
                this.ConfigId = ConfigId;
            }

            public String getReservateTime() {
                return ReservateTime;
            }

            public void setReservateTime(String ReservateTime) {
                this.ReservateTime = ReservateTime;
            }


            public Object getSeq() {
                return Seq;
            }

            public void setSeq(Object Seq) {
                this.Seq = Seq;
            }
        }
    }
}
