package com.suwadi.web.model.microfinance;

import java.math.BigDecimal;
import java.util.Date;

public class LoanPaymentForm {
	private Long id;
	private BigDecimal amount;
	private Date paymentDate;
	private Long collectedById;
	private Long loanId;
	private String remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Long getCollectedById() {
		return collectedById;
	}

	public void setCollectedById(Long collectedById) {
		this.collectedById = collectedById;
	}

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
