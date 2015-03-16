package com.suwadi.service;

import java.util.List;

import com.suwadi.domain.LoanPayment;
import com.suwadi.web.model.microfinance.LoanPaymentForm;

public interface LoanPaymentService extends GenericService<LoanPayment> {
	public List<LoanPayment> findLoanPaymentsByLoanId(Long loanId);

	public LoanPayment save(LoanPaymentForm loanPaymentForm);

	public LoanPayment update(LoanPaymentForm loanPaymentForm);

	public LoanPaymentForm getLoanPaymentForm(Long loanPaymentId);
}
