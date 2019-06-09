package com.techGoal.service;

import com.techGoal.frame.BaseTest;
import com.techGoal.mapper.JobPositionMapper;
import com.techGoal.mapper.UserPwdMapper;
import com.techGoal.pojo.dao.UserLabelDo;
import com.techGoal.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

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

    @Autowired
    private CacheService cacheService;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test() {
        Boolean bo1 = redisUtil.zAdd("user1", "user2", 0.1);
        Boolean bo2 = redisUtil.zAdd("user1", "user3", 0.5);
        redisUtil.zAdd("user1", "user4", 0.2);
        redisUtil.zAdd("user1", "user5", 0.8);
        // 返回元素在集合的排名,score由大到小,角标从0开始
        Long sort2 = redisUtil.zReverseRank("user1", "user2");
        Long sort5 = redisUtil.zReverseRank("user1", "user5");
        log.info("sort={}", sort2);
        log.info("sort={}", sort5);
        // 获取集合元素, 从大到小排序
        Set<String> set = redisUtil.zReverseRange("user1", 0, 3);
        log.info("set={}", set);
    }
    @Test
    public void getUserLabels() {
        List<UserLabelDo> res = cacheService.getUserLabels();
        log.info("res:{}", res);
    }
}
