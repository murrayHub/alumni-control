package com.alumni.control.exception;

/**
 * description : 错误码-枚举类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 15:37
 */
public enum  CommonErrorCode implements ErrorCode {
    DATA_BASE_ERROR("DATA_BASE_ERROR", "数据库操作异常"),
    PARAMETER_VALID_NOT_PASS("PARAMETER_VALID_NOT_PASS", "参数校验不通过"),
    PARAMETER_FIELD_VALID_NOT_PASS("PARAMETER_FIELD_VALID_NOT_PASS", " 参数对象内部属性校验不通过"),
    BLANK_IS_ILLEGAL_PARAM("BLANK_IS_ILLEGAL_PARAM", "参数为空（含null）是非法的"),
    NULL_IS_ILLEGAL_PARAM("NULL_IS_ILLEGAL_PARAM", "参数为null是非法的"),
    EMPTY_COLLECTION_ILLEGAL_PARAM("EMPTY_COLLECTION_ILLEGAL_PARAM", "参数集合为空（含null）是非法的"),
    REQ_PARAM_COLLECTION_SIZE_OUT_OF_LIMIT("REQ_PARAM_COLLECTION_SIZE_OUT_OF_LIMIT", "请求参数集合元素个数超过限定值"),
    REQ_PARAM_VALUE_OUT_OF_LIMIT_RANG("REQ_PARAM_VALUE_OUT_OF_LIMIT_RANG", "请求参数值超过限范围"),
    PAGE_SIZE_OUT_OF_LIMIT("PAGE_SIZE_OUT_OF_LIMIT", "每页显示数量超过限定值"),
    REMOTE_SERVICE_INVOKE_FAIL("REMOTE_SERVICE_INVOKE_FAIL", "远程服务调用返回失败"),
    REMOTE_SERVICE_UNKNOWN_RESULT("REMOTE_SERVICE_UNKNOWN_RESULT", "远程服务调用返回未决结果"),
    SYSTEM_INNER_ERROR("SYSTEM_INNER_ERROR", "系统内部错误"),
    UNEXPECTED_ERROR("UNEXPECTED_ERROR", "非预期的系统错误"),
    QUERY_RESULT_NULL("QUERY_RESULT_NULL", "查询结果集为空"),
    VALUE_NOT_SUPPORT("VALUE_NOT_SUPPORT", "不支持的取值");

    private String errorCode;
    private String errorDesc;

    private CommonErrorCode(String errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }
    @Override
    public String getErrorCode() {
        return this.errorCode;
    }
    @Override
    public String getErrorDesc() {
        return this.errorDesc;
    }
    @Override
    public void setErrorDesc(String errorMsg) {
        this.errorDesc = errorMsg;
    }
}
