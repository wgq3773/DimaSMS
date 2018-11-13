package com.dima.test.sms.DimaSMS.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

public class CommonUtils {

	public static final String CONFIG_FILE_PATH = "smsCommonSetting.properties";
	public static final String NICK_NAME = "nickName";
	public static final String RANDOM_CODE = "randomCode";
	
	private static Map<String, ArrayList<String>> configMap = new HashMap<>();
	private static ArrayList<String> configNickNameList = new ArrayList<String>();
	private static ArrayList<String> configRandomCodeList = new ArrayList<String>();

	/**
	 * 	获取UUID，替换中间横线
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 从配置文件中随机获取昵称
	 * @return
	 */
	public static String getRandomNickNameFromProperties() {
		String nickName = PropertiesUtils.getProperty(CONFIG_FILE_PATH, NICK_NAME);
		if (StringUtils.isBlank(nickName)) {
			return null;
		}
		if (!nickName.contains("&&")) {
			return nickName;
		}
		
		if (configMap.isEmpty() || configNickNameList.isEmpty()) {
			String[] nickNames = nickName.split("&&");
			for (String string : nickNames) {
				configNickNameList.add(string);
			}
			configMap.put(NICK_NAME, configNickNameList);
		}
		List<String> list = configMap.get(NICK_NAME);
		int index = new Random().nextInt(list.size());
		String randomNickName = list.get(index);
		list.remove(index);
		return randomNickName;
	}
	
	/**
	 * 从配置文件中随机获取暗号
	 * @return
	 */
	public static String getRandomCodeFromProperties() {
		String nickName = PropertiesUtils.getProperty(CONFIG_FILE_PATH, RANDOM_CODE);
		if (StringUtils.isBlank(nickName)) {
			return null;
		}
		if (!nickName.contains("&&")) {
			return nickName;
		}
		
		if (configMap.isEmpty() || configRandomCodeList.isEmpty()) {
			String[] nickNames = nickName.split("&&");
			for (String string : nickNames) {
				configRandomCodeList.add(string);
			}
			configMap.put(RANDOM_CODE, configRandomCodeList);
		}
		List<String> list = configMap.get(RANDOM_CODE);
		int index = new Random().nextInt(list.size());
		String randomNickName = list.get(index);
		list.remove(index);
		return randomNickName;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 30; i++) {
			String randomNickNameFromProperties = getRandomCodeFromProperties();
			System.out.println(randomNickNameFromProperties);
		}
	}
}