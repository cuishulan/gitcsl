package com.bjfdkj.singlecsl.db.utlis;

import android.content.Context;
import android.util.Log;


import com.bjfdkj.singlecsl.db.bean.BookkeeperInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/6/1 0001.
 *
 * 档案管理 Bookkeeper   Utils
 */

public class BookkeeperDaoUtils extends DaoUtlis<BookkeeperInfo> {
    public BookkeeperDaoUtils(Context context) {
        super(context);
    }

    @Override
    public boolean insertDao(BookkeeperInfo meizi) {
        boolean flag = false;
        flag = mManager.getDaoSession().getBookkeeperInfoDao().insert(meizi) == -1 ? false : true;
        Log.i(TAG, "insert Meizi :" + flag + "-->" + meizi.toString());
        return flag;
    }

    @Override
    public boolean insertMultDao(List<BookkeeperInfo> meiziList) {
        return false;
    }

    @Override
    public boolean updateDao(BookkeeperInfo meizi) {
        return false;
    }

    @Override
    public boolean deleteDao(BookkeeperInfo meizi) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    @Override
    public List<BookkeeperInfo> queryAllDao() {
        return mManager.getDaoSession().loadAll(BookkeeperInfo.class);
    }

    @Override
    public BookkeeperInfo queryDaoById(long key) {
        return null;
    }

    @Override
    public List<BookkeeperInfo> queryDaoByNativeSql(String sql, String[] conditions) {
        return null;
    }
    @Override
    public List<BookkeeperInfo> queryDaoByQueryBuilder(long id) {
        return null;
    }
}
