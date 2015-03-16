package com.suwadi.web.model.payroll;

import java.util.Date;

public class EmploymentForm {
	private Long employeeId;
	private Long employmentId;
	private Date startDate;
	private Date endDate;
	private Long employmentStatusId;
	private String workLocation;
	private String remarks;
	private Long payScheduleId;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getEmploymentId() {
		return employmentId;
	}

	public void setEmploymentId(Long employmentId) {
		this.employmentId = employmentId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getEmploymentStatusId() {
		return employmentStatusId;
	}

	public void setEmploymentStatusId(Long employmentStatusId) {
		this.employmentStatusId = employmentStatusId;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getPayScheduleId() {
		return payScheduleId;
	}

	public void setPayScheduleId(Long payScheduleId) {
		this.payScheduleId = payScheduleId;
	}

}
