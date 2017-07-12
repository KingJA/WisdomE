package com.kingja.cardpackage.entiy;

import java.io.Serializable;
import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/12/2 14:50
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetBindingList {

    /**
     * ResultCode : 0
     * ResultText : 获取绑定列表成功
     * DataTypeCode : GetBindingList
     * TaskID : 1
     * Content : [{"BindingID":"bc6ddb76-c13e-4f7a-b869-baf89279c6fc",
     * "UserID":"5f1289f6-74f2-46d0-9d72-31ae3e519b65","EcID":"44688033-0FDB-4B00-A716-F46F605873A1",
     * "CreateTime":"2017-04-17 09:31:22","PlateNumber":"9999888","CardID":"330326198710110734","CardType":1,
     * "OwnerName":"type","Phone":null,"Code":null,"Auditstate":1,"Status":1,"IsRead":1,
     * "ElectricCar":{"PrerateID":null,"PrerateName":null,"Vehiclebrand":"79","Vehiclemodels":null,"ColorID":"16",
     * "Engineno":"1","Shelvesno":"1","BuyDate":"2017-04-17 00:00:00","Price":null,"OwnerName":"type","CardType":1,
     * "Cardid":"330326198710110734","Phone1":"13736350001","Phone2":null,"Remark":null,"CreateTime":"2017-04-17
     * 09:31:22","State":0,"UserID":"5f1289f6-74f2-46d0-9d72-31ae3e519b65","InvalidTime":"0001-01-01 00:00:00",
     * "IsDelete":0,"RegistersiteId":"","ConfigId":"","ReservateTime":"0001-01-01 00:00:00",
     * "Register_Config":{"ConfigId":null,"RegistersiteId":null,"OnTime":null,"OffTime":null,"InstallCnt":null,
     * "SurplusNum":null,"InTime":null,"Week":null,"WeekName":"未知"},"Registersite":{"RegistersiteId":"",
     * "RegistersiteName":"","DepartmentId":"","CodeNo":"","Adress":"","Lat":0,"Lng":0,"Name":"","Phone":"",
     * "Remark":"","isdel":0,"UnitNo":""},"Seq":0,"ColorName":null,"VehicleBrandName":null},"ClaimState":0,
     * "PolicyEndTime":null,"BuyDate":"2017/4/17 0:00:00","IsAuto":0,"Week":null,"StartTime":"0001-01-01 00:00:00",
     * "EndTime":"0001-01-01 00:00:00"},{"BindingID":"bc6ddb76-c13e-4f7a-b869-baf89279c6fd",
     * "UserID":"5f1289f6-74f2-46d0-9d72-31ae3e519b65","EcID":"5A05F952-1A88-4FC4-AA5C-AD6D5BB07458",
     * "CreateTime":"2017-04-17 10:31:22","PlateNumber":"8889999","CardID":"330326198710110734","CardType":1,
     * "OwnerName":"zhangsan","Phone":null,"Code":null,"Auditstate":0,"Status":0,"IsRead":1,
     * "ElectricCar":{"PrerateID":null,"PrerateName":null,"Vehiclebrand":"24","Vehiclemodels":null,"ColorID":"15",
     * "Engineno":"8","Shelvesno":"8","BuyDate":"2017-04-17 00:00:00","Price":null,"OwnerName":"zhangsan",
     * "CardType":1,"Cardid":"330326198710110734","Phone1":"13736350001","Phone2":null,"Remark":null,
     * "CreateTime":"2017-04-17 10:31:22","State":0,"UserID":"5f1289f6-74f2-46d0-9d72-31ae3e519b65",
     * "InvalidTime":"0001-01-01 00:00:00","IsDelete":0,"RegistersiteId":"","ConfigId":"","ReservateTime":"0001-01-01
     * 00:00:00","Register_Config":{"ConfigId":null,"RegistersiteId":null,"OnTime":null,"OffTime":null,
     * "InstallCnt":null,"SurplusNum":null,"InTime":null,"Week":null,"WeekName":"未知"},
     * "Registersite":{"RegistersiteId":"","RegistersiteName":"","DepartmentId":"","CodeNo":"","Adress":"","Lat":0,
     * "Lng":0,"Name":"","Phone":"","Remark":"","isdel":0,"UnitNo":""},"Seq":0,"ColorName":null,
     * "VehicleBrandName":null},"ClaimState":0,"PolicyEndTime":null,"BuyDate":"2017/4/17 0:00:00","IsAuto":0,
     * "Week":null,"StartTime":"0001-01-01 00:00:00","EndTime":"0001-01-01 00:00:00"}]
     */

    private String ResultCode;
    private String ResultText;
    private String DataTypeCode;
    private String TaskID;
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

    public static class ContentBean implements Serializable {
        /**
         * BindingID : bc6ddb76-c13e-4f7a-b869-baf89279c6fc
         * UserID : 5f1289f6-74f2-46d0-9d72-31ae3e519b65
         * EcID : 44688033-0FDB-4B00-A716-F46F605873A1
         * CreateTime : 2017-04-17 09:31:22
         * PlateNumber : 9999888
         * CardID : 330326198710110734
         * CardType : 1
         * OwnerName : type
         * Phone : null
         * Code : null
         * Auditstate : 1
         * Status : 1
         * IsRead : 1
         * ElectricCar : {"PrerateID":null,"PrerateName":null,"Vehiclebrand":"79","Vehiclemodels":null,
         * "ColorID":"16","Engineno":"1","Shelvesno":"1","BuyDate":"2017-04-17 00:00:00","Price":null,
         * "OwnerName":"type","CardType":1,"Cardid":"330326198710110734","Phone1":"13736350001","Phone2":null,
         * "Remark":null,"CreateTime":"2017-04-17 09:31:22","State":0,
         * "UserID":"5f1289f6-74f2-46d0-9d72-31ae3e519b65","InvalidTime":"0001-01-01 00:00:00","IsDelete":0,
         * "RegistersiteId":"","ConfigId":"","ReservateTime":"0001-01-01 00:00:00",
         * "Register_Config":{"ConfigId":null,"RegistersiteId":null,"OnTime":null,"OffTime":null,"InstallCnt":null,
         * "SurplusNum":null,"InTime":null,"Week":null,"WeekName":"未知"},"Registersite":{"RegistersiteId":"",
         * "RegistersiteName":"","DepartmentId":"","CodeNo":"","Adress":"","Lat":0,"Lng":0,"Name":"","Phone":"",
         * "Remark":"","isdel":0,"UnitNo":""},"Seq":0,"ColorName":null,"VehicleBrandName":null}
         * ClaimState : 0
         * PolicyEndTime : null
         * BuyDate : 2017/4/17 0:00:00
         * IsAuto : 0
         * Week : null
         * StartTime : 0001-01-01 00:00:00
         * EndTime : 0001-01-01 00:00:00
         */

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
        private int AutoStatus;
        private int Status;
        private int IsRead;
        private int CalimState;
        private ElectricCarBean ElectricCar;
        private int ClaimState;
        private Object PolicyEndTime;
        private String BuyDate;
        private int IsAuto;
        private String Week;
        private String StartTime;
        private String EndTime;

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

        public Object getPolicyEndTime() {
            return PolicyEndTime;
        }

        public void setPolicyEndTime(Object PolicyEndTime) {
            this.PolicyEndTime = PolicyEndTime;
        }

        public String getBuyDate() {
            return BuyDate;
        }

        public void setBuyDate(String BuyDate) {
            this.BuyDate = BuyDate;
        }

        public int getIsAuto() {
            return IsAuto;
        }

        public void setIsAuto(int IsAuto) {
            this.IsAuto = IsAuto;
        }

        public String getWeek() {
            return Week;
        }

        public void setWeek(String Week) {
            this.Week = Week;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public int getCalimState() {
            return CalimState;
        }

        public void setCalimState(int calimState) {
            CalimState = calimState;
        }

        public int getAutoStatus() {
            return AutoStatus;
        }

        public void setAutoStatus(int autoStatus) {
            AutoStatus = autoStatus;
        }

        public static class ElectricCarBean {
            /**
             * PrerateID : null
             * PrerateName : null
             * Vehiclebrand : 79
             * Vehiclemodels : null
             * ColorID : 16
             * Engineno : 1
             * Shelvesno : 1
             * BuyDate : 2017-04-17 00:00:00
             * Price : null
             * OwnerName : type
             * CardType : 1
             * Cardid : 330326198710110734
             * Phone1 : 13736350001
             * Phone2 : null
             * Remark : null
             * CreateTime : 2017-04-17 09:31:22
             * State : 0
             * UserID : 5f1289f6-74f2-46d0-9d72-31ae3e519b65
             * InvalidTime : 0001-01-01 00:00:00
             * IsDelete : 0
             * RegistersiteId :
             * ConfigId :
             * ReservateTime : 0001-01-01 00:00:00
             * Register_Config : {"ConfigId":null,"RegistersiteId":null,"OnTime":null,"OffTime":null,
             * "InstallCnt":null,"SurplusNum":null,"InTime":null,"Week":null,"WeekName":"未知"}
             * Registersite : {"RegistersiteId":"","RegistersiteName":"","DepartmentId":"","CodeNo":"","Adress":"",
             * "Lat":0,"Lng":0,"Name":"","Phone":"","Remark":"","isdel":0,"UnitNo":""}
             * Seq : 0
             * ColorName : null
             * VehicleBrandName : null
             */

            private Object PrerateID;
            private Object PrerateName;
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
            private String RegistersiteId;
            private String ConfigId;
            private String ReservateTime;
            private RegisterConfigBean Register_Config;
            private RegistersiteBean Registersite;
            private int Seq;
            private Object ColorName;
            private Object VehicleBrandName;

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

            public Object getColorName() {
                return ColorName;
            }

            public void setColorName(Object ColorName) {
                this.ColorName = ColorName;
            }

            public Object getVehicleBrandName() {
                return VehicleBrandName;
            }

            public void setVehicleBrandName(Object VehicleBrandName) {
                this.VehicleBrandName = VehicleBrandName;
            }

            public static class RegisterConfigBean {
                /**
                 * ConfigId : null
                 * RegistersiteId : null
                 * OnTime : null
                 * OffTime : null
                 * InstallCnt : null
                 * SurplusNum : null
                 * InTime : null
                 * Week : null
                 * WeekName : 未知
                 */

                private Object ConfigId;
                private Object RegistersiteId;
                private Object OnTime;
                private Object OffTime;
                private Object InstallCnt;
                private Object SurplusNum;
                private Object InTime;
                private Object Week;
                private String WeekName;

                public Object getConfigId() {
                    return ConfigId;
                }

                public void setConfigId(Object ConfigId) {
                    this.ConfigId = ConfigId;
                }

                public Object getRegistersiteId() {
                    return RegistersiteId;
                }

                public void setRegistersiteId(Object RegistersiteId) {
                    this.RegistersiteId = RegistersiteId;
                }

                public Object getOnTime() {
                    return OnTime;
                }

                public void setOnTime(Object OnTime) {
                    this.OnTime = OnTime;
                }

                public Object getOffTime() {
                    return OffTime;
                }

                public void setOffTime(Object OffTime) {
                    this.OffTime = OffTime;
                }

                public Object getInstallCnt() {
                    return InstallCnt;
                }

                public void setInstallCnt(Object InstallCnt) {
                    this.InstallCnt = InstallCnt;
                }

                public Object getSurplusNum() {
                    return SurplusNum;
                }

                public void setSurplusNum(Object SurplusNum) {
                    this.SurplusNum = SurplusNum;
                }

                public Object getInTime() {
                    return InTime;
                }

                public void setInTime(Object InTime) {
                    this.InTime = InTime;
                }

                public Object getWeek() {
                    return Week;
                }

                public void setWeek(Object Week) {
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
                 * RegistersiteId :
                 * RegistersiteName :
                 * DepartmentId :
                 * CodeNo :
                 * Adress :
                 * Lat : 0.0
                 * Lng : 0.0
                 * Name :
                 * Phone :
                 * Remark :
                 * isdel : 0
                 * UnitNo :
                 */

                private String RegistersiteId;
                private String RegistersiteName;
                private String DepartmentId;
                private String CodeNo;
                private String Adress;
                private double Lat;
                private double Lng;
                private String Name;
                private String Phone;
                private String Remark;
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

                public String getCodeNo() {
                    return CodeNo;
                }

                public void setCodeNo(String CodeNo) {
                    this.CodeNo = CodeNo;
                }

                public String getAdress() {
                    return Adress;
                }

                public void setAdress(String Adress) {
                    this.Adress = Adress;
                }

                public double getLat() {
                    return Lat;
                }

                public void setLat(double Lat) {
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

                public String getUnitNo() {
                    return UnitNo;
                }

                public void setUnitNo(String UnitNo) {
                    this.UnitNo = UnitNo;
                }
            }
        }
    }
}
