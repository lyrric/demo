package com.demo.elastic.search.ennum;

/**
 * Created on 2018/6/11.
 *
 * @author wangxiaodong
 */
public enum  EsEnum {

    /**
     * Es索引
     */
    INDEX("test1");
    private String value;

    EsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
