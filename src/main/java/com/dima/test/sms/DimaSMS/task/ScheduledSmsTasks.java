package com.dima.test.sms.DimaSMS.task;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.dima.test.sms.DimaSMS.dto.SmsSendDTO;
import com.dima.test.sms.DimaSMS.sms.AliyunSMSService;
import com.dima.test.sms.DimaSMS.utils.CommonUtils;
import com.dima.test.sms.DimaSMS.utils.PropertiesUtils;

@Component
@Configurable
@EnableScheduling
public class ScheduledSmsTasks {
	
	private static Log log = LogFactory.getLog(PropertiesUtils.class);
	
	@Autowired
	private AliyunSMSService aliyunSMSService;
	
	@Value("${phoneNumbers}")
	private String phoneNumbers;
	
	@Value("${signName}")
	private String signName;
	
	@Value("${templateCode_good_morning}")
	private String templateCode_good_morning;
	
	@Value("${templateCode_good_afternoon}")
	private String templateCode_good_afternoon;
	
	@Value("${templateCode_good_night}")
	private String templateCode_good_night;
	
	@Value("${templateCode_auto_response}")
	private String templateCode_auto_response;
	
	@Value("${accessKeyId}")
	private String accessKeyId;
	
	@Value("${accessKeySecret}")
	private String accessKeySecret;
	
	private int count = 0;
	
	// 定时任务，每天早上八点执行，发送早安短信
	@Scheduled(cron = "0 0 8 * * ?")
	public void goodMorning(){
		SmsSendDTO smsSendDTO = new SmsSendDTO();
		smsSendDTO.setAccessKeyId(accessKeyId);
		smsSendDTO.setAccessKeySecret(accessKeySecret);
		smsSendDTO.setOutId(CommonUtils.getUUID());
		smsSendDTO.setPhoneNumbers(phoneNumbers);
		smsSendDTO.setSignName(signName);
		smsSendDTO.setTemplateCode(templateCode_good_morning);
		// 亲爱的${name}宝宝早上好，又是新的一天了，满怀希望，心向阳光，做一个可爱阳光积极向上的小公举，不急不躁，心静如水，过好每一天！在Dima微信公众号后台输入今天的暗号：${code}，获取一点小惊喜哟！加油！！！
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", CommonUtils.getRandomNickNameFromProperties());
		jsonObject.put("code", CommonUtils.getRandomCodeFromProperties());
		smsSendDTO.setTemplateParam(jsonObject.toString());
		SendSmsResponse sendSmsResponse = sendSms(smsSendDTO);
		if (null != sendSmsResponse && sendSmsResponse.getCode().equals("OK")) {
			log.info("早安短信短信发送成功！短信流水为：" + sendSmsResponse.getBizId());
		}else {
			log.info("早安短信短息发送失败!");
		}
	}
	
	// 定时任务，每天中午一点执行，发送午安短信
	@Scheduled(cron = "0 0 13 * * ?")
	public void goodAfternoon() {
		SmsSendDTO smsSendDTO = new SmsSendDTO();
		smsSendDTO.setAccessKeyId(accessKeyId);
		smsSendDTO.setAccessKeySecret(accessKeySecret);
		smsSendDTO.setOutId(CommonUtils.getUUID());
		smsSendDTO.setPhoneNumbers(phoneNumbers);
		smsSendDTO.setSignName(signName);
		smsSendDTO.setTemplateCode(templateCode_good_afternoon);
		// 亲爱的${name}宝宝，上午过得好吗？到中午了，要休息一下哟，孔子说：中午不睡，下午崩溃。午安啦！ 可以在Dima微信公众号后台发送暗号：${code}获取一点惊喜哟！
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", CommonUtils.getRandomNickNameFromProperties());
		jsonObject.put("code", CommonUtils.getRandomCodeFromProperties());
		smsSendDTO.setTemplateParam(jsonObject.toString());
		SendSmsResponse sendSmsResponse = sendSms(smsSendDTO);
		if (null != sendSmsResponse && sendSmsResponse.getCode().equals("OK")) {
			log.info("午安短信短信发送成功！短信流水为：" + sendSmsResponse.getBizId());
		} else {
			log.info("午安短信短息发送失败!");
		}
	}
	
	// 定时任务，每天晚上十一点执行，发送晚安短信
	@Scheduled(cron = "0 0 23 * * ?")
	public void goodNight() {
		SmsSendDTO smsSendDTO = new SmsSendDTO();
		smsSendDTO.setOutId(CommonUtils.getUUID());
		smsSendDTO.setAccessKeyId(accessKeyId);
		smsSendDTO.setAccessKeySecret(accessKeySecret);
		smsSendDTO.setPhoneNumbers(phoneNumbers);
		smsSendDTO.setSignName(signName);
		smsSendDTO.setTemplateCode(templateCode_good_night);
		//亲爱的${name}宝宝，一天即将结束了，今天过得开心吗？如果开心，我也替你开心，如果不开心，可以向我倾述，这样你就只有一半的不开心了，不管多不开心，好好睡一觉吧，一觉睡到小时候。晚安宝宝。 可以在Dima微信公众号后台发送暗号：${code}获取一点惊喜哟！
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", CommonUtils.getRandomNickNameFromProperties());
		jsonObject.put("code", CommonUtils.getRandomCodeFromProperties());
		smsSendDTO.setTemplateParam(jsonObject.toString());
		SendSmsResponse sendSmsResponse = sendSms(smsSendDTO);
		if (null != sendSmsResponse && sendSmsResponse.getCode().equals("OK")) {
			log.info("晚安短信短信发送成功！短信流水为：" + sendSmsResponse.getBizId());
		} else {
			log.info("晚安短信短息发送失败!");
		}
	}

	//每30秒执行一次,初始延迟3秒开始执行
	@Scheduled(fixedRate = 1000 * 30,initialDelay = 1000 * 10)
	public void reportCurrentByCron() {
		log.info("每30秒执行一次定时任务，这是第" + ++count + "次执行，当前时间：" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
	}
	
	@SuppressWarnings("static-access")
	public SendSmsResponse sendSms(SmsSendDTO smsSendDTO) {
		SendSmsResponse sendSmsResponse = aliyunSMSService.sendSms(smsSendDTO);
		if (null != sendSmsResponse && sendSmsResponse.getCode().equals("OK")) {
			log.info("短信发送成功！短信流水为：" + sendSmsResponse.getBizId());
		}else {
			log.info("短息发送失败!");
			return null;
		}
		return sendSmsResponse;
	}
}