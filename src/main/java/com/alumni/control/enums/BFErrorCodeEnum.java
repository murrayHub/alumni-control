package com.alumni.control.enums;


import com.alumni.control.exception.ErrorCode;

/**
 * description : 错误码-枚举类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 15:31
 */
public enum BFErrorCodeEnum implements ErrorCode {
    ERROR_CODE_000001("000001", "traceLogId必填"),
    ERROR_CODE_000002("000002", "参数错误"),
    ERROR_CODE_000003("000003", "系统繁忙，请稍后再试"),
    ERROR_CODE_000004("000004", "正在处理，请勿频繁操作！"),
    ERROR_CODE_100001("100001", "工具-HttpUtil-异常"),
    ERROR_CODE_100002("100002", "ftp信息不存在"),
    ERROR_CODE_100003("100003", "文件或目录不存在"),
    ERROR_CODE_100005("100005", "DES加密异常"),
    ERROR_CODE_100006("100006", "DES解密异常"),
    ERROR_CODE_100007("100007", "AES加密异常"),
    ERROR_CODE_100008("100008", "AES解密异常");

    private String errorCode;
    private String errorDesc;

    public void setErrorDesc(String errorMsg) {
        this.errorDesc = errorMsg;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorDesc() {
        return this.errorDesc;
    }


    private BFErrorCodeEnum(String errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }
}
