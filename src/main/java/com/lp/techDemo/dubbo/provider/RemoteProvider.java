package com.lp.techDemo.dubbo.provider;

import java.io.IOException;

public class RemoteProvider extends AbstractProvider{

	public RemoteProvider(String config) {
		super(config);
	}

	public static void main(String[] args) throws IOException {
        new RemoteProvider("dubbo-remote.xml").startProvider();
	}

}
