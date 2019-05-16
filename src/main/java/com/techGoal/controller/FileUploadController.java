package com.techGoal.controller;

import com.techGoal.service.FileUpAndDownService;
import com.techGoal.service.IStatusMessage;
import com.techGoal.utils.web.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * description : 文件上传-控制层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/16 9:47
 */
@Slf4j
@Controller
@Api(description = "文件上传-控制层")
@RequestMapping("/upload")
public class FileUploadController {

    /**
     * 文件上传服务
     */
    @Autowired
    private FileUpAndDownService fileUpAndDownService;

    /**
     * 图片批量上传
     *
     * @param files 文件流
     * @return 结果
     */
    @ApiOperation("图片批量上传")
    @PostMapping(value = "/setFileUpload")
    @ResponseBody
    public AjaxResult setFileUpload(@RequestParam(value = "file", required = false) MultipartFile[] files) {
        AjaxResult result = new AjaxResult();
        log.info("请求批量上传图片,图片数量:{}", files.length);
        try {
            if (files.length > 0) {
                for (MultipartFile multipartFile : files) {
                    Map<String, Object> resultMap = fileUpAndDownService.upload(multipartFile);
                    if (!IStatusMessage.SystemStatus.SUCCESS.getCode().equals(resultMap.get("code"))) {
                        result.setCode(Integer.valueOf(IStatusMessage.SystemStatus.FILE_UPLOAD_ERROR.getCode()));
                        result.setMessage((String) resultMap.get("msg"));
                        return result;
                    }
                    log.info(">>>>>>图片上传成功，图片名称：{}", resultMap.get("newFileName"));
                }
                result.setCode(1000);
                log.info(">>>>>>图片批量上传成功");
            }else {
                log.error(">>>>>>上传图片为空文件");
                result.setCode(Integer.valueOf(IStatusMessage.SystemStatus.FILE_UPLOAD_NULL.getCode()));
                result.setMessage(IStatusMessage.SystemStatus.FILE_UPLOAD_NULL.getMessage());
            }
        } catch (Exception e) {
            log.error(">>>>>>图片上传异常，e={}", e.getMessage());
            result.setCode(Integer.valueOf(IStatusMessage.SystemStatus.FILE_UPLOAD_ERROR.getCode()));
            result.setMessage(IStatusMessage.SystemStatus.FILE_UPLOAD_ERROR.getMessage());
        }
        return result;
    }

}

