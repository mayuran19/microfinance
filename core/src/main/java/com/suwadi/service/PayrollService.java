package com.suwadi.service;

import com.suwadi.domain.Payroll;
import com.suwadi.web.model.payroll.PayrollForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface PayrollService extends GenericService<Payroll> {
	public Payroll createPayroll(PayrollForm payrollForm);

	public PagedResultSet paginate(Pager pager, String[] joins);

	public void processPayroll(Long payrollId);
}
