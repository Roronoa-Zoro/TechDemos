package com.lp.techDemo.disruptor.entity;

import java.util.Date;

public class LongEvent {

	private long value;
	private Date createdDate;
	
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	public Date getDate() {
		return createdDate;
	}
	public void setDate(Date date) {
		this.createdDate = date;
	}
	
	@Override
	public String toString() {
		return "LongEvent [value=" + value + ", createdDate=" + createdDate + "]";
	}
	
	
}
