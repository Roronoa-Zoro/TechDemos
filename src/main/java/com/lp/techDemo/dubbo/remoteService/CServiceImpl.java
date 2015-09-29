package com.lp.techDemo.dubbo.remoteService;

import com.lp.techDemo.dubbo.sharedService.CService;

public class CServiceImpl implements CService {

	@Override
	public String doInC() {
		
		return "CService";
	}

}
