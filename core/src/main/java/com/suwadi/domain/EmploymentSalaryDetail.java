package com.suwadi.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*
 * Author Mayuran.S
 */

@Entity(name = "employment_salary_details")
public class EmploymentSalaryDetail extends DomainObject {
	private static final long serialVersionUID = 1L;
	private Long id;
	private EmploymentSalaryHeader employmentSalaryHeader;
	private EmploymentPayType employmentPayType;
	private EmploymentPayReduction employmentPayReduction;
	private String payTypeName;
	private BigDecimal payTypeAmount;
	private String payReductionName;
	private BigDecimal payReductionAmount;
	private String remarks;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employment_salary_header_id")
	public EmploymentSalaryHeader getEmploymentSalaryHeader() {
		return employmentSalaryHeader;
	}

	public void setEmploymentSalaryHeader(
			EmploymentSalaryHeader employmentSalaryHeader) {
		this.employmentSalaryHeader = employmentSalaryHeader;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employment_pay_type_id")
	public EmploymentPayType getEmploymentPayType() {
		return employmentPayType;
	}

	public void setEmploymentPayType(EmploymentPayType employmentPayType) {
		this.employmentPayType = employmentPayType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employment_pay_reduction_id")
	public EmploymentPayReduction getEmploymentPayReduction() {
		return employmentPayReduction;
	}

	public void setEmploymentPayReduction(
			EmploymentPayReduction employmentPayReduction) {
		this.employmentPayReduction = employmentPayReduction;
	}

	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

	public BigDecimal getPayTypeAmount() {
		return payTypeAmount;
	}

	public void setPayTypeAmount(BigDecimal payTypeAmount) {
		this.payTypeAmount = payTypeAmount;
	}

	public String getPayReductionName() {
		return payReductionName;
	}

	public void setPayReductionName(String payReductionName) {
		this.payReductionName = payReductionName;
	}

	public BigDecimal getPayReductionAmount() {
		return payReductionAmount;
	}

	public void setPayReductionAmount(BigDecimal payReductionAmount) {
		this.payReductionAmount = payReductionAmount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof EmploymentSalaryDetail) {
				EmploymentSalaryDetail other = (EmploymentSalaryDetail) obj;
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
