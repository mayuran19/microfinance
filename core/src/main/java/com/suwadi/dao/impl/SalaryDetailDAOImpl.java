package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.SalaryDetailDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.SalaryDetail;

@Repository("salaryDetailDAO")
public class SalaryDetailDAOImpl extends
		GenericHibernateDAOSupport<SalaryDetail> implements SalaryDetailDAO {

	public SalaryDetailDAOImpl() {
		super(SalaryDetail.class);
	}

}
