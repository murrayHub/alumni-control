package com.alumni.control.utils.web;

import com.alumni.control.exception.BaseException;
import com.alumni.control.exception.CommonErrorCode;
import com.alumni.control.exception.ErrorCode;
import com.alumni.control.exception.ParameterException;
import org.apache.commons.lang3.StringUtils;

/**
 * description : web异常处理工具类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 17:19
 */
public class WebExceptionUtils {
    public WebExceptionUtils() {
    }

    public static <T> AjaxResult<T> getResponse(Throwable err) {
        ErrorCode errorCode = getErrorCode(err);
        return new AjaxResult(errorCode);
    }

    public static ErrorCode getErrorCode(Throwable err) {
        try {
            if (err instanceof BaseException) {
                BaseException e = (BaseException)err;
                String errorMsg = StringUtils.isBlank(e.getExtraMsg()) ? e.getErrorCode().getErrorDesc() : e.getExtraMsg();
                ErrorCode errorCode = e.getErrorCode();
                errorCode.setErrorDesc(errorMsg);
                return errorCode;
            } else {
                return err instanceof ParameterException ? WebResultEnum.INVALID_ARGUMENT : WebResultEnum.SYSTEM_ERROR;
            }
        } catch (Exception var4) {
            return WebResultEnum.SYSTEM_ERROR;
        }
    }

    public static String getErrorMsg(Throwable err) {
        return getWebErrorCode(err).getErrorDesc();
    }

    public static ErrorCode getWebErrorCode(Throwable err) {
        try {
            if (err instanceof BaseException) {
                BaseException e = (BaseException)err;
                String errorMsg = StringUtils.isBlank(e.getExtraMsg()) ? e.getErrorCode().getErrorDesc() : e.getExtraMsg();
                ErrorCode errorCode = e.getErrorCode();
                errorCode.setErrorDesc(errorMsg);
                return errorCode;
            } else if (err instanceof IllegalArgumentException) {
                return CommonErrorCode.PARAMETER_VALID_NOT_PASS;
            } else {
                return err instanceof ParameterException ? CommonErrorCode.PARAMETER_VALID_NOT_PASS : CommonErrorCode.SYSTEM_INNER_ERROR;
            }
        } catch (Exception var4) {
            return CommonErrorCode.SYSTEM_INNER_ERROR;
        }
    }
}
