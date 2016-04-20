package com.lp.techDemo.schedule.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DemoTask implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.err.println("DemoTask==============================");
	}

}
