package com.lp.techDemo.schedule.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.quartz.JobKey;
import com.lp.techDemo.schedule.vo.JobVO;

public class JobContainer {

	private static final ConcurrentHashMap<JobVO, JobKey> jobContainer = new ConcurrentHashMap<>();
	private static final String DEFAULT_GROUP = "DEFAULT";
	
	public static void putJob(JobVO jobVO, JobKey jobKey) {
		jobContainer.put(jobVO, jobKey);
	}
	
	public static JobVO getJobVO(String jobName) {
		return getJobVO(jobName, DEFAULT_GROUP);
	}
	
	public static JobVO getJobVO(String jobName, String jobGroup) {
		boolean jobExists = isJobExist(jobName, jobGroup);
		if (jobExists) {
			return getSpecificJobVO(jobName, jobGroup);
		}
		return null;
	}
	
	private static JobVO getSpecificJobVO(String jobName, String jobGroup) {
		JobVO jv = new JobVO();
		jv.setJobName(jobName);
		jv.setJobGroup(jobGroup);
		for (JobVO jvs : jobContainer.keySet()) {
			if (jvs.equals(jv)) {
				return jvs;
			}
		}
		return null;
	}
	
	public static JobKey getJobKey(String jobName) {
		return getJobKey(jobName, DEFAULT_GROUP);
	}
	
	public static JobKey getJobKey(String jobName, String jobGroup) {
		JobVO job = new JobVO();
		job.setJobName(jobName);
		job.setJobGroup(jobGroup);
		return jobContainer.get(job);
	}
	
	public static boolean isJobExist(String jobName) {
		return isJobExist(jobName, DEFAULT_GROUP);
	}
	
	public static boolean isJobExist(String jobName, String jobGroup) {
		JobVO job = new JobVO();
		job.setJobName(jobName);
		job.setJobGroup(jobGroup);
		return jobContainer.containsKey(job);
	}
	
	public static void showJobs() {
		for (Map.Entry<JobVO, JobKey> entry : jobContainer.entrySet()) {
			System.err.println(entry.getKey());
		}
	}
}
