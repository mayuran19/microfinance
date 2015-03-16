package com.suwadi.web.model.payroll;

import java.util.Date;

public class PayrollForm {
	private Long payrollId;
	private Long payScheduleId;
	private Date fromDate;
	private Date toDate;

	public Long getPayrollId() {
		return payrollId;
	}

	public void setPayrollId(Long payrollId) {
		this.payrollId = payrollId;
	}

	public Long getPayScheduleId() {
		return payScheduleId;
	}

	public void setPayScheduleId(Long payScheduleId) {
		this.payScheduleId = payScheduleId;
	}

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

}
