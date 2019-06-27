package com.alumni.control.service;

import com.alumni.control.dict.NumberLongDict;
import com.alumni.control.frame.BaseTest;
import com.alumni.control.pojo.dto.CommonParamDto;
import com.alumni.control.redis.RedisManager;
import com.alumni.control.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.testng.annotations.Test;

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
    @Autowired
    private RedisManager redisManager;
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void yyy() {
        CommonParamDto commonParamDto = new CommonParamDto();
        commonParamDto.setLoginNo("213123");
        redisManager.insertObject(commonParamDto, "abc", NumberLongDict.TWO_HOUR);
        CommonParamDto commonParamDto2 = redisManager.queryObjectByKey("abc", CommonParamDto.class);
        log.info("equal:{}", commonParamDto2);
    }
}
