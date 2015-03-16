package com.suwadi.service;

import java.util.List;

import com.suwadi.domain.ExpenseDetail;

public interface ExpenseDetailService extends GenericService<ExpenseDetail> {
	public void save(List<ExpenseDetail> expenseDetails);

	public void delete(List<ExpenseDetail> expenseDetails);

	public List<ExpenseDetail> findByExpenseId(Long expenseId);

	public List<ExpenseDetail> findByPropertyWithEagerFetch(
			String propertyName, Object propertyValue, String[] joins);
}
