package com.suwadi.web.model.payroll;

import java.util.ArrayList;
import java.util.List;

public class EmploymentPayReductionHeaderForm {
	private Long employmentId;
	private Long employeeId;
	private List<EmploymentPayReductionDetailForm> employmentPayReductionDetailForms = new ArrayList<EmploymentPayReductionDetailForm>();

	public Long getEmploymentId() {
		return employmentId;
	}

	public void setEmploymentId(Long employmentId) {
		this.employmentId = employmentId;
	}

	public List<EmploymentPayReductionDetailForm> getEmploymentPayReductionDetailForms() {
		return employmentPayReductionDetailForms;
	}

	public void setEmploymentPayReductionDetailForms(
			List<EmploymentPayReductionDetailForm> employmentPayReductionDetailForms) {
		this.employmentPayReductionDetailForms = employmentPayReductionDetailForms;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

}
