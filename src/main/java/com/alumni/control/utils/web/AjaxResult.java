package com.alumni.control.utils.web;

import com.alumni.control.exception.ErrorCode;
import lombok.Data;

import java.io.Serializable;

/**
 * description : Ajax结果
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 16:39
 */
@Data
public class AjaxResult<T> implements Serializable {
    private static final long serialVersionUID = -6711133977486943661L;
    private int code;
    private String message;
    private T result;

    public AjaxResult() {
    }

    public AjaxResult(ErrorCode webResultDict) {
        this.code = Integer.valueOf(webResultDict.getErrorCode());
        this.message = webResultDict.getErrorDesc();
    }

    public AjaxResult(ErrorCode webResultDict, T obj) {
        this.code = Integer.valueOf(webResultDict.getErrorCode());
        this.message = webResultDict.getErrorDesc();
        this.result = obj;
    }

    public AjaxResult(T result) {
        this.code = Integer.valueOf(WebResultEnum.SUCCESS.getErrorCode());
        this.message = WebResultEnum.SUCCESS.getErrorDesc();
        this.result = result;
    }

}
