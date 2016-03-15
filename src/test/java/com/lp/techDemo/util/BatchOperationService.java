package com.lp.techDemo.util;

import java.util.List;

public interface BatchOperationService<T, R> {

	void batchInsert(List<T> list);
	
	void batchUpdate(List<T> list);
	
	R batchQuery(T object);
}
