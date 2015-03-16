package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.FamilyRelationshipDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.FamilyRelationship;

@Repository("familyRelationshipDAO")
public class FamilyRelationshipDAOImpl extends
		GenericHibernateDAOSupport<FamilyRelationship> implements
		FamilyRelationshipDAO {
	public FamilyRelationshipDAOImpl() {
		super(FamilyRelationship.class);
	}
}
