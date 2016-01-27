package com.lp.techDemo.event.task.worker;

import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskExecutor {

	private final Logger log = LoggerFactory.getLogger(TaskExecutor.class);
	private final int defaultMinSize = 2;
	private final int defaultMaxSize = 2;
	private final long defaultKeepAliveTime = 5L;
	
	private int minSize;
	private int maxSize;
	private long keepAliveTime;
	
	private final String threadCoreSize = "threadCoreSize";
	private final String threadMaxSize = "threadMaxSize";
	private final String threadKeepAliveTime = "threadKeepAliveTime";
	
	private ThreadPoolExecutor executor;
	private AtomicInteger ai = new AtomicInteger(1);
	
	
	public void startExecutor(ExecutorArgument ea) {
		String instanceId = ea.getInstanceId();
		Properties prop = ea.getProp();
		initPoolArgument(prop);
		executor = new ThreadPoolExecutor(minSize, maxSize, keepAliveTime, 
				TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new TaskThreadFactory(instanceId));
		log.info("TaskExecutor for {} is started", instanceId);
	}
	
	private void initPoolArgument(Properties prop) {
		if (prop == null) {
			minSize = defaultMinSize;
			maxSize = defaultMaxSize;
			keepAliveTime = defaultKeepAliveTime;
			return;
		}
		Object value = prop.get(threadCoreSize);
		if (value == null || !NumberUtils.isNumber(value.toString().trim())) {
			minSize = defaultMinSize;
		} else{
			minSize = Integer.valueOf(value.toString().trim());
		}
		
		value = prop.get(threadMaxSize);
		if (value == null || !NumberUtils.isNumber(value.toString().trim())) {
			maxSize = defaultMaxSize;
		} else{
			maxSize = Integer.valueOf(value.toString().trim());
		}
		
		value = prop.get(threadKeepAliveTime);
		if (value == null || !NumberUtils.isNumber(value.toString().trim())) {
			keepAliveTime = defaultKeepAliveTime;
		} else{
			keepAliveTime = Integer.valueOf(value.toString().trim());
		}
		log.info("set thread pool with coreSize:{}, maxSize:{}, keepAliveTime:{}",
				new Object[]{minSize, maxSize, keepAliveTime});
	}
	
	public void shutdownExecutor() {
		if (executor != null) {
			executor.shutdown();
		}
		log.info("TaskExecutor is shutdown");
	}
	//TODO thread pool to run
	public void execute(Runnable r) {
		executor.execute(r);
	}
	
	class TaskThreadFactory implements ThreadFactory{
		private final String prefix = "_Worker_";
		private String threadPrefix;
		
		public TaskThreadFactory(String instanceId) {
			super();
			this.threadPrefix = instanceId + prefix;
		}

		@Override
		public Thread newThread(Runnable r) {
			return new Thread(r, threadPrefix + ai.getAndIncrement());
		}
		
	}
}
