package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.ExpenseTypeDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.ExpenseType;

@Repository("expenseTypeDAO")
public class ExpenseTypeDAOImpl extends GenericHibernateDAOSupport<ExpenseType>
		implements ExpenseTypeDAO {

	public ExpenseTypeDAOImpl() {
		super(ExpenseType.class);
	}

}
