package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.DocumentDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.Document;

@Repository("documentDAO")
public class DocumentDAOImpl extends GenericHibernateDAOSupport<Document>
		implements DocumentDAO {

	public DocumentDAOImpl() {
		super(Document.class);
	}

}
