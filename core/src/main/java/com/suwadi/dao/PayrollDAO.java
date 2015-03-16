package com.suwadi.dao;

import java.util.Date;

import com.suwadi.domain.Payroll;

public interface PayrollDAO extends GenericDAO<Payroll> {
	public Boolean isPayrollExist(Long payScheduleId, Date startDate,
			Date endDate);
}
