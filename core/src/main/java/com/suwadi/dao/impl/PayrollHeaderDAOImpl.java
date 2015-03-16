package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.PayrollHeaderDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.PayRollHeader;

@Repository("payrollHeaderDAO")
public class PayrollHeaderDAOImpl extends
		GenericHibernateDAOSupport<PayRollHeader> implements PayrollHeaderDAO {

	public PayrollHeaderDAOImpl() {
		super(PayRollHeader.class);
	}

}
