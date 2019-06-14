package com.alumni.control.utils.validation.validate.impl;

import com.alumni.control.utils.DateUtil;
import com.alumni.control.utils.validation.validate.DateFormatValidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

/**
 * description : TODO
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/19 21:00
 */
public class DateFormatValidateImpl implements ConstraintValidator<DateFormatValidate, String> {
    private static final Logger log = LoggerFactory.getLogger(DateFormatValidateImpl.class);
    private String format;

    public DateFormatValidateImpl() {
    }

    @Override
    public void initialize(DateFormatValidate constraintAnnotation) {
        this.format = constraintAnnotation.format();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Date parse = DateUtil.parse(s, this.format);
            if (DateUtil.format(parse).equals(s)) {
                return true;
            }
        } catch (Exception var4) {
            log.error("日期格式校验错误", var4);
        }

        return false;
    }
}
