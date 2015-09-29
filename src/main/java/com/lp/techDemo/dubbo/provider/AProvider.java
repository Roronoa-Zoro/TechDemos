package com.lp.techDemo.dubbo.provider;

import java.io.IOException;

public class AProvider extends AbstractProvider {

	public AProvider(String config) {
		super(config);
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws IOException {
        new AProvider("dubbo-remote-A.xml").startProvider();
	}

}
