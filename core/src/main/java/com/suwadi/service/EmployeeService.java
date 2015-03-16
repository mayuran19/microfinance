package com.suwadi.service;

import com.suwadi.domain.Employee;
import com.suwadi.web.model.payroll.EmployeeForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface EmployeeService extends GenericService<Employee> {
	public Employee save(EmployeeForm employeeForm);

	public PagedResultSet findAllEmployees(Pager pager);

	public EmployeeForm findByEmployeeId(Long employeeId);

	public Employee update(EmployeeForm employeeForm);
}
