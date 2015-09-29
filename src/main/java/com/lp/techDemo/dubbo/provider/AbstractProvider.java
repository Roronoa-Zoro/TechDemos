package com.lp.techDemo.dubbo.provider;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class AbstractProvider {

	private static final Logger log = LoggerFactory.getLogger(AbstractProvider.class);
	
	private String config;

	public AbstractProvider(String config) {
		super();
		this.config = config;
	}
	
	public void startProvider() throws IOException{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(config);
        context.start();
        log.info("begin to provide service, press any key to exit.");
        System.in.read(); // 按任意键退出
        context.close();
        log.info("remote server exit...");
	}
}
