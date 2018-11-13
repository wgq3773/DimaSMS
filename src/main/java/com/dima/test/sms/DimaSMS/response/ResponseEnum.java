package com.dima.test.sms.DimaSMS.response;

public enum ResponseEnum {
	
	SUCCESS("0000","成功"),
	FAILED("0001","失败");
	
	private String code;
	private String desc;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	ResponseEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

}
