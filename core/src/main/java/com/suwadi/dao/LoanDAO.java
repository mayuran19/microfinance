package com.suwadi.dao;

import java.math.BigDecimal;

import com.suwadi.domain.Loan;

public interface LoanDAO extends GenericDAO<Loan> {
	public BigDecimal getTotalPayments(Long loanId);
}
