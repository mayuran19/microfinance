package com.suwadi.service;

import java.math.BigDecimal;
import java.util.List;

import com.suwadi.domain.Payment;

public interface PaymentService extends GenericService<Payment> {
	public List<Payment> findByExpenseId(Long id);

	public void addPayment(Payment payment);

	public BigDecimal getTotalPaymentByExpenseId(Long expenseId);

	public BigDecimal getTotalPaymentByExpenseId(Long expenseId,
			Long exceptPaymentId);

	public void updatePayment(Payment payment);
}
