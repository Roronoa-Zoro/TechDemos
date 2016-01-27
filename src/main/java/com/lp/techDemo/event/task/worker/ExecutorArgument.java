package com.lp.techDemo.event.task.worker;

import java.util.Properties;

public class ExecutorArgument {

	private String instanceId;
	private Properties prop;
	
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public Properties getProp() {
		return prop;
	}
	public void setProp(Properties prop) {
		this.prop = prop;
	}
	
}
