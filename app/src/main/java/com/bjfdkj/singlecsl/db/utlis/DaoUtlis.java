package com.bjfdkj.singlecsl.db.utlis;

import android.content.Context;

import java.util.List;

/**
 * Created by Administrator on 2018/5/30 0030.
 */

public abstract class DaoUtlis<T> {
     static final String TAG = DaoUtlis.class.getSimpleName();
     DaoManager mManager;

    public DaoUtlis(Context context){
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }
    /**
     * 完成meizi记录的插入，如果表未创建，先创建Meizi表
     * @param meizi
     * @return
     */
    public abstract boolean insertDao(T meizi);



    /**
     * 插入多条数据，在子线程操作
     * @param meiziList
     * @return
     */
    public abstract boolean insertMultDao(final List<T> meiziList);

    /**
     * 修改一条数据
     * @param meizi
     * @return
     */
    public abstract boolean updateDao(T meizi);


    /**
     * 删除单条记录
     * @param meizi
     * @return
     */
    public abstract boolean deleteDao(T meizi);


    /**
     * 删除所有记录
     * @return
     */
    public abstract boolean deleteAll();

    /**
     * 查询所有记录
     * @return
     */
    public abstract List<T> queryAllDao();


    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public abstract T queryDaoById(long key);

    /**
     * 使用native sql进行查询操作
     */
    public abstract List<T> queryDaoByNativeSql(String sql, String[] conditions);

    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public abstract List<T> queryDaoByQueryBuilder(long id);


}