package com.suwadi.dao.impl;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.DocumentSendingMediumDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.DocumentSendingMedium;

@Repository("documentSendingMediumDAO")
public class DocumentSendingMediumDAOImpl extends
		GenericHibernateDAOSupport<DocumentSendingMedium> implements
		DocumentSendingMediumDAO {

	public DocumentSendingMediumDAOImpl() {
		super(DocumentSendingMedium.class);
	}

}
