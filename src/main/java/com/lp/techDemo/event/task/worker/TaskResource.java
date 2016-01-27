package com.lp.techDemo.event.task.worker;

import java.util.Properties;

import com.lp.techDemo.event.task.TaskUnit;

public class TaskResource {

	private TaskExecutor te;
	
	public void acceptTask(TaskUnit tu) {
		te.execute(tu);
	}
	
	public void initExecutor(String instanceId, Properties prop) {
		te = new TaskExecutor();
		ExecutorArgument ea = new ExecutorArgument();
		ea.setInstanceId(instanceId);
		ea.setProp(prop);
		te.startExecutor(ea);
	}
	
	public void shutdownExecutor() {
		te.shutdownExecutor();
	}
}
