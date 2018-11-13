package com.demo.spring.boot2.exception;

import com.demo.spring.boot2.util.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collection;

/**
 * 统一异常处理
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static final String MSG_SEPARATOR = ";";

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理异常
     * @param e 异常
     * @return 统一返回
     */
    @ExceptionHandler({Exception.class})
    public HttpResult allException(Exception e) {
        if (e instanceof BindException) {
            //javaBean验证器 异常处理
            BindException bindEx = (BindException) e;
            return  HttpResult.failure(getMessage(bindEx.getAllErrors()));
        } else if (e instanceof ConstraintViolationException) {
            //方法参数验证器 异常处理
            ConstraintViolationException validationEx = (ConstraintViolationException) e;
            return HttpResult.failure(getMessage(validationEx.getConstraintViolations()));
        } else if (e instanceof BizException) {
            //业务异常处理
            return HttpResult.failure(e.getMessage());
        } else {
            logger.error("系统发生了不可预知异常",e);
            return HttpResult.error();
        }
    }

    /**
     * 组装信息
     * @param results 验证结果容器
     * @return 验证信息
     */
    private String getMessage(Collection<?> results) {
        if (results != null) {
            StringBuilder msgBuilder = new StringBuilder("参数验证失败:");
            //取出验证结果
            for (Object item : results) {
                if (item instanceof ConstraintViolation) {
                    //方法参数验证结果的获取方式
                    msgBuilder.append(((ConstraintViolation) item).getMessage()).append(MSG_SEPARATOR);
                } else if (item instanceof ObjectError) {
                    //javaBean参数验证结果的获取方式
                    msgBuilder.append(((ObjectError) item).getDefaultMessage()).append(MSG_SEPARATOR);
                }
            }

            String msg = msgBuilder.toString();
            //去除最后一个逗号
            if (msg.endsWith(MSG_SEPARATOR)) {
                msg = msg.substring(0, msg.lastIndexOf(MSG_SEPARATOR));
            }

            return msg;
        } else {
            return null;
        }
    }

}
