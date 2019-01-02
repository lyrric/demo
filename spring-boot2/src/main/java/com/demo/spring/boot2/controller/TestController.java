package com.demo.spring.boot2.controller;

import com.demo.spring.boot2.entity.User;
import com.demo.spring.boot2.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wangxiaodong
 * Created by wangxiaodong on 2018/6/6.
 */
@RestController
public class TestController {

    @Resource
    private UserService userServiceImpl;

    @GetMapping(value = "/test")
    User index(int id){
        return userServiceImpl.findById(id);
    }


}
