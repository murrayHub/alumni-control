package com.techGoal.service;

import com.techGoal.frame.BaseTest;
import com.techGoal.mapper.JobPositionMapper;
import com.techGoal.pojo.dao.JobPositionDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

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
public class UnitTestDemo extends BaseTest {

    /**
     * 职位信息
     */
    @Autowired
    private JobPositionMapper jobPositionDoMapper;

    @Test
    public void test() {

        JobPositionDo jobPositionDo = new JobPositionDo();
        jobPositionDo.setParentNo(Integer.valueOf(1012));
        List<JobPositionDo> list = jobPositionDoMapper.select(jobPositionDo);
        log.info("list----------{}", list);
    }
}
