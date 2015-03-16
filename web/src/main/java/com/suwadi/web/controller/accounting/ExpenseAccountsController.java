package com.suwadi.web.controller.accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suwadi.domain.ExpenseAccount;
import com.suwadi.service.ExpenseAccountService;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.validator.ExpenseAccountValidator;

@Controller
@RequestMapping(value = "/accounting/expenseAccounts")
public class ExpenseAccountsController {
	private ExpenseAccountService expenseAccountService;
	private ExpenseAccountValidator expenseAccountValidator;

	@Autowired
	public void setExpenseAccountService(
			ExpenseAccountService expenseAccountService) {
		this.expenseAccountService = expenseAccountService;
	}

	@Autowired
	public void setExpenseAccountValidator(
			ExpenseAccountValidator expenseAccountValidator) {
		this.expenseAccountValidator = expenseAccountValidator;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Pager pager) {
		model.addAttribute("pager", this.expenseAccountService.paginate(pager));
		return "/accounting/expenseAccounts/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		ExpenseAccount expenseAccount = new ExpenseAccount();
		model.addAttribute("expenseAccount", expenseAccount);

		return "/accounting/expenseAccounts/add";
	}

	@RequestMapping(value = "processAdd", method = RequestMethod.POST)
	public String processAdd(
			@ModelAttribute("expenseAccount") ExpenseAccount expenseAccount,
			BindingResult result, Model model) {
		this.expenseAccountValidator.validate(expenseAccount, result);
		if (result.hasErrors()) {
			model.addAttribute("expenseAccount", expenseAccount);

			return "accounting/expenseAccounts/add";
		} else {
			expenseAccountService.save(expenseAccount);

			return "redirect:/accounting/expenseAccounts/list";
		}
	}

	@RequestMapping(value = "/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(
			@ModelAttribute("expenseAccount") ExpenseAccount expenseAccount,
			BindingResult result, Model model) {
		this.expenseAccountValidator.validate(expenseAccount, result);
		if (result.hasErrors()) {
			model.addAttribute("expenseAccount", expenseAccount);
			return "accounting/expenseAccounts/edit";
		} else {
			expenseAccountService.update(expenseAccount);

			// flash.info("district.added.success");
			return "redirect:/accounting/expenseAccounts/list";
		}
	}

	@RequestMapping(value = "/{id}")
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("expenseAccount",
				this.expenseAccountService.findById(id));
		return "accounting/expenseAccounts/show";
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		ExpenseAccount expenseAccount = this.expenseAccountService.findById(id);
		model.addAttribute("expenseAccount", expenseAccount);

		return "accounting/expenseAccounts/edit";
	}
}
