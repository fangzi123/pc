package com.micro.rent.biz.weixin.service;

import com.micro.rent.biz.myrent.vo.AuthInfo;

/**
 * @author dell
 * @version 1.0
 * @Description: 微信网页授权服务接口
 * @date 2014-8-28
 */
public interface AuthService {

    /**
     * 用户同意授权，获取code
     *
     * @param redirectURI 用户同意授权后，重定向路径
     * @param state       重定向时携带的参数值（a-zA-Z0-9的参数值）
     */
    void getCodeAndRedirect(String redirectURI, String state);

    /**
     * 通过code换取网页授权信息
     *
     * @param code
     * @return 网页授权信息
     */
    AuthInfo baseAuth(String code);
}
