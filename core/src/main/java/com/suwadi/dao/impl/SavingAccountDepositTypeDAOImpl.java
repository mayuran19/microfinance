package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.SavingAccountDepositTypeDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.SavingAccountDepositType;

@Repository("savingAccountDepositType")
public class SavingAccountDepositTypeDAOImpl extends
		GenericHibernateDAOSupport<SavingAccountDepositType> implements
		SavingAccountDepositTypeDAO {

	public SavingAccountDepositTypeDAOImpl() {
		super(SavingAccountDepositType.class);
	}

}
