package com.suwadi.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.EmploymentPayTypeDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.EmploymentPayType;
import com.suwadi.domain.PayType;
import com.suwadi.domain.SalaryType;

@Repository("employmentPayTypeDAO")
public class EmploymentPayTypeDAOImpl extends
		GenericHibernateDAOSupport<EmploymentPayType> implements
		EmploymentPayTypeDAO {

	public EmploymentPayTypeDAOImpl() {
		super(EmploymentPayType.class);
	}

	@SuppressWarnings("unchecked")
	public List<PayType> findPayTypeByEmploymentId(Long employmentId) {
		String hqlQuery = String.format(
				"select obj.payType from %s obj where obj.employment.id = ?",
				EmploymentPayType.class.getName());
		return getHibernateTemplate().find(hqlQuery, employmentId);
	}

	@SuppressWarnings("unchecked")
	public List<EmploymentPayType> findEmploymentPayTypeByEmploymentId(
			Long employmentId) {
		String hqlQuery = String
				.format("select obj from %s obj left join fetch obj.payType where obj.employment.id = ?",
						EmploymentPayType.class.getName());
		return getHibernateTemplate().find(hqlQuery, employmentId);
	}

	@SuppressWarnings("unchecked")
	public Map<Long, BigDecimal> getFixedPayTypeAmountMapByEmploymentId(
			Long employmentId) {
		Map<Long, BigDecimal> map = new HashMap<Long, BigDecimal>();
		String hqlQuery = String
				.format("select obj.payType.id,obj.amount from %s obj where obj.employment.id = ?",
						EmploymentPayType.class.getName());
		List<Object[]> lst = getHibernateTemplate()
				.find(hqlQuery, employmentId);
		for (Object[] obj : lst) {
			Long payTypeId = (Long) obj[0];
			BigDecimal amount = (BigDecimal) obj[1];
			if (amount != null) {
				map.put(payTypeId, amount);
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public Map<Long, SalaryType> getFixedPayTypeSalaryTypeMapByEmploymentId(
			Long employmentId) {
		Map<Long, SalaryType> map = new HashMap<Long, SalaryType>();
		String hqlQuery = String
				.format("select obj.payType.id,obj.salaryType from %s obj where obj.employment.id = ?",
						EmploymentPayType.class.getName());
		List<Object[]> lst = getHibernateTemplate()
				.find(hqlQuery, employmentId);
		for (Object[] obj : lst) {
			Long payTypeId = (Long) obj[0];
			SalaryType salaryType = (SalaryType) obj[1];
			if (salaryType != null) {
				map.put(payTypeId, salaryType);
			}
		}
		return map;
	}

}
