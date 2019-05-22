package com.techGoal.controller;

import com.google.common.collect.Maps;
import com.techGoal.service.FileUpAndDownService;
import com.techGoal.service.IStatusMessage;
import com.techGoal.utils.MessageProperties;
import com.techGoal.utils.web.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * description : 文件管理-控制层
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/16 9:47
 */
@Slf4j
@Controller
@Api(description = "文件管理-控制层")
@RequestMapping("/file-manage")
public class FileManageController {

    /**
     * 文件上传服务
     */
    @Autowired
    private FileUpAndDownService fileUpAndDownService;

    /**
     * 获取properties配置文件中的信息
     */
    @Autowired
    private MessageProperties config;

    /**
     * 图片批量上传
     *
     * @param files 文件流
     * @return 结果
     */
    @ApiOperation("图片批量上传")
    @PostMapping(value = "/set-file-upload")
    @ResponseBody
    public AjaxResult setFileUpload(@RequestParam(value = "file", required = false) MultipartFile[] files) {
        AjaxResult result = new AjaxResult();
        Map<String, Object> resultMap = Maps.newConcurrentMap();
        log.info("请求批量上传图片,图片数量:{}", files.length);
        try {
            if (files.length > 0) {
                for (MultipartFile multipartFile : files) {
                    resultMap = fileUpAndDownService.upload(multipartFile);
                    if (!IStatusMessage.SystemStatus.SUCCESS.getCode().equals(resultMap.get("code"))) {
                        result.setCode(Integer.valueOf(IStatusMessage.SystemStatus.FILE_UPLOAD_ERROR.getCode()));
                        result.setMessage((String) resultMap.get("msg"));
                        return result;
                    }
                    log.info(">>>>>>图片上传成功，图片名称：{}", resultMap.get("newFileName"));
                }
                result.setCode(1000);
                result.setResult(resultMap);
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

    /**
     * 图片预览
     *
     * @param file 文件名称
     * @return 结果
     */
    @ApiOperation("图片预览")
    @RequestMapping("/query-pic/{file}")
    @ResponseBody
    public void query_pic(HttpServletRequest request, HttpServletResponse response,@PathVariable(name = "file") String file) {
        try {
            // 截取图片后缀
            String picSuffix = file.split("\\.")[file.split("\\.").length-1];
            String[] IMAGE_TYPE = config.getImageType().split(",");
            boolean flag = false;
            for (String type : IMAGE_TYPE) {
                if (StringUtils.endsWithIgnoreCase(picSuffix, type)) {
                    flag = true;
                    break;
                }
            }
            if(flag){
                String fullPath = "D://Baofu/UploadData/images/20190519/"+file;
                File pf = new File(fullPath);
                if (!pf.exists()) {
                    return;
                }
                double rate = 1; //rate是压缩比率  1为原图  0.1为最模糊
                int[] results = getImgWidth(pf);
                int widthdist = 0;
                int heightdist = 0;
                if (results == null || results[0] == 0 || results[1] == 0) {
                    return;
                } else {
                    widthdist = (int) (results[0] * rate);
                    heightdist = (int) (results[1] * rate);
                }
                Image src = javax.imageio.ImageIO.read(pf);
                BufferedImage tag = new BufferedImage((int) widthdist, (int) heightdist,
                        BufferedImage.TYPE_INT_RGB);

                tag.getGraphics().drawImage(src.getScaledInstance(widthdist, heightdist, Image.SCALE_SMOOTH), 0, 0,
                        null);
                ServletOutputStream fout = response.getOutputStream();
                ImageIO.write(tag, picSuffix, fout);
                fout.close();
            }
        } catch (Exception e) {
            //异常处理
            log.error(">>>>>>图片预览异常");
            e.printStackTrace();
        }
    }

    /**
     * 获取图片尺寸
     * @param file 文件流
     * @return 结果
     */
    public static int[] getImgWidth(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int result[] = { 0, 0 };
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            result[0] = src.getWidth(null); // 得到源图宽
            result[1] = src.getHeight(null); // 得到源图高
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}

