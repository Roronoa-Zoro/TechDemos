package com.lp.techDemo.dubbo.remoteService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lp.techDemo.dubbo.sharedService.BService;
import com.lp.techDemo.dubbo.sharedService.CService;

public class BServiceImpl implements BService {
	private static final Logger log = LoggerFactory.getLogger(BServiceImpl.class);
	private CService cs;

	public CService getCs() {
		return cs;
	}
	public void setCs(CService cs) {
		this.cs = cs;
	}

	@Override
	public String doInB() {
		log.info("====================================enter doInB");
		return "BService ";
	}

}
