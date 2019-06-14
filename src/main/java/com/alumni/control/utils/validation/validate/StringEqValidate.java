package com.alumni.control.utils.validation.validate;


import com.alumni.control.utils.validation.validate.impl.StringEqValidateImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * description : TODO
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/19 21:01
 */
@Constraint(
        validatedBy = {StringEqValidateImpl.class}
)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StringEqValidate {
    String message();

    String sub();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}