package com.lp.techDemo.disruptor.producer;

import com.lmax.disruptor.RingBuffer;
import com.lp.techDemo.http.message.MessageEntity;
import com.lp.techDemo.message.provider.MessagePublisher;

public class DisruptorEntityProducer implements MessagePublisher{

	private final RingBuffer<MessageEntity> ringBuffer;

	public DisruptorEntityProducer(RingBuffer<MessageEntity> ringBuffer) {
		super();
		this.ringBuffer = ringBuffer;
	}
	

	@Override
	public boolean publishMessage(Object payload) {
		long sequence = ringBuffer.next();
		try{
			MessageEntity event = ringBuffer.get(sequence);
			event.setEntity(payload);
		}finally{
			ringBuffer.publish(sequence);
		}
		return true;
	}
}
