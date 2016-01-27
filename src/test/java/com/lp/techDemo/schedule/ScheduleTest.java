package com.lp.techDemo.schedule;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
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
//		JobVO task2 = ss.showSpecificJob("task2", "DEFAULT");
//		assertEquals("NORMAL", task2.getJobStatus());
//		
//		task2 = ss.pauseJob("task2", "DEFAULT");
//		assertEquals("PAUSED", task2.getJobStatus());
//		
//		task2 = ss.resumeJob("task2", "DEFAULT");
//		assertEquals("NORMAL", task2.getJobStatus());
	}
	@Test
	public void testPureQuartz() throws Exception {
//		JobDetail jd = JobBuilder.newJob(DemoTasks.class).withIdentity("demoTask","demoGroup").build();
//		SimpleTrigger st = TriggerBuilder.newTrigger()
//							  .withIdentity("trigger", "demoGroup")
//							  .startNow()
//		                      .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//				 												 .withIntervalInMinutes(1)
//				 												 .withRepeatCount(0))
//		                      .forJob(jd)
//		                      .build();
//		SchedulerFactory sf = new StdSchedulerFactory();
//		Scheduler s = sf.getScheduler();
//		s.scheduleJob(jd, st);
//		s.start();
		TimeUnit.MINUTES.sleep(10);
//		s.shutdown();
	}
	class Tasks implements Job {

		@Override
		public void execute(JobExecutionContext context) throws JobExecutionException {
			System.err.println(111111);
		}
		
	}
	public class DemoTasks implements Job {

		public DemoTasks() {
			super();
		}

		@Override
		public void execute(JobExecutionContext context) throws JobExecutionException {
			System.err.println("DemoTask==============================");
		}

	}
	
}
