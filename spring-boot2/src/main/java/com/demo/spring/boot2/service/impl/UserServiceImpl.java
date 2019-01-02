package com.demo.spring.boot2.service.impl;

import com.demo.spring.boot2.entity.User;
import com.demo.spring.boot2.mapper.manual.UserExtMapper;
import com.demo.spring.boot2.service.UserService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created on 2018/6/7.
 *
 * @author wangxiaodong
 */
@Service
@CommonsLog
public class UserServiceImpl implements UserService {

    @Resource
    private UserExtMapper userExtMapper;

    @Cacheable(value = "user")
    @Override
    public User findById(int id){
       log.info("未命中");
        return userExtMapper.selectByPrimaryKey(id);
    }
}
