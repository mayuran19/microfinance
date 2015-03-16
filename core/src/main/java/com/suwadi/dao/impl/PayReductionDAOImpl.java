package com.suwadi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.PayReductionDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.PayReduction;

@Repository("payReductionDAO")
public class PayReductionDAOImpl extends
		GenericHibernateDAOSupport<PayReduction> implements PayReductionDAO {

	public PayReductionDAOImpl() {
		super(PayReduction.class);
	}

	@SuppressWarnings("unchecked")
	public List<PayReduction> findAllFixedPayReductions() {
		String hqlQuery = String.format(
				"select obj from %s obj where obj.isFixed = true",
				PayReduction.class.getName());
		return this.getHibernateTemplate().find(hqlQuery);
	}
}
