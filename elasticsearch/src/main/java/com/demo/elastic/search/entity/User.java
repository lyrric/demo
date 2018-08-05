package com.demo.elastic.search.entity;

import com.demo.elastic.search.core.EsAnnotation;

/**
 * Created on 2018/6/8.
 *
 * @author wangxiaodong
 */
@EsAnnotation(type = "user")
public class User extends BaseEntity{


    private String username;
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
