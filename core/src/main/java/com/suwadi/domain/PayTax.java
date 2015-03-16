package com.suwadi.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PayTax extends DomainObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String payTax;
	private String description;
	private TaxType taxType;
	private Boolean isFixedPay;
	private Boolean isVariablePay;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPayTax() {
		return payTax;
	}

	public void setPayTax(String payTax) {
		this.payTax = payTax;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Enumerated(value = EnumType.STRING)
	public TaxType getTaxType() {
		return taxType;
	}

	public void setTaxType(TaxType taxType) {
		this.taxType = taxType;
	}

	public Boolean getIsFixedPay() {
		return isFixedPay;
	}

	public void setIsFixedPay(Boolean isFixedPay) {
		this.isFixedPay = isFixedPay;
	}

	public Boolean getIsVariablePay() {
		return isVariablePay;
	}

	public void setIsVariablePay(Boolean isVariablePay) {
		this.isVariablePay = isVariablePay;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof PayTax) {
				PayTax other = (PayTax) obj;
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
