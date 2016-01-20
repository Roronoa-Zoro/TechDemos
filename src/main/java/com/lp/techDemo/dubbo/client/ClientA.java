package com.lp.techDemo.dubbo.client;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.alibaba.dubbo.rpc.RpcContext;
import com.lp.techDemo.dubbo.sharedService.AService;

public class ClientA extends AbstractClient {

	public ClientA(String config) {
		super(config);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		new ClientA("dubbo-client-A.xml").doBusiness();
	}
	
	@Override
	protected void doLogic() {
		AService as = (AService) context.getBean("aService");
		String s = as.doInA();
		System.err.println("sssssssssssss"+s);
//		Future<String> f = RpcContext.getContext().getFuture();
//		try {
//			String result = f.get();
//			System.err.println("================get result from a service via async way ->" + result);
//		} catch (InterruptedException | ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
}
