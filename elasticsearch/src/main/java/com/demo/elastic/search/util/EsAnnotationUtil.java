package com.demo.elastic.search.util;

import com.demo.elastic.search.core.EsAnnotation;
import com.demo.elastic.search.entity.BaseEntity;

import java.lang.annotation.Annotation;

/**
 * Created on 2018/6/12.
 *
 * @author wangxiaodong
 */
public class EsAnnotationUtil {

    public static String getTypeByClass(Class<? extends BaseEntity> clazz) {
        if(!clazz.isAnnotationPresent(EsAnnotation.class)){
            //throw new Exception(clazz.getName() + "找不到EsAnnotation注解");
            return null;
        }
        EsAnnotation esAnnotation = (EsAnnotation) clazz.getDeclaredAnnotation(EsAnnotation.class);
        return esAnnotation.type();
    }
}
