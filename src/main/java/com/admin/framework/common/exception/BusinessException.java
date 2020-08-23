package com.admin.framework.common.exception;

import com.admin.framework.component.utils.JSONUtil;

import java.util.List;

/**
 * @Author zsw
 * @Description
 * @Date Create in 13:36 2019\8\12 0012
 */
public class BusinessException extends RuntimeException {

    private Integer code = -500;
    private String message;

    /**
     * 无参构造函数
     */
    public BusinessException(){
        super();
    }

    /**
     * 用详细信息指定一个异常
     * @param message
     */
    public BusinessException(String message){
        super(message);
        this.message = message;
    }

    /**
     * 用详细信息指定一个异常
     * @param code
     * @param message
     */
    public BusinessException(Integer code,String message){
        super(message);
        this.code = code;
        this.code = code;
    }

    /**
     * 用详细信息指定一个异常
     * @param message
     */
    public BusinessException(List<String> message){
        super(JSONUtil.objToJsonStr(message));
    }

    /**
     * 用指定的详细信息和原因构造一个新的异常
     * @param message
     * @param cause
     */
    public BusinessException(String message, Throwable cause){
        super(message,cause);
    }

    /**
     * 用指定原因构造一个新的异常
     * @param cause
     */
    public BusinessException(Throwable cause) {
        super(cause);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }



    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
