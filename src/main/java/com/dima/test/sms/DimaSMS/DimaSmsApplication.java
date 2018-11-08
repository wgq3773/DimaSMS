package com.dima.test.sms.DimaSMS;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.dima.test.sms.DimaSMS.utils.PropertiesUtils;

@EnableWebMvc
@SpringBootApplication
@ImportResource("classpath:applicationContext-sms.xml")
public class DimaSmsApplication {
	
	private static Log log = LogFactory.getLog(PropertiesUtils.class);

	public static void main(String[] args) {
		log.info("开始启动SpringBoot程序<DimaSMS>......");
		SpringApplication.run(DimaSmsApplication.class, args);
		log.info("SpringBoot程序<DimaSMS>启动完成......");
	}
}