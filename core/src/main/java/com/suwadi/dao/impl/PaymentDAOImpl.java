package com.suwadi.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.suwadi.dao.PaymentDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.Payment;

@Repository("paymentDAO")
public class PaymentDAOImpl extends GenericHibernateDAOSupport<Payment>
		implements PaymentDAO {

	public PaymentDAOImpl() {
		super(Payment.class);
	}

	@SuppressWarnings("unchecked")
	public List<Payment> findByExpenseId(Long id) {
		String hqlQuery = String
				.format("select payment from %s as payment where payment.expense.id = :expenseId",
						Payment.class.getName());
		Query query = this.getSession().createQuery(hqlQuery)
				.setLong("expenseId", id);

		return query.list();
	}

	public BigDecimal getTotalPaymentByExpenseId(Long expenseId) {
		String hqlQuery = String
				.format("select sum(payment.amount) from %s payment where payment.expense.id = :expenseId",
						Payment.class.getName());
		BigDecimal bigDecimal = (BigDecimal) this.getSession()
				.createQuery(hqlQuery).setLong("expenseId", expenseId)
				.uniqueResult();
		return bigDecimal;
	}

	public BigDecimal getTotalPaymentByExpenseId(Long expenseId,
			Long exceptPaymentId) {
		String hqlQuery = String
				.format("select sum(payment.amount) from %s payment where payment.expense.id = :expenseId and payment.id <> :exceptPaymentId",
						Payment.class.getName());
		BigDecimal bigDecimal = (BigDecimal) this.getSession()
				.createQuery(hqlQuery).setLong("expenseId", expenseId)
				.setLong("exceptPaymentId", exceptPaymentId).uniqueResult();
		return bigDecimal;
	}
}
