package com.suwadi.dao;

import com.suwadi.domain.DocumentTracking;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

public interface DocumentTrackingDAO extends GenericDAO<DocumentTracking> {
	public PagedResultSet paginateInboundDocuments(final Pager pager);

	public PagedResultSet paginateOutboundDocuments(final Pager pager);
}
