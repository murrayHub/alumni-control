package com.alumni.control.utils.validation;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * description : TODO
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/19 20:57
 */
public class BaseFacade implements Serializable {
    @NotBlank(
            message = "traceLogId|traceLogId字段必填"
    )
    String traceLogId;

    public BaseFacade() {
    }

    public void setTraceLogId(String traceLogId) {
        this.traceLogId = traceLogId;
    }

    public String getTraceLogId() {
        return this.traceLogId;
    }

    @Override
    public String toString() {
        return "BaseFacade()";
    }
}
