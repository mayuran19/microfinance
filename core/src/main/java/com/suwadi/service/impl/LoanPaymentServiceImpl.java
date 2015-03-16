package com.suwadi.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suwadi.dao.EmployeeDAO;
import com.suwadi.dao.LoanDAO;
import com.suwadi.dao.LoanPaymentDAO;
import com.suwadi.dao.UserDAO;
import com.suwadi.domain.Employee;
import com.suwadi.domain.Loan;
import com.suwadi.domain.LoanPayment;
import com.suwadi.service.LoanPaymentService;
import com.suwadi.web.model.microfinance.LoanPaymentForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("loanPaymentService")
public class LoanPaymentServiceImpl implements LoanPaymentService {
	private LoanPaymentDAO loanPaymentDAO;
	private LoanDAO loanDAO;
	private UserDAO userDAO;
	private EmployeeDAO employeeDAO;

	@Autowired
	public void setLoanPaymentDAO(LoanPaymentDAO loanPaymentDAO) {
		this.loanPaymentDAO = loanPaymentDAO;
	}

	@Autowired
	public void setLoanDAO(LoanDAO loanDAO) {
		this.loanDAO = loanDAO;
	}

	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Autowired
	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public LoanPayment save(LoanPayment t) {
		return this.loanPaymentDAO.save(t);
	}

	public LoanPayment update(LoanPayment t) {
		return this.loanPaymentDAO.update(t);
	}

	public LoanPayment findById(Long id) {
		return this.loanPaymentDAO.findById(id);
	}

	public LoanPayment findById(Long id, String... include) {
		return this.loanPaymentDAO.findById(id, include);
	}

	public LoanPayment delete(LoanPayment t) {
		return this.loanPaymentDAO.delete(t);
	}

	public List<LoanPayment> findAll() {
		return this.loanPaymentDAO.findAll();
	}

	public List<LoanPayment> findAll(Pager pager) {
		return this.loanPaymentDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.paginate(pager);
	}

	public Integer getAllCount() {
		return this.loanPaymentDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.loanPaymentDAO.isUnique(id, fieldName, fieldValue);
	}

	public List<LoanPayment> findLoanPaymentsByLoanId(Long loanId) {
		return this.loanPaymentDAO.findLoanPaymentsByLoanId(loanId);
	}

	@Transactional
	public LoanPayment save(LoanPaymentForm loanPaymentForm) {
		Loan loan = this.loanDAO.findById(loanPaymentForm.getLoanId());
		// User collectedBy = this.userDAO.findById(loanPaymentForm
		// .getCollectedById());
		Employee employee = this.employeeDAO.findById(loanPaymentForm
				.getCollectedById());
		LoanPayment loanPayment = new LoanPayment();
		loanPayment.setAmount(loanPaymentForm.getAmount());
		// loanPayment.setCollectedBy(collectedBy);
		loanPayment.setEmployee(employee);
		loanPayment.setLoan(loan);
		loanPayment.setRemarks(loanPaymentForm.getRemarks());
		loanPayment.setPaymentDate(loanPaymentForm.getPaymentDate());
		this.save(loanPayment);

		loan.setPaidAmount(loan.getPaidAmount().add(loanPayment.getAmount()));
		loan.setDueAmount(loan.getDueAmount().subtract(
				loanPaymentForm.getAmount()));
		this.loanDAO.update(loan);

		return loanPayment;
	}

	@Transactional
	public LoanPayment update(LoanPaymentForm loanPaymentForm) {
		Loan loan = this.loanDAO.findById(loanPaymentForm.getLoanId());
		BigDecimal oldTotalPaidAmount = this.loanDAO.getTotalPayments(loan
				.getId());
		// User collectedBy = this.userDAO.findById(loanPaymentForm
		// .getCollectedById());
		Employee employee = this.employeeDAO.findById(loanPaymentForm
				.getCollectedById());
		LoanPayment loanPayment = this.findById(loanPaymentForm.getId());
		BigDecimal oldPaidAmount = loanPayment.getAmount();
		loanPayment.setAmount(loanPaymentForm.getAmount());
		// loanPayment.setCollectedBy(collectedBy);
		loanPayment.setEmployee(employee);
		loanPayment.setLoan(loan);
		loanPayment.setRemarks(loanPaymentForm.getRemarks());
		loanPayment.setPaymentDate(loanPaymentForm.getPaymentDate());
		this.update(loanPayment);

		loan.setPaidAmount(oldTotalPaidAmount.subtract(oldPaidAmount).add(
				loanPaymentForm.getAmount()));
		loan.setDueAmount(loan.getLoanAmount().subtract(loan.getPaidAmount()));
		this.loanDAO.update(loan);

		return loanPayment;
	}

	public LoanPaymentForm getLoanPaymentForm(Long loanPaymentId) {
		LoanPayment loanPayment = this.findById(loanPaymentId, "collectedBy",
				"loan");
		LoanPaymentForm loanPaymentForm = new LoanPaymentForm();
		loanPaymentForm.setAmount(loanPayment.getAmount());
		loanPaymentForm.setCollectedById(loanPayment.getEmployee().getId());
		loanPaymentForm.setId(loanPayment.getId());
		loanPaymentForm.setPaymentDate(loanPayment.getPaymentDate());
		loanPaymentForm.setLoanId(loanPayment.getLoan().getId());
		loanPaymentForm.setRemarks(loanPayment.getRemarks());

		return loanPaymentForm;
	}

}
