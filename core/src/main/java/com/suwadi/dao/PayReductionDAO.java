package com.suwadi.dao;

import java.util.List;

import com.suwadi.domain.PayReduction;

public interface PayReductionDAO extends GenericDAO<PayReduction> {
	public List<PayReduction> findAllFixedPayReductions();
}
