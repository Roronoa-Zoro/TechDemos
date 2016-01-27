package com.lp.techDemo.task;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.lp.techDemo.AstractTestCase;
import com.lp.techDemo.event.impl.EmailEvent;
import com.lp.techDemo.event.invoker.EventInvoker;

@ContextConfiguration(locations={"classpath:eventBasedTask.xml"})
public class TaskTest extends AstractTestCase {

	@Autowired
	private EventInvoker ei;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws Exception {
		EmailEvent ee = new EmailEvent();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			ei.fireEvent(ee);
		}
		long end = System.currentTimeMillis();
		
		TimeUnit.MINUTES.sleep(1L);
		System.err.println("send 10000 event used " + (end - start) + " ms");
		
	}

}
