package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.DocumentSendingMediumDAO;
import com.suwadi.domain.DocumentSendingMedium;
import com.suwadi.service.DocumentSendingMediumService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("documentSendingMediumService")
public class DocumentSendingMediumServiceImpl implements
		DocumentSendingMediumService {
	private DocumentSendingMediumDAO documentSendingMediumDAO;

	@Autowired
	public void setDocumentSendingMediumDAO(
			DocumentSendingMediumDAO documentSendingMediumDAO) {
		this.documentSendingMediumDAO = documentSendingMediumDAO;
	}

	public DocumentSendingMedium save(DocumentSendingMedium t) {
		return this.documentSendingMediumDAO.save(t);
	}

	public DocumentSendingMedium update(DocumentSendingMedium t) {
		return this.documentSendingMediumDAO.update(t);
	}

	public DocumentSendingMedium findById(Long id) {
		return this.documentSendingMediumDAO.findById(id);
	}

	public DocumentSendingMedium findById(Long id, String... include) {
		return this.documentSendingMediumDAO.findById(id, include);
	}

	public DocumentSendingMedium delete(DocumentSendingMedium t) {
		return this.documentSendingMediumDAO.delete(t);
	}

	public List<DocumentSendingMedium> findAll() {
		return this.documentSendingMediumDAO.findAll();
	}

	public List<DocumentSendingMedium> findAll(Pager pager) {
		return this.documentSendingMediumDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.documentSendingMediumDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.documentSendingMediumDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.documentSendingMediumDAO
				.isUnique(id, fieldName, fieldValue);
	}

}
