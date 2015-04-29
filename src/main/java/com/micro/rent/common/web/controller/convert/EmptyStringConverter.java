package com.micro.rent.common.web.controller.convert;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class EmptyStringConverter implements Converter<String, String> {

    @Override
    public String convert(String target) {

        return StringUtils.defaultIfEmpty(target, null);
    }

}
