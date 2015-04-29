package com.micro.rent.common.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author
 * @version 1.0
 * @Description Spring上下文工具
 * @date 2013-4-17
 */
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    public static Object getBean(String id) {
        return context.getBean(id);
    }

    public static <T> T getBean(String beanName, Class<T> c) {
        return context.getBean(beanName, c);
    }

    public static <T> T getBean(Class<T> c) {
        return context.getBean(c);
    }

    @Override
    public void setApplicationContext(ApplicationContext ac)
            throws BeansException {
        ApplicationContextUtil.context = ac;
    }
}
