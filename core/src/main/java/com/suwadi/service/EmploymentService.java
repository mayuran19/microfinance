package com.suwadi.service;

import java.util.Date;
import java.util.List;

import com.suwadi.domain.Employment;
import com.suwadi.domain.EmploymentSalaryHeader;
import com.suwadi.web.model.payroll.EmploymentForm;
import com.suwadi.web.model.payroll.PayrollSelectEmployeesForm;

public interface EmploymentService extends GenericService<Employment> {
	public List<Employment> findByEmployeeId(Long employeeId);

	public Employment save(EmploymentForm employmentForm);

	public Employment findActiveEmploymentByEmployeeId(Long employeeId);

	public List<Employment> findAllByPayScheduleId(Long payScheduleId);

	public List<EmploymentSalaryHeader> createEmploymentSalaryHeader(
			PayrollSelectEmployeesForm payrollSelectEmployeesForm);

	public EmploymentSalaryHeader createEmploymentSalaryHeader(Long payRollId,
			Long payScheduleId, Date fromDate, Date toDate, Long employeeId);
}
