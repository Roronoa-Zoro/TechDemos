package com.lp.techDemo.event.invoker;

import com.lp.techDemo.event.AbstractEvent;
import com.lp.techDemo.event.task.queue.TaskQueue;
import com.lp.techDemo.event.task.queue.TaskQueueManager;

public class EventInvoker {

	private String invokerName;
	private String taskQueueName;
	
	public String getInvokerName() {
		return invokerName;
	}

	public void setInvokerName(String invokerName) {
		this.invokerName = invokerName;
	}

	public String getTaskQueueName() {
		return taskQueueName;
	}

	public void setTaskQueueName(String taskQueueName) {
		this.taskQueueName = taskQueueName;
	}

	public void fireEvent(AbstractEvent event) {
		TaskQueue tq = TaskQueueManager.getTaskQueue(taskQueueName);
		event.generateEventArgument();
		tq.addTask(event.getEventArgument());
	}
}
