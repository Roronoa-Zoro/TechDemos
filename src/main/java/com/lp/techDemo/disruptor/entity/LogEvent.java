package com.lp.techDemo.disruptor.entity;

public class LogEvent {

	private String log;
	private String[] splitedLog;

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String[] getSplitedLog() {
		return splitedLog;
	}

	public void setSplitedLog(String[] splitedLog) {
		this.splitedLog = splitedLog;
	}
	
}
