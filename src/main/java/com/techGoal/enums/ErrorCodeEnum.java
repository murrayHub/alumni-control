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
 * @author Murray
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
    /**
     * 账号已存在
     */
    ERROR_CODE_000003("000003", "账号已存在"),
    /**
     * 该邮箱已存在
     */
    ERROR_CODE_000004("000004", "该邮箱已存在"),
    /**
     * 该手机号已存在
     */
    ERROR_CODE_000005("000005", "该手机号已存在"),
    /**
     * 该微信号已存在
     */
    ERROR_CODE_000006("000006", "该微信号已存在"),
    /**
     * 标签数量最大为6
     */
    ERROR_CODE_000007("000007", "标签数量最大为6"),
    /**
     * 用户编号为空
     */
    ERROR_CODE_000008("000008", "用户编号为空"),
    /**
     * 圈编号为空
     */
    ERROR_CODE_000009("000009", "圈编号为空"),
    /**
     * 入圈申请已提交，等待受理，请勿重复提交申请
     */
    ERROR_CODE_000010("000010", "入圈申请已提交，等待受理，请勿重复提交申请"),
    /**
     * 您已成功加入该圈，请勿重复提交申请
     */
    ERROR_CODE_000011("000011", "您已成功加入该圈，请勿重复提交申请"),
    /**
     * 您已关注过该用户
     */
    ERROR_CODE_000012("000012", "您已关注过该用户"),
    /**
     * 您尚未关注过该用户
     */
    ERROR_CODE_000013("000013", "您尚未关注过该用户"),


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
