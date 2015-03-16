package com.suwadi.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.EmploymentSalaryHeaderDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.EmploymentSalaryHeader;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Repository("employmentSalaryHeaderDAO")
public class EmploymentSalaryHeaderDAOImpl extends
		GenericHibernateDAOSupport<EmploymentSalaryHeader> implements
		EmploymentSalaryHeaderDAO {

	public EmploymentSalaryHeaderDAOImpl() {
		super(EmploymentSalaryHeader.class);
	}

	@SuppressWarnings("unchecked")
	public PagedResultSet findByPayrollId(final Long payrollId,
			final Pager pager) {
		final String hqlQuery = String
				.format("select employmentSalaryHeader from %s employmentSalaryHeader left join fetch employmentSalaryHeader.employment left join fetch employmentSalaryHeader.employment.employee where employmentSalaryHeader.payroll.id = ?",
						EmploymentSalaryHeader.class.getName());
		List<EmploymentSalaryHeader> list = this.getHibernateTemplate()
				.execute(new HibernateCallback<List<EmploymentSalaryHeader>>() {
					public List<EmploymentSalaryHeader> doInHibernate(
							Session session) throws HibernateException,
							SQLException {
						Query query = session
								.createQuery(hqlQuery)
								.setLong(0, payrollId)
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
								.format("select count(employmentSalaryHeader) from %s employmentSalaryHeader left join employmentSalaryHeader.employment left join employmentSalaryHeader.employment.employee where employmentSalaryHeader.payroll.id = ?",
										EmploymentSalaryHeader.class.getName());
						Long cnt = (Long) session.createQuery(hqlQuery)
								.setLong(0, payrollId).uniqueResult();
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
