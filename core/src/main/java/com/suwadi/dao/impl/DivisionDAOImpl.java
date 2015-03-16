package com.suwadi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.DivisionDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.Division;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Repository("divisionDAO")
public class DivisionDAOImpl extends GenericHibernateDAOSupport<Division>
		implements DivisionDAO {
	public DivisionDAOImpl() {
		super(Division.class);
	}

	@SuppressWarnings("unchecked")
	public List<Division> findAllByDistrictId(Long districtId) {
		String hql = String.format(
				"select obj from %s as obj where obj.district.id = ? and status =  1",
				Division.class.getName());
		return this.getHibernateTemplate().find(hql, districtId);
	}

	public PagedResultSet findAllByDistrictId(Long districtId, Pager pager) {
		StringBuffer sb = new StringBuffer();
		String basicQuery = String.format(
				"select obj from %s obj where obj.district.id = %d and status =  1",
				Division.class.getName(), districtId);
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
		List<Division> divisions = query.list();

		// calculate the total result count
		String hqlForCount = String.format(
				"select count(obj.id) from %s obj where obj.district.id = %d and status =  1",
				Division.class.getName(), districtId);
		Long cnt = (Long) this.getSession().createQuery(hqlForCount)
				.uniqueResult();
		int count = cnt.intValue();

		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(divisions);
		pr.setRowCount(count);

		return pr;
	}

}
