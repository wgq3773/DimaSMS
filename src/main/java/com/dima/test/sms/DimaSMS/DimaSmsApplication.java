package com.dima.test.sms.DimaSMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DimaSmsApplication {

	public static void main(String[] args) {
		System.out.println("启动SpringBoot开始......");
		SpringApplication.run(DimaSmsApplication.class, args);
		System.out.println("启动SpringBoot结束......");
	}
}