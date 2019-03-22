package com.techGoal;

import com.techGoal.utils.ConfigUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.techGoal")
@MapperScan(basePackages = "com.techGoal.mapper")
public class TechGoalApplication {

	public static void main(String[] args) {
		ConfigUtil.initProperties();
		SpringApplication.run(TechGoalApplication.class, args);
	}

}
