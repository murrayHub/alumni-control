package com.alumni.control.exception;

/**
 * description : 错误码
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 15:29
 */
public interface ErrorCode {
    String getErrorCode();

    String getErrorDesc();

    void setErrorDesc(String var1);
}
