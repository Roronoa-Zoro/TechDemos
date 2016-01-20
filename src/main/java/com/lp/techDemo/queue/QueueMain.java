package com.lp.techDemo.queue;

import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QueueMain {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("data.xml");
		
		
		TimeUnit.MINUTES.sleep(5);
	}

}
