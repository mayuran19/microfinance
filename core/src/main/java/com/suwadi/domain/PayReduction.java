package com.suwadi.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "pay_reductions")
public class PayReduction extends DomainObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String payReduction;
	private String description;
	private Boolean isFixed;
	private Boolean isVariable;
	private Boolean isMandatory;
	private Boolean isUserEnteredAmount;
	private SalaryType salaryType;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPayReduction() {
		return payReduction;
	}

	public void setPayReduction(String payReduction) {
		this.payReduction = payReduction;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsFixed() {
		return isFixed;
	}

	public void setIsFixed(Boolean isFixed) {
		this.isFixed = isFixed;
	}

	public Boolean getIsVariable() {
		return isVariable;
	}

	public void setIsVariable(Boolean isVariable) {
		this.isVariable = isVariable;
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
			if (obj instanceof PayReduction) {
				PayReduction other = (PayReduction) obj;
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
