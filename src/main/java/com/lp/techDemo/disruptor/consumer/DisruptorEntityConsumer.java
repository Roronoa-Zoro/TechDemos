package com.lp.techDemo.disruptor.consumer;

import com.lmax.disruptor.dsl.Disruptor;
import com.lp.techDemo.disruptor.handler.DisruptorEntityHandler;
import com.lp.techDemo.http.message.MessageEntity;
import com.lp.techDemo.http.message.listener.GeneralMessageConsumer;

public class DisruptorEntityConsumer implements GeneralMessageConsumer{

	private Disruptor<MessageEntity> disruptor2;
	
	public DisruptorEntityConsumer(Disruptor<MessageEntity> disruptor2) {
		super();
		this.disruptor2 = disruptor2;
	}


	@Override
	public void onMessageReceive() {
		
		disruptor2.handleEventsWith(new DisruptorEntityHandler());
	}
}
