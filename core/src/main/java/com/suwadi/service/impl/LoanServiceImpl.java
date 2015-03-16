package com.suwadi.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.LoanDAO;
import com.suwadi.dao.LoanRecoveryMethodDAO;
import com.suwadi.domain.Beneficiary;
import com.suwadi.domain.Loan;
import com.suwadi.domain.LoanRecoveryMethod;
import com.suwadi.service.BeneficiaryService;
import com.suwadi.service.LoanService;
import com.suwadi.web.model.microfinance.LoanForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("loanService")
public class LoanServiceImpl implements LoanService {
	private LoanDAO loanDAO;
	private BeneficiaryService beneficiaryService;
	private LoanRecoveryMethodDAO loanRecoveryMethodDAO;

	@Autowired
	public void setLoanDAO(LoanDAO loanDAO) {
		this.loanDAO = loanDAO;
	}

	@Autowired
	public void setBeneficiaryService(BeneficiaryService beneficiaryService) {
		this.beneficiaryService = beneficiaryService;
	}

	@Autowired
	public void setLoanRecoveryMethodDAO(
			LoanRecoveryMethodDAO loanRecoveryMethodDAO) {
		this.loanRecoveryMethodDAO = loanRecoveryMethodDAO;
	}

	public Loan save(Loan t) {
		return this.loanDAO.save(t);
	}

	public Loan update(Loan t) {
		return this.loanDAO.update(t);
	}

	public Loan findById(Long id) {
		return this.loanDAO.findById(id);
	}

	public Loan findById(Long id, String... include) {
		return this.loanDAO.findById(id, include);
	}

	public Loan delete(Loan t) {
		return this.loanDAO.delete(t);
	}

	public List<Loan> findAll() {
		return this.loanDAO.findAll();
	}

	public List<Loan> findAll(Pager pager) {
		return this.loanDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.loanDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.loanDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.loanDAO.isUnique(id, fieldName, fieldValue);
	}

	public Loan save(LoanForm loanForm) {
		Loan loan = new Loan();
		Beneficiary beneficiary = this.beneficiaryService.findById(loanForm
				.getBeneficiaryId());
		LoanRecoveryMethod recoveryMethod = this.loanRecoveryMethodDAO
				.findById(loanForm.getLoanRecoveryMethodId());
		loan.setBeneficiary(beneficiary);
		loan.setLoanRecoveryMethod(recoveryMethod);
		loan.setLoanAmount(loanForm.getLoanAmount());
		loan.setDueAmount(loanForm.getLoanAmount());
		loan.setLoanEndDate(loanForm.getLoanEndDate());
		loan.setLoanName(loanForm.getLoanName());
		loan.setLoanPurpose(loanForm.getLoanPurpose());
		loan.setLoanStartDate(loanForm.getLoanStartDate());
		loan.setRemarks(loanForm.getRemarks());
		loan.setGracePeriod(loanForm.getGracePeriod());
		loan.setLoanPeriod(loanForm.getLoanPeriod());
		loan.setInterestRate(loanForm.getInterestRate());
		this.save(loan);
		return loan;
	}

	public LoanForm getLoanForm(Long loanId) {
		LoanForm loanForm = new LoanForm();
		Loan loan = this.findById(loanId, "beneficiary", "loanRecoveryMethod");
		loanForm.setBeneficiaryId(loan.getBeneficiary().getId());
		loanForm.setLoanRecoveryMethodId(loan.getLoanRecoveryMethod() != null ? loan
				.getLoanRecoveryMethod().getId() : 0);
		loanForm.setDueAmount(loan.getDueAmount());
		loanForm.setId(loan.getId());
		loanForm.setLoanAmount(loan.getLoanAmount());
		loanForm.setLoanEndDate(loan.getLoanEndDate());
		loanForm.setLoanName(loan.getLoanName());
		loanForm.setLoanPurpose(loan.getLoanPurpose());
		loanForm.setLoanStartDate(loan.getLoanStartDate());
		loanForm.setPaidAmount(loan.getPaidAmount());
		loanForm.setRemarks(loan.getRemarks());
		loanForm.setGracePeriod(loan.getGracePeriod());
		loanForm.setLoanPeriod(loan.getLoanPeriod());
		loanForm.setInterestRate(loan.getInterestRate());
		return loanForm;
	}

	public Loan update(LoanForm loanForm) {
		Loan loan = this.findById(loanForm.getId());
		Beneficiary beneficiary = this.beneficiaryService.findById(loanForm
				.getBeneficiaryId());
		LoanRecoveryMethod recoveryMethod = this.loanRecoveryMethodDAO
				.findById(loanForm.getLoanRecoveryMethodId());
		loan.setLoanRecoveryMethod(recoveryMethod);
		loan.setBeneficiary(beneficiary);
		loan.setLoanAmount(loanForm.getLoanAmount());
		loan.setDueAmount(loanForm.getLoanAmount().subtract(
				this.getTotalPayments(loanForm.getId())));
		loan.setLoanEndDate(loanForm.getLoanEndDate());
		loan.setLoanName(loanForm.getLoanName());
		loan.setLoanPurpose(loanForm.getLoanPurpose());
		loan.setLoanStartDate(loanForm.getLoanStartDate());
		loan.setRemarks(loanForm.getRemarks());
		loan.setGracePeriod(loanForm.getGracePeriod());
		loan.setLoanPeriod(loanForm.getLoanPeriod());
		loan.setInterestRate(loanForm.getInterestRate());
		this.update(loan);
		return loan;
	}

	public BigDecimal getTotalPayments(Long loanId) {
		return this.loanDAO.getTotalPayments(loanId);
	}

}
