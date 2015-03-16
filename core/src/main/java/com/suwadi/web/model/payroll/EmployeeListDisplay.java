package com.suwadi.web.model.payroll;

public class EmployeeListDisplay {
	private Long employeeId;
	private Long activeEmploymentId;
	private String name;
	private String mobileNumber;
	private String nicNo;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Long getActiveEmploymentId() {
		return activeEmploymentId;
	}

	public void setActiveEmploymentId(Long activeEmploymentId) {
		this.activeEmploymentId = activeEmploymentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getNicNo() {
		return nicNo;
	}

	public void setNicNo(String nicNo) {
		this.nicNo = nicNo;
	}

}
