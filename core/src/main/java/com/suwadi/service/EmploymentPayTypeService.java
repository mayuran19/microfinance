package com.suwadi.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.suwadi.domain.EmploymentPayType;
import com.suwadi.domain.PayType;
import com.suwadi.domain.SalaryType;
import com.suwadi.web.model.payroll.EmploymentPayTypesForm;

public interface EmploymentPayTypeService extends
		GenericService<EmploymentPayType> {
	public List<PayType> findPayTypeByEmploymentId(Long employmentId);

	public List<EmploymentPayType> findEmploymentPayTypeByEmploymentId(
			Long employmentId);

	public void addPayTypesToEmployment(
			EmploymentPayTypesForm employmentPayTypesForm);

	public Map<Long, BigDecimal> getFixedPayTypeAmountMapByEmploymentId(
			Long employmentId);

	public Map<Long, SalaryType> getFixedPayTypeSalaryTypeMapByEmploymentId(
			Long employmentId);
}
