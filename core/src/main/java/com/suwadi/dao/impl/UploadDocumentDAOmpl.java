package com.suwadi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.UploadDocumentDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.UploadDocument;

@Repository("uploadDocumentDAO")
public class UploadDocumentDAOmpl extends
		GenericHibernateDAOSupport<UploadDocument> implements UploadDocumentDAO {

	public UploadDocumentDAOmpl() {
		super(UploadDocument.class);
	}

	public void save(List<UploadDocument> uploadDocuments) {
		for (int i = 0; i < uploadDocuments.size(); i++) {
			getHibernateTemplate().save(uploadDocuments.get(i));
			if (i % 20 == 0) { // 20, same as the JDBC batch size
				// flush a batch of inserts and release memory:
				getHibernateTemplate().flush();
				getHibernateTemplate().clear();
			}
		}
	}
	
	public List<UploadDocument> findDocByRefId(Long referenceId) {
		String hql = String.format(
				"select obj from %s as obj where  status =  1 and reference_id = :referenceId",
				UploadDocument.class.getName());
		return this.getHibernateTemplate().find(hql);
	}


}