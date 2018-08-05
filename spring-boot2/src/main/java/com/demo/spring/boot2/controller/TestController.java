package com.demo.spring.boot2.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.demo.spring.boot2.entity.User;
import com.demo.spring.boot2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @author wangxiaodong
 * Created by wangxiaodong on 2018/6/6.
 */
@RestController
public class TestController {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserService userService;

    @GetMapping(value = "/test")
    User index(){
        User user = userService.findById(1);
        return user;
    }


}
