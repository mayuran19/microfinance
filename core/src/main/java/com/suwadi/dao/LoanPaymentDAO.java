package com.suwadi.dao;

import java.util.List;

import com.suwadi.domain.LoanPayment;

public interface LoanPaymentDAO extends GenericDAO<LoanPayment> {
	public List<LoanPayment> findLoanPaymentsByLoanId(Long loanId);
}
