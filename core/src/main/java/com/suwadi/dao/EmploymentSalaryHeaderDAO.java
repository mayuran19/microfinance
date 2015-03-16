package com.suwadi.dao;

import com.suwadi.domain.EmploymentSalaryHeader;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface EmploymentSalaryHeaderDAO extends
		GenericDAO<EmploymentSalaryHeader> {
	public PagedResultSet findByPayrollId(final Long payrollId,
			final Pager pager);
}
