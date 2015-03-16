package com.mayuran19.migrator;

import org.springframework.context.ApplicationContext;

import com.mayuran19.application.SpringApplicationContextInitializer;
import com.mayuran19.migrator.service.FixIncomeAccountTotalService;

public class FixIncomeAccountTotal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplicationContextInitializer springContextInitializer = new SpringApplicationContextInitializer();
		ApplicationContext applicationContext = springContextInitializer
				.initializeApplicationContext();
		FixIncomeAccountTotalService fixIncomeAccountTotalService = new FixIncomeAccountTotalService();
		fixIncomeAccountTotalService.setApplicationContext(applicationContext);
		fixIncomeAccountTotalService.fixIncomeTotal();
	}

}
