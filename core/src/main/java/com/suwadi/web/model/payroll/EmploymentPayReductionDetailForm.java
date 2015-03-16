package com.suwadi.web.model.payroll;

import java.math.BigDecimal;

import com.suwadi.domain.SalaryType;

public class EmploymentPayReductionDetailForm {
	private Long payReductionId;
	private BigDecimal amount;
	private Boolean isMandatory;
	private Boolean isUserEnteredAmount;
	private SalaryType salaryType;

	public Long getPayReductionId() {
		return payReductionId;
	}

	public void setPayReductionId(Long payReductionId) {
		this.payReductionId = payReductionId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Boolean getIsMandatory() {
		return isMandatory;
	}

	public void setIsMandatory(Boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	public Boolean getIsUserEnteredAmount() {
		return isUserEnteredAmount;
	}

	public void setIsUserEnteredAmount(Boolean isUserEnteredAmount) {
		this.isUserEnteredAmount = isUserEnteredAmount;
	}

	public SalaryType getSalaryType() {
		return salaryType;
	}

	public void setSalaryType(SalaryType salaryType) {
		this.salaryType = salaryType;
	}

}
