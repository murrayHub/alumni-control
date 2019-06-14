package com.alumni.control.utils.web;

/**
 * description : TODO
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 16:45
 */
public enum WebResultModeEnum {
    AJAX("1", "Ajax方式"),
    PAGE_QUERY("2", "分页查询方式"),
    OTHER("2", "下拉列表、无需状态码的查询等等");

    private String errorCode;
    private String errorDesc;

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorDesc() {
        return this.errorDesc;
    }

    private WebResultModeEnum(String errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }
}
