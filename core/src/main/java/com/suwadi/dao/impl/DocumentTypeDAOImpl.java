package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.DocumentTypeDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.DocumentType;

@Repository("documentTypeDAO")
public class DocumentTypeDAOImpl extends
		GenericHibernateDAOSupport<DocumentType> implements DocumentTypeDAO {

	public DocumentTypeDAOImpl() {
		super(DocumentType.class);
	}

}
