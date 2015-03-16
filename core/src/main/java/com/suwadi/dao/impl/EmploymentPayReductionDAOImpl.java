package com.suwadi.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.EmploymentPayReductionDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.EmploymentPayReduction;
import com.suwadi.domain.SalaryType;

@Repository("employmentPayReductionDAO")
public class EmploymentPayReductionDAOImpl extends
		GenericHibernateDAOSupport<EmploymentPayReduction> implements
		EmploymentPayReductionDAO {

	public EmploymentPayReductionDAOImpl() {
		super(EmploymentPayReduction.class);
	}

	@SuppressWarnings("unchecked")
	public Map<Long, BigDecimal> getFixedPayReductionAmountMapByEmploymentId(
			Long employmentId) {
		Map<Long, BigDecimal> map = new HashMap<Long, BigDecimal>();
		String hqlQuery = String
				.format("select obj.payReduction.id,obj.amount from %s obj where obj.employment.id = ?",
						EmploymentPayReduction.class.getName());
		List<Object[]> lst = getHibernateTemplate()
				.find(hqlQuery, employmentId);
		for (Object[] obj : lst) {
			Long payReductionId = (Long) obj[0];
			BigDecimal amount = (BigDecimal) obj[1];
			if (amount != null) {
				map.put(payReductionId, amount);
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public Map<Long, SalaryType> getFixedPayReductionSalaryTypeMapByEmploymentId(
			Long employmentId) {
		Map<Long, SalaryType> map = new HashMap<Long, SalaryType>();
		String hqlQuery = String
				.format("select obj.payReduction.id,obj.salaryType from %s obj where obj.employment.id = ?",
						EmploymentPayReduction.class.getName());
		List<Object[]> lst = getHibernateTemplate()
				.find(hqlQuery, employmentId);
		for (Object[] obj : lst) {
			Long payReductionId = (Long) obj[0];
			SalaryType salaryType = (SalaryType) obj[1];
			if (salaryType != null) {
				map.put(payReductionId, salaryType);
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public List<EmploymentPayReduction> findEmploymentPayReductionsByEmploymentId(
			Long employmentId) {
		String hqlQuery = String
				.format("select obj from %s obj left join fetch obj.payReduction where obj.employment.id = ?",
						EmploymentPayReduction.class.getName());
		return getHibernateTemplate().find(hqlQuery, employmentId);
	}

}
