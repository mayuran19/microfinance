package com.suwadi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.SocietyDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.Society;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Repository("societyDAO")
public class SocietyDAOImpl extends GenericHibernateDAOSupport<Society>
		implements SocietyDAO {

	public SocietyDAOImpl() {
		super(Society.class);
	}

	public PagedResultSet getSocietiesByGNDivisionId(Long gnDivisionId,
			Pager pager) {
		StringBuffer sb = new StringBuffer();
		String basicQuery = String.format(
				"select obj from %s obj where obj.gnDivision.id = %d ",
				Society.class.getName(), gnDivisionId);
		sb.append(basicQuery);

		if (pager.getSortOrder() == "asc") {
			sb.append("order by").append(" ").append(pager.getSortColumn())
					.append(" ").append("asc").append(" ");
		} else {
			sb.append("order by").append(" ").append(pager.getSortColumn())
					.append(" ").append("desc").append(" ");
		}
		Query query = this.getSession().createQuery(sb.toString())
				.setFirstResult((pager.getPage() - 1) * pager.getPageSize())
				.setMaxResults(pager.getPageSize());
		List<Society> divisions = query.list();

		// calculate the total result count
		String hqlForCount = String
				.format("select count(obj.id) from %s obj where obj.gnDivision.id = %d",
						Society.class.getName(), gnDivisionId);
		Long cnt = (Long) this.getSession().createQuery(hqlForCount)
				.uniqueResult();
		int count = cnt.intValue();

		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(divisions);
		pr.setRowCount(count);

		return pr;
	}

	public PagedResultSet getAllSocieties(Pager pager) {
		StringBuffer sb = new StringBuffer();
		String basicQuery = String
				.format("select obj from %s obj left join fetch obj.gnDivision left join fetch obj.gnDivision.division left join fetch obj.gnDivision.division.district order by obj.name, obj.gnDivision.division.district.name",
						Society.class.getName());
		sb.append(basicQuery);
		Query query = this.getSession().createQuery(sb.toString())
				.setFirstResult((pager.getPage() - 1) * pager.getPageSize())
				.setMaxResults(pager.getPageSize());
		List<Society> societies = query.list();

		// calculate the total result count
		String hqlForCount = String.format("select count(obj.id) from %s obj",
				Society.class.getName());
		Long cnt = (Long) this.getSession().createQuery(hqlForCount)
				.uniqueResult();
		int count = cnt.intValue();

		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(societies);
		pr.setRowCount(count);

		return pr;
	}

	@SuppressWarnings("unchecked")
	public List<Society> getSocietiesByGNDivisionId(Long gnDivisionId) {
		StringBuffer sb = new StringBuffer();
		String basicQuery = String.format(
				"select obj from %s obj where obj.gnDivision.id = %d",
				Society.class.getName(), gnDivisionId);
		sb.append(basicQuery);
		return this.getHibernateTemplate().find(sb.toString());
	}

}
