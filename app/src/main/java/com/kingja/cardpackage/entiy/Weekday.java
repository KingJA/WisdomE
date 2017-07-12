package com.kingja.cardpackage.entiy;

/**
 * Description:TODO
 * Create Time:2017/4/18 15:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Weekday {
    private boolean checked;
    private String weekday;

    public Weekday(boolean checked, String weekday) {
        this.checked = checked;
        this.weekday = weekday;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }
}
