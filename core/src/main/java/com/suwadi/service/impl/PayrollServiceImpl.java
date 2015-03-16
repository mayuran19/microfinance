package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.PayrollDAO;
import com.suwadi.domain.Employment;
import com.suwadi.domain.Payroll;
import com.suwadi.service.EmployeeService;
import com.suwadi.service.EmploymentService;
import com.suwadi.service.PayScheduleService;
import com.suwadi.service.PayrollService;
import com.suwadi.web.model.payroll.PayrollForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("payrollService")
public class PayrollServiceImpl implements PayrollService {
	private PayrollDAO payrollDAO;
	private PayScheduleService payScheduleService;
	private EmployeeService employeeService;
	private EmploymentService employmentService;

	@Autowired
	public void setPayrollDAO(PayrollDAO payrollDAO) {
		this.payrollDAO = payrollDAO;
	}

	@Autowired
	public void setPayScheduleService(PayScheduleService payScheduleService) {
		this.payScheduleService = payScheduleService;
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Autowired
	public void setEmploymentService(EmploymentService employmentService) {
		this.employmentService = employmentService;
	}

	public Payroll save(Payroll t) {
		return this.payrollDAO.save(t);
	}

	public Payroll update(Payroll t) {
		return this.payrollDAO.update(t);
	}

	public Payroll findById(Long id) {
		return this.payrollDAO.findById(id);
	}

	public Payroll findById(Long id, String... include) {
		return this.payrollDAO.findById(id, include);
	}

	public Payroll delete(Payroll t) {
		return this.payrollDAO.delete(t);
	}

	public List<Payroll> findAll() {
		return this.payrollDAO.findAll();
	}

	public List<Payroll> findAll(Pager pager) {
		return this.payrollDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.payrollDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.payrollDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.payrollDAO.isUnique(id, fieldName, fieldValue);
	}

	public Payroll createPayroll(PayrollForm payrollForm) {
		Payroll payroll = new Payroll();
		payroll.setFromDate(payrollForm.getFromDate());
		payroll.setPaySchedule(this.payScheduleService.findById(payrollForm
				.getPayScheduleId()));
		payroll.setToDate(payrollForm.getToDate());
		this.save(payroll);
		return payroll;
	}

	public PagedResultSet paginate(Pager pager, String[] joins) {
		return this.payrollDAO.paginate(pager, joins);
	}

	public void processPayroll(Long payrollId) {
		Payroll payroll = this.findById(payrollId, "paySchedule");
		List<Employment> employments = this.employmentService
				.findAllByPayScheduleId(payroll.getPaySchedule().getId());
		for (Employment employment : employments) {
			this.employmentService.createEmploymentSalaryHeader(payrollId,
					payroll.getPaySchedule().getId(), payroll.getFromDate(),
					payroll.getToDate(), employment.getId());
		}
		payroll.setPayrollStatus("PROCESSED");
		this.update(payroll);
	}

}
