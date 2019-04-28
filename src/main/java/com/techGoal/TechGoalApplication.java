package com.techGoal;

import com.techGoal.utils.ConfigUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.techGoal")
@MapperScan(value = "tk.mybatis.mapper.annotation", basePackages = "com.techGoal.mapper")
public class TechGoalApplication {

	public static void main(String[] args) {
		ConfigUtil.initProperties();
		SpringApplication.run(TechGoalApplication.class, args);
	}

}
