package com.lp.techDemo.event.receiver.impl;

import com.lp.techDemo.event.EventArgument;
import com.lp.techDemo.event.receiver.EventReceiver;

public class EmailEventReceiver implements EventReceiver {

	@Override
	public void executeEvent(EventArgument ea) {
		System.err.println(Thread.currentThread().getName() + " " + ea.getBasicArgument());
	}

}
