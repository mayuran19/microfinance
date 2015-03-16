package com.mayuran19.migrator.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.suwadi.dao.IncomeDAO;
import com.suwadi.domain.Income;
import com.suwadi.domain.IncomeDetail;

public class FixIncomeAccountTotalService implements ApplicationContextAware {
	private static final Logger LOGGER = Logger.getLogger("migrator");
	private ApplicationContext applicationContext;
	private IncomeDAO incomeDAO;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		LOGGER.info("Initializing the spring context");
		this.applicationContext = applicationContext;
		this.incomeDAO = (IncomeDAO) applicationContext.getBean("IncomeDAO");
	}

	public void fixIncomeTotal() {
		List<Income> incomes = this.incomeDAO
				.findAllWithEagerFetch(new String[] { "incomeDetails" });
		LOGGER.info("Getting all incomes");
		for (Income income : incomes) {
			if (income.getTotal() == null) {
				LOGGER.info("updating total");
				LOGGER.info("ID:" + income.getId() + ", total is null");
				List<IncomeDetail> incomeDetails = income.getIncomeDetails();
				BigDecimal incometotal = new BigDecimal(0);
				for (IncomeDetail incomeDetail : incomeDetails) {
					LOGGER.info("IncomeId:" + income.getId()
							+ ", IncomeDetailID:" + incomeDetail.getId()
							+ ",Line total:" + incomeDetail.getLineTotal());
					incometotal = incometotal.add(incomeDetail.getLineTotal());
				}
				income.setTotal(incometotal);
				this.incomeDAO.update(income);
				LOGGER.info("total updated successfully");
				LOGGER.info("***************************");
			}
		}
	}
}
