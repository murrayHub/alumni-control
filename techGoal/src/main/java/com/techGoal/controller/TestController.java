package com.techGoal.controller;

import cn.hutool.json.JSONUtil;
import com.techGoal.pojo.dao.UserLogin;
import com.techGoal.redis.RedisManager;
import com.techGoal.service.UserLoginService;
import com.techGoal.utils.web.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description : 测试专用
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/3/20 10:21
 */
@Api(description = "test-测试Controller")
@Slf4j
@RestController
@RequestMapping("root")
public class TestController {

    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private RedisManager redisManager;

    @ApiOperation("仅仅是测试")
    @GetMapping("testForGet")
    public AjaxResult test(){
        UserLogin userLogin = userLoginService.getUserLoginInfo();
//        String name = redisManager.queryObjectByKey("name");
//        redisManager.insertObject("123", "numberExam");
//        String numberExam = redisManager.queryObjectByKey("numberExam");
//        log.info("name={},numberExam={}", name,numberExam);
        return new AjaxResult(userLogin);
    }
}
