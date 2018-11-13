package com.demo.spring.boot2.util;

/**
 * 响应封装
 */
public class HttpResult {

    private final static int SUCCESS_CODE = 1;
    private final static int FAILURE_CODE = -1;
    private final static boolean SUCCESS_STATUS = true;
    private final static boolean FAILURE_STATUS = false;

    private HttpResult(boolean success, int code, String msg, Object data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 响应码。
     * 如果没有特别注明，code为0时表示请求访问成功
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 是否成功。
     * 当系统抛出预料之外的异常的时候，这个值为false,
     * 其他情况这个值都是true。
     */
    private Boolean success;

    /**
     * 响应数据
     */
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    /**
     * 响应成功
     * @param success 是否成功
     * @param code 响应代码
     * @param msg 响应消息
     * @param data 响应数据
     * @return 响应对象
     */
    public static HttpResult success(boolean success, int code, String msg, Object data) {
        return new HttpResult(success, code, msg,data);
    }

    /**
     * 默认成功响应
     * @param data 数据
     * @return 响应
     */
    public static HttpResult success(Object data) {
        return success(SUCCESS_STATUS, SUCCESS_CODE, "请求成功",data);
    }

    /**
     * 响应请求失败
     * @param code 约定code
     * @param msg  信息
     * @return 失败的响应
     */
    public static HttpResult failure(boolean success, int code, String msg, Object data) {
        return new HttpResult(success, code, msg,data);
    }

    /**
     * 响应请求失败
     * @param msg 失败信息
     * @return 响应
     */
    public static HttpResult failure(String msg) {
        return failure(SUCCESS_STATUS, FAILURE_CODE, msg,null);
    }

    /**
     * 出现预料之外的错误
     * @return 错误信息
     */
    public static HttpResult error() {
        return failure(FAILURE_STATUS, FAILURE_CODE, "系统出现了一点小问题",null);
    }

    public static HttpResult error(String msg) {
        return failure(FAILURE_STATUS, FAILURE_CODE, msg,null);
    }
}
