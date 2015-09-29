package com.lp.techDemo.dubbo.remoteService;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lp.techDemo.dubbo.sharedService.RemoteService;

public class RemoteServiceImpl implements RemoteService {
	
	private static final Logger log = LoggerFactory.getLogger(RemoteServiceImpl.class);
	
	@Override
	public void saySomething() {
		log.info("\n\nserve client invoke, \n\n");
		System.out.println("\nsimulate logic\n");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
