package com.alumni.control.utils.validation;

import com.alumni.control.enums.BFErrorCodeEnum;
import com.alumni.control.exception.BizServiceException;
import com.alumni.control.exception.CommonErrorCode;
import com.alumni.control.exception.ErrorCode;
import com.alumni.control.exception.ParameterException;
import com.alumni.control.utils.TechGoalObjects;
import com.alumni.control.utils.web.WebResultEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * description : 参数校验
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/19 20:58
 */
public class ParamValidate {
    private static final Logger log = LoggerFactory.getLogger(ParamValidate.class);
    private static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();

    public ParamValidate() {
    }

    public static <T> void verify(T vo) {
        String errorMsg = validateParams(vo, "|#|");
        String[] validateParams = errorMsg.split("\\|#\\|");
        if (TechGoalObjects.isNotEmpty(validateParams) && TechGoalObjects.isNotEmpty(validateParams[0])) {
            log.error("参数错误:{}", errorMsg);
            throw new ParameterException(WebResultEnum.INVALID_ARGUMENT);
        }
    }

    public static <T> void verify(T vo, Class... clasz) {
        String errorMsg = validateParams(vo, "|#|", clasz);
        String[] validateParams = errorMsg.split("\\|#\\|");
        if (TechGoalObjects.isNotEmpty(validateParams) && TechGoalObjects.isNotEmpty(validateParams[0])) {
            log.error("参数错误:{}", errorMsg);
            throw new ParameterException(WebResultEnum.INVALID_ARGUMENT);
        }
    }

    public static <T> void validate(T vo) {
        String validateParams = validateParams(vo, "|#|");
        if (TechGoalObjects.isNotBlank(validateParams)) {
            BFErrorCodeEnum.ERROR_CODE_000002.setErrorDesc(validateParams);
            throw new BizServiceException(BFErrorCodeEnum.ERROR_CODE_000002);
        }
    }

    public static <T> void validate(T vo, Class... clasz) {
        String validateParams = validateParams(vo, "|#|", clasz);
        if (TechGoalObjects.isNotBlank(validateParams)) {
            BFErrorCodeEnum.ERROR_CODE_000002.setErrorDesc(validateParams);
            throw new BizServiceException(BFErrorCodeEnum.ERROR_CODE_000002);
        }
    }

    public static <T> void validateParams(T validateModel) {
        Validator validator = FACTORY.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(validateModel, new Class[0]);
        if (violations != null && violations.size() != 0) {
            Iterator var3 = violations.iterator();
            if (var3.hasNext()) {
                ConstraintViolation<T> violation = (ConstraintViolation)var3.next();
                throw new BizServiceException(CommonErrorCode.PARAMETER_VALID_NOT_PASS, violation.getMessage());
            }
        }
    }

    public static <T> String validateParams(T validateModel, String separator, Class... clasz) {
        Validator validator = FACTORY.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(validateModel, clasz);
        if (violations != null && violations.size() != 0) {
            if (null == separator) {
                separator = "";
            }

            StringBuffer stringBuffer = new StringBuffer();
            Iterator var6 = violations.iterator();

            while(var6.hasNext()) {
                ConstraintViolation<T> violation = (ConstraintViolation)var6.next();
                stringBuffer.append(separator).append(violation.getMessage());
            }

            return !"".equals(separator) ? stringBuffer.substring(stringBuffer.indexOf(separator) + separator.length()) : stringBuffer.toString();
        } else {
            return "";
        }
    }

    public static <T> String validateParams(T validateModel, String separator) {
        Validator validator = FACTORY.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(validateModel, new Class[0]);
        if (violations != null && violations.size() != 0) {
            if (null == separator) {
                separator = "";
            }

            StringBuffer stringBuffer = new StringBuffer();
            Iterator var5 = violations.iterator();

            while(var5.hasNext()) {
                ConstraintViolation<T> violation = (ConstraintViolation)var5.next();
                stringBuffer.append(separator).append(violation.getMessage());
            }

            return !"".equals(separator) ? stringBuffer.substring(stringBuffer.indexOf(separator) + separator.length()) : stringBuffer.toString();
        } else {
            return "";
        }
    }

    public static void validateNull(Object object) {
        if (object == null) {
            throw new BizServiceException(CommonErrorCode.BLANK_IS_ILLEGAL_PARAM);
        }
    }

    public static void validateNull(Object object, ErrorCode errorCode) {
        if (object == null) {
            throw new BizServiceException(errorCode);
        }
    }

    public static void validateStringNull(String... params) {
        String[] var1 = params;
        int var2 = params.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String param = var1[var3];
            if (StringUtils.isBlank(param)) {
                throw new BizServiceException(CommonErrorCode.BLANK_IS_ILLEGAL_PARAM, "参数信息中包含空字符");
            }
        }

    }

    public static void checkUpdate(int updNum) {
        if (updNum != 1) {
            throw new BizServiceException(CommonErrorCode.DATA_BASE_ERROR);
        }
    }

    public static void checkUpdate(int updNum, String errorMsg) {
        if (updNum != 1) {
            throw new BizServiceException(CommonErrorCode.DATA_BASE_ERROR, errorMsg);
        }
    }

    public static void checkUpdate(int updNum, ErrorCode errorCode) {
        if (updNum != 1) {
            throw new BizServiceException(errorCode);
        }
    }

    public static <T> void checkCollectSize(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new BizServiceException(CommonErrorCode.EMPTY_COLLECTION_ILLEGAL_PARAM);
        }
    }

    public static <T> void checkCollectSize(Collection<T> collection, ErrorCode errorCode) {
        if (collection == null || collection.isEmpty()) {
            throw new BizServiceException(errorCode);
        }
    }

    public static void addResult(int result) {
        if (result != 1) {
            throw new BizServiceException(CommonErrorCode.DATA_BASE_ERROR);
        }
    }

    public static void addResult(int result, String errorMsg) {
        if (result != 1) {
            throw new BizServiceException(CommonErrorCode.DATA_BASE_ERROR, errorMsg);
        }
    }

    public static void validateQueryCount(int count, ErrorCode errorCode) {
        if (count < 1) {
            throw new BizServiceException(errorCode);
        }
    }

    public static void validateQueryCount(int count) {
        if (count < 1) {
            throw new BizServiceException(CommonErrorCode.QUERY_RESULT_NULL);
        }
    }

    public static void validateQueryCount(int count, String errorMsg) {
        if (count < 1) {
            throw new BizServiceException(CommonErrorCode.QUERY_RESULT_NULL, errorMsg);
        }
    }
}