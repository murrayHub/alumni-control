package com.alumni.control.utils.web;

import java.lang.annotation.*;

/**
 * description : ajax结果状态码
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 16:44
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface WebEnhance {
    WebResultModeEnum mode() default WebResultModeEnum.AJAX;

    boolean reqLogFlag() default true;

    boolean respLogFlag() default true;
}
