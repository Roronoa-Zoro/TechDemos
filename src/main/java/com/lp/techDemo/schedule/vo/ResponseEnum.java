package com.lp.techDemo.schedule.vo;

public enum ResponseEnum {

	Success("成功"),
	Failure("失败");
	private String desc;

	private ResponseEnum(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
}
