package com.techGoal.service;

import com.techGoal.dict.CommonDict;
import com.techGoal.dict.NumberDict;
import com.techGoal.frame.BaseTest;
import com.techGoal.mapper.JobPositionMapper;
import com.techGoal.mapper.UserPwdMapper;
import com.techGoal.pojo.dao.JobPositionDo;
import com.techGoal.pojo.dao.UserPwdDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

/**
 * description : 单元测试类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/22 9:40
 */
@Slf4j
@Rollback(false)
public class UnitTestDemo extends BaseTest {

    /**
     * 职位信息
     */
    @Autowired
    private JobPositionMapper jobPositionDoMapper;
    @Autowired
    private UserPwdMapper userPwdMapper;

    @Test
    public void test() {

        UserPwdDo userPwdDo = new UserPwdDo();
        userPwdDo.setUserId(123456L);
        userPwdDo.setPwd("123");
        userPwdDo.setPwdType(NumberDict.ONE);
        userPwdDo.setCreateAt(new Date());
        userPwdDo.setCreateBy(CommonDict.SYSTEM);
        userPwdMapper.insert(userPwdDo);
    }
}
