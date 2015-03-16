package com.suwadi.web.model.microfinance;

import java.math.BigDecimal;
import java.util.Date;

public class SavingAccountWithdrawForm {
	private Long id;
	private Long savingAccountId;
	private Date withdrawDate;
	private BigDecimal amount;
	private Long processedById;
	private String remarks;
	private Long savingAccountWithdrawTypeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSavingAccountId() {
		return savingAccountId;
	}

	public void setSavingAccountId(Long savingAccountId) {
		this.savingAccountId = savingAccountId;
	}

	public Date getWithdrawDate() {
		return withdrawDate;
	}

	public void setWithdrawDate(Date withdrawDate) {
		this.withdrawDate = withdrawDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getProcessedById() {
		return processedById;
	}

	public void setProcessedById(Long processedById) {
		this.processedById = processedById;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getSavingAccountWithdrawTypeId() {
		return savingAccountWithdrawTypeId;
	}

	public void setSavingAccountWithdrawTypeId(Long savingAccountWithdrawTypeId) {
		this.savingAccountWithdrawTypeId = savingAccountWithdrawTypeId;
	}

}
