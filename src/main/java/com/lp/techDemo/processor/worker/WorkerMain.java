package com.lp.techDemo.processor.worker;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WorkerMain {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-processor.xml");
		ProcessorWorker pw = (ProcessorWorker) ac.getBean("worker");
		pw.startWorker();
	}

}
