package com.lp.techDemo.event;

import java.util.Map;

public class EventArgument {

	private Object basicArgument;
	private Map<String, Object> extraArgument;
	
	public Object getBasicArgument() {
		return basicArgument;
	}
	public void setBasicArgument(Object basicArgument) {
		this.basicArgument = basicArgument;
	}
	public Map<String, Object> getExtraArgument() {
		return extraArgument;
	}
	public void setExtraArgument(Map<String, Object> extraArgument) {
		this.extraArgument = extraArgument;
	}
}
