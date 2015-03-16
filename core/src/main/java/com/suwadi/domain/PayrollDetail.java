package com.suwadi.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "payroll_details")
public class PayrollDetail extends DomainObject {
	private static final long serialVersionUID = 1L;
	private Long id;
	private PayRollHeader payRollHeader;
	private String name;
	private String description;
	private Boolean isUnitBased;
	private BigDecimal unit;
	private BigDecimal unitPrice;
	private BigDecimal amount;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payroll_header_id")
	public PayRollHeader getPayRollHeader() {
		return payRollHeader;
	}

	public void setPayRollHeader(PayRollHeader payRollHeader) {
		this.payRollHeader = payRollHeader;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof PayrollDetail) {
				PayrollDetail other = (PayrollDetail) obj;
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
