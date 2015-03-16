package com.suwadi.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "document_trackings")
public class DocumentTracking extends DomainObject {
	private Long id;
	private DocumentType documentType;
	private DocumentSendingMedium documentSendingMedium;
	private String sendingMediumIdentityNumber;
	private String sendingToPersonName;
	private String sendingByPersonName;
	private String documentSubject;
	private Date sentOutDate;
	private DocumentStatus documentStatus;
	private String referenceNumber;
	private Integer inboundOutBound;
	private String remarks;
	private Integer noOfDocuments;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_type_id")
	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_sending_medium_id")
	public DocumentSendingMedium getDocumentSendingMedium() {
		return documentSendingMedium;
	}

	public void setDocumentSendingMedium(
			DocumentSendingMedium documentSendingMedium) {
		this.documentSendingMedium = documentSendingMedium;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "document_status_id")
	public DocumentStatus getDocumentStatus() {
		return documentStatus;
	}

	public void setDocumentStatus(DocumentStatus documentStatus) {
		this.documentStatus = documentStatus;
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

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof DocumentTracking) {
				DocumentTracking other = (DocumentTracking) obj;
				if (this.getId().longValue() == other.getId().longValue()) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
