package com.demo.spring.boot2.service;

import com.demo.spring.boot2.entity.User;

/**
 * Created on 2019/1/2.
 *
 * @author wangxiaodong
 */
public interface UserService {

    User findById(int id);
}
