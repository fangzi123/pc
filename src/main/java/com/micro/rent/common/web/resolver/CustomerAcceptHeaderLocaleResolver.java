package com.micro.rent.common.web.resolver;

import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class CustomerAcceptHeaderLocaleResolver extends AcceptHeaderLocaleResolver {
    private Locale myLocal;

    public Locale resolveLocale(HttpServletRequest request) {
        return myLocal;
    }

    public void setLocale(HttpServletRequest request,
                          HttpServletResponse response, Locale locale) {
        if (locale == null) {
            locale = Locale.CHINESE;
        }
        myLocal = locale;
    }
}
