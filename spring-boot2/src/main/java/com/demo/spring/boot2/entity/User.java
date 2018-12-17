package com.demo.spring.boot2.entity;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.*;

/**
 * Created on 2018/6/7.
 *
 * @author wangxiaodong
 */
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
