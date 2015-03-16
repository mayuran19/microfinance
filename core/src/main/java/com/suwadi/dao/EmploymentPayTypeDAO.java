package com.suwadi.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.suwadi.domain.EmploymentPayType;
import com.suwadi.domain.PayType;
import com.suwadi.domain.SalaryType;

public interface EmploymentPayTypeDAO extends GenericDAO<EmploymentPayType> {
	public List<PayType> findPayTypeByEmploymentId(Long employmentId);

	public List<EmploymentPayType> findEmploymentPayTypeByEmploymentId(
			Long employmentId);

	public Map<Long, BigDecimal> getFixedPayTypeAmountMapByEmploymentId(
			Long employmentId);

	public Map<Long, SalaryType> getFixedPayTypeSalaryTypeMapByEmploymentId(
			Long employmentId);
}
