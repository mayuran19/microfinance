package com.suwadi.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.EmploymentPayTaxDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.EmploymentPayTax;

@Repository("employmentPayTaxDAO")
public class EmploymentPayTaxDAOImpl extends
		GenericHibernateDAOSupport<EmploymentPayTax> implements
		EmploymentPayTaxDAO {

	public EmploymentPayTaxDAOImpl() {
		super(EmploymentPayTax.class);
	}

	@SuppressWarnings("unchecked")
	public Map<Long, BigDecimal> getFixedPayTaxAmountMapByEmploymentId(
			Long employmentId) {
		Map<Long, BigDecimal> map = new HashMap<Long, BigDecimal>();
		String hqlQuery = String
				.format("select obj.payTax.id,obj.amount from %s obj where obj.employment.id = ?",
						EmploymentPayTax.class.getName());
		List<Object[]> lst = getHibernateTemplate()
				.find(hqlQuery, employmentId);
		for (Object[] obj : lst) {
			Long payTaxId = (Long) obj[0];
			BigDecimal amount = (BigDecimal) obj[1];
			if (amount != null) {
				map.put(payTaxId, amount);
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public List<EmploymentPayTax> findEmploymentPayTaxesByEmploymentId(
			Long employmentId) {
		String hqlQuery = String
				.format("select obj from %s obj left join fetch obj.payTax where obj.employment.id = ?",
						EmploymentPayTax.class.getName());
		return getHibernateTemplate().find(hqlQuery, employmentId);
	}

}
