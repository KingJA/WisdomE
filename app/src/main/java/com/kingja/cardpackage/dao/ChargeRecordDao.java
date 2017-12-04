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
        public final static Property Sn = new Property(0, String.class, "sn", true, "SN");
        public final static Property StartTime = new Property(1, String.class, "startTime", false, "START_TIME");
        public final static Property EndTime = new Property(2, String.class, "endTime", false, "END_TIME");
        public final static Property EndReason = new Property(3, int.class, "endReason", false, "END_REASON");
        public final static Property MaxVoltage = new Property(4, double.class, "maxVoltage", false, "MAX_VOLTAGE");
        public final static Property MaxElectricity = new Property(5, double.class, "maxElectricity", false, "MAX_ELECTRICITY");
        public final static Property TotlePower = new Property(6, double.class, "totlePower", false, "TOTLE_POWER");
        public final static Property EnvironmentTemperature = new Property(7, double.class, "environmentTemperature", false, "ENVIRONMENT_TEMPERATURE");
        public final static Property MaxBatteryTemperature = new Property(8, double.class, "maxBatteryTemperature", false, "MAX_BATTERY_TEMPERATURE");
        public final static Property MaxChargerTemperature = new Property(9, double.class, "maxChargerTemperature", false, "MAX_CHARGER_TEMPERATURE");
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
                "\"SN\" TEXT PRIMARY KEY NOT NULL ," + // 0: sn
                "\"START_TIME\" TEXT," + // 1: startTime
                "\"END_TIME\" TEXT," + // 2: endTime
                "\"END_REASON\" INTEGER NOT NULL ," + // 3: endReason
                "\"MAX_VOLTAGE\" REAL NOT NULL ," + // 4: maxVoltage
                "\"MAX_ELECTRICITY\" REAL NOT NULL ," + // 5: maxElectricity
                "\"TOTLE_POWER\" REAL NOT NULL ," + // 6: totlePower
                "\"ENVIRONMENT_TEMPERATURE\" REAL NOT NULL ," + // 7: environmentTemperature
                "\"MAX_BATTERY_TEMPERATURE\" REAL NOT NULL ," + // 8: maxBatteryTemperature
                "\"MAX_CHARGER_TEMPERATURE\" REAL NOT NULL );"); // 9: maxChargerTemperature
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CHARGE_RECORD\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ChargeRecord entity) {
        stmt.clearBindings();
 
        String sn = entity.getSn();
        if (sn != null) {
            stmt.bindString(1, sn);
        }
 
        String startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindString(2, startTime);
        }
 
        String endTime = entity.getEndTime();
        if (endTime != null) {
            stmt.bindString(3, endTime);
        }
        stmt.bindLong(4, entity.getEndReason());
        stmt.bindDouble(5, entity.getMaxVoltage());
        stmt.bindDouble(6, entity.getMaxElectricity());
        stmt.bindDouble(7, entity.getTotlePower());
        stmt.bindDouble(8, entity.getEnvironmentTemperature());
        stmt.bindDouble(9, entity.getMaxBatteryTemperature());
        stmt.bindDouble(10, entity.getMaxChargerTemperature());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ChargeRecord entity) {
        stmt.clearBindings();
 
        String sn = entity.getSn();
        if (sn != null) {
            stmt.bindString(1, sn);
        }
 
        String startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindString(2, startTime);
        }
 
        String endTime = entity.getEndTime();
        if (endTime != null) {
            stmt.bindString(3, endTime);
        }
        stmt.bindLong(4, entity.getEndReason());
        stmt.bindDouble(5, entity.getMaxVoltage());
        stmt.bindDouble(6, entity.getMaxElectricity());
        stmt.bindDouble(7, entity.getTotlePower());
        stmt.bindDouble(8, entity.getEnvironmentTemperature());
        stmt.bindDouble(9, entity.getMaxBatteryTemperature());
        stmt.bindDouble(10, entity.getMaxChargerTemperature());
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public ChargeRecord readEntity(Cursor cursor, int offset) {
        ChargeRecord entity = new ChargeRecord( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // sn
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // startTime
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // endTime
            cursor.getInt(offset + 3), // endReason
            cursor.getDouble(offset + 4), // maxVoltage
            cursor.getDouble(offset + 5), // maxElectricity
            cursor.getDouble(offset + 6), // totlePower
            cursor.getDouble(offset + 7), // environmentTemperature
            cursor.getDouble(offset + 8), // maxBatteryTemperature
            cursor.getDouble(offset + 9) // maxChargerTemperature
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ChargeRecord entity, int offset) {
        entity.setSn(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setStartTime(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setEndTime(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setEndReason(cursor.getInt(offset + 3));
        entity.setMaxVoltage(cursor.getDouble(offset + 4));
        entity.setMaxElectricity(cursor.getDouble(offset + 5));
        entity.setTotlePower(cursor.getDouble(offset + 6));
        entity.setEnvironmentTemperature(cursor.getDouble(offset + 7));
        entity.setMaxBatteryTemperature(cursor.getDouble(offset + 8));
        entity.setMaxChargerTemperature(cursor.getDouble(offset + 9));
     }
    
    @Override
    protected final String updateKeyAfterInsert(ChargeRecord entity, long rowId) {
        return entity.getSn();
    }
    
    @Override
    public String getKey(ChargeRecord entity) {
        if(entity != null) {
            return entity.getSn();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ChargeRecord entity) {
        return entity.getSn() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
