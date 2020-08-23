package com.admin.framework.common.utils;


import com.admin.framework.component.utils.JSONUtil;
import com.admin.framework.component.utils.StringUtil;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author zsw
 * @Description
 * @Date Create in 18:15 2019\8\14 0014
 */
public class RequestUtil {

    /**
     * 获取当前的请求
     * @return
     */
    public static HttpServletRequest getCurrentRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if(requestAttributes == null){
            return null;
        }
        return ((ServletRequestAttributes)requestAttributes).getRequest();
    }


    /**
     * 获取ip
     * @return
     */
    public static String getIp(){
        HttpServletRequest request = getCurrentRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return StringUtil.isEmpty(ip)?"127.0.0.1":ip;
    }

    /**
     * 获取访问设备
     * @return
     */
    public static String getUserAgent(){
        HttpServletRequest request = getCurrentRequest();
        String agentString = request.getHeader("user-agent");
        return agentString;
    }

    /**
     * 获取请求参数
     * @return
     */
    public static String getParams(){
        HttpServletRequest request = getCurrentRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();
        return JSONUtil.objToJsonStr(parameterMap);
    }

    /**
     * 获取方法
     * @return
     */
    public static String getMethod(){
        HttpServletRequest request = getCurrentRequest();
        return request.getMethod();
    }

    /**
     * 路径
     * @return
     */
    public static String getUrl(){
        HttpServletRequest request = getCurrentRequest();
        return request.getRequestURL().toString();
    }

    /**
     * 路径
     * @return
     */
    public static String getUri(){
        HttpServletRequest request = getCurrentRequest();
        return request.getRequestURI();
    }

    /**
     * 获取上传的文件
     * @return
     */
    public static Map<String, MultipartFile> getFileMap(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        MultipartResolver resolver = new StandardServletMultipartResolver();
        MultipartHttpServletRequest mRequest = resolver.resolveMultipart(request);
        return mRequest.getFileMap();
    }



}
