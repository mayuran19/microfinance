package com.suwadi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.GNDivisionDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.Division;
import com.suwadi.domain.GNDivision;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Repository("gnDivisionDAO")
public class GNDivisionDAOImpl extends GenericHibernateDAOSupport<GNDivision>
		implements GNDivisionDAO {

	public GNDivisionDAOImpl() {
		super(GNDivision.class);
	}

	public PagedResultSet findAllByDivisionId(Long divisionId, Pager pager) {
		StringBuffer sb = new StringBuffer();
		String basicQuery = String.format(
				"select obj from %s obj where obj.division.id = %d ",
				GNDivision.class.getName(), divisionId);
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
		List<GNDivision> divisions = query.list();

		// calculate the total result count
		String hqlForCount = String.format(
				"select count(obj.id) from %s obj where obj.division.id = %d",
				GNDivision.class.getName(), divisionId);
		Long cnt = (Long) this.getSession().createQuery(hqlForCount)
				.uniqueResult();
		int count = cnt.intValue();

		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(divisions);
		pr.setRowCount(count);

		return pr;
	}

	@SuppressWarnings("unchecked")
	public List<GNDivision> findAllByDivisionId(Long divisionId) {
		String hql = String.format(
				"select obj from %s as obj where obj.division.id = ?",
				GNDivision.class.getName());
		return this.getHibernateTemplate().find(hql, divisionId);
	}

}
