package com.micro.rent.biz;

import com.micro.rent.common.exceptions.BizException;
import com.micro.rent.common.support.BusinessUtil;
import com.micro.rent.msg.Errors;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;


public class BaseValidateEl {

    protected transient Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected MessageSourceAccessor msa;

    /**
     * 校验必输栏位
     *
     * @param value   值
     * @param eleName 栏位
     */
    protected void validateNotNull(Object value, String eleName) {
        try {
            Validate.notNull(value, msa.getMessage(Errors.E_100000, new Object[]{eleName}));
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage(), e);
            throw new BizException(e.getMessage(), e);
        }
    }

    /**
     * 校验必输栏位
     *
     * @param value  值
     * @param name   栏位
     * @param length 长度
     */
    protected void validateRequiredEle(String value, String name, int length) {
        validateNotNull(value, name);
        if (BusinessUtil.compareStringByteLength(value, length)) {
            log.info(msa.getMessage(Errors.E_100001, new Object[]{name, length}));
            throw new BizException(msa.getMessage(Errors.E_100001, new Object[]{name, length}));
        }
    }

    /**
     * 校验非必输栏位
     *
     * @param value  值
     * @param name   栏位
     * @param length 长度
     */
    protected void validateNoRequiredEle(String value, String name, int length) {
        if (!StringUtils.isBlank(value)) {
            validateRequiredEle(value, name, length);
        }
    }
}
