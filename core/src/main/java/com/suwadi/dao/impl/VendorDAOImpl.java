package com.suwadi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.VendorDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.Vendor;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Repository("vendorDAO")
public class VendorDAOImpl extends GenericHibernateDAOSupport<Vendor> implements VendorDAO{

	public VendorDAOImpl() {
		super(Vendor.class);
	}

	public PagedResultSet findAllActiveVendors(Pager pager) {
		StringBuffer sb = new StringBuffer();
		String basicQuery = String.format(
				"select obj from %s obj where status =  1",
				Vendor.class.getName());
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
		List<Vendor> vendorL = query.list();

		// calculate the total result count
		String hqlForCount = String.format(
				"select count(obj.id) from %s obj where status =  1",
				Vendor.class.getName());
		Long cnt = (Long) this.getSession().createQuery(hqlForCount)
				.uniqueResult();
		int count = cnt.intValue();

		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(vendorL);
		pr.setRowCount(count);

		return pr;
	}

}
