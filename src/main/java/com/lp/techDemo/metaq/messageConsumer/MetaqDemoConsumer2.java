package com.lp.techDemo.metaq.messageConsumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MetaqDemoConsumer2 {

	public static void main(String[] args) {
		start();
	}

	static void start(){
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("metaq-consumer2.xml");
		c.start();
		System.err.println("start2 is ready..................................................");
	}
}
