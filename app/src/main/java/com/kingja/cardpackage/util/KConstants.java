package com.kingja.cardpackage.util;

/**
 * Description：TODO
 * Create Time：2016/8/4 16:53
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class KConstants {
    /**
     * Webservice参数
     */
//    public static final String WEBSERVER_URL = "http://zafkapp.test.iotone.cn:12026/rentalestate.asmx";//
// WebServices访问地址，测试
//    public static final String WEBSERVER_URL = "http://122.228.188.210:20043/WebCardHolder.asmx";// WebServices访问地址
    public static final String WEBSERVER_URL = com.tdr.wisdome.util.Constants.WEBSERVER_URL;// WebServices访问地址
    public static final String WEBSERVER_NAMESPACE = "http://tempuri.org/";// 命名空间
    public static final String WEBSERVER_REREQUEST = "CardHolder";
    public static final String APPLICATION_NAME = "CardPackage";
    public static final String CITY_POLICE_DB = "citypolice_wz.db";
    public static final String E_CARD = "e_card.db";

    /*  ============================================  卡类型  ============================================*/

    /*我的住房*/
    public static final String CARD_TYPE_HOUSE = "1001";
    /*我家出租屋*/
    public static final String CARD_TYPE_RENT = "1002";
    /*我的店*/
    public static final String CARD_TYPE_SHOP = "1004";
    /*出租房代管*/
    public static final String CARD_TYPE_AGENT = "1007";
    /*出租房中介*/
    public static final String CARD_TYPE_INTERMEDIARY = "1008";
    /*我的车*/
    public static final String CARD_TYPE_CAR = "1003";


    public static final int ROLE_INTERMEDIARY = 4;
    public static final int ROLE_RENT = 2;
    public static final int ROLE_AGENT = 5;

    /*  ============================================  接口方法  ============================================*/

    /*我家出租屋列表*/
    public static final String ChuZuWu_List = "ChuZuWu_List";
    /*我的店列表*/
    public static final String ShangPu_List = "ShangPu_List";
    /*出租房代管列表*/
    public static final String ChuZuWu_ListByManager = "ChuZuWu_ListByManager";
    /*我的住房列表*/
    public static final String ChuZuWu_ListByRenter = "ChuZuWu_ListByRenter";
    /*人员管理列表*/
    public static final String ChuZuWu_MenPaiAuthorizationList = "ChuZuWu_MenPaiAuthorizationList";
    /*查询房间信息*/
    public static final String ChuZuWu_RoomInfo = "ChuZuWu_RoomInfo";
    /*修改房间信息*/
    public static final String ChuZuWu_ModifyRoom = "ChuZuWu_ModifyRoom";
    /*设备信息*/
    public static final String ChuZuWu_DeviceLists = "ChuZuWu_DeviceLists";
    /*申报列表*/
    public static final String ChuZuWu_LKSelfReportingList = "ChuZuWu_LKSelfReportingList";
    /*申报离开*/
    public static final String ChuZuWu_LKSelfReportingOut = "ChuZuWu_LKSelfReportingOut";
    /*自主申报*/
    public static final String ChuZuWu_LKSelfReportingIn = "ChuZuWu_LKSelfReportingIn";
    /*手动撤布防*/
    public static final String ChuZuWu_SetDeployStatus = "ChuZuWu_SetDeployStatus";
    /*卡包登录*/
    public static final String User_LogInForKaBao = "User_LogInForKaBao";
    /*店铺撤布防*/
    public static final String ShangPu_SetDeployStatus = "ShangPu_SetDeployStatus";
    /*店铺员工管理*/
    public static final String ShangPu_EmployeeList = "ShangPu_EmployeeList";
    /*店铺移除员工*/
    public static final String ShangPu_DismissEmployee = "ShangPu_DismissEmployee";
    /*店铺信息*/
    public static final String ShangPu_ViewInfo = "ShangPu_ViewInfo";
    /*修改店铺信息*/
    public static final String ShangPu_Modify = "ShangPu_Modify";
    /*店铺二维码*/
    public static final String ShangPu_AddEmployee = "ShangPu_AddEmployee";
    /*店铺设备列表*/
    public static final String ShangPu_DeviceLists = "ShangPu_DeviceLists";
    /*店铺设备移除*/
    public static final String Common_RemoveDevice = "Common_RemoveDevice";
    /*店铺设备绑定*/
    public static final String Common_AddDevice = "Common_AddDevice";
    /*加入店铺*/
    public static final String ShangPu_JoinShangPu = "ShangPu_JoinShangPu";
    /*添加店铺*/
    public static final String ShangPu_Add = "ShangPu_Add";
    /*标准地址关键字检索*/
    public static final String Basic_StandardAddressCodeByKey = "Basic_StandardAddressCodeByKey";
    /*查看三实有出租房信息*/
    public static final String ChuZuWu_GetSSYByStandAddressCode = "ChuZuWu_GetSSYByStandAddressCode";
    /*修改上下班时间*/
    public static final String ShangPu_WorkTimeModify = "ShangPu_WorkTimeModify";
    /*出租房预警信息*/
    public static final String ChuZuWu_MessageList = "ChuZuWu_MessageList";
    /*商铺预警信息*/
    public static final String ShangPu_MessageList = "ShangPu_MessageList";
    /*预警详细信息*/
    public static final String Message_InquireDetailOfMessage = "Message_InquireDetailOfMessage";
    /*出租屋信息*/
    public static final String ChuZuWu_Info = "ChuZuWu_Info";
    /*管理员列表*/
    public static final String ChuZuWu_AdminList = "ChuZuWu_AdminList";
    /*出租房二维码*/
    public static final String ChuZuWu_AddAdmin = "ChuZuWu_AddAdmin";
    /*加入管理员*/
    public static final String ChuZuWu_JoinManage = "ChuZuWu_JoinManage";
    /*删除管理员*/
    public static final String ChuZuWu_RemoveAdmin = "ChuZuWu_RemoveAdmin";
    /*查看派出所*/
    public static final String Basic_PaiChuSuoOfStandardAddress = "Basic_PaiChuSuoOfStandardAddress";
    /*员工商铺列表*/
    public static final String ShangPu_ListByEmp = "ShangPu_ListByEmp";
    /*员工删除店铺*/
    public static final String ShangPu_UserOut = "ShangPu_UserOut";
    /*转让店铺*/
    public static final String ShangPu_Transfer = "ShangPu_Transfer";
    /*接收店铺*/
    public static final String ShangPu_TakeOver = "ShangPu_TakeOver";
    /*我的车列表*/
    public static final String GetBindingList = "GetBindingList";
    /*获取编码表*/
    public static final String GetCodeList = "GetCodeList";
    /*获取理赔信息*/
    public static final String GetClaimInfo = "GetClaimInfo";
    /*申报理赔*/
    public static final String DeclareClaimInfo = "DeclareClaimInfo";
    /*获取手机验证码*/
    public static final String SendCodeSms = "SendCodeSms";
    /*获取预约登记点*/
    public static final String GetRegistersiteList = "GetRegistersiteList";
    /*获取预约时间段*/
    public static final String GetRegister_ConfigList = "GetRegister_ConfigList";
    /*编辑预约信息*/
    public static final String EditPreRatePlus = "EditPreRatePlus";
    /*获取预登记列表*/
    public static final String GetPreRate = "GetPreRate";
    /*获取预登记信息*/
    public static final String GetPreRateOne = "GetPreRateOne";
    /*登录*/
    public static final String Login = "Login";
    /*注册*/
    public static final String AddUsers = "AddUsers";
    /*是否注册*/
    public static final String IsRegisterPhone = "IsRegisterPhone";
    /*车辆首页*/
    public static final String IndexData = "IndexData";
    /*开启布防*/
    public static final String AddElecDeploy = "AddElecDeploy";
    /*关闭布防*/
    public static final String DelElecDeploy = "DelElecDeploy";
    /*解绑*/
    public static final String DelPreRate = "DelPreRate";
    /*预登记*/
    public static final String AddPreRate = "AddPreRate"; /*预登记信息修改*/
    public static final String EditPreRate = "EditPreRate";
    /*解除车辆绑定*/
    public static final String UnBinding = "UnBinding";
    /*重置密码*/
    public static final String ChangePassword = "ChangePassword";
    /*获取我的消息*/
    public static final String GetUserMessage = "GetUserMessage";
    /*设置消息已读*/
    public static final String SetUserMessage = "SetUserMessage";
    /*获取我的消息 分页*/
    public static final String GetUserMessagePager = "GetUserMessagePager";
    /*设置所有消息已读*/
    public static final String SetUserMessageAll = "SetUserMessageAll";
    /*出租房中介查询*/
    public static final String ChuZuWu_AgencyInquire = "ChuZuWu_AgencyInquire";
    /*保险列表*/
    public static final String GetClaimInfoList = "GetClaimInfoList";
    /*车辆报警记录*/
    public static final String GetMessagePager = "GetMessagePager";
    /*我的消息*/
    public static final String GetLastUserMessage = "GetLastUserMessage";
    /*委托布防*/
    public static final String SetEntrustDeploy = "SetEntrustDeploy";
    /*自动布防*/
    public static final String SetAutoDeploy = "SetAutoDeploy";
    /*新的获取绑定车辆*/
    public static final String GetBindCarList = "GetBindCarList";
    /*更新功能表*/
    public static final String UpdateFunctionList = "UpdateFunctionList";
    /*获取城市列表*/
    public static final String GetCityList = "GetCityList";
    /*获取卡片列表*/
    public static final String GetUserCards = "GetUserCards";
    /*增加用户卡*/
    public static final String AddUserCards = "AddUserCards";
    /*删除用户卡*/
    public static final String DeleteUserCards = "DeleteUserCards";
    /*设置车辆警报已读*/
    public static final String KB_IsRead = "KB_IsRead";
    /*电子发票列表*/
    public static final String GetInvoiceInfoList = "GetInvoiceInfoList";
    /*无标准地址申报*/
    public static final String ChuZuWu_AgencySelfReportingIn = "ChuZuWu_AgencySelfReportingIn";
    /*无标准地址申报人员列表*/
    public static final String ChuZuWu_AgencySelfReportingList = "ChuZuWu_AgencySelfReportingList";
    /*无标准地址申报人员删除*/
    public static final String ChuZuWu_AgencySelfReportingOut = "ChuZuWu_AgencySelfReportingOut";
    /*获取城市支持的卡列表*/
    public static final String GetCityCardList = "GetCityCardList";
    /*NFC门禁*/
    public static final String NFCDevice_List = "NFCDevice_List";

}
