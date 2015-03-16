package com.suwadi.web.model.microfinance;

import java.math.BigDecimal;
import java.util.Date;

public class LoanForm {
	private Long id;
	private BigDecimal loanAmount = new BigDecimal(0);
	private BigDecimal paidAmount = new BigDecimal(0);;
	private BigDecimal dueAmount = new BigDecimal(0);;
	private String loanName;
	private Date loanStartDate;
	private Date loanEndDate;
	private String loanPurpose;
	private String Remarks;
	private Long beneficiaryId;
	private BigDecimal gracePeriod;
	private Integer loanPeriod;
	private BigDecimal interestRate;
	private Long loanRecoveryMethodId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public BigDecimal getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(BigDecimal dueAmount) {
		this.dueAmount = dueAmount;
	}

	public String getLoanName() {
		return loanName;
	}

	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	public Date getLoanStartDate() {
		return loanStartDate;
	}

	public void setLoanStartDate(Date loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	public Date getLoanEndDate() {
		return loanEndDate;
	}

	public void setLoanEndDate(Date loanEndDate) {
		this.loanEndDate = loanEndDate;
	}

	public String getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public Long getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(Long beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public BigDecimal getGracePeriod() {
		return gracePeriod;
	}

	public void setGracePeriod(BigDecimal gracePeriod) {
		this.gracePeriod = gracePeriod;
	}

	public Integer getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(Integer loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public Long getLoanRecoveryMethodId() {
		return loanRecoveryMethodId;
	}

	public void setLoanRecoveryMethodId(Long loanRecoveryMethodId) {
		this.loanRecoveryMethodId = loanRecoveryMethodId;
	}

}
