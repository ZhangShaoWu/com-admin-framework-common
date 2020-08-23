package com.admin.framework.common.exception;

import com.admin.framework.common.constant.BaseResp;
import com.admin.framework.common.entity.Resp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ZSW
 * @date 2018/8/11
 */
@ControllerAdvice
@Component
public class BaseException {

    @Value("${exception.debug}")
    private boolean debug;

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Resp defaultExceptionHandler(HttpServletRequest request, Exception e){
        if(debug){
            e.printStackTrace();
        }
        if(e instanceof BusinessException){
            BusinessException ex = (BusinessException) e;
            return Resp.error(ex.getCode(),ex.getMessage());
        }
        if(e instanceof HttpRequestMethodNotSupportedException){
            HttpRequestMethodNotSupportedException ex = (HttpRequestMethodNotSupportedException) e;
            return Resp.error(ex.getMessage());
        }
        if(e instanceof HttpMediaTypeNotSupportedException){
            HttpMediaTypeNotSupportedException ex = (HttpMediaTypeNotSupportedException) e;
            return Resp.error(ex.getMessage());
        }
        if(e instanceof HttpMessageNotReadableException){
            HttpMessageNotReadableException ex = (HttpMessageNotReadableException) e;
            return Resp.error(ex.getMessage());
        }
        if(e instanceof MissingServletRequestParameterException){
            MissingServletRequestParameterException ex = (MissingServletRequestParameterException) e;
            return Resp.error("必填参数空:" + ex.getParameterName());
        }
        return BaseResp.UNKNOWN_ERROR;
    }
}
