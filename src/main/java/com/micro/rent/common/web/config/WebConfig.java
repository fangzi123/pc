package com.micro.rent.common.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/commonJS/**").addResourceLocations(
                "/commonJS/");
        registry.addResourceHandler("/bootstrap/**").addResourceLocations(
                "/bootstrap/");
        registry.addResourceHandler("/mvcjs/**").addResourceLocations(
                "/WEB-INF/views/");
        registry.addResourceHandler("/ftl/**").addResourceLocations(
                "/WEB-INF/ftls/");
        registry.addResourceHandler("/FusionCharts/**").addResourceLocations(
                "/FusionCharts/");
        registry.addResourceHandler("/images/**").addResourceLocations(
                "/images/");
        registry.addResourceHandler("/resources/**").addResourceLocations(
                "/resources/");

    }
}
