package com.lp.techDemo;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lp.recon.Bootstrap;

public class LifecycleTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void lifecycleTest() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("lifecycle.xml");
//		TimeUnit.SECONDS.sleep(20L);
//		TimeUnit.MINUTES.sleep(3);
		Bootstrap b = (Bootstrap) ac.getBean("bootstrap");
		b.startProcessor();
		TimeUnit.MINUTES.sleep(3);
	}

}
