package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.TermOfPaymentDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.TermOfPayment;

@Repository("TermOfPaymentDAO")
public class TermOfPaymentDAOImpl extends
		GenericHibernateDAOSupport<TermOfPayment> implements TermOfPaymentDAO {
	public TermOfPaymentDAOImpl() {
		super(TermOfPayment.class);
	}

}
