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

    /*================================GET================================*/
    public static String getToken() {
        return (String) SpUtils.get(TOKEN, EMPTY);
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

    /*================================PUT================================*/

    public static void putToken(String token) {
        SpUtils.put(TOKEN, token);
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


}
