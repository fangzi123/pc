package com.micro.rent.common.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import com.micro.rent.biz.shiro.ShiroDbRealm.ShiroUser;
import com.micro.rent.common.comm.Constants;
import com.micro.rent.common.support.SessionUtil;
import com.micro.rent.pc.web.shiro.ShiroTenantRealm.ShiroTenant;

/**
 * @author
 * @version 1.0
 * @Description 日志处理过滤器
 * @date 2012-11-15
 */
public class LoggingProcessFilter extends OncePerRequestFilter {

    /**
     * 匿名用户(未登录)
     */
    private static final String ANONYM = "anonym";
    private static final String PATTERN = "yyyyMMddHHmmSS";
    /**
     * 日志追踪号
     */
    private static final String TRACE_NO = "traceNo";
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {

        Object user = SecurityUtils.getSubject().getPrincipal();
        String userName = "";
        if (user instanceof ShiroUser) {
            userName = ((ShiroUser) user).getUserName();
        } else if (user instanceof ShiroTenant) {
            userName = ((ShiroTenant) user).getUserName();
        }

        // 若无法确认用户身份(未登录用户)，则加入匿名标志
        StringBuilder sb = new StringBuilder();
        if (user == null) {
            log.trace("cannot get user authentication.");
            sb.append(ANONYM);
        } else {
            log.trace("current user principal: {}", new Object[] { userName });
            sb.append(userName);
        }
        log.trace("trace No.: {}", new Object[] { sb.toString() });
        MDC.put(TRACE_NO, sb.toString());

        // 扩展日志追踪号
        MDC.put(Constants.SESSION_ID, SessionUtil.getSessionId());
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }

    }
}
