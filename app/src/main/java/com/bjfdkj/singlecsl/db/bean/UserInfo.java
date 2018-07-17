package com.bjfdkj.singlecsl.db.bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Administrator on 2018/5/30 0030.
 * 用户
 */
@Entity
public class UserInfo {
    @Id(autoincrement = true)
    private Long   _ID;
    @Property(nameInDb = "USERNAME")//名称
    private String username;
    @Property(nameInDb = "USERUNIT")//单位名称
    private String userunit;
    @Property(nameInDb = "USERSEX")//性别
    private String usersex;
    @Property(nameInDb = "USERNATION")//民族
    private String usernation;
    @Property(nameInDb = "USERDUTY")//职务名称
    private String userduty;
    //外键
    @NotNull
    private Long userId;

    @Generated(hash = 1491539088)
    public UserInfo(Long _ID, String username, String userunit, String usersex,
            String usernation, String userduty, @NotNull Long userId) {
        this._ID = _ID;
        this.username = username;
        this.userunit = userunit;
        this.usersex = usersex;
        this.usernation = usernation;
        this.userduty = userduty;
        this.userId = userId;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }

    public Long get_ID() {
        return this._ID;
    }

    public void set_ID(Long _ID) {
        this._ID = _ID;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserunit() {
        return this.userunit;
    }

    public void setUserunit(String userunit) {
        this.userunit = userunit;
    }

    public String getUsersex() {
        return this.usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    public String getUsernation() {
        return this.usernation;
    }

    public void setUsernation(String usernation) {
        this.usernation = usernation;
    }

    public String getUserduty() {
        return this.userduty;
    }

    public void setUserduty(String userduty) {
        this.userduty = userduty;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
