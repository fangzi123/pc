package com.micro.rent.pc.web.shiro;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.micro.rent.pc.entity.Tenant;
import com.micro.rent.pc.service.RealmService;

public class ShiroTenantRealm extends AuthorizingRealm {

    private RealmService realmService;
    /**
     * @Title: doGetAuthenticationInfo
     * @Description: 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        String userName = token.getPrincipal().toString();
        Tenant tenant = realmService.findUserInfoByName(userName);
        if (tenant != null) {
            ShiroTenant user = new ShiroTenant();
            user.setUserName(userName);
            user.setMobile(tenant.getMobile());
            user.setId(tenant.getId());
            user.setHeadImg(tenant.getHeadImg());
            return new SimpleAuthenticationInfo(user, tenant.getPassword(),
                    getName());
        } else {
            return null;
        }
    }
    /**
     * @Title: doGetAuthorizationInfo
     * @Description: 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        return info;
    }

    public RealmService getRealmService() {
        return realmService;
    }

    public void setRealmService(RealmService realmService) {
        this.realmService = realmService;
    }

    /**
     * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
     */
    public static class ShiroTenant implements Serializable {

        private static final long serialVersionUID = 1L;

        private Integer id;

        private String userName;

        private String mobile;

        private String headImg;

        /**
         * @return the userName
         */
        public String getUserName() {
            return userName;
        }

        /**
         * @param userName
         *            the userName to set
         */
        public void setUserName(String userName) {
            this.userName = userName;
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this,
                    ToStringStyle.MULTI_LINE_STYLE);
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

    }

}
