package com.lp.techDemo.http.message;

import java.io.Serializable;

public class MessageEntity implements Serializable{

	private static final long serialVersionUID = -4691054540808911311L;
	private Object entity;

	public Object getEntity() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}
	
	
}
