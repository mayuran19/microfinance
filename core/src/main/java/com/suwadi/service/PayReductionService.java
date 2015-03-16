package com.suwadi.service;

import java.util.List;

import com.suwadi.domain.PayReduction;

public interface PayReductionService extends GenericService<PayReduction> {
	public List<PayReduction> findAllFixedPayReductions();
}
