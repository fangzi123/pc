package com.micro.rent.common.web.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author
 * @version 1.0
 * @Description 自定义映射异常解析器
 * @date 2012-11-19
 */
public class CustomMappingExceptionResolver extends
        SimpleMappingExceptionResolver {

    protected transient Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response, Object handler, Exception ex) {
        // Expose ModelAndView for chosen error view.
        String viewName = determineViewName(ex, request);
        if (viewName != null) {
            // Apply HTTP status code for error views, if specified.
            // Only apply it if we're processing a top-level request.
            Integer statusCode = determineStatusCode(request, viewName);
            if (statusCode != null) {
                applyStatusCodeIfPossible(request, response, statusCode);
            }

            //add by
            log.error("====Error Type====: {}", new Object[]{ex.getClass()}, ex);


            return getModelAndView(viewName, ex, request)
                    .addObject("errorType", ex.getClass().getSimpleName())
                    .addObject("message", ex.getMessage());
        } else {
            return null;
        }
    }
}
