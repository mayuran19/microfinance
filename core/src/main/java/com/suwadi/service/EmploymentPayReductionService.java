package com.suwadi.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.suwadi.domain.EmploymentPayReduction;
import com.suwadi.domain.SalaryType;
import com.suwadi.web.model.payroll.EmploymentPayReductionHeaderForm;

public interface EmploymentPayReductionService extends
		GenericService<EmploymentPayReduction> {
	public Map<Long, BigDecimal> getFixedPayReductionAmountMapByEmploymentId(
			Long employmentId);

	public List<EmploymentPayReduction> findEmploymentPayReductionsByEmploymentId(
			Long employmentId);

	public void addPayReductionsToEmployment(
			EmploymentPayReductionHeaderForm employmentPayReductionHeaderForm);

	public Map<Long, SalaryType> getFixedPayReductionSalaryTypeMapByEmploymentId(
			Long employmentId);
}
