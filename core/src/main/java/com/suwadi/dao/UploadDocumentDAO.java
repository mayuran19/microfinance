package com.suwadi.dao;

import com.suwadi.domain.UploadDocument;
import java.util.List;

public interface UploadDocumentDAO extends GenericDAO<UploadDocument> {
	public List<UploadDocument> findDocByRefId(Long id);
	public void save(List<UploadDocument> uploadDocuments);
}
