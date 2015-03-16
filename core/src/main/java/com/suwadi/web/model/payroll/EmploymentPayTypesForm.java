package com.suwadi.web.model.payroll;

import java.util.ArrayList;
import java.util.List;

public class EmploymentPayTypesForm {
	private Long employeeId;
	private Long employmentId;
	List<EmploymentPayTypeForm> employmentPayTypeForm = new ArrayList<EmploymentPayTypeForm>();

	public Long getEmploymentId() {
		return employmentId;
	}

	public void setEmploymentId(Long employmentId) {
		this.employmentId = employmentId;
	}

	public List<EmploymentPayTypeForm> getEmploymentPayTypeForm() {
		return employmentPayTypeForm;
	}

	public void setEmploymentPayTypeForm(
			List<EmploymentPayTypeForm> employmentPayTypeForm) {
		this.employmentPayTypeForm = employmentPayTypeForm;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

}
