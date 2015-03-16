package com.suwadi.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "payroll_salary_headers")
public class SalaryHeader extends DomainObject {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String description;
	private RecurringPeriod recurringPeriod;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payroll_recurring_period_id")
	public RecurringPeriod getRecurringPeriod() {
		return recurringPeriod;
	}

	public void setRecurringPeriod(RecurringPeriod recurringPeriod) {
		this.recurringPeriod = recurringPeriod;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof SalaryHeader) {
				SalaryHeader other = (SalaryHeader) obj;
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
