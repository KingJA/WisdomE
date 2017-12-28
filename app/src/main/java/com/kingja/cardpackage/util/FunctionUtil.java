package com.kingja.cardpackage.util;

import com.kingja.cardpackage.db.ECardXutils3;
import com.kingja.cardpackage.entiy.UpdateFunctions;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2017/5/5 15:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FunctionUtil {
    public static boolean autoDeployalbe() {
        boolean autoDeployable = false;
        List<UpdateFunctions> autoDeploys = ECardXutils3.getInstance().selectAllWheres(UpdateFunctions.class,
                "CityCode", DataManager.getCityCode(), "ColumnValue", "2");
        if (autoDeploys!=null&&autoDeploys.size() > 0) {
            UpdateFunctions function = autoDeploys.get(0);
            if (function != null && (function.getIsValid() == 1)&& (function.getIsGrant() == 1)) {
                autoDeployable = true;
            }
        }
        return autoDeployable;
    }

    public static boolean showInvoicealbe() {
        boolean showInvoicealbe = false;
        List<UpdateFunctions> functionses = ECardXutils3.getInstance().selectAllWheres(UpdateFunctions.class,
                "CityCode", DataManager.getCityCode(), "ColumnValue", "3");
        if (functionses!=null&&functionses.size() > 0) {
            UpdateFunctions function = functionses.get(0);
            if (function != null && (function.getIsValid() == 1)&& (function.getIsGrant() == 1)) {
                showInvoicealbe = true;
            }
        }
        return showInvoicealbe;
    }
    public static boolean showWeibind() {
        boolean showInvoicealbe = false;
        List<UpdateFunctions> functionses = ECardXutils3.getInstance().selectAllWheres(UpdateFunctions.class,
                "CityCode", DataManager.getCityCode(), "ColumnValue", "4");
        if (functionses!=null&&functionses.size() > 0) {
            UpdateFunctions function = functionses.get(0);
            if (function != null && (function.getIsValid() == 1)&& (function.getIsGrant() == 1)) {
                showInvoicealbe = true;
            }
        }
        return showInvoicealbe;
    }
}
