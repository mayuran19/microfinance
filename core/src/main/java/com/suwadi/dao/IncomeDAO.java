package com.suwadi.dao;

import java.util.List;

import com.suwadi.domain.Income;
import com.suwadi.web.model.accounting.IncomeDetailForm;
import com.suwadi.web.model.accounting.IncomeForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface IncomeDAO extends GenericDAO<Income> {
	public PagedResultSet findAllIncomes(Pager pager);

	public IncomeForm getIncomeForm(Long id);

	public List<IncomeDetailForm> getIncomeDetailFormByExpenseId(Long incomeId);
}
