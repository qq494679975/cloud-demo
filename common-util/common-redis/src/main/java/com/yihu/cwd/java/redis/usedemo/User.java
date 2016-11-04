package com.yihu.cwd.java.redis.usedemo;

import java.util.Date;

/**
 * Created by Administrator on 2016.10.26.
 */
public class User {
    private int id;
    private String username;
    private Date birthday;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public User() {
    }

    public User(int id, String username,Date birthday) {
        this.id = id;
        this.birthday = birthday;
        this.username = username;
    }
}
