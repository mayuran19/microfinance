package com.suwadi.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.suwadi.domain.EmploymentPayTax;

public interface EmploymentPayTaxDAO extends GenericDAO<EmploymentPayTax> {
	public Map<Long, BigDecimal> getFixedPayTaxAmountMapByEmploymentId(
			Long employmentId);

	public List<EmploymentPayTax> findEmploymentPayTaxesByEmploymentId(
			Long employmentId);
}
