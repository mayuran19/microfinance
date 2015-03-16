package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.SavingAccountWithdrawTypeDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.SavingAccountWithdrawType;

@Repository("savingAccountWithdrawTypeDAO")
public class SavingAccountWithdrawTypeDAOImpl extends
		GenericHibernateDAOSupport<SavingAccountWithdrawType> implements
		SavingAccountWithdrawTypeDAO {

	public SavingAccountWithdrawTypeDAOImpl() {
		super(SavingAccountWithdrawType.class);
	}

}
