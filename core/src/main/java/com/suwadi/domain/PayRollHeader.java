package com.suwadi.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "payroll_headers")
public class PayRollHeader extends DomainObject {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Employment employment;
	private Date date;
	private List<PayrollDetail> payrollDetails = new ArrayList<PayrollDetail>();

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "payroll_header_id", insertable = false, updatable = false)
	public List<PayrollDetail> getPayrollDetails() {
		return payrollDetails;
	}

	public void setPayrollDetails(List<PayrollDetail> payrollDetails) {
		this.payrollDetails = payrollDetails;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof PayRollHeader) {
				PayRollHeader other = (PayRollHeader) obj;
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
