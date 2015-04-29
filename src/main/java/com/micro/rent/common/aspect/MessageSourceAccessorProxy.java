package com.micro.rent.common.aspect;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

import java.lang.reflect.Method;

/**
 * @author
 * @version 1.0
 * @Description MessageSourceAccessor由于没有默认构造，手工做代理。
 * @date 2013-4-16
 */
public class MessageSourceAccessorProxy {

    private static transient Logger log = LoggerFactory.getLogger(MessageSourceAccessorProxy.class);

    /**
     * @param messageSource
     * @return
     * @Description CGLIB 代理MessageSourceAccessor
     * @author
     */
    public static MessageSourceAccessor getProxy(MessageSource messageSource) {
        log.info("Creating a new proxy for MessageSourceAccessor.");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MessageSourceAccessor.class);
        enhancer.setCallback(new MessageSourceHandler());
        return (MessageSourceAccessor) enhancer.create(
                new Class[]{MessageSource.class}, new Object[]{messageSource});

    }

    /**
     * @author
     * @version 1.0
     * @Description
     * @date 2013-4-16
     */
    private static class MessageSourceHandler implements MethodInterceptor {
        private transient Logger log = LoggerFactory.getLogger(this.getClass());

        @Override
        public Object intercept(Object obj, Method method, Object[] args,
                                MethodProxy proxy) throws Throwable {
            Object result = proxy.invokeSuper(obj, args);
            //仅对首参数为String类型的方法进行处理
            if (args != null && args.length > 0 && (args[0] instanceof String)) {
                log.trace("append code before message.");
                return new StringBuilder()
                        //.append(args[0])
                        //.append("|")
                        .append(result).toString();
            }
            return result;
        }
    }
}
