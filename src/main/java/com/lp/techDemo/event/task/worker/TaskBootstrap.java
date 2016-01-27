package com.lp.techDemo.event.task.worker;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lp.techDemo.event.task.TaskMonitorThread;

public class TaskBootstrap {

	private static final String DEFAULT_INSTANCE = "Default_TaskInstance";
	private final Logger log = LoggerFactory.getLogger(TaskBootstrap.class);
	
	private String taskInstanceId;
	//thread pool properties
	private Properties prop;
	private TaskResource taskResource;
	private TaskMonitorThread monitor;
	
	public String getTaskInstanceId() {
		return taskInstanceId;
	}
	public void setTaskInstanceId(String taskInstanceId) {
		this.taskInstanceId = taskInstanceId;
	}

	public Properties getProp() {
		return prop;
	}
	public void setProp(Properties prop) {
		this.prop = prop;
	}
	public TaskResource getTaskResource() {
		return taskResource;
	}
	public void setTaskResource(TaskResource taskResource) {
		this.taskResource = taskResource;
	}
	public TaskMonitorThread getMonitor() {
		return monitor;
	}
	public void setMonitor(TaskMonitorThread monitor) {
		this.monitor = monitor;
	}
	
	public void init() {
		if (StringUtils.isBlank(taskInstanceId)) {
			taskInstanceId = DEFAULT_INSTANCE;
		}
		
		monitor.start();
		
		taskResource.initExecutor(taskInstanceId, prop);
		log.info("TaskBootstrap[{}] is running", taskInstanceId);
	}
	
	public void shutdown() {
		if (monitor != null) {
			monitor.shutdown();
		}
		if (taskResource != null) {
			taskResource.shutdownExecutor();
		}
		
		log.info("TaskBootstrap[{}] is shutdown", taskInstanceId);
	}
	
}
