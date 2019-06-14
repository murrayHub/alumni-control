package com.alumni.control.exception;


/**
 * description : 异常处理
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 15:28
 */
public class BizServiceException extends BaseException {
    public BizServiceException(ErrorCode errorCode, Throwable cause) {
        this(errorCode, (String)null, cause);
    }

    public BizServiceException(ErrorCode errorCode, String extraMsg, Throwable cause) {
        super(errorCode, extraMsg, cause);
    }

    public BizServiceException(ErrorCode errorCode) {
        this(errorCode, (String)null, (Throwable)null);
    }

    public BizServiceException(ErrorCode errorCode, String extraMsg) {
        this(errorCode, extraMsg, (Throwable)null);
    }
}
