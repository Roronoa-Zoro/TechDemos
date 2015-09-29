package com.lp.techDemo.disruptor.producer;

import java.nio.ByteBuffer;
import java.util.Date;

import com.lmax.disruptor.RingBuffer;
import com.lp.techDemo.disruptor.entity.LongEvent;
public class LongEventProducer {

	private final RingBuffer<LongEvent> ringBuffer;

	public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
		super();
		this.ringBuffer = ringBuffer;
	}
	
	public void onData(ByteBuffer bb, Date date){
		long sequence = ringBuffer.next();
		try{
			LongEvent event = ringBuffer.get(sequence);
			event.setValue(bb.getLong(0));
			event.setDate(date);
		}finally{
			ringBuffer.publish(sequence);
		}
		
	}
}
