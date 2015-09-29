package com.lp.techDemo.dubbo.client;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public abstract class AbstractClient {
	
	private static final Logger log = LoggerFactory.getLogger(AbstractClient.class);
	
	protected ClassPathXmlApplicationContext context;
	private String config;
	
	public AbstractClient(String config) {
		super();
		this.config = config;
	}
	
	private void start(){
		context = new ClassPathXmlApplicationContext(config);
		context.start();
	}
	
	private void closeContext(){
		context.close();
	}
	
	private void waitForUser(){
		try {
			log.info("=====================press any key to exit.");
			System.in.read();// 按任意键退出
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	protected abstract void doLogic();
	
	protected void doBusiness(){
		start();
		
		doLogic();
		
		waitForUser();
		
		closeContext();
	}
}
