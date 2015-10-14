package com.lp.techDemo.disruptor.demo;

import static org.junit.Assert.*;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.junit.After;
import org.junit.Test;

import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lp.techDemo.disruptor.entity.LogEvent;
import com.lp.techDemo.disruptor.entity.LogEventFactory;
import com.lp.techDemo.disruptor.entity.LongEvent;
import com.lp.techDemo.disruptor.entity.LongEventFactory;
import com.lp.techDemo.disruptor.handler.LogHandlerWithMethodName;
import com.lp.techDemo.disruptor.handler.LongEventConsumer;
import com.lp.techDemo.disruptor.handler.LongEventConsumer2;
import com.lp.techDemo.disruptor.handler.LongEventHandler;
import com.lp.techDemo.disruptor.producer.LongEventProducerWithTranslator;

public class DisruptorDemo {

	Disruptor<LongEvent> disruptor;
	
	@After
	public void closeDisruptor(){
		if (disruptor != null){
			disruptor.shutdown();
		}
	}
	
	private void buildDisruptor(){
		Executor executor = Executors.newFixedThreadPool(5);
		LongEventFactory factory = new LongEventFactory();
		
		//ring buffer size
		int bufferSize = 1024;
		
		//executor will invoke consumers
		disruptor = 
				new Disruptor<>(factory, bufferSize, executor, ProducerType.SINGLE, new SleepingWaitStrategy());
	}
	
	//many consumers, each consumer can get every events
	@Test
	public void testMain() throws InterruptedException {
		buildDisruptor();
		
		disruptor.handleEventsWith(new LongEventHandler(), new LongEventHandler());
		
		sendEvent();
	}
	
	//many consumers， each consumer can only get one event
	@Test
	public void workerPoolTest() throws InterruptedException{
		
		buildDisruptor();

		disruptor.handleEventsWithWorkerPool(new LongEventConsumer(), new LongEventConsumer2());
		
		sendEvent();
	}
	
	private void sendEvent(){
		disruptor.start();
		//general sender
//		LongEventProducer producer = new LongEventProducer(rb);
		
		//send event with translator, this translator is similar to template
		LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(disruptor.getRingBuffer());
		
		ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; l <= 100; l++){
            bb.putLong(0, l);
            producer.onData(bb, new Date());
//            TimeUnit.SECONDS.sleep(1);
        }
	}
	
	@Test
	public void calculateTime(){
		Executor executor = Executors.newFixedThreadPool(2);
		Executor executor2 = Executors.newFixedThreadPool(2);
		LogEventFactory factory = new LogEventFactory();
		
		//ring buffer size
		int bufferSize = 1024;
		
		Disruptor<LogEvent> disruptor1 = 
				new Disruptor<>(factory, bufferSize * 2, executor, ProducerType.SINGLE, new SleepingWaitStrategy());
		
		Disruptor<LogEvent> disruptor2 = 
				new Disruptor<>(factory, bufferSize, executor2, ProducerType.MULTI, new SleepingWaitStrategy());
		
//		disruptor1.handleEventsWith(new LogHandlerWithMethodName(null), new LogHandlerWithMethodName());
		
	}

}
