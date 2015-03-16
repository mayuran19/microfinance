package com.suwadi.web.model.payroll;

import java.util.Date;

public class PayrollSelectEmployeesForm {
	private Date fromDate;
	private Date toDate;
	private Long payScheduleId;
	private Long[] employmentIds;
	private Long payrollId;

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Long getPayScheduleId() {
		return payScheduleId;
	}

	public void setPayScheduleId(Long payScheduleId) {
		this.payScheduleId = payScheduleId;
	}

	public Long[] getEmploymentIds() {
		return employmentIds;
	}

	public void setEmploymentIds(Long[] employmentIds) {
		this.employmentIds = employmentIds;
	}

	public Long getPayrollId() {
		return payrollId;
	}

	public void setPayrollId(Long payrollId) {
		this.payrollId = payrollId;
	}

}
