package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.DocumentSendingMediumDAO;
import com.suwadi.dao.DocumentStatusDAO;
import com.suwadi.dao.DocumentTrackingDAO;
import com.suwadi.dao.DocumentTypeDAO;
import com.suwadi.domain.DocumentTracking;
import com.suwadi.service.DocumentTrackingService;
import com.suwadi.web.model.documentTracking.DocumentTrackingForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("documentTrackingService")
public class DocumentTrackingServiceImpl implements DocumentTrackingService {
	private DocumentTrackingDAO documentTrackingDAO;
	private DocumentTypeDAO documentTypeDAO;
	private DocumentStatusDAO documentStatusDAO;
	private DocumentSendingMediumDAO documentSendingMediumDAO;

	@Autowired
	public void setDocumentTrackingDAO(DocumentTrackingDAO documentTrackingDAO) {
		this.documentTrackingDAO = documentTrackingDAO;
	}

	@Autowired
	public void setDocumentTypeDAO(DocumentTypeDAO documentTypeDAO) {
		this.documentTypeDAO = documentTypeDAO;
	}

	@Autowired
	public void setDocumentStatusDAO(DocumentStatusDAO documentStatusDAO) {
		this.documentStatusDAO = documentStatusDAO;
	}

	@Autowired
	public void setDocumentSendingMediumDAO(
			DocumentSendingMediumDAO documentSendingMediumDAO) {
		this.documentSendingMediumDAO = documentSendingMediumDAO;
	}

	public DocumentTracking save(DocumentTracking t) {
		return this.documentTrackingDAO.save(t);
	}

	public DocumentTracking update(DocumentTracking t) {
		return this.documentTrackingDAO.update(t);
	}

	public DocumentTracking findById(Long id) {
		return this.documentTrackingDAO.findById(id);
	}

	public DocumentTracking findById(Long id, String... include) {
		return this.documentTrackingDAO.findById(id, include);
	}

	public DocumentTracking delete(DocumentTracking t) {
		return this.documentTrackingDAO.delete(t);
	}

	public List<DocumentTracking> findAll() {
		return this.documentTrackingDAO.findAll();
	}

	public List<DocumentTracking> findAll(Pager pager) {
		return this.documentTrackingDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.documentTrackingDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.documentTrackingDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.documentTrackingDAO.isUnique(id, fieldName, fieldValue);
	}

	public DocumentTracking save(DocumentTrackingForm documentTrackingForm) {
		DocumentTracking documentTracking = new DocumentTracking();
		documentTracking.setDocumentSendingMedium(this.documentSendingMediumDAO
				.findById(documentTrackingForm.getDocumentSendingMediumId()));
		documentTracking.setDocumentStatus(this.documentStatusDAO
				.findById(documentTrackingForm.getDocumentStatusId()));
		documentTracking.setDocumentSubject(documentTrackingForm
				.getDocumentSubject());
		documentTracking.setDocumentType(this.documentTypeDAO
				.findById(documentTrackingForm.getDocumentTypeId()));
		documentTracking.setInboundOutBound(documentTrackingForm
				.getInboundOutBound());
		documentTracking.setReferenceNumber(documentTrackingForm
				.getReferenceNumber());
		documentTracking.setRemarks(documentTrackingForm.getRemarks());
		documentTracking.setSendingByPersonName(documentTrackingForm
				.getSendingByPersonName());
		documentTracking.setSendingMediumIdentityNumber(documentTrackingForm
				.getSendingMediumIdentityNumber());
		documentTracking.setSendingToPersonName(documentTrackingForm
				.getSendingToPersonName());
		documentTracking.setSentOutDate(documentTrackingForm.getSentOutDate());
		documentTracking.setNoOfDocuments(documentTrackingForm.getNoOfDocuments());
		return this.save(documentTracking);
	}

	public DocumentTracking update(DocumentTrackingForm documentTrackingForm) {
		DocumentTracking documentTracking = this.findById(documentTrackingForm
				.getId());
		documentTracking.setDocumentSendingMedium(this.documentSendingMediumDAO
				.findById(documentTrackingForm.getDocumentSendingMediumId()));
		documentTracking.setDocumentStatus(this.documentStatusDAO
				.findById(documentTrackingForm.getDocumentStatusId()));
		documentTracking.setDocumentSubject(documentTrackingForm
				.getDocumentSubject());
		documentTracking.setDocumentType(this.documentTypeDAO
				.findById(documentTrackingForm.getDocumentTypeId()));
		documentTracking.setInboundOutBound(documentTrackingForm
				.getInboundOutBound());
		documentTracking.setReferenceNumber(documentTrackingForm
				.getReferenceNumber());
		documentTracking.setRemarks(documentTrackingForm.getRemarks());
		documentTracking.setSendingByPersonName(documentTrackingForm
				.getSendingByPersonName());
		documentTracking.setSendingMediumIdentityNumber(documentTrackingForm
				.getSendingMediumIdentityNumber());
		documentTracking.setSendingToPersonName(documentTrackingForm
				.getSendingToPersonName());
		documentTracking.setSentOutDate(documentTrackingForm.getSentOutDate());
		documentTracking.setNoOfDocuments(documentTrackingForm.getNoOfDocuments());
		return this.update(documentTracking);
	}

	public DocumentTrackingForm findByDocumentTrackingId(Long documentTrackingId) {
		DocumentTracking documentTracking = this.findById(documentTrackingId,
				"documentType", "documentSendingMedium", "documentStatus");
		DocumentTrackingForm documentTrackingForm = new DocumentTrackingForm();
		documentTrackingForm.setDocumentSendingMediumId(documentTracking
				.getDocumentSendingMedium().getId());
		documentTrackingForm.setDocumentStatusId(documentTracking
				.getDocumentStatus().getId());
		documentTrackingForm.setDocumentSubject(documentTracking
				.getDocumentSubject());
		documentTrackingForm.setDocumentTypeId(documentTracking
				.getDocumentType().getId());
		documentTrackingForm.setId(documentTracking.getId());
		documentTrackingForm.setInboundOutBound(documentTracking
				.getInboundOutBound());
		documentTrackingForm.setReferenceNumber(documentTracking
				.getReferenceNumber());
		documentTrackingForm.setRemarks(documentTracking.getRemarks());
		documentTrackingForm.setSendingByPersonName(documentTracking
				.getSendingByPersonName());
		documentTrackingForm.setSendingMediumIdentityNumber(documentTracking
				.getSendingMediumIdentityNumber());
		documentTrackingForm.setSendingToPersonName(documentTracking
				.getSendingToPersonName());
		documentTrackingForm.setSentOutDate(documentTracking.getSentOutDate());
		documentTrackingForm.setNoOfDocuments(documentTracking.getNoOfDocuments());
		return documentTrackingForm;
	}

	public PagedResultSet paginateInboundDocuments(Pager pager) {
		return this.documentTrackingDAO.paginateInboundDocuments(pager);
	}

	public PagedResultSet paginateOutboundDocuments(Pager pager) {
		return this.documentTrackingDAO.paginateOutboundDocuments(pager);
	}
}
