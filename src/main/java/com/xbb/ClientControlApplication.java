package com.xbb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync	//开启异步
@ComponentScan      //启动注解扫描配置
@EnableAutoConfiguration //自动配置
@EnableScheduling    // 开启定时器
@SpringBootApplication
public class ClientControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientControlApplication.class, args);
	}
}
