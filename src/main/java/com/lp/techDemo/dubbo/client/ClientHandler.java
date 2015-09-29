package com.lp.techDemo.dubbo.client;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.lp.techDemo.dubbo.sharedService.RemoteService;

public class ClientHandler extends AbstractClient{

	public ClientHandler(String config) {
		super(config);
	}

	public static void main(String[] args) throws IOException {
		new ClientHandler("dubbo-client.xml").doBusiness();
	}

	@Override
	protected void doLogic() {
		RemoteService rs = (RemoteService) context.getBean("demoService");
		while (true){
			rs.saySomething();
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
