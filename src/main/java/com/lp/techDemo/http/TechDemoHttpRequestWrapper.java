package com.lp.techDemo.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

public class TechDemoHttpRequestWrapper extends HttpServletRequestWrapper {

	public TechDemoHttpRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public HttpSession getSession() {
		return null;
	}

	@Override
	public HttpSession getSession(boolean create) {
		return null;
	}

}
