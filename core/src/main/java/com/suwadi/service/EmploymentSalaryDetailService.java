package com.suwadi.service;

import java.util.List;

import com.suwadi.domain.EmploymentSalaryDetail;

public interface EmploymentSalaryDetailService extends
		GenericService<EmploymentSalaryDetail> {
	List<EmploymentSalaryDetail> findByEmploymentSalaryHeaderId(
			Long employmentSalaryHeaderId);

	public List<EmploymentSalaryDetail> findAllEarningsByEmploymentSalaryHeaderId(
			Long employmentSalaryHeaderId);

	public List<EmploymentSalaryDetail> findAllReductionsByEmploymentSalaryHeaderId(
			Long employmentSalaryHeaderId);
}
