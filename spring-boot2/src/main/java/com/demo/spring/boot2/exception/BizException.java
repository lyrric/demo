package com.demo.spring.boot2.exception;

/**
 * 自定义业务异常
 */
public class BizException extends RuntimeException {
    public BizException(String msg){
        super(msg);
    }
}
