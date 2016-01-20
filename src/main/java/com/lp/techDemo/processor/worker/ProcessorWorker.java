package com.lp.techDemo.processor.worker;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lp.techDemo.processor.AbstractProcessor;
import com.lp.techDemo.processor.message.ProcessorMessage;
import com.lp.techDemo.processor.reader.ProcessorMessageReader;

public class ProcessorWorker {

	private static final Logger log = LoggerFactory.getLogger(ProcessorWorker.class);
	
	private List<AbstractProcessor> processors;
	private AbstractProcessor startProcessor;
	private ProcessorMessageReader reader;
	
	ScheduledExecutorService executor;
	
	private AtomicInteger ai = new AtomicInteger(0);
	
	public ProcessorWorker(int threadSize) {
		executor = Executors.newScheduledThreadPool(threadSize, new WorkerFactory());
	}
	
	public List<AbstractProcessor> getProcessors() {
		return processors;
	}
	public void setProcessors(List<AbstractProcessor> processors) {
		this.processors = processors;
	}
	
	public void setReader(ProcessorMessageReader reader) {
		this.reader = reader;
	}
	
	private void constructProcessorChain() {
		if (processors == null || processors.isEmpty()) {
			throw new IllegalArgumentException("must setup processors at first");
		}
		
		startProcessor = processors.get(0);
		AbstractProcessor last = startProcessor;
		if (processors.size() > 1) {
			for (int i = 1; i < processors.size(); i++) {
				AbstractProcessor current = processors.get(i);
				last.setNext(current);
				last = current;
			}
		}
	}
	
	public ProcessorMessage getMessage() {
		//simulate logic, get message somewhere
		ProcessorMessage obj = reader.getMessage();
		return obj;
	}
	
	public void servicing() throws Throwable {
		ProcessorMessage obj = getMessage();
		startProcessor.processing(obj);
	}
	
	public void startWorker() {
		constructProcessorChain();
		startProcessor.startProcessor();
		log.info("processor started...");
		executor.scheduleAtFixedRate(new Worker(), 1000, 1000, TimeUnit.MILLISECONDS);
	}
	
	public void destroy() {
		executor.shutdown();
		startProcessor.stopProcessor();
	}
	
	class Worker implements Runnable{

		@Override
		public void run() {
			try {
				servicing();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
	
	class WorkerFactory implements ThreadFactory{

		@Override
		public Thread newThread(Runnable r) {
			return new Thread(r, "WorkerFactory-"+ai.getAndIncrement());
		}
		
	}
}
