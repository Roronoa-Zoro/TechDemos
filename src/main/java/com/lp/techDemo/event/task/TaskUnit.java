package com.lp.techDemo.event.task;

import com.lp.techDemo.event.EventArgument;
import com.lp.techDemo.event.receiver.EventReceiver;

/**
 * 
 * @author Jimmy Lee
 *
 */
public class TaskUnit implements Runnable {

	private EventReceiver er;
	private EventArgument ea;
	
	public TaskUnit(EventReceiver er, EventArgument ea) {
		super();
		this.er = er;
		this.ea = ea;
	}

	@Override
	public void run() {
		er.executeEvent(ea);
	}

}
