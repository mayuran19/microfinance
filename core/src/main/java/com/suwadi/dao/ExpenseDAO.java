package com.suwadi.dao;

import java.math.BigDecimal;

import com.suwadi.domain.Expense;
import com.suwadi.web.model.accounting.ExpenseForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface ExpenseDAO extends GenericDAO<Expense> {
	public void updatePayment(BigDecimal add, Long id);

	public PagedResultSet findAllExpenses(Pager pager);

	public ExpenseForm getExpenseForm(Long id);

	public PagedResultSet getExpenseReportDisplayPaginated(Pager pager);
}
