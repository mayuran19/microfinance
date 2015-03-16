package com.suwadi.dao;

import java.util.List;

import com.suwadi.domain.ExpenseDetail;

public interface ExpenseDetailDAO extends GenericDAO<ExpenseDetail> {
	public void save(List<ExpenseDetail> expenseDetails);

	public void merge(List<ExpenseDetail> expenseDetails);

	public void saveOrMerge(List<ExpenseDetail> expenseDetails);

	public void delete(List<ExpenseDetail> expenseDetails);

	public List<ExpenseDetail> findByExpenseId(Long expenseId);
}
