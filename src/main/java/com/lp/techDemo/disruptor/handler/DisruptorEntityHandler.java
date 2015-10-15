package com.lp.techDemo.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import com.lp.techDemo.disruptor.entity.DisruptorEntity;
import com.lp.techDemo.http.message.MessageEntity;

public class DisruptorEntityHandler implements EventHandler<MessageEntity>{

	@Override
	public void onEvent(MessageEntity event, long sequence, boolean endOfBatch) throws Exception {
		DisruptorEntity de = (DisruptorEntity) event.getEntity();
		System.err.println(Thread.currentThread().getName() + " get " + de);
	}

}
