package com.alumni.control.utils.validation.validate.impl;

import com.alumni.control.utils.validation.validate.StringEqValidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * description : TODO
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/19 21:00
 */
public class StringEqValidateImpl implements ConstraintValidator<StringEqValidate, String> {
    private String sub;

    public StringEqValidateImpl() {
    }

    @Override
    public void initialize(StringEqValidate constraintAnnotation) {
        this.sub = constraintAnnotation.sub();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return this.sub.equals(s);
    }
}