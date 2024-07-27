package com.light.springinit.exception;

import com.light.springinit.exception.errorcode.ErrorCode;

/**
 * 图表异常
 *
 * @author null&&
 * @Date 2024/7/16 16:46
 */
public class ChartException extends BizException {
    public ChartException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ChartException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public ChartException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }

    public ChartException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode);
    }

    public ChartException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode errorCode) {
        super(message, cause, enableSuppression, writableStackTrace, errorCode);
    }
}
