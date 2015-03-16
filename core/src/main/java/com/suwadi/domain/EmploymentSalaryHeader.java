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
 * author Mayuran.S
 */

@Entity(name = "employment_salary_headers")
public class EmploymentSalaryHeader extends DomainObject {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Employment employment;
	private Payroll payroll;
	private BigDecimal grossPay;
	private BigDecimal netPay;
	private BigDecimal totalReductions;
	private String employeeName;
	private String employeeNicNo;

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
	@JoinColumn(name = "payroll_id")
	public Payroll getPayroll() {
		return payroll;
	}

	public void setPayroll(Payroll payroll) {
		this.payroll = payroll;
	}

	public BigDecimal getGrossPay() {
		return grossPay;
	}

	public void setGrossPay(BigDecimal grossPay) {
		this.grossPay = grossPay;
	}

	public BigDecimal getNetPay() {
		return netPay;
	}

	public void setNetPay(BigDecimal netPay) {
		this.netPay = netPay;
	}

	public BigDecimal getTotalReductions() {
		return totalReductions;
	}

	public void setTotalReductions(BigDecimal totalReductions) {
		this.totalReductions = totalReductions;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof EmploymentSalaryHeader) {
				EmploymentSalaryHeader other = (EmploymentSalaryHeader) obj;
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

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeNicNo() {
		return employeeNicNo;
	}

	public void setEmployeeNicNo(String employeeNicNo) {
		this.employeeNicNo = employeeNicNo;
	}

}
