package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.EmploymentPayRateDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.EmploymentPayRate;

@Repository("employmentPayRateDAO")
public class EmploymentPayRateDAOImpl extends
		GenericHibernateDAOSupport<EmploymentPayRate> implements
		EmploymentPayRateDAO {

	public EmploymentPayRateDAOImpl() {
		super(EmploymentPayRate.class);
	}

}
