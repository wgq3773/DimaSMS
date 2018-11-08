package com.dima.test.sms.DimaSMS.task;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {
	
	private int count = 0;

	//每2秒执行一次,初始延迟3秒开始执行
	@Scheduled(fixedRate = 2000,initialDelay = 3000)
	public void reportCurrentByCron() {
		System.out.println("每2秒执行一次定时任务，这是第" + ++count + "次执行，当前时间：" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
	}
}