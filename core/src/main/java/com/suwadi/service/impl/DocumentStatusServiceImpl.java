package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.DocumentStatusDAO;
import com.suwadi.domain.DocumentStatus;
import com.suwadi.service.DocumentStatusService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("documentStatusService")
public class DocumentStatusServiceImpl implements DocumentStatusService {
	private DocumentStatusDAO documentStatusDAO;

	@Autowired
	public void setDocumentStatusDAO(DocumentStatusDAO documentStatusDAO) {
		this.documentStatusDAO = documentStatusDAO;
	}

	public DocumentStatus save(DocumentStatus t) {
		return this.documentStatusDAO.save(t);
	}

	public DocumentStatus update(DocumentStatus t) {
		return this.documentStatusDAO.update(t);
	}

	public DocumentStatus findById(Long id) {
		return this.documentStatusDAO.findById(id);
	}

	public DocumentStatus findById(Long id, String... include) {
		return this.documentStatusDAO.findById(id, include);
	}

	public DocumentStatus delete(DocumentStatus t) {
		return this.documentStatusDAO.delete(t);
	}

	public List<DocumentStatus> findAll() {
		return this.documentStatusDAO.findAll();
	}

	public List<DocumentStatus> findAll(Pager pager) {
		return this.documentStatusDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.documentStatusDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.documentStatusDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.documentStatusDAO.isUnique(id, fieldName, fieldValue);
	}

}
