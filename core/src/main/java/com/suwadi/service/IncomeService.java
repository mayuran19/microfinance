package com.suwadi.service;

import com.suwadi.domain.Income;
import com.suwadi.web.model.accounting.IncomeForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface IncomeService extends GenericService<Income> {
	public Income merge(Income income);

	public PagedResultSet findAllIncomes(Pager pager);

	public IncomeForm getIncomeForm(Long id);

	public IncomeForm updateIncome(IncomeForm incomeForm);

	public IncomeForm addIncome(IncomeForm incomeForm);
}
