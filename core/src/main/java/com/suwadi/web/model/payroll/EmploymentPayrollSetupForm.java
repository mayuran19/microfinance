package com.suwadi.web.model.payroll;

public class EmploymentPayrollSetupForm {
	private Long employmentId;
	private Long employeeId;
	private Long[] fixedPayTypes;
	private Long[] fixedPayTaxes;
	private Long[] fixedPayReductions;

	public Long getEmploymentId() {
		return employmentId;
	}

	public void setEmploymentId(Long employmentId) {
		this.employmentId = employmentId;
	}

	public Long[] getFixedPayTypes() {
		return fixedPayTypes;
	}

	public void setFixedPayTypes(Long[] fixedPayTypes) {
		this.fixedPayTypes = fixedPayTypes;
	}

	public Long[] getFixedPayTaxes() {
		return fixedPayTaxes;
	}

	public void setFixedPayTaxes(Long[] fixedPayTaxes) {
		this.fixedPayTaxes = fixedPayTaxes;
	}

	public Long[] getFixedPayReductions() {
		return fixedPayReductions;
	}

	public void setFixedPayReductions(Long[] fixedPayReductions) {
		this.fixedPayReductions = fixedPayReductions;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

}
