package com.demo.spring.boot2.mapper.manual;

import com.demo.spring.boot2.entity.User;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created on 2018/6/7.
 *
 * @author wangxiaodong
 */

public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
