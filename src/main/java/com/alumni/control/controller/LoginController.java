package com.alumni.control.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description : 登录-控制层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/6/23 10:43
 */
@Api(description = "login-Controller")
@Slf4j
@Controller
@RequestMapping("base")
public class LoginController {

    @ApiOperation(value = "校友管理-登录页面")
    @GetMapping("/register")
    public String alumniManageDetail() {
        return "/register";
    }
}
