package com.suwadi.web.controller.accounting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suwadi.domain.Expense;
import com.suwadi.domain.ExpenseDetail;
import com.suwadi.domain.Payment;
import com.suwadi.service.ExpenseAccountService;
import com.suwadi.service.ExpenseDetailService;
import com.suwadi.service.ExpenseService;
import com.suwadi.service.PaymentService;
import com.suwadi.service.ProductService;
import com.suwadi.service.UploadDocumentService;
import com.suwadi.service.VendorService;
import com.suwadi.web.model.accounting.ExpenseDetailForm;
import com.suwadi.web.model.accounting.ExpenseForm;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.validator.ExpenseValidator;

@Controller
@RequestMapping(value = "/accounting/expenses")
public class ExpensesController {
	private ExpenseService expenseService;
	private VendorService vendorService;
	private ExpenseAccountService expenseAccountService;
	private ProductService productService;
	private ExpenseValidator expenseValidator;
	private PaymentService paymentService;
	private UploadDocumentService uploadDocumentService;
	private ExpenseDetailService expenseDetailService;

	@Autowired
	public void setExpenseService(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	@Autowired
	public void setVendorService(VendorService vendorService) {
		this.vendorService = vendorService;
	}

	@Autowired
	public void setExpenseAccountService(
			ExpenseAccountService expenseAccountService) {
		this.expenseAccountService = expenseAccountService;
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	public void setExpenseValidator(ExpenseValidator expenseValidator) {
		this.expenseValidator = expenseValidator;
	}

	@Autowired
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@Autowired
	public void setUploadDocumentService(
			UploadDocumentService uploadDocumentService) {
		this.uploadDocumentService = uploadDocumentService;
	}

	@Autowired
	public void setExpenseDetailService(
			ExpenseDetailService expenseDetailService) {
		this.expenseDetailService = expenseDetailService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String index(Model model, Pager pager) {
		// this.expenseService.findAllExpenses(pager);
		model.addAttribute("pager", this.expenseService.findAllExpenses(pager));
		return "/accounting/expenses/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		ExpenseForm expenseForm = new ExpenseForm();
		ExpenseDetailForm expenseDetailForm = new ExpenseDetailForm();
		expenseForm.getExpenseDetails().add(expenseDetailForm);

		model.addAttribute("expenseForm", expenseForm);
		model.addAttribute("vendors", vendorService.findAll());
		model.addAttribute("products", this.productService.findAll());
		model.addAttribute("expenseAccounts",
				this.expenseAccountService.findAll());
		return "/accounting/expenses/add";
	}

	@RequestMapping(value = "/processAdd", method = RequestMethod.POST)
	public String processAdd(
			@ModelAttribute("expenseForm") ExpenseForm expenseForm,
			BindingResult result, Model model) {
		this.expenseValidator.validate(expenseForm, result);
		if (result.hasErrors()) {
			model.addAttribute("expenseForm", expenseForm);
			model.addAttribute("vendors", vendorService.findAll());
			model.addAttribute("products", this.productService.findAll());
			model.addAttribute("expenseAccounts",
					this.expenseAccountService.findAll());
			return "/accounting/expenses/add";
		} else {
			this.expenseService.addExpense(expenseForm);
			return "redirect:/accounting/expenses/list";
		}
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		ExpenseForm expense = this.expenseService.getExpenseForm(id);
		model.addAttribute("expense", expense);
		model.addAttribute("vendors", vendorService.findAll());
		model.addAttribute("products", this.productService.findAll());
		model.addAttribute("expenseAccounts",
				this.expenseAccountService.findAll());
		return "accounting/expenses/edit";
	}

	@RequestMapping(value = "/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(
			@ModelAttribute("expense") ExpenseForm expenseForm,
			BindingResult result, Model model) {
		this.expenseService.updateExpense(expenseForm);
		return "redirect:/accounting/expenses/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model model) {
		Expense expense = this.expenseService.findById(id, "vendor");
		List<ExpenseDetail> expenseDetails = this.expenseDetailService
				.findByPropertyWithEagerFetch("expense.id", expense.getId(),
						new String[] { "product", "expenseAccount" });
		expense.setExpenseDetails(expenseDetails);
		List<Payment> payments = this.paymentService.findByExpenseId(id);
		model.addAttribute("expense", expense);
		model.addAttribute("payments", payments);
		return "accounting/expenses/show";
	}
}
