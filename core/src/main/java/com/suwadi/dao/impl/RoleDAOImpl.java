package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.RoleDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.Role;

@Repository("roleDAO")
public class RoleDAOImpl extends GenericHibernateDAOSupport<Role> implements
		RoleDAO {
	public RoleDAOImpl() {
		super(Role.class);
	}
}
