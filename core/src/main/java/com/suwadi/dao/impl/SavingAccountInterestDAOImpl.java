package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.SavingAccountInterestDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.SavingAccountInterest;

@Repository("savingAccountInterestDAO")
public class SavingAccountInterestDAOImpl extends
		GenericHibernateDAOSupport<SavingAccountInterest> implements
		SavingAccountInterestDAO {

	public SavingAccountInterestDAOImpl() {
		super(SavingAccountInterest.class);
	}

}
