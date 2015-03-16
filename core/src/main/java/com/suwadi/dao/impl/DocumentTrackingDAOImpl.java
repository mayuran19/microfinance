package com.suwadi.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.DocumentTrackingDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.DocumentTracking;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Repository("documentTrackingDAO")
public class DocumentTrackingDAOImpl extends
		GenericHibernateDAOSupport<DocumentTracking> implements
		DocumentTrackingDAO {

	public DocumentTrackingDAOImpl() {
		super(DocumentTracking.class);
	}

	@SuppressWarnings("unchecked")
	public PagedResultSet paginateInboundDocuments(final Pager pager) {
		final String hqlQuery = String
				.format("select inboundDocument from %s inboundDocument left join fetch inboundDocument.documentSendingMedium left join fetch inboundDocument.documentType left join fetch inboundDocument.documentStatus where inboundDocument.inboundOutBound = 1 order by inboundDocument.sentOutDate desc",
						DocumentTracking.class.getName());
		List<DocumentTracking> list = this.getHibernateTemplate().execute(
				new HibernateCallback<List<DocumentTracking>>() {

					public List<DocumentTracking> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session
								.createQuery(hqlQuery)
								.setFirstResult(
										(pager.getPage() - 1)
												* pager.getPageSize())
								.setMaxResults(pager.getPageSize());
						return query.list();
					}
				});

		Integer count = this.getHibernateTemplate().execute(
				new HibernateCallback<Integer>() {
					public Integer doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hqlQuery = String
								.format("select count(inboundDocument) from %s inboundDocument where inboundDocument.inboundOutBound = 1",
										DocumentTracking.class.getName());
						Long cnt = (Long) session.createQuery(hqlQuery)
								.uniqueResult();
						int count = cnt.intValue();
						return count;
					}
				});

		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(list);
		pr.setRowCount(count);
		return pr;
	}

	public PagedResultSet paginateOutboundDocuments(final Pager pager) {
		final String hqlQuery = String
				.format("select outboundDocument from %s outboundDocument left join fetch outboundDocument.documentSendingMedium left join fetch outboundDocument.documentType left join fetch outboundDocument.documentStatus where outboundDocument.inboundOutBound = 2 order by outboundDocument.sentOutDate desc",
						DocumentTracking.class.getName());
		List<DocumentTracking> list = this.getHibernateTemplate().execute(
				new HibernateCallback<List<DocumentTracking>>() {

					public List<DocumentTracking> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session
								.createQuery(hqlQuery)
								.setFirstResult(
										(pager.getPage() - 1)
												* pager.getPageSize())
								.setMaxResults(pager.getPageSize());
						return query.list();
					}
				});

		Integer count = this.getHibernateTemplate().execute(
				new HibernateCallback<Integer>() {
					public Integer doInHibernate(Session session)
							throws HibernateException, SQLException {
						String hqlQuery = String
								.format("select count(outboundDocument) from %s outboundDocument where outboundDocument.inboundOutBound = 2",
										DocumentTracking.class.getName());
						Long cnt = (Long) session.createQuery(hqlQuery)
								.uniqueResult();
						int count = cnt.intValue();
						return count;
					}
				});

		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(list);
		pr.setRowCount(count);
		return pr;
	}
}
