package com.lp.techDemo.event.receiver;

import com.lp.techDemo.event.EventArgument;

public interface EventReceiver {

	void executeEvent(EventArgument ea);
}
