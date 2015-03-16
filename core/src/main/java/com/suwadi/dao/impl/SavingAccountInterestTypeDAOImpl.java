package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.SavingAccountInterestTypeDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.SavingAccountInterestType;

@Repository("savingAccountInterestTypeDAO")
public class SavingAccountInterestTypeDAOImpl extends
		GenericHibernateDAOSupport<SavingAccountInterestType> implements
		SavingAccountInterestTypeDAO {

	public SavingAccountInterestTypeDAOImpl() {
		super(SavingAccountInterestType.class);
	}

}
