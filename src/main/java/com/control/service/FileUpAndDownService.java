package com.techGoal.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * description : 文件上传服务
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/16 9:50
 */
public interface FileUpAndDownService {
    /**
     * 图片上传
     * @param file 文件流
     * @return 结果
     */
    Map<String, Object> uploadPicture(MultipartFile file) throws Exception;

    /**
     * 图片上传
     * @param file 文件流
     * @return 结果
     */
    Map<String, Object> upload(MultipartFile file) throws Exception;
}
