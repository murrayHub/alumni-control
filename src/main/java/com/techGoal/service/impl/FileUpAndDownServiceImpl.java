package com.techGoal.service.impl;

import com.techGoal.dict.NumberStrDict;
import com.techGoal.service.FileUpAndDownService;
import com.techGoal.service.IStatusMessage;
import com.techGoal.utils.DateUtil;
import com.techGoal.utils.MessageProperties;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * description : 文件上传服务-实现类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/16 9:51
 */
@Slf4j
@Component
public class FileUpAndDownServiceImpl implements FileUpAndDownService {

    @Autowired
    private MessageProperties config; //用来获取properties配置文件中的信息

    /**
     * 图片上传
     *
     * @param file 文件流
     * @return 结果
     */
    @Override
    public Map<String, Object> uploadPicture(MultipartFile file) throws Exception {
        Map<String, Object> resMap = new HashMap<>();
        String[] IMAGE_TYPE = config.getImageType().split(",");
        String path = null;
        boolean flag = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), type)) {
                flag = true;
                break;
            }
        }
        if (flag) {
            String uuid = NumberStrDict.ONE + UUID.randomUUID().toString().substring(0, 5) + DateUtil.getCurrent();
            // 获得文件类型
            String fileType = file.getContentType();
            // 获得文件后缀名称
            String imageName = fileType.substring(fileType.indexOf("/") + 1);
            // 原名称
            String oldFileName = file.getOriginalFilename();
            // 新名称
            String newFileName = uuid + "." + imageName;
            // 年月日文件夹
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String basedir = sdf.format(new Date());
            // 进行压缩(大于4M)
            if (file.getSize() > config.getFileSize()) {
                // 重新生成
                String newUUID = NumberStrDict.ONE + UUID.randomUUID().toString().substring(0, 5) + DateUtil.getCurrent();
                newFileName = newUUID + "." + imageName;
                path = config.getUpPath() + "/" + basedir + "/" + newUUID + "." + imageName;
                // 如果目录不存在则创建目录
                File oldFile = new File(path);
                if (!oldFile.exists()) {
                    oldFile.mkdirs();
                }
                file.transferTo(oldFile);
                // 压缩图片
                Thumbnails.of(oldFile).scale(config.getScaleRatio()).toFile(path);
                // 显示路径
                resMap.put("path", "/" + basedir + "/" + newUUID + "." + imageName);
            } else {
                path = config.getUpPath() + "/" + basedir + "/" + uuid + "." + imageName;
                // 如果目录不存在则创建目录
                File uploadFile = new File(path);
                if (!uploadFile.exists()) {
                    uploadFile.mkdirs();
                }
                file.transferTo(uploadFile);
                // 显示路径
                resMap.put("path", "/" + basedir + "/" + uuid + "." + imageName);
            }
            resMap.put("result", IStatusMessage.SystemStatus.SUCCESS.getMessage());
            resMap.put("oldFileName", oldFileName);
            resMap.put("newFileName", newFileName);
            resMap.put("fileSize", file.getSize());
            resMap.put("code", IStatusMessage.SystemStatus.SUCCESS.getCode());
        } else {
            resMap.put("result", "图片格式不正确,支持png|jpg|jpeg");
            resMap.put("code", IStatusMessage.SystemStatus.PARAM_ERROR.getCode());
        }
        return resMap;
    }

    /**
     * 图片上传
     *
     * @param file 文件流
     * @return 结果
     */
    @Override
    public Map<String, Object> upload(MultipartFile file) throws Exception {
        Map<String, Object> returnMap = new HashMap<>();
        if (!file.isEmpty()) {
            Map<String, Object> picMap = uploadPicture(file);
            if (IStatusMessage.SystemStatus.SUCCESS.getMessage().equals(picMap.get("result"))) {
                return picMap;
            } else {
                returnMap.put("result", IStatusMessage.SystemStatus.ERROR.getMessage());
                returnMap.put("msg", picMap.get("result"));
                returnMap.put("code", IStatusMessage.SystemStatus.ERROR.getCode());
            }
        } else {
            log.info(">>>>>>上传图片为空文件");
            returnMap.put("result", IStatusMessage.SystemStatus.FILE_UPLOAD_NULL.getMessage());
            returnMap.put("msg", IStatusMessage.SystemStatus.FILE_UPLOAD_NULL.getMessage());
            returnMap.put("code", IStatusMessage.SystemStatus.FILE_UPLOAD_NULL.getCode());
        }
        return returnMap;
    }
}

