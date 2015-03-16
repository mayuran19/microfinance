package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.UploadDocumentDAO;
import com.suwadi.domain.UploadDocument;
import com.suwadi.service.UploadDocumentService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("uploadDocumentService")
public class UploadDocumentServiceImpl implements UploadDocumentService {
	private UploadDocumentDAO uploadDocDAO;

	public UploadDocumentDAO getUploadDocumentDAO() {
		return uploadDocDAO;
	}

	@Autowired
	public void setUploadDocumentDAO(UploadDocumentDAO uploadDocDAO) {
		this.uploadDocDAO = uploadDocDAO;
	}

	public UploadDocument save(UploadDocument t) {
		return this.uploadDocDAO.save(t);
	}

	public UploadDocument update(UploadDocument t) {
		return this.uploadDocDAO.update(t);
	}

	public UploadDocument findById(Long id) {
		return this.uploadDocDAO.findById(id);
	}

	public UploadDocument findById(Long id, String... include) {
		return this.uploadDocDAO.findById(id, include);
	}

	public UploadDocument delete(UploadDocument t) {
		return this.uploadDocDAO.delete(t);
	}

	public List<UploadDocument> findAll() {
		return this.uploadDocDAO.findAll();
	}

	public List<UploadDocument> findAll(Pager pager) {
		return this.uploadDocDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.uploadDocDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.uploadDocDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.uploadDocDAO.isUnique(id, fieldName, fieldValue);
	}

	public void save(List<UploadDocument> uploadDocuments) {
		this.uploadDocDAO.save(uploadDocuments);
	}

}