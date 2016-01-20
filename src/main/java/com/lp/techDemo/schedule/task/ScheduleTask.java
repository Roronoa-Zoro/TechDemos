package com.lp.techDemo.schedule.task;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduleTask {

	private static final Logger log = LoggerFactory.getLogger(ScheduleTask.class);
	
	public void dummyTask1() {
		System.err.println(Thread.currentThread().getName() + " ScheduleTask.dummyTask1 is run======================");
		try {
			log.info("simulate logic in dummyTask1");
			TimeUnit.SECONDS.sleep(2L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void dummyTask2() {
		System.err.println(Thread.currentThread().getName() + " ScheduleTask.dummyTask2 is run======================");
		try {
			log.info("simulate logic in dummyTask2");
			TimeUnit.SECONDS.sleep(2L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
