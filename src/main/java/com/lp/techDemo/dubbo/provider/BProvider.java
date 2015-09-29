package com.lp.techDemo.dubbo.provider;

import java.io.IOException;

public class BProvider extends AbstractProvider {

	public BProvider(String config) {
		super(config);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		new AProvider("dubbo-remote-B.xml").startProvider();
	}

}
