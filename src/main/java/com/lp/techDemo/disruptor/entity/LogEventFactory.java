package com.lp.techDemo.disruptor.entity;

import com.lmax.disruptor.EventFactory;

public class LogEventFactory implements EventFactory<LogEvent>{

	@Override
	public LogEvent newInstance() {
		// TODO Auto-generated method stub
		return new LogEvent();
	}

}
