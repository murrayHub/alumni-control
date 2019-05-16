package com.techGoal.controller;

import com.techGoal.service.FileUpAndDownService;
import com.techGoal.service.IStatusMessage;
import com.techGoal.utils.web.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private FileUpAndDownService fileUpAndDownService;

    @RequestMapping(value = "/setFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult setFileUpload(@RequestParam(value = "file", required = false) MultipartFile file) {
        AjaxResult result = new AjaxResult();
        try {
            Map<String, Object> resultMap = fileUpAndDownService.upload(file);
            if (!IStatusMessage.SystemStatus.SUCCESS.getCode().equals(resultMap.get("code"))) {
                result.setCode(Integer.valueOf(IStatusMessage.SystemStatus.FILE_UPLOAD_ERROR.getCode()));
                result.setMessage((String) resultMap.get("msg"));
                return result;
            }
            result.setResult(resultMap);
            result.setCode(Integer.valueOf((String) resultMap.get("code")));
            log.info(">>>>>>图片上传成功，图片名称：{}", resultMap.get("newFileName"));
        } catch (Exception e) {
            log.error(">>>>>>图片上传异常，e={}", e.getMessage());
            result.setCode(Integer.valueOf(IStatusMessage.SystemStatus.FILE_UPLOAD_ERROR.getCode()));
            result.setMessage(IStatusMessage.SystemStatus.FILE_UPLOAD_ERROR.getMessage());
        }
        return result;
    }

}

