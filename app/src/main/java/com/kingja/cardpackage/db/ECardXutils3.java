package com.kingja.cardpackage.db;


import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.kingja.cardpackage.activity.NewCarActivity;
import com.kingja.cardpackage.entiy.City;
import com.kingja.cardpackage.entiy.ErrorResult;
import com.kingja.cardpackage.entiy.GetCityList;
import com.kingja.cardpackage.entiy.GetCodeList;
import com.kingja.cardpackage.entiy.GetUserCards;
import com.kingja.cardpackage.entiy.KJBikeCode;
import com.kingja.cardpackage.entiy.UpdateFunctionList_Result;
import com.kingja.cardpackage.entiy.UpdateFunctions;
import com.kingja.cardpackage.entiy.UserCard;
import com.kingja.cardpackage.net.ThreadPoolTask;
import com.kingja.cardpackage.net.WebServiceCallBack;
import com.kingja.cardpackage.util.KConstants;
import com.kingja.cardpackage.util.DataManager;
import com.kingja.cardpackage.util.TimeUtil;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kingja.cardpackage.util.KConstants.E_CARD;

/**
 * 项目名称：物联网城市防控(警用版)
 * 类描述：数据库管理类
 * 创建人：KingJA
 * 创建时间：2016/4/11 13:57
 * 修改备注：
 */
public class ECardXutils3 implements DbDao {
    private String TAG = getClass().getSimpleName();
    private  final String DEFAULT_UPDATE_DATE = "1990-01-01 00:00:01";
    private DbManager dbManager;
    private static ECardXutils3 mDbDaoXutils3;


    public static ECardXutils3 getInstance() {
        if (mDbDaoXutils3 == null) {
            synchronized (ECardXutils3.class) {
                if (mDbDaoXutils3 == null) {
                    mDbDaoXutils3 = new ECardXutils3();
                }
            }
        }
        return mDbDaoXutils3;
    }

    public static ECardXutils3 getInstance(String dbName) {
        if (mDbDaoXutils3 == null) {
            synchronized (ECardXutils3.class) {
                if (mDbDaoXutils3 == null) {
                    mDbDaoXutils3 = new ECardXutils3(dbName);
                }
            }
        }
        return mDbDaoXutils3;
    }


    public ECardXutils3() {
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName(E_CARD)
                .setDbVersion(1)
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        db.getDatabase().enableWriteAheadLogging();
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                    }
                });

        dbManager = x.getDb(daoConfig);
    }

    private ECardXutils3(String dbName) {
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName(dbName)
                .setDbVersion(1)
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        db.getDatabase().enableWriteAheadLogging();
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                    }
                });

        dbManager = x.getDb(daoConfig);
    }

    @Override
    public <T> T selectFirst(Class<T> clazz, String key, String value) {
        T t = null;
        try {
            t = dbManager.selector(clazz).where(key, "=", value).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public <T> T selectFirst(Class<T> clazz, String key1, String value1, String key2, String value2) {
        T t = null;
        try {
            t = dbManager.selector(clazz).where(key1, "=", value1).and(key2, "=", value2).findFirst();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public <T> List<T> selectAllWhere(Class<T> clazz, String key, String value) {
        List<T> list = null;
        try {
            list = dbManager.selector(clazz).where(key, "=", value).findAll();

        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public <T> List<T> selectAllWheres(Class<T> clazz, String key, String value, String key2, String value2) {
        List<T> list = null;
        try {
            list = dbManager.selector(clazz).where(key, "=", value).and(key2, "=", value2).findAll();

        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public <T> List<T> selectAllWhereNotEquil(Class<T> clazz, String key, String value, String key2, String value2) {
        List<T> list = null;
        try {
            list = dbManager.selector(clazz).where(key, "=", value).and(key2, "!=", value2).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public <T> List<T> selectAllWhereLike(Class<T> clazz, String key, String value) {
        List<T> list = null;
        try {
            list = dbManager.selector(clazz).where(key, "like", value).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public <T> List<T> selectAll(Class<T> clazz) {
        List<T> list = new ArrayList<>();
        try {
            list = dbManager.selector(clazz).findAll();
            if (list == null) {
                list = new ArrayList<>();
            }

        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public <T> List<T> selectAllAndOrder(Class<T> clazz, String order) {
        List<T> list = new ArrayList<>();
        try {
            list = dbManager.selector(clazz).orderBy(order, true).findAll();
            if (list == null) {
                list = new ArrayList<>();
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }

    public <T> List<T> selectAllWhereAndOrder(Class<T> clazz, String key, String value, String order, boolean desc) {
        List<T> list = null;
        try {
            list = dbManager.selector(clazz).where(key, "=", value).orderBy(order, desc).findAll();

        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public <T> void saveOrUpdate(final T t) {
        x.task().run(new Runnable() {
            @Override
            public void run() {
                try {
                    dbManager.saveOrUpdate(t);
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public <T> void deleteById(Class<T> clazz, String id) {
        try {
            dbManager.deleteById(clazz, id);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T> void deleteAll(Class<T> clazz) {
        try {
            dbManager.delete(clazz);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    public <T> void saveDate(final List<T> list) {
        x.task().run(new Runnable() {
            @Override
            public void run() {
                for (T t : list) {
                    try {
                        dbManager.saveOrUpdate(t);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
//
//    public <T> void saveDate(final List<T> list, final Handler handler) {
//        final Message message = handler.obtainMessage();
//        message.what = NewCarActivity.DB_UPDATE_START;
//        message.obj = list.size();
//        handler.sendMessage(message);
//        x.task().run(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < list.size(); i++) {
//                    try {
//                        dbManager.saveOrUpdate(list.get(i));
//                        message.what = NewCarActivity.DB_UPDATE_PROGRESS;
//                        message.obj = i;
//                        handler.sendMessage(message);
//                    } catch (DbException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        });
//        handler.sendEmptyMessage(NewCarActivity.DB_UPDATE_END);
//    }

    public <T> void saveDate(final List<T> list, final DbProgressListener dbProgressListener ) {

        x.task().run(new Runnable() {
            @Override
            public void run() {
                dbProgressListener.onProgressStart(list.size());
                for (int i = 0; i < list.size(); i++) {
                    try {
                        dbManager.saveOrUpdate(list.get(i));
                        dbProgressListener.onProgressProgress(i+1);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }
                dbProgressListener.onProgressEnd();
            }
        });

    }

   public interface DbProgressListener{
        void onProgressStart(int totleProgress);
        void onProgressProgress(int progress);
        void onProgressEnd();
    }
}
