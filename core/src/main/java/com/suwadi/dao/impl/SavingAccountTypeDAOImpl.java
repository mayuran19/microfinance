package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.SavingAccountTypeDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.SavingAccountType;

@Repository("savingAccountTypeDAO")
public class SavingAccountTypeDAOImpl extends
		GenericHibernateDAOSupport<SavingAccountType> implements
		SavingAccountTypeDAO {

	public SavingAccountTypeDAOImpl() {
		super(SavingAccountType.class);
	}

}
