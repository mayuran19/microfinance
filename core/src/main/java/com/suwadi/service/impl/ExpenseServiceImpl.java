package com.suwadi.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suwadi.dao.ExpenseDAO;
import com.suwadi.domain.Expense;
import com.suwadi.domain.ExpenseDetail;
import com.suwadi.service.ExpenseAccountService;
import com.suwadi.service.ExpenseDetailService;
import com.suwadi.service.ExpenseService;
import com.suwadi.service.ProductService;
import com.suwadi.service.UploadDocumentService;
import com.suwadi.service.VendorService;
import com.suwadi.web.model.accounting.ExpenseDetailForm;
import com.suwadi.web.model.accounting.ExpenseForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("expenseService")
public class ExpenseServiceImpl implements ExpenseService {
	private ExpenseDAO expenseDAO;
	private ExpenseDetailService expenseDetailService;
	private VendorService vendorService;
	private ProductService productService;
	private ExpenseAccountService expenseAccountService;
	private UploadDocumentService uploadDocumentService;

	@Autowired
	public void setExpenseDAO(ExpenseDAO expenseDAO) {
		this.expenseDAO = expenseDAO;
	}

	@Autowired
	public void setExpenseDetailService(
			ExpenseDetailService expenseDetailService) {
		this.expenseDetailService = expenseDetailService;
	}

	@Autowired
	public void setVendorService(VendorService vendorService) {
		this.vendorService = vendorService;
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	public void setExpenseAccountService(
			ExpenseAccountService expenseAccountService) {
		this.expenseAccountService = expenseAccountService;
	}

	@Autowired
	public void setUploadDocumentService(
			UploadDocumentService uploadDocumentService) {
		this.uploadDocumentService = uploadDocumentService;
	}

	public Expense save(Expense t) {
		return this.expenseDAO.save(t);
	}

	public Expense update(Expense t) {
		return this.expenseDAO.update(t);
	}

	public Expense findById(Long id) {
		return this.expenseDAO.findById(id);
	}

	public Expense delete(Expense t) {
		return this.expenseDAO.delete(t);
	}

	public List<Expense> findAll() {
		return this.expenseDAO.findAll();
	}

	public List<Expense> findAll(Pager pager) {
		return this.expenseDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.expenseDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.expenseDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.expenseDAO.isUnique(id, fieldName, fieldValue);
	}

	public Expense findById(Long id, String... include) {
		return this.expenseDAO.findById(id, include);
	}

	public Expense merge(Expense expense) {
		return this.expenseDAO.merge(expense);
	}

	public PagedResultSet findAllExpenses(Pager pager) {
		return this.expenseDAO.findAllExpenses(pager);
	}

	public ExpenseForm getExpenseForm(Long id) {
		return this.expenseDAO.getExpenseForm(id);
	}

	@Transactional
	public ExpenseForm updateExpense(ExpenseForm expenseForm) {
		Expense expense = this.expenseDAO.findById(expenseForm.getId());
		expense.setDate(expenseForm.getDate());
		expense.setChequeNo(expenseForm.getChequeNo());
		expense.setDueDate(expenseForm.getDueDate());
		expense.setInvoiceNumber(expenseForm.getInvoiceNumber());
		expense.setNotes(expenseForm.getNotes());
		expense.setPurchaseOrder(expenseForm.getPurchaseOrder());
		expense.setTotal(new BigDecimal(0));
		for (ExpenseDetailForm expenseDetailForm : expenseForm
				.getExpenseDetails()) {
			expense.setTotal(expense.getTotal().add(
					expenseDetailForm.getTotal()));
		}
		expense.setVendor(this.vendorService.findById(expenseForm.getVendorId()));
		this.update(expense);

		this.expenseDetailService.delete(this.expenseDetailService
				.findByExpenseId(expense.getId()));

		for (ExpenseDetailForm expenseDetailForm : expenseForm
				.getExpenseDetails()) {
			ExpenseDetail expenseDetail = new ExpenseDetail();
			expenseDetail.setDescription(expenseDetailForm.getDescription());
			expenseDetail.setExpense(expense);
			expenseDetail.setExpenseAccount(this.expenseAccountService
					.findById(expenseDetailForm.getExpenseAccountId()));
			expenseDetail.setProduct(this.productService
					.findById(expenseDetailForm.getProductId()));
			expenseDetail.setQuantity(expenseDetailForm.getQuantity());
			expenseDetail.setTotal(expenseDetailForm.getTotal());
			expenseDetail.setUnitPrice(expenseDetailForm.getUnitPrice());
			this.expenseDetailService.save(expenseDetail);
		}

		return expenseForm;
	}

	@Transactional
	public ExpenseForm addExpense(ExpenseForm expenseForm) {
		Expense expense = new Expense();
		expense.setDate(expenseForm.getDate());
		expense.setChequeNo(expenseForm.getChequeNo());
		expense.setDueDate(expenseForm.getDueDate());
		expense.setInvoiceNumber(expenseForm.getInvoiceNumber());
		expense.setNotes(expenseForm.getNotes());
		expense.setPurchaseOrder(expenseForm.getPurchaseOrder());
		expense.setVendor(this.vendorService.findById(expenseForm.getVendorId()));
		expense.setTotal(new BigDecimal(0));
		for (ExpenseDetailForm expenseDetailForm : expenseForm
				.getExpenseDetails()) {
			expense.setTotal(expense.getTotal().add(
					expenseDetailForm.getTotal()));
		}
		this.expenseDAO.save(expense);

		for (ExpenseDetailForm expenseDetailForm : expenseForm
				.getExpenseDetails()) {
			ExpenseDetail expenseDetail = new ExpenseDetail();
			expenseDetail.setDescription(expenseDetailForm.getDescription());
			expenseDetail.setExpense(expense);
			expenseDetail.setExpenseAccount(this.expenseAccountService
					.findById(expenseDetailForm.getExpenseAccountId()));
			expenseDetail.setProduct(this.productService
					.findById(expenseDetailForm.getProductId()));
			expenseDetail.setQuantity(expenseDetailForm.getQuantity());
			expenseDetail.setTotal(expenseDetailForm.getTotal());
			expenseDetail.setUnitPrice(expenseDetailForm.getUnitPrice());
			this.expenseDetailService.save(expenseDetail);
		}

		return expenseForm;
	}

	public PagedResultSet getExpenseReportDisplayPaginated(Pager pager) {
		return this.expenseDAO.getExpenseReportDisplayPaginated(pager);
	}
}
