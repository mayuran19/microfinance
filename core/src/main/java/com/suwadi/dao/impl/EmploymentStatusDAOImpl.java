package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.EmploymentStatusDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.EmploymentStatus;

@Repository("employmentStatusDAO")
public class EmploymentStatusDAOImpl extends
		GenericHibernateDAOSupport<EmploymentStatus> implements
		EmploymentStatusDAO {

	public EmploymentStatusDAOImpl() {
		super(EmploymentStatus.class);
	}

}
