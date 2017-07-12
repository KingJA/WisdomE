package com.kingja.cardpackage.entiy;

/**
 * Description:TODO
 * Create Time:2017/4/21 16:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CityEvent {
    public CityEvent(String cityName) {
        this.cityName = cityName;
    }

    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
