package com.alumni.control.exception;

import com.alumni.control.utils.MessageHelper;
import com.alumni.control.utils.web.WebResultEnum;

/**
 * description : 参数异常处理类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 17:20
 */
public class ParameterException extends RuntimeException {
    private static final String MSG_TEMPLATE = "错误码:{0}, message:{1}";

    public ParameterException(WebResultEnum errorCode, Throwable cause) {
        super(getMessage(errorCode), cause);
    }

    public ParameterException(WebResultEnum errorCode) {
        super(getMessage(errorCode), (Throwable) null);
    }

    private static String getMessage(WebResultEnum errorCode) {
        return MessageHelper.formatMsg("错误码:{0}, message:{1}", new Object[]{errorCode.getErrorCode(), errorCode.getErrorDesc()});
    }
}
