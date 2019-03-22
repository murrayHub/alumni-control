package com.techGoal.service.impl;

import com.techGoal.enums.IDTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 生成用户号
 *
 * @author : Murray
 * @date : 2017/11/29
 */
@Component
@Slf4j
public class BaseIDBizImplBase extends BaseIDBiz {

    /**
     * 用户编号luckyNO
     */
    private static final String LUCKY_NO = "8";
    /**
     * 用户编号头三位
     */
    private static final String USER_NO_HEADER = "518";

    /**
     * redis最大值，当达到最大值时清空redis key
     */
    private static final int REDIS_MAX_VALUE = 9999999;
    /**
     * redis中生成key
     */
    private static final String REDIS_KEY = "GLOBAL-ACC:USER_NO_KEY";

    /**
     * 生成long型用户编号
     *
     * @param userType 用户类型
     * @return 返回用户编号
     */
    public Long generateLongUserNoBy(int userType) {

        return Long.valueOf(generateStringUserNoBy(userType));

    }

    /**
     * 生成String型用户编号
     *
     * @param userType 用户类型
     * @return 返回用户编号
     */
    public String generateStringUserNoBy(int userType) {
        if (IDTypeEnum.USER.getCode() == userType) {
            return generateStringUserNo();
        }
        if (IDTypeEnum.CUSTOMER.getCode() == userType) {
            return generateStringCustomerNo();
        }
        if (IDTypeEnum.OPERATOR.getCode() == userType) {
            return generateStringOperatorNo();
        }
        return null;
    }

    /**
     * 生成用户编号
     *
     * @return 返回用户编号
     */
    private String generateStringUserNo() {

        return super.generate19By(USER_NO_HEADER, IDTypeEnum.USER, REDIS_KEY, REDIS_MAX_VALUE, LUCKY_NO);

    }

    /**
     * 生成客户用户编号
     *
     * @return 返回用户编号
     */
    private String generateStringCustomerNo() {

        return super.generate19By(USER_NO_HEADER, IDTypeEnum.CUSTOMER, REDIS_KEY, REDIS_MAX_VALUE, LUCKY_NO);

    }

    /**
     * 生成操作员编号
     *
     * @return 返回用户编号
     */
    private String generateStringOperatorNo() {

        return super.generate19By(USER_NO_HEADER, IDTypeEnum.OPERATOR, REDIS_KEY, REDIS_MAX_VALUE, LUCKY_NO);

    }

}
