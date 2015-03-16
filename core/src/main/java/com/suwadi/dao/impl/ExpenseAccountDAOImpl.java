package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.ExpenseAccountDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.ExpenseAccount;

@Repository("expenseAccountDAO")
public class ExpenseAccountDAOImpl extends
		GenericHibernateDAOSupport<ExpenseAccount> implements ExpenseAccountDAO {

	public ExpenseAccountDAOImpl() {
		super(ExpenseAccount.class);
	}

}
