package com.lp.techDemo.disruptor.handler;

import com.lmax.disruptor.WorkHandler;
import com.lp.techDemo.disruptor.entity.LongEvent;

public class LongEventConsumer2 implements WorkHandler<LongEvent>{

	private static final String PROCESSOR_NAME = "LongEventConsumer2";
	
	@Override
	public void onEvent(LongEvent event) throws Exception {
		System.err.println(PROCESSOR_NAME + " processing -> " + event);
		
	}

}
