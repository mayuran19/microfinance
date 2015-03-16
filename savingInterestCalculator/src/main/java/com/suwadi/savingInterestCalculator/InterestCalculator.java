package com.suwadi.savingInterestCalculator;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.suwadi.domain.SavingAccount;
import com.suwadi.service.SavingAccountService;

public class InterestCalculator implements ApplicationContextAware {
	private ApplicationContext applicationContext;
	private SavingAccountService savingAccountService;

	public void calculateInterest() {
		List<SavingAccount> savingAccounts = this.savingAccountService
				.findAll();
		for (SavingAccount savingAccount : savingAccounts) {
			System.out.println(savingAccount.getAccountNumber());
		}
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
		this.savingAccountService = (SavingAccountService) applicationContext
				.getBean("savingAccountService");
	}
}
