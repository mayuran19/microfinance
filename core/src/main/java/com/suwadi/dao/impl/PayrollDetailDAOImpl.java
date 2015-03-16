package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.PayrollDetailDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.PayrollDetail;

@Repository("payrollDetailDAO")
public class PayrollDetailDAOImpl extends
		GenericHibernateDAOSupport<PayrollDetail> implements PayrollDetailDAO {

	public PayrollDetailDAOImpl() {
		super(PayrollDetail.class);
	}

}
