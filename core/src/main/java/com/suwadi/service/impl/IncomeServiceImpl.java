package com.suwadi.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suwadi.dao.CustomerDAO;
import com.suwadi.dao.IncomeDAO;
import com.suwadi.dao.IncomeDetailDAO;
import com.suwadi.dao.ProductDAO;
import com.suwadi.dao.TermOfPaymentDAO;
import com.suwadi.domain.Income;
import com.suwadi.domain.IncomeDetail;
import com.suwadi.service.IncomeService;
import com.suwadi.web.model.accounting.IncomeDetailForm;
import com.suwadi.web.model.accounting.IncomeForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("incomeService")
public class IncomeServiceImpl implements IncomeService {
	private IncomeDAO incomeDAO;
	private CustomerDAO customerDAO;
	private TermOfPaymentDAO termOfPaymentDAO;
	private IncomeDetailDAO incomeDetailDAO;
	private ProductDAO productDAO;

	@Autowired
	public void setIncomeDAO(IncomeDAO incomeDAO) {
		this.incomeDAO = incomeDAO;
	}

	@Autowired
	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Autowired
	public void setTermOfPaymentDAO(TermOfPaymentDAO termOfPaymentDAO) {
		this.termOfPaymentDAO = termOfPaymentDAO;
	}

	@Autowired
	public void setIncomeDetailDAO(IncomeDetailDAO incomeDetailDAO) {
		this.incomeDetailDAO = incomeDetailDAO;
	}

	@Autowired
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public Income save(Income t) {
		return this.incomeDAO.save(t);
	}

	public Income update(Income t) {
		return this.incomeDAO.update(t);
	}

	public Income findById(Long id) {
		return this.incomeDAO.findById(id);
	}

	public Income findById(Long id, String... include) {
		return this.incomeDAO.findById(id, include);
	}

	public Income delete(Income t) {
		return this.incomeDAO.delete(t);
	}

	public List<Income> findAll() {
		return this.incomeDAO.findAll();
	}

	public List<Income> findAll(Pager pager) {
		return this.incomeDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.incomeDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.incomeDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.incomeDAO.isUnique(id, fieldName, fieldValue);
	}

	public Income merge(Income income) {
		return this.incomeDAO.merge(income);
	}

	public PagedResultSet findAllIncomes(Pager pager) {
		return this.incomeDAO.findAllIncomes(pager);
	}

	public IncomeForm getIncomeForm(Long id) {
		return this.incomeDAO.getIncomeForm(id);
	}

	@Transactional
	public IncomeForm updateIncome(IncomeForm incomeForm) {
		Income income = this.incomeDAO.findById(incomeForm.getIncomeId());
		income.setCustomer(this.customerDAO.findById(incomeForm.getCustomerId()));
		income.setChequeNo(incomeForm.getChequeNo());
		income.setDate(incomeForm.getDate());
		income.setDueDate(incomeForm.getDueDate());
		income.setInvoiceNo(incomeForm.getInvoiceNo());
		income.setNotes(incomeForm.getNotes());
		income.setTermOfPayment(this.termOfPaymentDAO.findById(incomeForm
				.getTermOfPaymentId()));
		income.setTotal(new BigDecimal(0));
		for (IncomeDetailForm incomeDetailForm : incomeForm
				.getIncomeDetailForms()) {
			income.setTotal(income.getTotal().add(
					incomeDetailForm.getLineTotal()));
		}

		this.update(income);

		this.incomeDetailDAO.delete(this.incomeDetailDAO
				.findByIncomeId(incomeForm.getIncomeId()));

		for (IncomeDetailForm incomeDetailForm : incomeForm
				.getIncomeDetailForms()) {
			IncomeDetail incomeDetail = new IncomeDetail();
			incomeDetail.setDescription(incomeDetailForm.getDescription());
			incomeDetail.setIncome(income);
			incomeDetail.setLineTotal(incomeDetailForm.getLineTotal());
			incomeDetail.setProduct(this.productDAO.findById(incomeDetailForm
					.getProductId()));
			incomeDetail.setQuantity(incomeDetailForm.getQuantity());
			incomeDetail.setTax(new BigDecimal(0));
			incomeDetail.setUnitPrice(incomeDetailForm.getUnitPrice());
			this.incomeDetailDAO.save(incomeDetail);
		}

		return incomeForm;
	}

	@Transactional
	public IncomeForm addIncome(IncomeForm incomeForm) {
		Income income = new Income();
		income.setCustomer(this.customerDAO.findById(incomeForm.getCustomerId()));
		income.setChequeNo(incomeForm.getChequeNo());
		income.setDate(incomeForm.getDate());
		income.setDueDate(incomeForm.getDueDate());
		income.setInvoiceNo(incomeForm.getInvoiceNo());
		income.setNotes(incomeForm.getNotes());
		income.setTermOfPayment(this.termOfPaymentDAO.findById(incomeForm
				.getTermOfPaymentId()));
		income.setTotal(new BigDecimal(0));
		for (IncomeDetailForm incomeDetailForm : incomeForm
				.getIncomeDetailForms()) {
			income.setTotal(income.getTotal().add(
					incomeDetailForm.getLineTotal()));
		}
		this.incomeDAO.save(income);

		for (IncomeDetailForm incomeDetailForm : incomeForm
				.getIncomeDetailForms()) {
			IncomeDetail incomeDetail = new IncomeDetail();
			incomeDetail.setDescription(incomeDetailForm.getDescription());
			incomeDetail.setIncome(income);
			incomeDetail.setLineTotal(incomeDetailForm.getLineTotal());
			incomeDetail.setProduct(this.productDAO.findById(incomeDetailForm
					.getProductId()));
			incomeDetail.setQuantity(incomeDetailForm.getQuantity());
			incomeDetail.setTax(new BigDecimal(0));
			incomeDetail.setUnitPrice(incomeDetailForm.getUnitPrice());
			this.incomeDetailDAO.save(incomeDetail);
		}

		return incomeForm;
	}

}
