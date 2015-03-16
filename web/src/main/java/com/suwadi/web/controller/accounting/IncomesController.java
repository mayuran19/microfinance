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

import com.suwadi.domain.Income;
import com.suwadi.domain.IncomeDetail;
import com.suwadi.service.CustomerService;
import com.suwadi.service.IncomeDetailService;
import com.suwadi.service.IncomeService;
import com.suwadi.service.ProductService;
import com.suwadi.service.TermOfPaymentService;
import com.suwadi.web.model.accounting.IncomeDetailForm;
import com.suwadi.web.model.accounting.IncomeForm;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.validator.IncomeValidator;

@Controller
@RequestMapping(value = "/accounting/incomes")
public class IncomesController {
	private CustomerService customerService;
	private TermOfPaymentService termOfPaymentService;
	private ProductService productService;
	private IncomeValidator incomeValidator;
	private IncomeService incomeService;
	private IncomeDetailService incomeDetailService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Autowired
	public void setTermOfPaymentService(
			TermOfPaymentService termOfPaymentService) {
		this.termOfPaymentService = termOfPaymentService;
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	public void setIncomeValidator(IncomeValidator incomeValidator) {
		this.incomeValidator = incomeValidator;
	}

	@Autowired
	public void setIncomeService(IncomeService incomeService) {
		this.incomeService = incomeService;
	}

	@Autowired
	public void setIncomeDetailService(IncomeDetailService incomeDetailService) {
		this.incomeDetailService = incomeDetailService;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String index(Model model, Pager pager) {
		// this.expenseService.findAllExpenses(pager);
		model.addAttribute("pager", this.incomeService.findAllIncomes(pager));
		return "/accounting/incomes/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		IncomeForm incomeForm = new IncomeForm();
		IncomeDetailForm incomeDetailForm = new IncomeDetailForm();
		incomeForm.getIncomeDetailForms().add(incomeDetailForm);

		model.addAttribute("incomeForm", incomeForm);
		model.addAttribute("customers", this.customerService.findAll());
		model.addAttribute("termOfPayments",
				this.termOfPaymentService.findAll());
		model.addAttribute("products", this.productService.findAll());
		return "/accounting/incomes/add";
	}

	@RequestMapping(value = "/processAdd", method = RequestMethod.POST)
	public String processAdd(
			@ModelAttribute("incomeForm") IncomeForm incomeForm,
			BindingResult result, Model model) {
		this.incomeValidator.validate(incomeForm, result);
		if (result.hasErrors()) {
			model.addAttribute("incomeForm", incomeForm);
			model.addAttribute("customers", this.customerService.findAll());
			model.addAttribute("termOfPayments",
					this.termOfPaymentService.findAll());
			model.addAttribute("products", this.productService.findAll());
			return "/accounting/incomes/add";
		} else {
			this.incomeService.addIncome(incomeForm);
			return "redirect:/accounting/incomes/list";
		}

	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		IncomeForm incomeForm = this.incomeService.getIncomeForm(id);
		model.addAttribute("incomeForm", incomeForm);
		model.addAttribute("customers", this.customerService.findAll());
		model.addAttribute("termOfPayments",
				this.termOfPaymentService.findAll());
		model.addAttribute("products", this.productService.findAll());
		return "accounting/incomes/edit";
	}

	@RequestMapping(value = "/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(@ModelAttribute("income") IncomeForm incomeForm,
			BindingResult result, Model model) {
		this.incomeService.updateIncome(incomeForm);
		return "redirect:/accounting/incomes/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model) {
		Income income = this.incomeService.findById(id, "customer",
				"termOfPayment");
		List<IncomeDetail> incomeDetails = this.incomeDetailService
				.findByPropertyWithEagerFetch("income.id", income.getId(),
						new String[] { "product" });
		income.setIncomeDetails(incomeDetails);
		model.addAttribute("income", income);
		return "accounting/incomes/show";
	}
}
