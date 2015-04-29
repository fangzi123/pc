package com.micro.rent.common.web;

import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.Charset;

public class CustomerStringHttpMessageConverter extends StringHttpMessageConverter {

    private static final MediaType utf8 = new MediaType("text", "plain", Charset.forName("UTF-8"));

    @Override
    protected MediaType getDefaultContentType(String dumy) {
        return utf8;
    }
}
