package com.kingja.cardpackage.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.kingja.cardpackage.greendaobean.ErrorInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ERROR_INFO".
*/
public class ErrorInfoDao extends AbstractDao<ErrorInfo, String> {

    public static final String TABLENAME = "ERROR_INFO";

    /**
     * Properties of entity ErrorInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Sn = new Property(0, String.class, "sn", true, "SN");
        public final static Property ErrorTime = new Property(1, String.class, "errorTime", false, "ERROR_TIME");
        public final static Property ErrorMsg = new Property(2, String.class, "errorMsg", false, "ERROR_MSG");
        public final static Property ErrorType = new Property(3, int.class, "errorType", false, "ERROR_TYPE");
    }


    public ErrorInfoDao(DaoConfig config) {
        super(config);
    }
    
    public ErrorInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ERROR_INFO\" (" + //
                "\"SN\" TEXT PRIMARY KEY NOT NULL ," + // 0: sn
                "\"ERROR_TIME\" TEXT," + // 1: errorTime
                "\"ERROR_MSG\" TEXT," + // 2: errorMsg
                "\"ERROR_TYPE\" INTEGER NOT NULL );"); // 3: errorType
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ERROR_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ErrorInfo entity) {
        stmt.clearBindings();
 
        String sn = entity.getSn();
        if (sn != null) {
            stmt.bindString(1, sn);
        }
 
        String errorTime = entity.getErrorTime();
        if (errorTime != null) {
            stmt.bindString(2, errorTime);
        }
 
        String errorMsg = entity.getErrorMsg();
        if (errorMsg != null) {
            stmt.bindString(3, errorMsg);
        }
        stmt.bindLong(4, entity.getErrorType());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ErrorInfo entity) {
        stmt.clearBindings();
 
        String sn = entity.getSn();
        if (sn != null) {
            stmt.bindString(1, sn);
        }
 
        String errorTime = entity.getErrorTime();
        if (errorTime != null) {
            stmt.bindString(2, errorTime);
        }
 
        String errorMsg = entity.getErrorMsg();
        if (errorMsg != null) {
            stmt.bindString(3, errorMsg);
        }
        stmt.bindLong(4, entity.getErrorType());
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public ErrorInfo readEntity(Cursor cursor, int offset) {
        ErrorInfo entity = new ErrorInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // sn
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // errorTime
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // errorMsg
            cursor.getInt(offset + 3) // errorType
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ErrorInfo entity, int offset) {
        entity.setSn(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setErrorTime(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setErrorMsg(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setErrorType(cursor.getInt(offset + 3));
     }
    
    @Override
    protected final String updateKeyAfterInsert(ErrorInfo entity, long rowId) {
        return entity.getSn();
    }
    
    @Override
    public String getKey(ErrorInfo entity) {
        if(entity != null) {
            return entity.getSn();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ErrorInfo entity) {
        return entity.getSn() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
