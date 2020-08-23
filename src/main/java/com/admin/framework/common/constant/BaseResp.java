package com.admin.framework.common.constant;


import com.admin.framework.common.entity.Resp;

/**
 *  响应常量
 * @author ZSW
 * @date 2018/8/11
 */
public class BaseResp {

    /**
     * 未知错误
     */
    public static final Resp UNKNOWN_ERROR = Resp.error();


    /**
     * 鉴权失败
     */
    public static final Resp ACCESS_DENIED = Resp.error(401,"鉴权失败");

    /**
     * 没有权限
     * */
    public static final Resp FORBIDDEN = Resp.error(403,"您没有权限进行此操作");

}
