package com.suwadi.dao;

import java.util.List;

import com.suwadi.domain.IncomeDetail;

public interface IncomeDetailDAO extends GenericDAO<IncomeDetail> {
	public void save(List<IncomeDetail> incomeDetails);

	public void merge(List<IncomeDetail> incomeDetails);

	public void saveOrMerge(List<IncomeDetail> incomeDetails);

	public void delete(List<IncomeDetail> incomeDetails);

	public List<IncomeDetail> findByIncomeId(Long incomeId);
}
