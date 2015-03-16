package com.suwadi.web.model.microfinance;

import java.math.BigDecimal;
import java.util.Date;

public class SavingAccountDepositForm {
	private Long id;
	private Date depositDate;
	private BigDecimal amount;
	private Long savingAccountId;
	private Long collectedById;
	private String remarks;
	private Long savingAccountDepositTypeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getSavingAccountId() {
		return savingAccountId;
	}

	public void setSavingAccountId(Long savingAccountId) {
		this.savingAccountId = savingAccountId;
	}

	public Long getCollectedById() {
		return collectedById;
	}

	public void setCollectedById(Long collectedById) {
		this.collectedById = collectedById;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getSavingAccountDepositTypeId() {
		return savingAccountDepositTypeId;
	}

	public void setSavingAccountDepositTypeId(Long savingAccountDepositTypeId) {
		this.savingAccountDepositTypeId = savingAccountDepositTypeId;
	}

}
