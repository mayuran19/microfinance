package com.suwadi.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "saving_account_deposits")
public class SavingAccountDeposit extends DomainObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date depositDate;
	private BigDecimal amount;
	private SavingAccount savingAccount;
	private Employee collectedBy;
	private String remarks;
	private SavingAccountDepositType savingAccountDepositType;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "saving_account_id")
	public SavingAccount getSavingAccount() {
		return savingAccount;
	}

	public void setSavingAccount(SavingAccount savingAccount) {
		this.savingAccount = savingAccount;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	public Employee getCollectedBy() {
		return collectedBy;
	}

	public void setCollectedBy(Employee collectedBy) {
		this.collectedBy = collectedBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "saving_account_deposit_type_id")
	public SavingAccountDepositType getSavingAccountDepositType() {
		return savingAccountDepositType;
	}

	public void setSavingAccountDepositType(
			SavingAccountDepositType savingAccountDepositType) {
		this.savingAccountDepositType = savingAccountDepositType;
	}

}
