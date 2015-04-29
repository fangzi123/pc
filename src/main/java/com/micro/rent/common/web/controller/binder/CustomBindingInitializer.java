package com.micro.rent.common.web.controller.binder;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

/**
 * @author
 * @version 1.0
 * @Description 自定义属性编辑器
 * @date 2013-4-19
 */
public class CustomBindingInitializer implements WebBindingInitializer {

    private transient Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 候选日期模式
     */
    private String[] candidateDatePatterns;

    @Override
    public void initBinder(WebDataBinder binder, WebRequest request) {
        initDateBinder(binder, request);
    }

    /**
     * @param binder
     * @param request
     * @Description 日期属性编辑器
     * @author
     */
    private void initDateBinder(WebDataBinder binder, WebRequest request) {
        // 扩展日期绑定
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                try {
                    setValue(DateUtils.parseDate(value, candidateDatePatterns));
                } catch (ParseException e) {
                    log.error(e.getMessage(), e);
                    setValue(null);
                }
            }

            public String getAsText() {
                return String.valueOf(getValue());
            }
        });

        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                setValue(StringUtils.defaultIfEmpty(value, null));
            }

            public String getAsText() {
                return String.valueOf(getValue());
            }
        });

        binder.registerCustomEditor(Integer.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                setValue(StringUtils.defaultIfEmpty(value, null));
            }

            public String getAsText() {
                return String.valueOf(getValue());
            }
        });

        binder.registerCustomEditor(Long.class, new PropertyEditorSupport() {
            public String getAsText() {
                return String.valueOf(getValue());
            }

            public void setAsText(String value) {
                setValue(StringUtils.defaultIfEmpty(value, null));
            }
        });
    }

    public void setCandidateDatePatterns(String[] candidateDatePatterns) {
        this.candidateDatePatterns = candidateDatePatterns;
    }
}
