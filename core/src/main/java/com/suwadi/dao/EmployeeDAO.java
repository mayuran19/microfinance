package com.suwadi.dao;

import com.suwadi.domain.Employee;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface EmployeeDAO extends GenericDAO<Employee> {
	public PagedResultSet findAllEmployees(Pager pager);
}
