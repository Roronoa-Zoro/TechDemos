package com.lp.techDemo.dubbo.remoteService;

import com.lp.techDemo.dubbo.sharedService.RemoteService;

public class RemoteServiceMock implements RemoteService {

	//when there's rpc exception, this service will be invoked
	@Override
	public void saySomething() {
		System.out.println("in remote service mock===============================");
	}

}
