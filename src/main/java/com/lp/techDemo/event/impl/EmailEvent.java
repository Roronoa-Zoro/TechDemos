package com.lp.techDemo.event.impl;

import com.lp.techDemo.event.AbstractEvent;
import com.lp.techDemo.event.EventArgument;

public class EmailEvent extends AbstractEvent {

	@Override
	public void generateEventArgument() {
		EventArgument ea = new EventArgument();
		String tmp = "this is demo email";
		ea.setBasicArgument(tmp);
		super.setEventArgument(ea);
	}
}
