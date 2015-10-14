package com.lp.techDemo.dubbo.remoteService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.rpc.RpcContext;
import com.lp.techDemo.dubbo.sharedService.AService;
import com.lp.techDemo.dubbo.sharedService.BService;
import com.lp.techDemo.dubbo.sharedService.CService;

public class AServiceImpl implements AService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AServiceImpl.class);
	private BService bs;
	private CService cs;
	
	public BService getBs() {
		return bs;
	}

	public void setBs(BService bs) {
		this.bs = bs;
	}

	public CService getCs() {
		return cs;
	}

	public void setCs(CService cs) {
		this.cs = cs;
	}

	@Override
	public String doInA() {
		LOGGER.info("=============================enter doInA");
//		EchoService es = (EchoService) bs;
//		es.$echo("OK");
		String b = bs.doInB();
		LOGGER.info("=============================get result from b service [{}]", b);
		String c = cs.doInC();
		LOGGER.info("=============================get result from c service [{}]", c);
//		String bb = bs.doInB();
//		log.info("=============================get result from b service [{}]", bb);
		return "AService+" +  "+" + c;
	}
	
	private String doInAWithAsync(){
		LOGGER.info("=============================enter doInA");
		bs.doInB();
		Future<String> f = RpcContext.getContext().getFuture();
		try {
			String result = f.get();
			LOGGER.info("=============================result from b service, [{}]", result);
			return "AService +" + result;
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "AService with exception";
	}

}
