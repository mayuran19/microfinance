package com.suwadi.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.suwadi.domain.EmploymentPayReduction;
import com.suwadi.domain.SalaryType;

public interface EmploymentPayReductionDAO extends
		GenericDAO<EmploymentPayReduction> {
	public Map<Long, BigDecimal> getFixedPayReductionAmountMapByEmploymentId(
			Long employmentId);

	public List<EmploymentPayReduction> findEmploymentPayReductionsByEmploymentId(
			Long employmentId);

	public Map<Long, SalaryType> getFixedPayReductionSalaryTypeMapByEmploymentId(
			Long employmentId);
}
