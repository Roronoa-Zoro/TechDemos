package com.lp.techDemo.disruptor.entity;

import com.lmax.disruptor.EventFactory;
import com.lp.techDemo.http.message.MessageEntity;

public class DisruptorEntityFactory implements EventFactory<MessageEntity>{

	@Override
	public MessageEntity newInstance() {
		return new MessageEntity();
	}

}
