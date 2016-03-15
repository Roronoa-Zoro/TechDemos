package com.lp.techDemo.util;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBatchOperation<T,R> implements BatchOperationService<T, R> {

	public void batchInsert(List<T> list){
		int bathLimit = getBatchUpdateSize();
		if (list.size()<= bathLimit) {
			doInsert(list);
		}
		
		batchOperate(list, 1);
        
	}
	
	public void batchUpdate(List<T> list) {
		int bathLimit = getBatchUpdateSize();
		if (list.size()<= bathLimit) {
			doUpdate(list);
		}
		
		batchOperate(list, 2);
	}
	
	private void batchOperate(List<T> list, int operateType) {
		int bathLimit = getBatchUpdateSize();
		List<T> tempList = new ArrayList<>();
		for (int i = 0; i < list.size(); i += bathLimit) {
			if (i + bathLimit > list.size()) {
				tempList = list.subList(i, list.size());
			} else {
				tempList = list.subList(i, i + bathLimit);
			}
			if (1 == operateType) {
				doInsert(tempList);
				continue;
			}
			if (2 == operateType) {
				doUpdate(tempList);
				continue;
			}
		}
	}

	protected abstract int getBatchUpdateSize();
	
	protected abstract void doInsert(List<T> list);
	
	protected abstract void doUpdate(List<T> list);
	
	public R batchQuery(T object) {
		//TODO
		return null;
	}
}
