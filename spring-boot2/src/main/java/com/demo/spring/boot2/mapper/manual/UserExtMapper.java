package com.demo.spring.boot2.mapper.manual;

import com.demo.spring.boot2.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created on 2018/6/7.
 *
 * @author wangxiaodong
 */
@Mapper
public interface UserExtMapper extends BaseMapper<User>{

}
