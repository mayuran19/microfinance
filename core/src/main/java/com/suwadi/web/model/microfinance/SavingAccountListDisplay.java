package com.suwadi.web.model.microfinance;

import java.math.BigDecimal;
import java.util.Date;

public class SavingAccountListDisplay {
	private Long savingAccountId;
	private String accountHolderName;
	private String accountNumber;
	private Date openedAt;
	private BigDecimal balance;

	public Long getSavingAccountId() {
		return savingAccountId;
	}

	public void setSavingAccountId(Long savingAccountId) {
		this.savingAccountId = savingAccountId;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getOpenedAt() {
		return openedAt;
	}

	public void setOpenedAt(Date openedAt) {
		this.openedAt = openedAt;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
