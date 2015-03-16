package com.suwadi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.PayTaxDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.PayTax;

@Repository("payTaxDAO")
public class PayTaxDAOImpl extends GenericHibernateDAOSupport<PayTax> implements
		PayTaxDAO {
	public PayTaxDAOImpl() {
		super(PayTax.class);
	}

	@SuppressWarnings("unchecked")
	public List<PayTax> findAllFixedPayTaxes() {
		String hqlQuery = String.format(
				"select obj from %s obj where obj.isFixedPay = true",
				PayTax.class.getName());
		return getHibernateTemplate().find(hqlQuery);
	}
}
