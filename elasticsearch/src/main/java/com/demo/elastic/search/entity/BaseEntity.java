package com.demo.elastic.search.entity;

import java.io.Serializable;

/**
 * Created on 2018/6/8.
 * 子类必须有@EsAnnotation注解
 * @author wangxiaodong
 */
public abstract class BaseEntity implements Serializable{

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
