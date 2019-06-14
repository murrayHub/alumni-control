package com.alumni.control.utils.web;


import com.alumni.control.exception.ErrorCode;

/**
 * description : Ajax结果-枚举类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 16:41
 */
public enum WebResultEnum implements ErrorCode {


    SUCCESS("0", "成功"),
    INVALID_ARGUMENT("1", "参数错误"),
    SYSTEM_ERROR("2", "系统内部异常");

    private String errorCode;
    private String errorDesc;

    @Override
    public void setErrorDesc(String errorMsg) {
        this.errorDesc = errorMsg;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorDesc() {
        return this.errorDesc;
    }

    WebResultEnum(String errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }
}
