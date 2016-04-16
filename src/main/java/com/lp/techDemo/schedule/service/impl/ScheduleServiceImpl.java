package com.lp.techDemo.schedule.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lp.techDemo.schedule.service.ScheduleService;
import com.lp.techDemo.schedule.vo.JobVO;
import com.lp.techDemo.schedule.vo.ResponseEnum;
import com.lp.techDemo.schedule.vo.ResponseVO;

public class ScheduleServiceImpl implements ScheduleService {

	private static final Logger log = LoggerFactory.getLogger(ScheduleServiceImpl.class);
	private Scheduler scheduler;
	
	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	@Override
	public List<JobVO> showAllJobs() throws Exception {
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<JobVO> jobList = new ArrayList<>();
		for (JobKey jobKey : jobKeys) {
	    	JobVO job = showSpecificJob(jobKey.getName(), jobKey.getGroup(), jobKey);
		    jobList.add(job);
		}
		log.info("get all jobs done");
		return jobList;
	}
	
	
	public ResponseVO<List<JobVO>> showAllScheduleJobs() throws Exception {
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<JobVO> jobList = new ArrayList<>();
		for (JobKey jobKey : jobKeys) {
	    	JobVO job = showSpecificJob(jobKey.getName(), jobKey.getGroup(), jobKey);
		    jobList.add(job);
		}
		log.info("get all jobs done");
		ResponseVO<List<JobVO>> res = new ResponseVO<>();
		res.setResponseObject(jobList);
		res.setResponseState(ResponseEnum.Success);
		return res;
	}

	@Override
	public JobVO pauseJob(String jobName, String jobGroup) throws Exception {
		JobKey jk = JobKey.jobKey(jobName, jobGroup);
		scheduler.pauseJob(jk);
		JobVO job = showSpecificJob(jobName, jobGroup, jk);
		log.info("paused job:{}", job);
		return job;
	}

	@Override
	public JobVO resumeJob(String jobName, String jobGroup) throws Exception {
		JobKey jk = JobKey.jobKey(jobName, jobGroup);
		scheduler.resumeJob(jk);
		JobVO job = showSpecificJob(jobName, jobGroup, jk);
		log.info("resumed job:{}", job);
		return job;
	}
	
	@Override
	public List<JobVO> getRunningJobs() throws Exception {
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		List<JobVO> jobList = new ArrayList<>(executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			JobVO job = new JobVO();
		    JobDetail jobDetail = executingJob.getJobDetail();
		    JobKey jobKey = jobDetail.getKey();
		    Trigger trigger = executingJob.getTrigger();
		    job.setJobName(jobKey.getName());
		    job.setJobGroup(jobKey.getGroup());
		    job.setJobDesc("触发器:" + trigger.getKey());
		    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
		    job.setJobStatus(triggerState.name());
		    if (trigger instanceof CronTrigger) {
		        CronTrigger cronTrigger = (CronTrigger) trigger;
		        String cronExpression = cronTrigger.getCronExpression();
		        job.setCronExpression(cronExpression);
		    }
		    jobList.add(job);
		}
		log.info("get running jobs done");
		return jobList;
	}

	@Override
	public JobVO showSpecificJob(String jobName, String jobGroup) throws Exception {
		return showSpecificJob(jobName, jobGroup, null);
	}
	
	//by default, one job include one trigger
	private JobVO showSpecificJob(String jobName, String jobGroup, JobKey jobKey) throws Exception {
		log.info("get job info for job:{}, group:{}", jobName, jobGroup);
		JobVO job = new JobVO();
		job.setJobName(jobName);
        job.setJobGroup(jobGroup);
        if (jobKey == null) {
        	jobKey = JobKey.jobKey(jobName, jobGroup);
        }
        List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
		for (Trigger trigger : triggers) {
	        job.setJobDesc("触发器:" + trigger.getKey());
	        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
	        job.setJobStatus(triggerState.name());
	        if (trigger instanceof CronTrigger) {
	            CronTrigger cronTrigger = (CronTrigger) trigger;
	            String cronExpression = cronTrigger.getCronExpression();
	            job.setCronExpression(cronExpression);
	        }
	    }
		
		return job;
	}

	@Override
	public JobVO rescheduleJob(String jobName, String jobGroup) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
