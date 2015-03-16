package com.suwadi.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "payroll_salary_details")
public class SalaryDetail extends DomainObject {
	private static final long serialVersionUID = 1L;
	private Long id;
	private SalaryPaymentType salaryPaymentType;
	private BigDecimal amount;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payroll_salary_payment_type_id")
	public SalaryPaymentType getSalaryPaymentType() {
		return salaryPaymentType;
	}

	public void setSalaryPaymentType(SalaryPaymentType salaryPaymentType) {
		this.salaryPaymentType = salaryPaymentType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof SalaryDetail) {
				SalaryDetail other = (SalaryDetail) obj;
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
