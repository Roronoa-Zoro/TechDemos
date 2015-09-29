package com.lp.techDemo.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lp.techDemo.disruptor.entity.LogEvent;
import com.lp.techDemo.disruptor.util.NumberCalculator;

public class LogHandlerWithMethodName implements EventHandler<LogEvent>{

	private final RingBuffer<LogEvent> ringBuffer;
	
	public LogHandlerWithMethodName(RingBuffer<LogEvent> ringBuffer) {
		super();
		this.ringBuffer = ringBuffer;
	}

	@Override
	public void onEvent(LogEvent event, long sequence, boolean endOfBatch) throws Exception {
		String line = event.getLog();
		String[] detail = line.split(" ");
		String methodName = detail[6];
		NumberCalculator.Instance.increaseMethodName(methodName);
		
		sendDataToNext(detail);
	}
	
	private void sendDataToNext(String[] data){
		long sequence = ringBuffer.next();
		try{
			LogEvent event = ringBuffer.get(sequence);
			event.setSplitedLog(data);
		}finally{
			ringBuffer.publish(sequence);
		}
	}

}
