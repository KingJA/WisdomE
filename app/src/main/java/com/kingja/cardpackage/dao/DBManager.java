package com.kingja.cardpackage.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.kingja.cardpackage.greendaobean.ErrorInfo;

import java.util.List;

public class DBManager {
    private final static String dbName = "KingJA_Power";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;
    private final SQLiteDatabase db;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        db = getWritableDatabase();
    }

    /**
     * 获取单例引用
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    private ErrorInfoDao getErrorInfoDao() {
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        return daoSession.getErrorInfoDao();
    }

    /**
     * 插入蓝牙记录
     */
    public void insertBleInfo(ErrorInfo errorInfo) {
        ErrorInfoDao errorInfoDao = getErrorInfoDao();
        errorInfoDao.insert(errorInfo);
    }


    /**
     * 删除所有蓝牙信息
     */
    public void deleteAllErrorInfo() {
        ErrorInfoDao errorInfoDao = getErrorInfoDao();
        errorInfoDao.deleteAll();
    }

    /**
     * 获取所有异常记录
     */
    public List<ErrorInfo> getErrorInfos() {
        ErrorInfoDao errorInfoDao = getErrorInfoDao();
        List<ErrorInfo> list = errorInfoDao.loadAll();
        return list;
    }

}
