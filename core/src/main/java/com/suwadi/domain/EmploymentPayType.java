package com.suwadi.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "employment_pay_types")
public class EmploymentPayType extends DomainObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Employment employment;
	private PayType payType;
	private BigDecimal amount;
	private SalaryType salaryType;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employment_id")
	public Employment getEmployment() {
		return employment;
	}

	public void setEmployment(Employment employment) {
		this.employment = employment;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pay_type_id")
	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Enumerated(value = EnumType.STRING)
	public SalaryType getSalaryType() {
		return salaryType;
	}

	public void setSalaryType(SalaryType salaryType) {
		this.salaryType = salaryType;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof EmploymentPayType) {
				EmploymentPayType other = (EmploymentPayType) obj;
				if (this.getId().longValue() == other.getId().longValue()) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
