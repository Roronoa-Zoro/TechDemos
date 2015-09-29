package com.lp.techDemo.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import com.lp.techDemo.disruptor.entity.LongEvent;

public class LongEventHandler implements EventHandler<LongEvent> {

	@Override
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
		//TODO
		System.err.println(Thread.currentThread().getName() + ", sequence#->" + sequence + ", " + event);
	}

}
