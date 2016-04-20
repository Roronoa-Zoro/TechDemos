package com.lp.recon.dataTask;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.lp.recon.DataReader;
import com.lp.recon.DataTask;
import com.lp.recon.ReconMessage;

public class QueueDataTask extends DataTask {

	private DataReader dataReader;
	private int maxNotFetchTimes = 5;
	
	public DataReader getDataReader() {
		return dataReader;
	}

	public void setDataReader(DataReader dataReader) {
		this.dataReader = dataReader;
	}
	
	@Override
	public void doTask() throws Exception {
		int notFetch = 0;
		while (notFetch < maxNotFetchTimes) {
			List<ReconMessage> data = dataReader.readData();
			if (data == null || data.isEmpty()) {
				TimeUnit.SECONDS.sleep(1L);
				notFetch++;
			}
			for (ReconMessage rm : data) {
				System.err.println(Thread.currentThread().getName() + " processing msg " + rm);
			}
		}
		super.getDone().countDown();
	}

}
