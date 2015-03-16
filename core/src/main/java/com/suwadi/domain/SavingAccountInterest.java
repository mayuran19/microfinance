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

@Entity(name = "saving_account_interests")
public class SavingAccountInterest extends DomainObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private SavingAccount savingAccount;
	private Date interestDate;
	private BigDecimal interest;
	private BigDecimal amountForInterest;
	private SavingAccountInterestType savingAccountInterestType;

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

	public Date getInterestDate() {
		return interestDate;
	}

	public void setInterestDate(Date interestDate) {
		this.interestDate = interestDate;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getAmountForInterest() {
		return amountForInterest;
	}

	public void setAmountForInterest(BigDecimal amountForInterest) {
		this.amountForInterest = amountForInterest;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "saving_account_interest_type_id")
	public SavingAccountInterestType getSavingAccountInterestType() {
		return savingAccountInterestType;
	}

	public void setSavingAccountInterestType(
			SavingAccountInterestType savingAccountInterestType) {
		this.savingAccountInterestType = savingAccountInterestType;
	}

}
