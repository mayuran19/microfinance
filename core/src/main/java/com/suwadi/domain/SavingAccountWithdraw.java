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

@Entity(name = "saving_account_withdraws")
public class SavingAccountWithdraw extends DomainObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private SavingAccount savingAccount;
	private Date withdrawDate;
	private BigDecimal amount;
	private Employee processedBy;
	private String remarks;
	private SavingAccountWithdrawType savingAccountWithdrawType;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "saving_account_id")
	public SavingAccount getSavingAccount() {
		return savingAccount;
	}

	public void setSavingAccount(SavingAccount savingAccount) {
		this.savingAccount = savingAccount;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	public Employee getProcessedBy() {
		return processedBy;
	}

	public void setProcessedBy(Employee processedBy) {
		this.processedBy = processedBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setSavingAccountWithdrawType(
			SavingAccountWithdrawType savingAccountWithdrawType) {
		this.savingAccountWithdrawType = savingAccountWithdrawType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "saving_account_withdraw_type_id")
	public SavingAccountWithdrawType getSavingAccountWithdrawType() {
		return savingAccountWithdrawType;
	}

}
