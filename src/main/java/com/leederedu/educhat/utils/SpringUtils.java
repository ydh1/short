package com.leederedu.educhat.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtils implements ApplicationContextAware {
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext contex)
			throws BeansException {
		SpringUtils.context = contex;
	}

	public static ApplicationContext getContext() {
		if(context == null){
//			try {
//				context = new FileSystemXmlApplicationContext("WebRoot/WEB-INF/config/applicationContext.xml");
//			} catch (Exception e) {
//				MyLog.error(e);
//			}
		}
		return context;
	}
}