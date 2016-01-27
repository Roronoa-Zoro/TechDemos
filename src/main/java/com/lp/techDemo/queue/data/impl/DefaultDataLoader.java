package com.lp.techDemo.queue.data.impl;

import java.util.ArrayList;
import java.util.List;

import com.lp.techDemo.queue.data.DataLoader;
import com.lp.techDemo.queue.data.DataRouter;

public class DefaultDataLoader implements DataLoader {

	private DataRouter dr;
	
	public DataRouter getDr() {
		return dr;
	}

	public void setDr(DataRouter dr) {
		this.dr = dr;
	}

	@Override
	public void loadData() {
		List<String> list = getDummyData();
		for (String str : list) {
			dr.routeData(str);
		}
		System.err.println("==========================loaded");
	}
	
	private List<String> getDummyData() {
		List<String> dummy = new ArrayList<>();
		
		dummy.add("jimmy");
		dummy.add("zoro");
		dummy.add("luffy");
		dummy.add("nami");
		dummy.add("sanji");
		return dummy;
	}

}
