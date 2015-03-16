package com.suwadi.dao.impl;

import java.math.BigDecimal;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.LoanDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.Loan;
import com.suwadi.domain.LoanPayment;

@Repository("loanDAO")
public class LoanDAOImpl extends GenericHibernateDAOSupport<Loan> implements
		LoanDAO {
	public LoanDAOImpl() {
		super(Loan.class);
	}

	public BigDecimal getTotalPayments(Long loanId) {
		String hqlQuery = String
				.format("select sum(obj.amount) from %s obj where obj.loan.id = :loanId group by obj.loan.id",
						LoanPayment.class.getName());
		Query query = this.getSession().createQuery(hqlQuery)
				.setLong("loanId", loanId);
		Object obj = query.uniqueResult();
		if (obj != null) {
			return (BigDecimal) obj;
		} else {
			return new BigDecimal(0);
		}
	}
}
