package com.kingja.cardpackage.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/4/19 11:18
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetBindCarList {

    /**
     * ResultCode : 0
     * ResultText : 获取绑定列表成功
     * DataTypeCode : GetBindCarList
     * TaskID : 1
     * Content : {"IsGrant":0,"BindingCars":[{"BindingID":"bc6ddb76-c13e-4f7a-b869-baf89279c6fc",
     * "PlateNumber":"9999888","Status":1,"IsRead":1,
     * "AutoDeployRecords":[{"LISTID":"cd813201273d43199304d7034061fda8","AutoStatus":1,"Week":"1111111",
     * "StartTime":"10:04","EndTime":"12:04"}]}]}
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
         * IsGrant : 0
         * BindingCars : [{"BindingID":"bc6ddb76-c13e-4f7a-b869-baf89279c6fc","PlateNumber":"9999888","Status":1,
         * "IsRead":1,"AutoDeployRecords":[{"LISTID":"cd813201273d43199304d7034061fda8","AutoStatus":1,
         * "Week":"1111111","StartTime":"10:04","EndTime":"12:04"}]}]
         */

        private int IsGrant;
        private List<BindingCarsBean> BindingCars;

        public int getIsGrant() {
            return IsGrant;
        }

        public void setIsGrant(int IsGrant) {
            this.IsGrant = IsGrant;
        }

        public List<BindingCarsBean> getBindingCars() {
            return BindingCars;
        }

        public void setBindingCars(List<BindingCarsBean> BindingCars) {
            this.BindingCars = BindingCars;
        }

        public static class BindingCarsBean {
            /**
             * BindingID : bc6ddb76-c13e-4f7a-b869-baf89279c6fc
             * PlateNumber : 9999888
             * Status : 1
             * IsRead : 1
             * AutoDeployRecords : [{"LISTID":"cd813201273d43199304d7034061fda8","AutoStatus":1,"Week":"1111111",
             * "StartTime":"10:04","EndTime":"12:04"}]
             */

            private String BindingID;
            private String PlateNumber;
            private int Status;
            private int IsRead;
            private List<AutoDeployRecordsBean> AutoDeployRecords;

            public String getBindingID() {
                return BindingID;
            }

            public void setBindingID(String BindingID) {
                this.BindingID = BindingID;
            }

            public String getPlateNumber() {
                return PlateNumber;
            }

            public void setPlateNumber(String PlateNumber) {
                this.PlateNumber = PlateNumber;
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

            public List<AutoDeployRecordsBean> getAutoDeployRecords() {
                return AutoDeployRecords;
            }

            public void setAutoDeployRecords(List<AutoDeployRecordsBean> AutoDeployRecords) {
                this.AutoDeployRecords = AutoDeployRecords;
            }

            public static class AutoDeployRecordsBean {
                /**
                 * LISTID : cd813201273d43199304d7034061fda8
                 * AutoStatus : 1
                 * Week : 1111111
                 * StartTime : 10:04
                 * EndTime : 12:04
                 */

                private String LISTID;
                private int AutoStatus;
                private String Week;
                private String StartTime;
                private String EndTime;

                public String getLISTID() {
                    return LISTID;
                }

                public void setLISTID(String LISTID) {
                    this.LISTID = LISTID;
                }

                public int getAutoStatus() {
                    return AutoStatus;
                }

                public void setAutoStatus(int AutoStatus) {
                    this.AutoStatus = AutoStatus;
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
            }
        }
    }
}
