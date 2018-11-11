package com.dima.test.sms.DimaSMS.utils;

import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

public class CommonUtils {

	public static final String configFileName = "smsCommonSetting.properties";

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 从配置文件中随机获取昵称
	 * @return
	 */
	public static String getRandomNickNameFromProperties() {
		String nickName = PropertiesUtils.getProperty(configFileName, "nickName");
		if (StringUtils.isNotBlank(nickName)) {
			if (nickName.contains("&&")) {
				String[] nickNames = nickName.split("&&");
				int nextInt = new Random().nextInt(nickNames.length);
				return nickNames[nextInt];
			} else {
				return nickName;
			}
		} else {
			return "宝宝";
		}
	}
	
	/**
	 * 从配置文件中随机获取暗号
	 * @return
	 */
	public static String getRandomCodeFromProperties() {
		String nickName = PropertiesUtils.getProperty(configFileName, "randomCode");
		if (StringUtils.isNotBlank(nickName)) {
			if (nickName.contains("&&")) {
				String[] nickNames = nickName.split("&&");
				int nextInt = new Random().nextInt(nickNames.length);
				return nickNames[nextInt];
			} else {
				return nickName;
			}
		} else {
			return "1024";
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			String randomNickNameFromProperties = getRandomCodeFromProperties();
			System.out.println(randomNickNameFromProperties);
		}
	}
}