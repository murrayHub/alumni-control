package com.techGoal.service;

/**
 * description：创建BaseID Service
 *
 * @author : Murray on 2018/12/1
 * @version : 1.0.0
 */
public interface CreateBaseIDService {

    /**
     * 创建用户号
     *
     * @return 用户号
     */
    Long createUserNo();

    /**
     * 创建客户号
     *
     * @return 客户号
     */
    Long createCustomerNo();

    /**
     * 创建操作员编号
     *
     * @return 操作员编号
     */
    Long createOperatorNo();
}
