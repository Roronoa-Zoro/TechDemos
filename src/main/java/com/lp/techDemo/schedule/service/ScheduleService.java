package com.lp.techDemo.schedule.service;

import java.util.List;

import com.lp.techDemo.schedule.vo.JobVO;
public interface ScheduleService {

	List<JobVO> showAllJobs() throws Exception;
	
	JobVO showSpecificJob(String jobName, String jobGroup) throws Exception;
	
	List<JobVO> getRunningJobs() throws Exception;
	
	JobVO pauseJob(String jobName, String jobGroup) throws Exception;
	
	JobVO resumeJob(String jobName, String jobGroup) throws Exception;
}
