package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.DocumentTypeDAO;
import com.suwadi.domain.DocumentType;
import com.suwadi.service.DocumentTypeService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("documentTypeService")
public class DocumentTypeServiceImpl implements DocumentTypeService {
	private DocumentTypeDAO documentTypeDAO;

	@Autowired
	public void setDocumentTypeDAO(DocumentTypeDAO documentTypeDAO) {
		this.documentTypeDAO = documentTypeDAO;
	}

	public DocumentType save(DocumentType t) {
		return this.documentTypeDAO.save(t);
	}

	public DocumentType update(DocumentType t) {
		return this.documentTypeDAO.update(t);
	}

	public DocumentType findById(Long id) {
		return this.documentTypeDAO.findById(id);
	}

	public DocumentType findById(Long id, String... include) {
		return this.documentTypeDAO.findById(id, include);
	}

	public DocumentType delete(DocumentType t) {
		return this.documentTypeDAO.delete(t);
	}

	public List<DocumentType> findAll() {
		return this.documentTypeDAO.findAll();
	}

	public List<DocumentType> findAll(Pager pager) {
		return this.documentTypeDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.documentTypeDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.documentTypeDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.documentTypeDAO.isUnique(id, fieldName, fieldValue);
	}

}
