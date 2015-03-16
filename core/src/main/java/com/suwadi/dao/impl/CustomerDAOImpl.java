package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.CustomerDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.Customer;

@Repository("CustomerDAO")
public class CustomerDAOImpl extends GenericHibernateDAOSupport<Customer>
		implements CustomerDAO {

	public CustomerDAOImpl() {
		super(Customer.class);
	}

}
