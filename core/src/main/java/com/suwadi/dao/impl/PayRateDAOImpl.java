package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.PayRateDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.PayRate;

@Repository("payRateDAO")
public class PayRateDAOImpl extends GenericHibernateDAOSupport<PayRate>
		implements PayRateDAO {

	public PayRateDAOImpl() {
		super(PayRate.class);
	}

}
