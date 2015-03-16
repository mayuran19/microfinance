package com.suwadi.service;

import com.suwadi.domain.DocumentTracking;
import com.suwadi.web.model.documentTracking.DocumentTrackingForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface DocumentTrackingService extends
		GenericService<DocumentTracking> {
	public DocumentTracking save(DocumentTrackingForm documentTrackingForm);

	public DocumentTracking update(DocumentTrackingForm documentTrackingForm);

	public DocumentTrackingForm findByDocumentTrackingId(Long documentTrackingId);

	public PagedResultSet paginateInboundDocuments(final Pager pager);

	public PagedResultSet paginateOutboundDocuments(final Pager pager);
}
