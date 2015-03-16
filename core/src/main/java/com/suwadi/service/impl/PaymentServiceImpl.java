package com.suwadi.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suwadi.dao.ExpenseDAO;
import com.suwadi.dao.PaymentDAO;
import com.suwadi.domain.Expense;
import com.suwadi.domain.Payment;
import com.suwadi.service.PaymentService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
	private PaymentDAO paymentDAO;
	private ExpenseDAO expenseDAO;

	@Autowired
	public void setPaymentDAO(PaymentDAO paymentDAO) {
		this.paymentDAO = paymentDAO;
	}

	@Autowired
	public void setExpenseDAO(ExpenseDAO expenseDAO) {
		this.expenseDAO = expenseDAO;
	}

	public Payment save(Payment t) {
		return this.paymentDAO.save(t);
	}

	public Payment update(Payment t) {
		return this.paymentDAO.update(t);
	}

	public Payment findById(Long id) {
		return this.paymentDAO.findById(id);
	}

	public Payment findById(Long id, String... include) {
		return this.paymentDAO.findById(id, include);
	}

	public Payment delete(Payment t) {
		return this.paymentDAO.delete(t);
	}

	public List<Payment> findAll() {
		return this.paymentDAO.findAll();
	}

	public List<Payment> findAll(Pager pager) {
		return this.paymentDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.paymentDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.paymentDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.paymentDAO.isUnique(id, fieldName, fieldValue);
	}

	public List<Payment> findByExpenseId(Long id) {
		return this.paymentDAO.findByExpenseId(id);
	}

	@Transactional
	public void addPayment(Payment payment) {
		Expense expense = this.expenseDAO
				.findById(payment.getExpense().getId());
		this.expenseDAO.updatePayment(
				expense.getPaidAmount().add(payment.getAmount()), payment
						.getExpense().getId());
		this.save(payment);
	}

	@Transactional
	public void updatePayment(Payment payment) {
		this.expenseDAO.updatePayment(
				this.getTotalPaymentByExpenseId(payment.getExpense().getId(),
						payment.getId()).add(payment.getAmount()), payment
						.getExpense().getId());
		this.update(payment);
	}

	public BigDecimal getTotalPaymentByExpenseId(Long expenseId) {
		return this.paymentDAO.getTotalPaymentByExpenseId(expenseId);
	}

	public BigDecimal getTotalPaymentByExpenseId(Long expenseId,
			Long exceptPaymentId) {
		return this.paymentDAO.getTotalPaymentByExpenseId(expenseId,
				exceptPaymentId);
	}
}
