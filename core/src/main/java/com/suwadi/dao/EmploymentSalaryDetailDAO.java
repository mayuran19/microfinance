package com.suwadi.dao;

import java.util.List;

import com.suwadi.domain.EmploymentSalaryDetail;

public interface EmploymentSalaryDetailDAO extends
		GenericDAO<EmploymentSalaryDetail> {
	List<EmploymentSalaryDetail> findByEmploymentSalaryHeaderId(
			Long employmentSalaryHeaderId);

	public List<EmploymentSalaryDetail> findAllEarningsByEmploymentSalaryHeaderId(
			Long employmentSalaryHeaderId);

	public List<EmploymentSalaryDetail> findAllReductionsByEmploymentSalaryHeaderId(
			Long employmentSalaryHeaderId);
}
