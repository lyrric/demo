package com.demo.spring.boot2.service;

import com.demo.spring.boot2.entity.User;
import com.demo.spring.boot2.mapper.manual.UserExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2018/6/7.
 *
 * @author wangxiaodong
 */
@Service
public class UserService {

    @Autowired
    UserExtMapper userExtMapper;

    public User findById(int id){
        User user = userExtMapper.selectByPrimaryKey(id);
        return user;
    }
}
