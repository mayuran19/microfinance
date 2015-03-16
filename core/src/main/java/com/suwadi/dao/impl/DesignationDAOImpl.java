package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.DesignationDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.Designation;

@Repository("designationDAO")
public class DesignationDAOImpl extends GenericHibernateDAOSupport<Designation>
		implements DesignationDAO {

	public DesignationDAOImpl() {
		super(Designation.class);
	}

}
