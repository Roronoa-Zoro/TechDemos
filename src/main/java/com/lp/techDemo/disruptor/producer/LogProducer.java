package com.lp.techDemo.disruptor.producer;

import java.io.BufferedReader;
import java.io.FileReader;

import com.lmax.disruptor.RingBuffer;
import com.lp.techDemo.disruptor.entity.LogEvent;

public class LogProducer {

	private final RingBuffer<LogEvent> ringBuffer;

	public LogProducer(RingBuffer<LogEvent> ringBuffer) {
		super();
		this.ringBuffer = ringBuffer;
	}
	
	public void onData(String logPath) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(logPath));
		String line = br.readLine();
		while (line != null){
			long sequence = ringBuffer.next();
			try{
				LogEvent event = ringBuffer.get(sequence);
				event.setLog(line);
			}finally{
				ringBuffer.publish(sequence);
			}
			line = br.readLine();
		}
		br.close();
	}
}
