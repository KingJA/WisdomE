package com.kingja.cardpackage.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.kingja.cardpackage.greendaobean.ChargeRecord;
import com.kingja.cardpackage.greendaobean.ErrorInfo;

import com.kingja.cardpackage.dao.ChargeRecordDao;
import com.kingja.cardpackage.dao.ErrorInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig chargeRecordDaoConfig;
    private final DaoConfig errorInfoDaoConfig;

    private final ChargeRecordDao chargeRecordDao;
    private final ErrorInfoDao errorInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        chargeRecordDaoConfig = daoConfigMap.get(ChargeRecordDao.class).clone();
        chargeRecordDaoConfig.initIdentityScope(type);

        errorInfoDaoConfig = daoConfigMap.get(ErrorInfoDao.class).clone();
        errorInfoDaoConfig.initIdentityScope(type);

        chargeRecordDao = new ChargeRecordDao(chargeRecordDaoConfig, this);
        errorInfoDao = new ErrorInfoDao(errorInfoDaoConfig, this);

        registerDao(ChargeRecord.class, chargeRecordDao);
        registerDao(ErrorInfo.class, errorInfoDao);
    }
    
    public void clear() {
        chargeRecordDaoConfig.clearIdentityScope();
        errorInfoDaoConfig.clearIdentityScope();
    }

    public ChargeRecordDao getChargeRecordDao() {
        return chargeRecordDao;
    }

    public ErrorInfoDao getErrorInfoDao() {
        return errorInfoDao;
    }

}
