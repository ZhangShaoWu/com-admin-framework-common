package com.admin.framework.common.entity;


import lombok.Data;

/**
 * restful接口响应统一格式
 * @author ZSW
 * @date 2018/8/10
 */
@Data
public class Resp<T> {

    public static final Integer DEFAULT_ERROR_CODE = -500;
    public static final String DEFAULT_ERROR_MSG = "服务器发生了未知错误，请联系管理员";
    public static final Integer DEFAULT_SUCCESS_CODE = 0;
    public static final String DEFAULT_SUCCESS_MSG = "操作成功";


    private Integer code = DEFAULT_SUCCESS_CODE;
    private String msg = DEFAULT_SUCCESS_MSG;
    private boolean success = true;
    private String redirectUrl;
    /**
     * 结果
     */
    private Object data;

    /**
     * 错误处理
     * @return
     */
    public static Resp error(){
        return error(DEFAULT_ERROR_MSG);
    }

    /**
     * 错误处理
     * @param msg 错误消息
     * @return
     */
    public static Resp error(String msg){
        return error(DEFAULT_ERROR_CODE,msg);
    }

    /**
     * 错误处理
     * @param code 错误码
     * @param msg 错误消息
     * @return
     */
    public static Resp error(Integer code, String msg){
        Resp resp = new Resp();
        resp.setCode(code);
        resp.setMsg(msg);
        resp.setSuccess(false);
        return resp;
    }

    /**
     * 成功处理
     * @return
     */
    public static Resp success(){
        return success(null,DEFAULT_SUCCESS_MSG);
    }

    /**
     * 成功处理
     * @param data 响应数据
     * @return
     */
    public static Resp success(Object data){
        return success(data,DEFAULT_SUCCESS_MSG);
    }

    /**
     * 成功处理
     * @param data 响应数据
     * @param msg 响应消息体
     * @return
     */
    public static Resp success(Object data,String msg){
        return success(data,msg,DEFAULT_SUCCESS_CODE);
    }

    /**
     * 成功处理
     * @param data 响应数据
     * @param code 响应状态码
     * @param msg 响应消息
     * @return
     */
    public static Resp success(Object data,String msg,Integer code){
        Resp resp = new Resp();
        resp.setData(data);
        resp.setCode(code);
        resp.setMsg(msg);
        return resp;
    }

    public Integer getCode() {
        return code;
    }

    public Resp<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Resp<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public Resp<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Resp<T> setData(Object data) {
        this.data = data;
        return this;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public Resp<T> setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
        return this;
    }
}
