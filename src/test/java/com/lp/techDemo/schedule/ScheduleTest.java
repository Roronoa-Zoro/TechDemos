package com.lp.techDemo.schedule;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.lp.techDemo.AstractTestCase;
import com.lp.techDemo.schedule.service.ScheduleService;
import com.lp.techDemo.schedule.vo.JobVO;

@ContextConfiguration(locations={"classpath:schedule.xml"})
public class ScheduleTest extends AstractTestCase{

	@Autowired
	private ScheduleService ss;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws Exception {
		JobVO task2 = ss.showSpecificJob("task2", "DEFAULT");
		assertEquals("NORMAL", task2.getJobStatus());
		
		task2 = ss.pauseJob("task2", "DEFAULT");
		assertEquals("PAUSED", task2.getJobStatus());
		
		task2 = ss.resumeJob("task2", "DEFAULT");
		assertEquals("NORMAL", task2.getJobStatus());
	}
	
}
