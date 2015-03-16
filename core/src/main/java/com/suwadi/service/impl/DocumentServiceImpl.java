package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.DocumentDAO;
import com.suwadi.domain.Document;
import com.suwadi.service.DocumentService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("documentService")
public class DocumentServiceImpl implements DocumentService {
	private DocumentDAO documentDAO;

	@Autowired
	public void setDocumentDAO(DocumentDAO documentDAO) {
		this.documentDAO = documentDAO;
	}

	public Document save(Document t) {
		return this.documentDAO.save(t);
	}

	public Document update(Document t) {
		return this.documentDAO.update(t);
	}

	public Document findById(Long id) {
		return this.documentDAO.findById(id);
	}

	public Document findById(Long id, String... include) {
		return this.documentDAO.findById(id, include);
	}

	public Document delete(Document t) {
		return this.documentDAO.delete(t);
	}

	public List<Document> findAll() {
		return this.documentDAO.findAll();
	}

	public List<Document> findAll(Pager pager) {
		return this.documentDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.documentDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.documentDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.documentDAO.isUnique(id, fieldName, fieldValue);
	}

}
