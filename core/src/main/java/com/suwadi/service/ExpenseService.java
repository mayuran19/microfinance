package com.suwadi.service;

import com.suwadi.domain.Expense;
import com.suwadi.web.model.accounting.ExpenseForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface ExpenseService extends GenericService<Expense> {
	public Expense merge(Expense expense);

	public PagedResultSet findAllExpenses(Pager pager);

	public ExpenseForm getExpenseForm(Long id);

	public ExpenseForm updateExpense(ExpenseForm expenseForm);

	public ExpenseForm addExpense(ExpenseForm expenseForm);

	public PagedResultSet getExpenseReportDisplayPaginated(Pager pager);
}
