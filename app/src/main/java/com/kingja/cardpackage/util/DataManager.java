package com.kingja.cardpackage.util;


/**
 * Description：TODO
 * Create Time：2016/8/15 13:51
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DataManager {
    private static final String TOKEN = "token";
    private static final String REAL_NAME = "realName";
    private static final String USER_ID = "userId";
    private static final String USER_PHONE = "userPhone";
    private static final String USER_NAME = "userName";
    private static final String ID_CARD = "userIdentitycard";
    private static final String CITY_NAME = "cityName";
    private static final String CITY_CODE = "cityCode";
    private static final String LAST_CITY = "lastCity";
    private static final String LAST_UPDATE_CAR_BRAND = "LAST_UPDATE_CAR_BRAND";
    private static final String CARD_CODE = "CARD_CODE";
    private static final String EMPTY = "";
    private static final String LAST_UPDATE_FUNCTION = "LAST_UPDATE_FUNCTION";
    private static final String BIRTHDAY = "birthday";
    private static final String ADDRESS = "address";
    private static final String SEX = "sex";
    private static final String CERTIFICATION = "CERTIFICATION";
    private static final String PHONE = "Phone";
    private static final String RESIDEADDRESS = "Resideaddress";
    private static final String UNITID = "UnitId";
    private static final String PROVINCECITYAREA = "provinceCityArea";

    private static final String PROVINCE = "PROVINCE";
    private static final String CITY = "CITY";
    private static final String DISTRICT = "DISTRICT";

    /*================================GET================================*/
    public static String getToken() {
        return (String) SpUtils.get(TOKEN, EMPTY);
    }

    public static String getUnitId() {
        return (String) SpUtils.get(UNITID, EMPTY);
    }

    public static String getLastUpdateFunction() {
        return (String) SpUtils.get(LAST_UPDATE_FUNCTION, EMPTY);
    }

    public static String getCityName() {
        return (String) SpUtils.get(CITY_NAME, EMPTY);
    }

    public static String getLastCity() {
        return (String) SpUtils.get(LAST_CITY, EMPTY);
    }

    public static String getUserId() {
        return (String) SpUtils.get(USER_ID, EMPTY);
    }

    public static String getUserPhone() {
        return (String) SpUtils.get(USER_PHONE, EMPTY);
    }

    public static String getUserName() {
        return (String) SpUtils.get(USER_NAME, EMPTY);
    }

    public static String getIdCard() {
        return (String) SpUtils.get(ID_CARD, EMPTY);
    }

    public static String getRealName() {
        return (String) SpUtils.get(REAL_NAME, EMPTY);
    }

    public static String getCityCode() {
        return (String) SpUtils.get(CITY_CODE, EMPTY);
    }

    public static String getCardCode() {
        return (String) SpUtils.get(CARD_CODE, EMPTY);
    }

    public static String getLastUpdateCarBrand() {
        return (String) SpUtils.get(LAST_UPDATE_CAR_BRAND, "1990-01-01 00:00:01");
    }

    public static String getAddress() {
        return (String) SpUtils.get(ADDRESS, EMPTY);
    }

    public static String getSex() {
        return (String) SpUtils.get(SEX, EMPTY);
    }

    public static String getBirthday() {
        return (String) SpUtils.get(BIRTHDAY, EMPTY);
    }

    public static String getResideaddress() {
        return (String) SpUtils.get(RESIDEADDRESS, EMPTY);
    }

    public static String getCertification() {
        return (String) SpUtils.get(CERTIFICATION, EMPTY);
    }

    public static String getProvinceCityArea() {
        return (String) SpUtils.get(PROVINCECITYAREA, EMPTY);
    }

    public static String getPhone() {
        return (String) SpUtils.get(PHONE, EMPTY);
    }

    public static String getProvince() {
        return (String) SpUtils.get(PROVINCE, EMPTY);
    }
    public static String getCity() {
        return (String) SpUtils.get(CITY, EMPTY);
    }
    public static String getDistrict() {
        return (String) SpUtils.get(DISTRICT, EMPTY);
    }
    /*================================PUT================================*/

    public static void putToken(String token) {
        SpUtils.put(TOKEN, token);
    }

    public static void putUnitId(String unitId) {
        SpUtils.put(UNITID, unitId);
    }

    public static void putUserId(String userId) {
        SpUtils.put(USER_ID, userId);
    }

    public static void putUserPhone(String userPhone) {
        SpUtils.put(USER_PHONE, userPhone);
    }

    public static void putUserName(String userName) {
        SpUtils.put(USER_NAME, userName);
    }

    public static void putIdCard(String idCard) {
        SpUtils.put(ID_CARD, idCard);
    }

    public static void putResideaddress(String resideaddress) {
        SpUtils.put(RESIDEADDRESS, resideaddress);
    }

    public static void putProvinceCityArea(String provinceCityArea) {
        SpUtils.put(PROVINCECITYAREA, provinceCityArea);
    }

    public static void putProvince(String province) {
        SpUtils.put(PROVINCE, province);
    }

    public static void putCity(String city) {
        SpUtils.put(CITY, city);
    }

    public static void putDistrict(String district) {
        SpUtils.put(DISTRICT, district);
    }

    public static void putCityName(String city) {
        SpUtils.put(CITY_NAME, city);
    }

    public static void putLastCity(String city) {
        SpUtils.put(LAST_CITY, city);
    }

    public static void putLastUpdateCarBrand(String date) {
        SpUtils.put(LAST_UPDATE_CAR_BRAND, date);
    }

    public static void putCardCodes(String cardCode) {
        SpUtils.put(CARD_CODE, cardCode);
    }

    public static void putCityCode(String cityCode) {
        SpUtils.put(CITY_CODE, cityCode);
    }

    public static void putLastUpdateFunction(String lastUpdateFunction) {
        SpUtils.put(LAST_UPDATE_FUNCTION, lastUpdateFunction);
    }

    public static void putAddresse(String address) {
        SpUtils.put(ADDRESS, address);
    }

    public static void putBirthday(String birthday) {
        SpUtils.put(BIRTHDAY, birthday);
    }

    public static void putPhone(String userPhone) {
        SpUtils.put(PHONE, userPhone);
    }

    public static void putSex(String sex) {
        SpUtils.put(SEX, sex);
    }

    public static void putRealName(String realName) {
        SpUtils.put(REAL_NAME, realName);
    }

    public static void putCertification(String certification) {
        SpUtils.put(CERTIFICATION, certification);
    }

}
