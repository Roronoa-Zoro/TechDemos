package com.lp.techDemo.metaq.entity;

import java.io.Serializable;
import java.util.Date;

public class MetaqDemoEntity implements Serializable {

	private static final long serialVersionUID = 1775754291102189394L;
	
	private long sendOrder;
	private String content;
	private Date sendTime;
	
	public long getSendOrder() {
		return sendOrder;
	}
	public void setSendOrder(long sendOrder) {
		this.sendOrder = sendOrder;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	
	@Override
	public String toString() {
		return "MetaqDemoEntity [sendOrder=" + sendOrder + ", content=" + content + ", sendTime=" + sendTime + "]";
	}
}
