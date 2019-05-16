package com.techGoal.enums;

import com.techGoal.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 错误码枚举类
 * <p>
 * 1.
 * </p>
 *
 * @author wukong
 * @version 1.0.0
 * @date 2017/11/4
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum implements ErrorCode {
    /**
     * 登陆密码错误
     */
    ERROR_CODE_000001("000001", "账户或密码错误"),
    /**
     * 文件上传失败
     */
    ERROR_CODE_000002("000002", "文件上传失败"),


    ;
    /**
     * 错误码
     */
    private String errorCode;


    /**
     * 异常描述
     */
    private String errorDesc;

    /**
     * 设置错误描述信息
     *
     * @param errorMsg 错误信息
     */
    @Override
    public void setErrorDesc(String errorMsg) {
        this.errorDesc = errorMsg;
    }
}
