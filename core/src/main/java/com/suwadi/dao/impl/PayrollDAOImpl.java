package com.suwadi.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.PayrollDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.Payroll;

@Repository("payrollDAO")
public class PayrollDAOImpl extends GenericHibernateDAOSupport<Payroll>
		implements PayrollDAO {

	public PayrollDAOImpl() {
		super(Payroll.class);
	}

	public Boolean isPayrollExist(Long payScheduleId, Date startDate,
			Date endDate) {
		String hqlQuery = String
				.format("select count(payroll) from %s payroll where payroll.paySchedule.id = ? and payroll.fromDate <= ? and payroll.toDate >= ?",
						Payroll.class.getName());
		Long cnt = (Long) this.getSession().createQuery(hqlQuery)
				.setLong(0, payScheduleId).setDate(1, startDate)
				.setDate(2, endDate).uniqueResult();
		return cnt.intValue() > 0;
	}
}
