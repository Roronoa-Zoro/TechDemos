package com.lp.techDemo.schedule.vo;

import java.io.Serializable;

public class ResponseVO<E> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Object responseObject;
	private ResponseEnum responseState;
	private int responseCount;
	
	public Object getResponseObject() {
		return responseObject;
	}
	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}
	public ResponseEnum getResponseState() {
		return responseState;
	}
	public void setResponseState(ResponseEnum responseState) {
		this.responseState = responseState;
	}
	public int getResponseCount() {
		return responseCount;
	}
	public void setResponseCount(int responseCount) {
		this.responseCount = responseCount;
	}
	
	
}
