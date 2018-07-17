package com.bjfdkj.singlecsl.db.utlis;

import android.content.Context;
import android.util.Log;


import com.bjfdkj.singlecsl.db.UserInfoDao;
import com.bjfdkj.singlecsl.db.bean.UserInfo;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by Administrator on 2018/5/30 0030.
 */

public class UserDaoUtils extends DaoUtlis<UserInfo> {


    public UserDaoUtils(Context context) {
        super(context);
    }

    @Override
    public boolean insertDao(UserInfo user) {
        boolean flag = false;
        flag = mManager.getDaoSession().getUserInfoDao().insert(user) == -1 ? false : true;
        Log.i(TAG, "insert Meizi :" + flag + "-->" + user.toString());
        return flag;
    }

    @Override
    public boolean insertMultDao(final List<UserInfo> meiziList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (UserInfo meizi : meiziList) {
                        mManager.getDaoSession().insertOrReplace(meizi);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updateDao(UserInfo meizi) {
        boolean flag = false;
        try {
            mManager.getDaoSession().update(meizi);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteDao(UserInfo meizi) {
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(meizi);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteAll() {
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().deleteAll(UserInfo.class);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<UserInfo> queryAllDao() {
        return mManager.getDaoSession().loadAll(UserInfo.class);
    }
    @Override
    public UserInfo queryDaoById(long key) {
        return mManager.getDaoSession().load(UserInfo.class, key);
    }
    @Override
    public List<UserInfo> queryDaoByNativeSql(String sql, String[] conditions) {
        return mManager.getDaoSession().queryRaw(UserInfo.class, sql, conditions);
    }
    @Override
    public List<UserInfo> queryDaoByQueryBuilder(long id) {
        QueryBuilder<UserInfo> queryBuilder = mManager.getDaoSession().queryBuilder(UserInfo.class);
        return queryBuilder.where(UserInfoDao.Properties._ID.eq(id)).list();
    }
    /**
     * 查找名字
     *
     * @param name
     * @return
     */
    public List<UserInfo> queryDaoByQueryBuilder(String name) {
        QueryBuilder<UserInfo> queryBuilder = mManager.getDaoSession().queryBuilder(UserInfo.class);
        return queryBuilder.where(UserInfoDao.Properties.Username.eq(name)).list();
    }
}