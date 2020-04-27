package com.alumni.control;

import com.alumni.control.utils.ConfigUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "com.alumni.control",
        exclude = {MultipartAutoConfiguration.class})
//@ComponentScan(basePackages = "com.alumni.control")
@MapperScan(value = "tk.mybatis.mapper.annotation", basePackages = "com.alumni.control.mapper")
//exclude表示自动配置时不包括Multipart配置
//@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
public class AlumniControlApplication {

    public static void main(String[] args) {
        ConfigUtil.initProperties();
        SpringApplication.run(AlumniControlApplication.class, args);
    }

    /**
     * 显示声明CommonsMultipartResolver为mutipartResolver
     */
    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        //resolver.setDefaultEncoding("UTF-8");
        //resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
        resolver.setResolveLazily(true);
        resolver.setMaxInMemorySize(40960);
        resolver.setMaxUploadSize(4 * 1024 * 1024);//上传文件大小 3M 3*1024*1024
        return resolver;
    }

}
