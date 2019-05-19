package com.techGoal.service.impl;

import com.techGoal.enums.IDTypeEnum;
import com.techGoal.redis.OrderIdManager;
import com.techGoal.service.CreateBaseIDService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description：创建BaseID ServiceImpl
 *
 * @author : Murray on 2018/12/1
 * @version : 1.0.0
 */
@Slf4j
@Service
public class CreateBaseIDServiceImpl implements CreateBaseIDService {

    /**
     * 创建帐号
     */
    @Autowired
    private BaseIDBizImplBase baseIDBizImplBase;

    /**
     * 创建宝付订单号
     */
    @Autowired
    private OrderIdManager orderIdManager;


    /**
     * 创建用户号
     *
     * @return 用户号
     */
    @Override
    public Long createUserNo() {
        return baseIDBizImplBase.generateLongUserNoBy(IDTypeEnum.USER.getCode());
    }

    /**
     * 创建客户号
     *
     * @return 客户号
     */
    @Override
    public Long createCustomerNo() {
        return baseIDBizImplBase.generateLongUserNoBy(IDTypeEnum.CUSTOMER.getCode());
    }

    /**
     * 创建操作员编号
     *
     * @return 操作员编号
     */
    @Override
    public Long createOperatorNo() {
        return baseIDBizImplBase.generateLongUserNoBy(IDTypeEnum.OPERATOR.getCode());
    }
}
