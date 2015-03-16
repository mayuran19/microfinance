package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.EmploymentPayScheduleDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.EmploymentPaySchedule;

@Repository("employmentPayScheduleDAO")
public class EmploymentPayScheduleDAOImpl extends
		GenericHibernateDAOSupport<EmploymentPaySchedule> implements
		EmploymentPayScheduleDAO {
	public EmploymentPayScheduleDAOImpl() {
		super(EmploymentPaySchedule.class);
	}
}
