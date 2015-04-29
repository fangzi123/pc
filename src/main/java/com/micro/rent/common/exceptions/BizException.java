package com.micro.rent.common.exceptions;

/**
 * @author
 * @version 1.0
 * @Description 业务异常类
 * @date 2013-4-17
 */
@SuppressWarnings("serial")
public class BizException extends RuntimeException {
    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }


}
