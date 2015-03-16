package com.suwadi.web.model.documentTracking;

import java.util.Date;

public class DocumentTrackingForm {
	private Long id;
	private Long documentTypeId;
	private Long documentSendingMediumId;
	private String sendingMediumIdentityNumber;
	private String sendingToPersonName;
	private String sendingByPersonName;
	private String documentSubject;
	private Date sentOutDate;
	private Long documentStatusId;
	private String referenceNumber;
	private Integer inboundOutBound;
	private String remarks;
	private Integer noOfDocuments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDocumentTypeId() {
		return documentTypeId;
	}

	public void setDocumentTypeId(Long documentTypeId) {
		this.documentTypeId = documentTypeId;
	}

	public Long getDocumentSendingMediumId() {
		return documentSendingMediumId;
	}

	public void setDocumentSendingMediumId(Long documentSendingMediumId) {
		this.documentSendingMediumId = documentSendingMediumId;
	}

	public String getSendingMediumIdentityNumber() {
		return sendingMediumIdentityNumber;
	}

	public void setSendingMediumIdentityNumber(
			String sendingMediumIdentityNumber) {
		this.sendingMediumIdentityNumber = sendingMediumIdentityNumber;
	}

	public String getSendingToPersonName() {
		return sendingToPersonName;
	}

	public void setSendingToPersonName(String sendingToPersonName) {
		this.sendingToPersonName = sendingToPersonName;
	}

	public String getSendingByPersonName() {
		return sendingByPersonName;
	}

	public void setSendingByPersonName(String sendingByPersonName) {
		this.sendingByPersonName = sendingByPersonName;
	}

	public String getDocumentSubject() {
		return documentSubject;
	}

	public void setDocumentSubject(String documentSubject) {
		this.documentSubject = documentSubject;
	}

	public Date getSentOutDate() {
		return sentOutDate;
	}

	public void setSentOutDate(Date sentOutDate) {
		this.sentOutDate = sentOutDate;
	}

	public Long getDocumentStatusId() {
		return documentStatusId;
	}

	public void setDocumentStatusId(Long documentStatusId) {
		this.documentStatusId = documentStatusId;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public Integer getInboundOutBound() {
		return inboundOutBound;
	}

	public void setInboundOutBound(Integer inboundOutBound) {
		this.inboundOutBound = inboundOutBound;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getNoOfDocuments() {
		return noOfDocuments;
	}

	public void setNoOfDocuments(Integer noOfDocuments) {
		this.noOfDocuments = noOfDocuments;
	}

}
