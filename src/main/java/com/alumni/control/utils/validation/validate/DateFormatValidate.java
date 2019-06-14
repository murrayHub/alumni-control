package com.alumni.control.utils.validation.validate;


import com.alumni.control.utils.validation.validate.impl.DateFormatValidateImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * description : 日期格式校验
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/19 20:59
 */
@Constraint(
        validatedBy = {DateFormatValidateImpl.class}
)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DateFormatValidate {
    String message();

    String format();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}