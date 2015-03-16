package com.suwadi.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.BeneficiaryDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.Beneficiary;
import com.suwadi.web.model.BeneficiarySearch;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Repository("beneficiaryDAO")
public class BeneficiaryDAOImpl extends GenericHibernateDAOSupport<Beneficiary>
		implements BeneficiaryDAO {
	public BeneficiaryDAOImpl() {
		super(Beneficiary.class);
	}

	public PagedResultSet findAllBeneficiaries(Pager pager) {
		StringBuffer sb = new StringBuffer();
		String basicQuery = String
				.format("select distinct obj from %s obj where obj.beneficiaryType = ?",
						Beneficiary.class.getName());
		sb.append(basicQuery);
		if (pager.getSortOrder() == "asc") {
			sb.append("order by").append(" ").append(pager.getSortColumn())
					.append(" ").append("asc").append(" ");
		} else {
			sb.append("order by").append(" ").append(pager.getSortColumn())
					.append(" ").append("desc").append(" ");
		}
		Query query = this.getSession().createQuery(sb.toString())
				.setInteger(0, 5)
				.setFirstResult((pager.getPage() - 1) * pager.getPageSize())
				.setMaxResults(pager.getPageSize());
		List<Beneficiary> list = query.list();

		String countQuery = String
				.format("select count(distinct obj) from %s obj where obj.beneficiaryType = ?",
						Beneficiary.class.getName());
		Long cnt = (Long) this.getSession().createQuery(countQuery)
				.setInteger(0, 5).uniqueResult();
		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(list);
		pr.setRowCount(cnt.intValue());
		return pr;
	}

	public PagedResultSet findByBenificiarySearchCriteria(
			BeneficiarySearch beneficiarySearch, Pager pager) {

		String searchSQL = " select b.*,b.group_id from beneficiaries b "
				+ " left join groups g on b.group_id = g.id "
				+ " left join societies so on g.society_id = so.id "
				+ " left join gn_divisions gnd on so.gn_division_id = gnd.id "
				+ " left join divisions dv on gnd.division_id = dv.id "
				+ " left join districts dis on dv.district_id = dis.id "
				+ " where b.beneficiary_type = 5 ";

		String countSql = " select count(b.id) from beneficiaries b "
				+ " left join groups g on b.group_id = g.id "
				+ " left join societies so on g.society_id = so.id "
				+ " left join gn_divisions gnd on so.gn_division_id = gnd.id "
				+ " left join divisions dv on gnd.division_id = dv.id "
				+ " left join districts dis on dv.district_id = dis.id "
				+ " where b.beneficiary_type = 5 ";

		if (beneficiarySearch.getFirstName() != null
				&& !beneficiarySearch.getFirstName().isEmpty()) {
			searchSQL += " and b.first_name like " + "'%"
					+ beneficiarySearch.getFirstName() + "%'";
			countSql += " and b.first_name like " + "'%"
					+ beneficiarySearch.getFirstName() + "%'";
		}

		if (beneficiarySearch.getLastName() != null
				&& !beneficiarySearch.getLastName().isEmpty()) {
			searchSQL += " and b.last_name like " + "'%"
					+ beneficiarySearch.getLastName() + "%'";
			countSql += " and b.last_name like " + "'%"
					+ beneficiarySearch.getLastName() + "%'";
		}

		if (beneficiarySearch.getNicNo() != null
				&& !beneficiarySearch.getNicNo().isEmpty()) {
			searchSQL += " and b.nic_no like " + "'%"
					+ beneficiarySearch.getNicNo() + "%'";
			countSql += " and b.nic_no like " + "'%"
					+ beneficiarySearch.getNicNo() + "%'";
		}
		if (beneficiarySearch.getMemberNo() != null
				&& !beneficiarySearch.getMemberNo().isEmpty()) {
			searchSQL += " and b.member_no like " + "'%"
					+ beneficiarySearch.getMemberNo() + "%'";
			countSql += " and b.member_no like " + "'%"
					+ beneficiarySearch.getMemberNo() + "%'";
		}
		if (beneficiarySearch.getGroup() != null) {
			searchSQL += " and g.id = " + beneficiarySearch.getGroup().getId();
			countSql += " and g.id = " + beneficiarySearch.getGroup().getId();
		}
		if (beneficiarySearch.getSociety() != null) {
			searchSQL += " and so.id = "
					+ beneficiarySearch.getSociety().getId();
			countSql += " and so.id = "
					+ beneficiarySearch.getSociety().getId();
		}
		if (beneficiarySearch.getGnDivision() != null) {
			searchSQL += " and gnd.id = "
					+ beneficiarySearch.getGnDivision().getId();
			countSql += " and gnd.id = "
					+ beneficiarySearch.getGnDivision().getId();
		}
		if (beneficiarySearch.getDivision() != null) {
			searchSQL += " and dv.id = "
					+ beneficiarySearch.getDivision().getId();
			countSql += " and dv.id = "
					+ beneficiarySearch.getDivision().getId();
		}
		if (beneficiarySearch.getDistrict() != null) {
			searchSQL += " and dis.id = "
					+ beneficiarySearch.getDistrict().getId();
			countSql += " and dis.id = "
					+ beneficiarySearch.getDistrict().getId();
		}

		Query query = this.getSession().createSQLQuery(searchSQL)
				.addEntity("beneficiary", Beneficiary.class);
		query.setFirstResult((pager.getPage() - 1) * pager.getPageSize())
				.setMaxResults(pager.getPageSize());
		List<Beneficiary> beneficiaries = query.list();
		BigInteger cnt = (BigInteger) this.getSession()
				.createSQLQuery(countSql).uniqueResult();
		int count = cnt.intValue();
		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(beneficiaries);
		pr.setRowCount(count);
		return pr;

	}

	public PagedResultSet findAllByGroupId(Long groupId, Pager pager) {
		StringBuffer sb = new StringBuffer();
		String basicQuery = String
				.format("select obj from %s obj where obj.group.id = %d  and obj.beneficiaryType = 5 ",
						Beneficiary.class.getName(), groupId);
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
		List<Beneficiary> beneficiaries = query.list();

		// calculate the total result count
		String hqlForCount = String
				.format("select count(obj.id) from %s obj where obj.group.id = %d and obj.beneficiaryType = 5 ",
						Beneficiary.class.getName(), groupId);
		Long cnt = (Long) this.getSession().createQuery(hqlForCount)
				.uniqueResult();
		int count = cnt.intValue();

		PagedResultSet pr = new PagedResultSet();
		pr.setPager(pager);
		pr.setResultSet(beneficiaries);
		pr.setRowCount(count);

		return pr;
	}
}
