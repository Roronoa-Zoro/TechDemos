package com.lp.techDemo.metaq.messageConsumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MetaqDemoConsumer3 {

	public static void main(String[] args) {
		start();
	}

	static void start(){
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("metaq-consumer3.xml");
		c.start();
		System.err.println("start3 is ready..................................................");
	}
}
