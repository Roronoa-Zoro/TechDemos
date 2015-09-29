package com.lp.techDemo.util;

import java.beans.PropertyDescriptor;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.helpers.MessageFormatter;

public class ToStringUtil {

	private static String header = "StringBuilder sb = new StringBuilder();\n";
	private static String className = "sb.append(\"{} [\")\n"; 
	private static String propertyString = ".append(\"{}=\").append({})\n"; 
	private static String end = ".append(\"]\");\n";
	private static String returnString = "return sb.toString();";
	
	public static String generateToString(Class<?> clz){
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(clz);
		
		StringBuilder sb = new StringBuilder();
		sb.append(header);
		
		sb.append(MessageFormatter.format(className, clz.getSimpleName()).getMessage());
		for (PropertyDescriptor pd : pds) {
			
			if (!pd.getName().equals("class")) {
				sb.append(MessageFormatter.format(propertyString, pd.getName(), pd.getName()).getMessage());
			}
		}
		sb.append(end);
		sb.append(returnString);
		return sb.toString();
	}
}
