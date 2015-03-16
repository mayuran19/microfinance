package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.SalaryHeaderDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.SalaryHeader;

@Repository("salaryHeaderDAO")
public class SalaryHeaderDAOImpl extends
		GenericHibernateDAOSupport<SalaryHeader> implements SalaryHeaderDAO {

	public SalaryHeaderDAOImpl() {
		super(SalaryHeader.class);
	}

}
