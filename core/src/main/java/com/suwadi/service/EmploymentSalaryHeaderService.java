package com.suwadi.service;

import com.suwadi.domain.EmploymentSalaryHeader;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface EmploymentSalaryHeaderService extends
		GenericService<EmploymentSalaryHeader> {
	public PagedResultSet findByPayrollId(final Long payrollId,
			final Pager pager);
}
