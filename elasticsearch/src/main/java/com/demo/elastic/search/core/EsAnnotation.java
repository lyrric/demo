package com.demo.elastic.search.core;

import java.lang.annotation.*;

/**
 * Created on 2018/6/12.
 * es注解
 * @author wangxiaodong
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EsAnnotation {

    /**
     *  es type
     * @return
     */
    String type();
}
