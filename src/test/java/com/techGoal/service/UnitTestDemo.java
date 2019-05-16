package com.techGoal.service;

import com.techGoal.frame.BaseTest;
import com.techGoal.mapper.RegionMapper;
import com.techGoal.pojo.dao.RegionDo;
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

    @Autowired
    private RegionMapper regionMapper;

    @Test
    public void test(){
        List<RegionDo> provinceList = regionMapper.selectCityByProId(Integer.valueOf(210000));
        log.info("list----------{}",provinceList);
    }
}
