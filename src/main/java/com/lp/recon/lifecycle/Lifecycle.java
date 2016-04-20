package com.lp.recon.lifecycle;

public interface Lifecycle {

	void init() throws Exception;
	
	void start() throws Exception;
	
	void stop() throws Exception;
	
}
