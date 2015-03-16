package com.suwadi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.PayTypeDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.PayType;

@Repository("payTypeDAO")
public class PayTypeDAOImpl extends GenericHibernateDAOSupport<PayType>
		implements PayTypeDAO {

	public PayTypeDAOImpl() {
		super(PayType.class);
	}

	@SuppressWarnings("unchecked")
	public List<PayType> findAllFixedPayTypes() {
		String hqlQuery = String
				.format("select obj from %s obj where obj.isFixedPay = true order by obj.displayOrder asc",
						PayType.class.getName());

		return this.getHibernateTemplate().find(hqlQuery);
	}
}
