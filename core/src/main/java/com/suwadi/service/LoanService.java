package com.suwadi.service;

import java.math.BigDecimal;

import com.suwadi.domain.Loan;
import com.suwadi.web.model.microfinance.LoanForm;

public interface LoanService extends GenericService<Loan> {
	public Loan save(LoanForm loanForm);

	public LoanForm getLoanForm(Long loanId);

	public Loan update(LoanForm loanForm);

	public BigDecimal getTotalPayments(Long loanId);
}
