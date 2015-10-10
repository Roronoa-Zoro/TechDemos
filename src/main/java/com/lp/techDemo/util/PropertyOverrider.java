package com.lp.techDemo.util;

import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyOverrideConfigurer;

public class PropertyOverrider extends PropertyOverrideConfigurer{

	private static final Logger logger = LoggerFactory.getLogger(PropertyOverrider.class);
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {
		for (@SuppressWarnings("rawtypes")
		Enumeration names = props.propertyNames(); names.hasMoreElements();) {
			String key = (String) names.nextElement();
			String value = props.getProperty(key);
			String decryptedValue = null;
			try {
				decryptedValue = EncryptUtil.decrypt(value);
				props.setProperty(key, decryptedValue);
			} catch (Exception e) {
				logger.error("decrypt fails", e);
				throw new RuntimeException(e);
			}
			
		}
		super.processProperties(beanFactory, props);
	}

	
}
