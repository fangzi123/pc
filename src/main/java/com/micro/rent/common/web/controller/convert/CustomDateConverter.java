package com.micro.rent.common.web.controller.convert;

import com.micro.rent.common.exceptions.SysException;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

/**
 * @author
 * @version 1.0
 * @Description 适用于注解mvc的日期类型转换器
 * @date 2013-4-26
 */
public class CustomDateConverter implements Converter<String, Date> {

    /**
     * 候选日期模式
     */
    private String[] candidateDatePatterns;
    private transient Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Date convert(String source) {
        try {
            return DateUtils.parseDate(source, candidateDatePatterns);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
            throw new SysException(e);
        }
    }

    public void setCandidateDatePatterns(String[] candidateDatePatterns) {
        this.candidateDatePatterns = candidateDatePatterns;
    }
}
