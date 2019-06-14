package com.alumni.control.utils.web;


import com.alumni.control.exception.ErrorCode;

import java.io.Serializable;

/**
 * description : Ajax结果-泛型类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 16:48
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1686038030676293508L;
    private boolean success;
    private T result;
    private String errorCode;
    private String errorMsg;

    public Result() {
    }

    public Result(T result) {
        this.success = true;
        this.result = result;
    }

    public Result(boolean flag, T result) {
        if (flag) {
            this.success = true;
            this.result = result;
        } else {
            this.success = false;
            this.errorCode = (String)result;
        }

    }

    public Result(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Result(ErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMsg = errorCode.getErrorDesc();
    }

    public boolean isSuccess() {
        return this.success;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.success = true;
        this.result = result;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.success = false;
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Result response = (Result)o;
            return this.success == response.success && this.result.equals(response.result) && this.errorCode.equals(response.errorCode);
        } else {
            return false;
        }
    }
    @Override
    public int hashCode() {
        int result1 = this.success ? 1 : 0;
        result1 = 31 * result1 + this.result.hashCode();
        result1 = 31 * result1 + this.errorCode.hashCode();
        return result1;
    }
    @Override
    public String toString() {
        return "Result(success=" + this.isSuccess() + ", result=" + this.getResult() + ", errorCode=" + this.getErrorCode() + ", errorMsg=" + this.getErrorMsg() + ")";
    }
}
