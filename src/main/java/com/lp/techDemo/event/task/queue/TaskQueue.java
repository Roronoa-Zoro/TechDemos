package com.lp.techDemo.event.task.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.lp.techDemo.event.EventArgument;

public class TaskQueue {

	private String name;
	private BlockingQueue<EventArgument> queue;
	
	public TaskQueue(String name) {
		super();
		this.name = name;
		this.queue = new LinkedBlockingQueue<>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void addTask(EventArgument ea) {
		try {
			queue.put(ea);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public EventArgument getTaskArgument() {
		return queue.poll();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskQueue other = (TaskQueue) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
