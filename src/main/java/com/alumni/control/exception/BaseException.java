package com.alumni.control.exception;

import com.alumni.control.utils.MessageHelper;

/**
 * description : 异常处理
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 15:27
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1279879878978996L;
    private static final String MSG_TEMPLATE = "错误码:{0}, 描述:{1}, 异常信息:{2}";
    private ErrorCode errorCode;
    private String extraMsg;
    private String language;

    public BaseException(ErrorCode errorCode, Throwable cause) {
        this(errorCode, (String) null, cause);
    }

    public BaseException(ErrorCode errorCode, String extraMsg, Throwable cause) {
        super(getMessage(errorCode, extraMsg), cause);
        this.errorCode = errorCode;
        this.extraMsg = extraMsg;
    }

    public BaseException(ErrorCode errorCode) {
        this(errorCode, (String) null, (Throwable) null);
    }

    public BaseException(ErrorCode errorCode, String extraMsg) {
        this(errorCode, extraMsg, (Throwable) null);
    }

    private static String getMessage(ErrorCode errorCode, String extraMessage) {
        extraMessage = null != extraMessage ? extraMessage : "";
        return MessageHelper.formatMsg("错误码:{0}, 描述:{1}, 异常信息:{2}", new Object[]{errorCode.getErrorCode(), errorCode.getErrorDesc(), extraMessage});
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public String getExtraMsg() {
        return this.extraMsg;
    }

    public void setExtraMsg(String extraMsg) {
        this.extraMsg = extraMsg;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
