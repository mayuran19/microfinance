package com.suwadi.web.model.payroll;

import java.math.BigDecimal;

import com.suwadi.domain.SalaryType;

public class EmploymentPayTypeForm {
	private Long payTypeId;
	private BigDecimal amount;
	private String payType;
	private String description;
	private Boolean isMandatory = false;
	private SalaryType salaryType;
	private Boolean isUserEnteredAmount;

	public Long getPayTypeId() {
		return payTypeId;
	}

	public void setPayTypeId(Long payTypeId) {
		this.payTypeId = payTypeId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsMandatory() {
		return isMandatory;
	}

	public void setIsMandatory(Boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	public SalaryType getSalaryType() {
		return salaryType;
	}

	public void setSalaryType(SalaryType salaryType) {
		this.salaryType = salaryType;
	}

	public Boolean getIsUserEnteredAmount() {
		return isUserEnteredAmount;
	}

	public void setIsUserEnteredAmount(Boolean isUserEnteredAmount) {
		this.isUserEnteredAmount = isUserEnteredAmount;
	}

}
