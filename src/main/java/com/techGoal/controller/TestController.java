package com.techGoal.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.techGoal.dict.NumberDict;
import com.techGoal.pojo.dto.PageParamsDto;
import com.techGoal.pojo.vo.UserLoginVo;
import com.techGoal.redis.RedisManager;
import com.techGoal.utils.page.PageParamConvert;
import com.techGoal.utils.page.PageRespDTO;
import com.techGoal.utils.web.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@Controller
@RequestMapping("root")
public class TestController {

    @Autowired
    private RedisManager redisManager;


    @ApiOperation("图片上传")
    @RequestMapping("uploadPic")
    public String uploadPic() {
        return "index";
    }




    /**
     * 路径传参
     * @param value 路径参数
     * @return
     */
    /*@ApiOperation("仅仅是测试4")
    @PostMapping("testForSetValue4/{value}")
    public AjaxResult testForSetValue4(@PathVariable String value) {
        UserLogin userLogin = new UserLogin();
        userLogin.setId(1);
        userLogin.setPwd(value);
        userLoginService.setValue3(userLogin);
        return new AjaxResult(userLogin);
    }*/

    /**
     * 分页查询功能测试
     * @return
     */
    /*@ApiOperation("仅仅是测试5")
    @PostMapping("testForSetValue4")
    public AjaxResult testForGetValue(@RequestBody PageParamsDto pageParamsDto){

        PageRespDTO<UserLoginVo> pageRespDTOs = new PageRespDTO<>();
        // 当前页
        Integer currentPage = PageParamConvert.getCurrentPage(pageParamsDto.getCurrentPage());
        //每页记录数
        Integer pageSize = PageParamConvert.getPageSize(pageParamsDto.getPageSize());
        //分页查询参数设置
        PageHelper.offsetPage(currentPage * pageSize, pageSize);
        // 分页器处理过程中，bean类是不能转化的!
        Page<UserLogin> pageData = (Page<UserLogin>)userLoginService.getUserLoginList();
        PageRespDTO result = PageParamConvert.getPageRespDto(pageData);
        // do -> vo ,要分页器处理完之后才能替换!
        List<UserLoginVo> list1 = Lists.newArrayList();
        for(UserLogin userLogin: pageData){
            UserLoginVo userLoginVo = new UserLoginVo();
            userLoginVo.setId(userLogin.getId());
            userLoginVo.setLoginNo(userLogin.getLoginNo());
            userLoginVo.setPwd(userLogin.getPwd());
            list1.add(userLoginVo);
        }
        result.setList(list1);
        pageRespDTOs.setList(result.getList());
        return new AjaxResult(pageRespDTOs);
    }*/

}
