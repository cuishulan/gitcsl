package com.bjfdkj.singlecsl.db.bean;


import com.bjfdkj.singlecsl.db.BookkeeperInfoDao;
import com.bjfdkj.singlecsl.db.DaoSession;
import com.bjfdkj.singlecsl.db.UserInfoDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * Created by Administrator on 2018/6/1 0001.
 * 实体
 */
@Entity
public class BookkeeperInfo {
    @Id(autoincrement = true)
    private Long _ID;
    private String zongbumen;

    //主键
    private Long           bookkeeperId;
    @ToMany(joinProperties = {@JoinProperty(name = "bookkeeperId", referencedName = "userId")})
    private List<UserInfo> userInfo;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1632808996)
    private transient BookkeeperInfoDao myDao;
    @Generated(hash = 1955711389)
    public BookkeeperInfo(Long _ID, String zongbumen, Long bookkeeperId) {
        this._ID = _ID;
        this.zongbumen = zongbumen;
        this.bookkeeperId = bookkeeperId;
    }
    @Generated(hash = 1008844780)
    public BookkeeperInfo() {
    }
    public Long get_ID() {
        return this._ID;
    }
    public void set_ID(Long _ID) {
        this._ID = _ID;
    }
    public String getZongbumen() {
        return this.zongbumen;
    }
    public void setZongbumen(String zongbumen) {
        this.zongbumen = zongbumen;
    }
    public Long getBookkeeperId() {
        return this.bookkeeperId;
    }
    public void setBookkeeperId(Long bookkeeperId) {
        this.bookkeeperId = bookkeeperId;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 627951257)
    public List<UserInfo> getUserInfo() {
        if (userInfo == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserInfoDao targetDao = daoSession.getUserInfoDao();
            List<UserInfo> userInfoNew = targetDao._queryBookkeeperInfo_UserInfo(bookkeeperId);
            synchronized (this) {
                if (userInfo == null) {
                    userInfo = userInfoNew;
                }
            }
        }
        return userInfo;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 847836514)
    public synchronized void resetUserInfo() {
        userInfo = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 150539517)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBookkeeperInfoDao() : null;
    }

}
