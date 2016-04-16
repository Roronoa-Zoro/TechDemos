package com.lp.recon;

import java.util.List;

public interface DataReader {

	List<ReconMessage> readData() throws Exception;
}
