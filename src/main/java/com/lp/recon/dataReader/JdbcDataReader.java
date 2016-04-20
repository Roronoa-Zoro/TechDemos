package com.lp.recon.dataReader;

import java.util.ArrayList;
import java.util.List;

import com.lp.recon.DataReader;
import com.lp.recon.ReconMessage;

public class JdbcDataReader implements DataReader {

	@Override
	public List<ReconMessage> readData() throws Exception {
		// simulate read from db
		List<ReconMessage> data = new ArrayList<>(1000);
		for (int i = 0; i < 1000; i++) {
			ReconMessage rm = new ReconMessage();
			rm.setMessageId(i);
			data.add(rm);
		}
		return data;
	}

}
