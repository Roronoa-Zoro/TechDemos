package com.lp.techDemo.processor.message;

import com.lp.techDemo.processor.AbstractProcessor;

public class ProcessorMessage {

	private Object payload;
	private Throwable t;
	AbstractProcessor errorProcessor;
	
	public Object getPayload() {
		return payload;
	}
	public void setPayload(Object payload) {
		this.payload = payload;
	}
	public Throwable getT() {
		return t;
	}
	public void setT(Throwable t) {
		this.t = t;
	}
	public AbstractProcessor getErrorProcessor() {
		return errorProcessor;
	}
	public void setErrorProcessor(AbstractProcessor errorProcessor) {
		this.errorProcessor = errorProcessor;
	}
	
}
