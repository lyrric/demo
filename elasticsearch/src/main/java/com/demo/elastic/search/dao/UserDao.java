package com.demo.elastic.search.dao;

import com.demo.elastic.search.core.EsTransPortClient;
import com.demo.elastic.search.entity.User;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created on 2018/6/8.
 *
 * @author wangxiaodong
 */
@Repository
public class UserDao extends BaseDao<User> {

    @Autowired
    public UserDao(EsTransPortClient esTransPortClient) {
        super(esTransPortClient);
    }


}
