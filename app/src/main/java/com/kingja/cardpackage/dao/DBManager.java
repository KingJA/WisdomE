package com.kingja.cardpackage.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.kingja.cardpackage.greendaobean.ChargeRecord;
import com.kingja.cardpackage.greendaobean.ErrorInfo;

import java.util.List;

public class DBManager {
    private final static String dbName = "KingJA_Ble";
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

    private ChargeRecordDao getChargeRecordDao() {
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        return daoSession.getChargeRecordDao();
    }

    /**
     * 插入充电记录
     */
    public void insertChargeRecord(ChargeRecord chargeRecord) {
        ChargeRecordDao chargeRecordDao = getChargeRecordDao();
        chargeRecordDao.insertOrReplace(chargeRecord);
    }

    /**
     * 插入异常记录
     */
    public void insertErrorInfo(ErrorInfo errorInfo) {
        ErrorInfoDao errorInfoDao = getErrorInfoDao();
        errorInfoDao.insertOrReplace(errorInfo);
    }


    /**
     * 删除所有异常记录
     */
    public void deleteAllErrorInfos() {
        ErrorInfoDao errorInfoDao = getErrorInfoDao();
        errorInfoDao.deleteAll();
    }

    /**
     * 删除所有充电记录
     */
    public void deleteAllChargeRecords() {
        ChargeRecordDao chargeRecordDao = getChargeRecordDao();
        chargeRecordDao.deleteAll();
    }


    /**
     * 获取所有充电记录
     */
    public List<ChargeRecord> getChargeRecords() {
        ChargeRecordDao chargeRecordDao = getChargeRecordDao();
        List<ChargeRecord> list = chargeRecordDao.loadAll();
        return list;
    }

    /**
     * 获取指定充电记录
     */
    public ChargeRecord getChargeRecordById(String sn) {
        ChargeRecord chargeRecord = null;
        ChargeRecordDao chargeRecordDao = getChargeRecordDao();
        List<ChargeRecord> results = chargeRecordDao.queryBuilder().where(ChargeRecordDao.Properties.Sn.eq(sn)).list();
        if (results != null && results.size() > 0) {
            chargeRecord = results.get(0);
        }
        return chargeRecord;
    }

    /**
     * 获取所有异常记录
     */
    public List<ErrorInfo> getErrorInfos() {
        ErrorInfoDao errorInfoDao = getErrorInfoDao();
        List<ErrorInfo> list = errorInfoDao.loadAll();
        return list;
    }

    /**
     * 修改充电记录
     */
    public void updateChargeRecords(ChargeRecord chargeRecord) {
        ChargeRecordDao chargeRecordDao = getChargeRecordDao();
        chargeRecordDao.update(chargeRecord);
    }


}
