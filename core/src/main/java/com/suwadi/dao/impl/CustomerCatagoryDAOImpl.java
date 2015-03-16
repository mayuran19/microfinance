package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.CustomerCatagoryDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.CustomerCatagory;

@Repository("CustomerCatagoryDAO")
public class CustomerCatagoryDAOImpl extends
		GenericHibernateDAOSupport<CustomerCatagory> implements
		CustomerCatagoryDAO {

	public CustomerCatagoryDAOImpl() {
		super(CustomerCatagory.class);
	}

}
