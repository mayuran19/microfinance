package com.suwadi.dao;

import java.util.List;

import com.suwadi.domain.Employment;
import com.suwadi.domain.EmploymentPayReduction;
import com.suwadi.domain.EmploymentPayType;

public interface EmploymentDAO extends GenericDAO<Employment> {
	public List<Employment> findByEmployeeId(Long employeeId);

	public Employment findActiveEmploymentByEmployeeId(Long employeeId);

	public List<Employment> findAllByPayScheduleId(Long payScheduleId);

	public List<EmploymentPayType> findEmploymentPayTypesByEmploymentId(
			Long employmentId);

	public List<EmploymentPayReduction> findEmploymentPayReductionsByEmploymentId(
			Long employmentId);

	public List<EmploymentPayReduction> findEmploymentFixedPayReductionsByEmploymentId(
			Long employmentId);

	public List<EmploymentPayReduction> findEmploymentPercentagePayReductionsByEmploymentId(
			Long employmentId);

	public List<EmploymentPayType> findEmploymentFixedPayTypesByEmploymentId(
			Long employmentId);

	public List<EmploymentPayType> findEmploymentPercentagePayTypesByEmploymentId(
			Long employmentId);
}
