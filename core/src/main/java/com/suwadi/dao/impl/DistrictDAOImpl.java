package com.suwadi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.DistrictDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.District;
import com.suwadi.domain.Division;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Repository("districtDAO")
public class DistrictDAOImpl extends GenericHibernateDAOSupport<District>
		implements DistrictDAO {
	public DistrictDAOImpl() {
		super(District.class);
	}

	@SuppressWarnings("unchecked")
	public List<District> findAllActiveDistrict() {
		String hql = String.format(
				"select obj from %s as obj where  status =  1",
				District.class.getName());
		return this.getHibernateTemplate().find(hql);
	}
	
	public PagedResultSet findAllActiveDistrict(Pager pager) {
		StringBuffer sb = new StringBuffer();
		String basicQuery = String.format(
				"select obj from %s obj where status =  1",
				District.class.getName());
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
		List<District> district = query.list();

		// calculate the total result count
		String hqlForCount = String.format(
				"select count(obj.id) from %s obj where status =  1",
				District.class.getName());
		Long cnt = (Long) this.getSession().createQuery(hqlForCount)
				.uniqueResult();
		int count = cnt.intValue();

		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(district);
		pr.setRowCount(count);

		return pr;
	}
	
}
