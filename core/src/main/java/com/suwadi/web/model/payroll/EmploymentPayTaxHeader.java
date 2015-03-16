package com.suwadi.web.model.payroll;

import java.util.ArrayList;
import java.util.List;

public class EmploymentPayTaxHeader {
	private Long employmentId;
	private Long employeeId;
	List<EmploymentPayTaxDetail> employmentPayTaxDetails = new ArrayList<EmploymentPayTaxDetail>();

	public Long getEmploymentId() {
		return employmentId;
	}

	public void setEmploymentId(Long employmentId) {
		this.employmentId = employmentId;
	}

	public List<EmploymentPayTaxDetail> getEmploymentPayTaxDetails() {
		return employmentPayTaxDetails;
	}

	public void setEmploymentPayTaxDetails(
			List<EmploymentPayTaxDetail> employmentPayTaxDetails) {
		this.employmentPayTaxDetails = employmentPayTaxDetails;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

}
