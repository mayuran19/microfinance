package com.suwadi.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.suwadi.domain.EmploymentPayTax;
import com.suwadi.web.model.payroll.EmploymentPayTaxHeader;

public interface EmploymentPayTaxService extends
		GenericService<EmploymentPayTax> {
	public Map<Long, BigDecimal> getFixedPayTaxAmountMapByEmploymentId(
			Long employmentId);

	public List<EmploymentPayTax> findEmploymentPayTaxesByEmploymentId(
			Long employmentId);

	public void addPayTaxesToEmployment(
			EmploymentPayTaxHeader employmentPayTaxHeader);
}
