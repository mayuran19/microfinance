package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.DocumentStatusDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.DocumentStatus;

@Repository("documentStatusDAO")
public class DocumentStatusDAOImpl extends
		GenericHibernateDAOSupport<DocumentStatus> implements DocumentStatusDAO {

	public DocumentStatusDAOImpl() {
		super(DocumentStatus.class);
	}

}
