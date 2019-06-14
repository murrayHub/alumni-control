package com.alumni.control.service;

/**
 * description : 图片上传响应结果类
 * <p>
 * </p>
 *
 * @author : Murray
 * @version : 1.0.0
 * @date : 2019/5/16 10:00
 */
public interface IStatusMessage {
    String getCode();
    String getMessage();

    enum SystemStatus implements IStatusMessage {

        SUCCESS("1000", "操作成功"), //请求成功
        ERROR("1001", "网络异常，请稍后重试~"),
        FILE_UPLOAD_NULL("1002","上传图片为空文件"),
        FILE_UPLOAD_ERROR("1003","上传图片失败"),
        PARAM_ERROR("1004","图片格式不正确,支持png|jpg|jpeg"),
        ;

        private String code;
        private String message;

        SystemStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }
        @Override
        public String getCode() {
            return this.code;
        }
        @Override
        public String getMessage() {
            return this.message;
        }
    }
}
