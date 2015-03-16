package com.suwadi.web.model.payroll;

import java.math.BigDecimal;

public class EmploymentPayTaxDetail {
	private Long payTaxId;
	private BigDecimal amount;

	public Long getPayTaxId() {
		return payTaxId;
	}

	public void setPayTaxId(Long payTaxId) {
		this.payTaxId = payTaxId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
