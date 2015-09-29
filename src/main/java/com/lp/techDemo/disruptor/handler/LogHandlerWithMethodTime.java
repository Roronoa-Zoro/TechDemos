package com.lp.techDemo.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import com.lp.techDemo.disruptor.entity.LogEvent;
import com.lp.techDemo.disruptor.util.NumberCalculator;

public class LogHandlerWithMethodTime implements EventHandler<LogEvent>{

	@Override
	public void onEvent(LogEvent event, long sequence, boolean endOfBatch) throws Exception {
		
		String[] args = event.getSplitedLog();
		NumberCalculator.Instance.increaseMethodTime(args[6], Long.valueOf(args[13].replace(".", "")));
		
	}

	
}
