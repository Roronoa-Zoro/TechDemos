package com.lp.techDemo.event.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lp.techDemo.event.EventArgument;
import com.lp.techDemo.event.receiver.EventReceiver;
import com.lp.techDemo.event.task.queue.TaskQueue;
import com.lp.techDemo.event.task.queue.TaskQueueManager;
import com.lp.techDemo.event.task.worker.TaskResource;

/**
 * monitor target event queue and dispatch to worker pool
 * @author Jimmy Lee
 *
 */
public class TaskMonitorThread extends Thread {

	
	private final Logger log = LoggerFactory.getLogger(TaskMonitorThread.class);
	private volatile boolean running = true;
	
	private EventReceiver er;
	private String taskQueueName;
	private TaskResource tr;
	
	public EventReceiver getEr() {
		return er;
	}

	public void setEr(EventReceiver er) {
		this.er = er;
	}

	public String getTaskQueueName() {
		return taskQueueName;
	}

	public void setTaskQueueName(String taskQueueName) {
		this.taskQueueName = taskQueueName;
	}

	public TaskResource getTr() {
		return tr;
	}

	public void setTr(TaskResource tr) {
		this.tr = tr;
	}

	@Override
	public void run() {
		TaskQueue tq = TaskQueueManager.getTaskQueue(taskQueueName);
		while (running) {
			
			EventArgument ea = tq.getTaskArgument();
			if (ea == null) {
				continue;
			}
			TaskUnit tu = TaskUnitFactory.createTaskUnit(er, ea);
			tr.acceptTask(tu);
			log.debug("accept a task");
		}
		log.info("TaskMonitorThread exit");
	}

	public void initMonitor() {
		this.start();
	}

	public void shutdown() {
		running = false;
		log.info("shut down TaskMonitorThread");
	}
}
