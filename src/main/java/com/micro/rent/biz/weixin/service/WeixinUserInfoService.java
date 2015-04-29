package com.micro.rent.biz.weixin.service;

import com.micro.rent.biz.weixin.vo.WeixinUserInfo;

/**
 * @author liqianxi
 * @version 1.0
 * @Description: 微信用户基本信息服务接口
 * @date 2014-12-13
 */
public interface WeixinUserInfoService {

    /**
     * 获取微信用户基本信息
     *
     * @param weixinId    用户对于公众号的唯一ID(OpenId)
     * @param accessToken 调用接口凭证
     */
    WeixinUserInfo getWeixinUserInfo(String weixinId, String accessToken);

}
