package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suwadi.dao.EmployeeDAO;
import com.suwadi.domain.Employee;
import com.suwadi.service.EmployeeService;
import com.suwadi.web.model.payroll.EmployeeForm;
import com.suwadi.web.model.payroll.EmployeeListDisplay;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDAO employeeDAO;

	@Autowired
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public Employee save(Employee t) {
		return this.employeeDAO.save(t);
	}

	public Employee update(Employee t) {
		return this.employeeDAO.update(t);
	}

	public Employee findById(Long id) {
		return this.employeeDAO.findById(id);
	}

	public Employee findById(Long id, String... include) {
		return this.employeeDAO.findById(id, include);
	}

	public Employee delete(Employee t) {
		return this.employeeDAO.delete(t);
	}

	public List<Employee> findAll() {
		return this.employeeDAO.findAll();
	}

	public List<Employee> findAll(Pager pager) {
		return this.employeeDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.employeeDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.employeeDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.employeeDAO.isUnique(id, fieldName, fieldValue);
	}

	@Transactional
	public Employee save(EmployeeForm employeeForm) {
		Employee employee = new Employee();
		employee.setAddress(employeeForm.getAddress());
		employee.setDateOfBirth(employeeForm.getDateOfBirth());
		employee.setEmail(employeeForm.getEmail());
		employee.setGender(employeeForm.getGender());
		employee.setMobileNo(employeeForm.getMobileNo());
		employee.setName(employeeForm.getName());
		employee.setNicNo(employeeForm.getNicNo());
		employee.setTelephoneNo(employeeForm.getTelephone());
		employee.setDesignation(employeeForm.getDesignation());
		this.save(employee);
		return employee;
	}

	@Transactional
	public Employee update(EmployeeForm employeeForm) {
		Employee employee = this.findById(employeeForm.getEmployeeId());
		employee.setAddress(employeeForm.getAddress());
		employee.setDateOfBirth(employeeForm.getDateOfBirth());
		employee.setEmail(employeeForm.getEmail());
		employee.setGender(employeeForm.getGender());
		employee.setMobileNo(employeeForm.getMobileNo());
		employee.setName(employeeForm.getName());
		employee.setNicNo(employeeForm.getNicNo());
		employee.setTelephoneNo(employeeForm.getTelephone());
		employee.setDesignation(employeeForm.getDesignation());
		this.update(employee);
		return employee;
	}

	public PagedResultSet findAllEmployees(Pager pager) {
		return this.employeeDAO.findAllEmployees(pager);
	}

	public EmployeeForm findByEmployeeId(Long employeeId) {
		Employee employee = this.findById(employeeId);
		EmployeeForm employeeForm = new EmployeeForm();
		employeeForm.setAddress(employee.getAddress());
		employeeForm.setDateOfBirth(employee.getDateOfBirth());
		employeeForm.setEmail(employee.getEmail());
		employeeForm.setEmployeeId(employee.getId());
		employeeForm.setGender(employee.getGender());
		employeeForm.setMobileNo(employee.getMobileNo());
		employeeForm.setName(employee.getName());
		employeeForm.setNicNo(employee.getNicNo());
		employeeForm.setTelephone(employee.getTelephoneNo());
		employeeForm.setDesignation(employee.getDesignation());

		return employeeForm;
	}

}
