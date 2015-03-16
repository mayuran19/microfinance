package com.suwadi.savingInterestCalculator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringApplicationContextInitializer {
	private ApplicationContext applicationContext;

	public ApplicationContext initializeApplicationContext() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		return applicationContext;
	}
}
