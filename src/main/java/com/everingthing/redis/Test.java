package com.everingthing.redis;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Arrays;

/**
 * 测试bean加载情况
 */
public class Test implements ApplicationContextAware{

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println(applicationContext == null);
		System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
	}

}
