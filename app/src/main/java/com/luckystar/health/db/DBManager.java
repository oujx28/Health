package com.luckystar.health.db;

import android.content.Context;

import com.luckystar.health.base.BaseApplication;
import com.luckystar.health.db.table.DaoMaster;
import com.luckystar.health.db.table.DaoSession;
import com.luckystar.health.db.table.DaoMaster.DevOpenHelper;
import com.luckystar.health.db.table.UserInfo;
import com.luckystar.health.db.table.UserInfoDao;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.rx.RxQuery;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/9/13.
 */

public class DBManager {
    /**
     * 数据库名
     */
    private static final String DATABASE_NAME = "health.db";

    public static final boolean ENCRYPTED = false;
    private DaoSession mDaoSession;
    private static Context sContext;
    private UserInfoDao mUserInfoDao;

    private DBManager() {
        sContext = BaseApplication.getAppContext();
        DevOpenHelper helper = new DevOpenHelper(sContext, DATABASE_NAME);
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();

        mUserInfoDao = mDaoSession.getUserInfoDao();
    }
    public static DBManager getInstance() {
        return DBManagerHolder.INSTANCE;
    }

    private static class DBManagerHolder {
        private static final DBManager INSTANCE = new DBManager();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public UserInfo getUserInfo(String id) {
        List<UserInfo> list = mUserInfoDao.queryBuilder().list();
        return mUserInfoDao.queryBuilder().where(UserInfoDao.Properties.User_id.eq(id)).unique();
    }

    public void insertUserInfo(UserInfo entity) {
        mUserInfoDao.insert(entity);
    }
}
