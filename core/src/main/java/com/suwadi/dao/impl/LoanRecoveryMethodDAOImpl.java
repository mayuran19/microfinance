package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.LoanRecoveryMethodDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.LoanRecoveryMethod;

@Repository("LoanRecoveryMethodDAO")
public class LoanRecoveryMethodDAOImpl extends
		GenericHibernateDAOSupport<LoanRecoveryMethod> implements
		LoanRecoveryMethodDAO {

	public LoanRecoveryMethodDAOImpl() {
		super(LoanRecoveryMethod.class);
	}

}
