package com.suwadi.savingInterestCalculator;

import org.springframework.context.ApplicationContext;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplicationContextInitializer springContextInitializer = new SpringApplicationContextInitializer();
		ApplicationContext applicationContext = springContextInitializer
				.initializeApplicationContext();
		InterestCalculator interestCalculator = new InterestCalculator();
		interestCalculator.setApplicationContext(applicationContext);
		interestCalculator.calculateInterest();
	}

}
