package com.suwadi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.GroupDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.Group;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Repository("groupDAO")
public class GroupDAOImpl extends GenericHibernateDAOSupport<Group> implements
		GroupDAO {
	public GroupDAOImpl() {
		super(Group.class);
	}

	public PagedResultSet findAllBySocietyId(Long societyId, Pager pager) {
		StringBuffer sb = new StringBuffer();
		String basicQuery = String.format(
				"select obj from %s obj where obj.society.id = %d ",
				Group.class.getName(), societyId);
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
		List<Group> groups = query.list();

		// calculate the total result count
		String hqlForCount = String.format(
				"select count(obj.id) from %s obj where obj.society.id = %d",
				Group.class.getName(), societyId);
		Long cnt = (Long) this.getSession().createQuery(hqlForCount)
				.uniqueResult();
		int count = cnt.intValue();

		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(groups);
		pr.setRowCount(count);

		return pr;
	}

	public List<Group> findAllBySocietyId(Long societyId) {
		StringBuffer sb = new StringBuffer();
		String basicQuery = String
				.format("select obj from %s obj where obj.society.id = %d order by obj.name",
						Group.class.getName(), societyId);
		sb.append(basicQuery);
		Query query = this.getSession().createQuery(sb.toString());
		List<Group> groups = query.list();

		return groups;
	}

}
