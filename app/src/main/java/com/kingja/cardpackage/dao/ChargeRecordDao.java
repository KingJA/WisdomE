package com.kingja.cardpackage.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.kingja.cardpackage.greendaobean.ChargeRecord;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CHARGE_RECORD".
*/
public class ChargeRecordDao extends AbstractDao<ChargeRecord, String> {

    public static final String TABLENAME = "CHARGE_RECORD";

    /**
     * Properties of entity ChargeRecord.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property OrderNo = new Property(0, String.class, "orderNo", true, "ORDER_NO");
        public final static Property Sn = new Property(1, String.class, "sn", false, "SN");
        public final static Property StartTime = new Property(2, String.class, "startTime", false, "START_TIME");
        public final static Property EndTime = new Property(3, String.class, "endTime", false, "END_TIME");
        public final static Property EndReason = new Property(4, int.class, "endReason", false, "END_REASON");
        public final static Property MaxVoltage = new Property(5, double.class, "maxVoltage", false, "MAX_VOLTAGE");
        public final static Property MaxElectricity = new Property(6, double.class, "maxElectricity", false, "MAX_ELECTRICITY");
        public final static Property TotlePower = new Property(7, double.class, "totlePower", false, "TOTLE_POWER");
        public final static Property EnvironmentTemperature = new Property(8, double.class, "environmentTemperature", false, "ENVIRONMENT_TEMPERATURE");
        public final static Property MaxBatteryTemperature = new Property(9, double.class, "maxBatteryTemperature", false, "MAX_BATTERY_TEMPERATURE");
        public final static Property MaxChargerTemperature = new Property(10, double.class, "maxChargerTemperature", false, "MAX_CHARGER_TEMPERATURE");
    }


    public ChargeRecordDao(DaoConfig config) {
        super(config);
    }
    
    public ChargeRecordDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CHARGE_RECORD\" (" + //
                "\"ORDER_NO\" TEXT PRIMARY KEY NOT NULL ," + // 0: orderNo
                "\"SN\" TEXT," + // 1: sn
                "\"START_TIME\" TEXT," + // 2: startTime
                "\"END_TIME\" TEXT," + // 3: endTime
                "\"END_REASON\" INTEGER NOT NULL ," + // 4: endReason
                "\"MAX_VOLTAGE\" REAL NOT NULL ," + // 5: maxVoltage
                "\"MAX_ELECTRICITY\" REAL NOT NULL ," + // 6: maxElectricity
                "\"TOTLE_POWER\" REAL NOT NULL ," + // 7: totlePower
                "\"ENVIRONMENT_TEMPERATURE\" REAL NOT NULL ," + // 8: environmentTemperature
                "\"MAX_BATTERY_TEMPERATURE\" REAL NOT NULL ," + // 9: maxBatteryTemperature
                "\"MAX_CHARGER_TEMPERATURE\" REAL NOT NULL );"); // 10: maxChargerTemperature
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CHARGE_RECORD\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ChargeRecord entity) {
        stmt.clearBindings();
 
        String orderNo = entity.getOrderNo();
        if (orderNo != null) {
            stmt.bindString(1, orderNo);
        }
 
        String sn = entity.getSn();
        if (sn != null) {
            stmt.bindString(2, sn);
        }
 
        String startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindString(3, startTime);
        }
 
        String endTime = entity.getEndTime();
        if (endTime != null) {
            stmt.bindString(4, endTime);
        }
        stmt.bindLong(5, entity.getEndReason());
        stmt.bindDouble(6, entity.getMaxVoltage());
        stmt.bindDouble(7, entity.getMaxElectricity());
        stmt.bindDouble(8, entity.getTotlePower());
        stmt.bindDouble(9, entity.getEnvironmentTemperature());
        stmt.bindDouble(10, entity.getMaxBatteryTemperature());
        stmt.bindDouble(11, entity.getMaxChargerTemperature());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ChargeRecord entity) {
        stmt.clearBindings();
 
        String orderNo = entity.getOrderNo();
        if (orderNo != null) {
            stmt.bindString(1, orderNo);
        }
 
        String sn = entity.getSn();
        if (sn != null) {
            stmt.bindString(2, sn);
        }
 
        String startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindString(3, startTime);
        }
 
        String endTime = entity.getEndTime();
        if (endTime != null) {
            stmt.bindString(4, endTime);
        }
        stmt.bindLong(5, entity.getEndReason());
        stmt.bindDouble(6, entity.getMaxVoltage());
        stmt.bindDouble(7, entity.getMaxElectricity());
        stmt.bindDouble(8, entity.getTotlePower());
        stmt.bindDouble(9, entity.getEnvironmentTemperature());
        stmt.bindDouble(10, entity.getMaxBatteryTemperature());
        stmt.bindDouble(11, entity.getMaxChargerTemperature());
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public ChargeRecord readEntity(Cursor cursor, int offset) {
        ChargeRecord entity = new ChargeRecord( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // orderNo
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // sn
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // startTime
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // endTime
            cursor.getInt(offset + 4), // endReason
            cursor.getDouble(offset + 5), // maxVoltage
            cursor.getDouble(offset + 6), // maxElectricity
            cursor.getDouble(offset + 7), // totlePower
            cursor.getDouble(offset + 8), // environmentTemperature
            cursor.getDouble(offset + 9), // maxBatteryTemperature
            cursor.getDouble(offset + 10) // maxChargerTemperature
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ChargeRecord entity, int offset) {
        entity.setOrderNo(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setSn(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setStartTime(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setEndTime(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setEndReason(cursor.getInt(offset + 4));
        entity.setMaxVoltage(cursor.getDouble(offset + 5));
        entity.setMaxElectricity(cursor.getDouble(offset + 6));
        entity.setTotlePower(cursor.getDouble(offset + 7));
        entity.setEnvironmentTemperature(cursor.getDouble(offset + 8));
        entity.setMaxBatteryTemperature(cursor.getDouble(offset + 9));
        entity.setMaxChargerTemperature(cursor.getDouble(offset + 10));
     }
    
    @Override
    protected final String updateKeyAfterInsert(ChargeRecord entity, long rowId) {
        return entity.getOrderNo();
    }
    
    @Override
    public String getKey(ChargeRecord entity) {
        if(entity != null) {
            return entity.getOrderNo();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ChargeRecord entity) {
        return entity.getOrderNo() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}