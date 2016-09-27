package com.lp.techDemo.mock;

import org.springframework.test.util.ReflectionTestUtils;


public class MockUtils {
	 
	//target is a bean managed by spring, actually it's a proxy
	public static Object getOriObject(Object target) throws Exception {
		Object oriTarget = AopTargetUtils.getTarget(target);
		return oriTarget;
	}
	
	public static Object getOriField(Object realTarget, String fieldName) throws Exception {
		Object oriTarget = ReflectionTestUtils.getField(realTarget, fieldName);
		return oriTarget;
	}
	
	public static void mockInnerField(Object realTarget, String fieldName, Object mockField) throws Exception {
		ReflectionTestUtils.setField(realTarget, fieldName, mockField);
	}
	
	//realTarget 
	public static void revertMockField(Object realTarget, String fieldName, Object oriField) {
		ReflectionTestUtils.setField(realTarget, fieldName, oriField);
	}
}
