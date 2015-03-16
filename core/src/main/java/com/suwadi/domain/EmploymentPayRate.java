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

@Entity(name = "employment_pay_rates")
public class EmploymentPayRate extends DomainObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Employment employment;
	private PayRate payRate;
	private Boolean isUnitBased;
	private BigDecimal unit;
	private BigDecimal unitPrice;
	private PayRatePer payRatePer;

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
	@JoinColumn(name = "pay_rate_id")
	public PayRate getPayRate() {
		return payRate;
	}

	public void setPayRate(PayRate payRate) {
		this.payRate = payRate;
	}

	public Boolean getIsUnitBased() {
		return isUnitBased;
	}

	public void setIsUnitBased(Boolean isUnitBased) {
		this.isUnitBased = isUnitBased;
	}

	public BigDecimal getUnit() {
		return unit;
	}

	public void setUnit(BigDecimal unit) {
		this.unit = unit;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Enumerated(EnumType.STRING)
	public PayRatePer getPayRatePer() {
		return payRatePer;
	}

	public void setPayRatePer(PayRatePer payRatePer) {
		this.payRatePer = payRatePer;
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
