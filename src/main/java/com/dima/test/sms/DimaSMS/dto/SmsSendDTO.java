package com.dima.test.sms.DimaSMS.dto;

public class SmsSendDTO {

	// 阿里云短信服务accessKeyId
	private String accessKeyId;
	// 阿里云短信服务accessKeySecret
	private String accessKeySecret;
	// 接收短信的电话号码
	private String phoneNumbers;
	// 短信签名
	private String signName;
	// 短信模板code
	private String templateCode;
	// 短信参数
	private String templateParam;
	// 扩展字段
	private String outId;
	// 查询短信用到bizId
	private String bizId;
	
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	public String getAccessKeyId() {
		return accessKeyId;
	}
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
	public String getAccessKeySecret() {
		return accessKeySecret;
	}
	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}
	public String getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public String getSignName() {
		return signName;
	}
	public void setSignName(String signName) {
		this.signName = signName;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getTemplateParam() {
		return templateParam;
	}
	public void setTemplateParam(String templateParam) {
		this.templateParam = templateParam;
	}
	public String getOutId() {
		return outId;
	}
	public void setOutId(String outId) {
		this.outId = outId;
	}
	@Override
	public String toString() {
		return "SmsSendDTO [accessKeyId=" + accessKeyId + ", accessKeySecret=" + accessKeySecret + ", phoneNumbers="
				+ phoneNumbers + ", signName=" + signName + ", templateCode=" + templateCode + ", templateParam="
				+ templateParam + ", outId=" + outId + ", bizId=" + bizId + "]";
	}
	
}
