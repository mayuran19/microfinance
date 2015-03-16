package com.suwadi.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.LoanPaymentDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.LoanPayment;

@Repository("loanPaymentDAO")
public class LoanPaymentDAOImpl extends GenericHibernateDAOSupport<LoanPayment>
		implements LoanPaymentDAO {
	public LoanPaymentDAOImpl() {
		super(LoanPayment.class);
	}

	@SuppressWarnings("unchecked")
	public List<LoanPayment> findLoanPaymentsByLoanId(Long loanId) {
		String hqlQuery = String
				.format("select loanPayment from %s loanPayment where loanPayment.loan.id = :loanId",
						LoanPayment.class.getName());
		Query query = this.getSession().createQuery(hqlQuery)
				.setLong("loanId", loanId);
		return query.list();
	}
}
