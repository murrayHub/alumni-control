package com.alumni.control.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * description : 上传图片配置信息类Bean
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/16 9:42
 */
@Component
@Data
public class MessageProperties {

    @Value("${message.fileSize}")
    private long fileSize;  //压缩大小

    @Value("${message.scaleRatio}")
    private double scaleRatio; //压缩比例

    @Value("${message.upPath}")
    private String upPath; //保存路径

    @Value("${message.imageType}")
    private String imageType; //图片类型
}
