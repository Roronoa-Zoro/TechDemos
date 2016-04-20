package com.lp.recon.dataTask;

import java.util.List;
import com.lp.recon.DataReader;
import com.lp.recon.DataRouter;
import com.lp.recon.DataTask;
import com.lp.recon.ReconMessage;

public class JdbcDataTask extends DataTask {

	private DataReader dataReader;
	private DataRouter dataRouter;
	
	public DataReader getDataReader() {
		return dataReader;
	}

	public void setDataReader(DataReader dataReader) {
		this.dataReader = dataReader;
	}

	public DataRouter getDataRouter() {
		return dataRouter;
	}

	public void setDataRouter(DataRouter dataRouter) {
		this.dataRouter = dataRouter;
	}

	@Override
	public void doTask() throws Exception {
		List<ReconMessage> data = dataReader.readData();
		System.err.println("read db data done.....................................");
		for (ReconMessage rm : data) {
			dataRouter.routeMessage(rm);
		}
		System.err.println("route db data done.....................................");
		super.getDone().countDown();
	}

}
