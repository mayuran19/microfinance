package com.suwadi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "pay_types")
public class PayType extends DomainObject {
	/*
	 * 1. Overtime Pay 2. Double Overtime Pay 3. Holiday Pay 4. Bonus
	 */
	private Long id;
	private String payType;
	private String description;
	private Boolean isFixedPay;
	private Boolean isVariablePay;
	private Integer displayOrder;
	private Boolean isMandatory;
	private Boolean isUserEnteredAmount;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public Boolean getIsMandatory() {
		return isMandatory;
	}

	public void setIsMandatory(Boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	public Boolean getIsUserEnteredAmount() {
		return isUserEnteredAmount;
	}

	public void setIsUserEnteredAmount(Boolean isUserEnteredAmount) {
		this.isUserEnteredAmount = isUserEnteredAmount;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof PayType) {
				PayType other = (PayType) obj;
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
