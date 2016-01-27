package com.lp.techDemo.event.task;

import com.lp.techDemo.event.EventArgument;
import com.lp.techDemo.event.receiver.EventReceiver;

public class TaskUnitFactory {

	public static TaskUnit createTaskUnit(EventReceiver er, EventArgument ea) {
		return new TaskUnit(er, ea);
	}
}
