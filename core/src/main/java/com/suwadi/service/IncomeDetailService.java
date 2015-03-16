package com.suwadi.service;

import java.util.List;

import com.suwadi.domain.IncomeDetail;

public interface IncomeDetailService extends GenericService<IncomeDetail> {
	public List<IncomeDetail> findByPropertyWithEagerFetch(String propertyName,
			Object propertyValue, String[] joins);
}
