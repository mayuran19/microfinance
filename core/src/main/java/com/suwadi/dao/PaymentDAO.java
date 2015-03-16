package com.suwadi.dao;

import java.math.BigDecimal;
import java.util.List;

import com.suwadi.domain.Payment;

public interface PaymentDAO extends GenericDAO<Payment> {
	public List<Payment> findByExpenseId(Long id);

	public BigDecimal getTotalPaymentByExpenseId(Long expenseId);
	
	public BigDecimal getTotalPaymentByExpenseId(Long expenseId,
			Long exceptPaymentId);
}
