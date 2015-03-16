package com.suwadi.web.controller.accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.suwadi.domain.Customer;
import com.suwadi.service.CustomerService;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.validator.CustomerValidator;

@Controller
@RequestMapping(value = "/accounting/customers")
public class CustomersController {
	private CustomerService customerService;
	private CustomerValidator customerValidator;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Autowired
	public void setCustomerValidator(CustomerValidator customerValidator) {
		this.customerValidator = customerValidator;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Pager pager) {
		model.addAttribute("pager", this.customerService.paginate(pager));
		return "/accounting/customers/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "/accounting/customers/add";
	}

	@RequestMapping(value = "processAdd", method = RequestMethod.POST)
	public String processAdd(@ModelAttribute("customer") Customer customer,
			BindingResult result, SessionStatus status, Model model) {
		this.customerValidator.validate(customer, result);
		if (result.hasErrors()) {
			model.addAttribute("customer", customer);

			return "accounting/customers/add";
		} else {
			customerService.save(customer);

			return "redirect:/accounting/customers/list";
		}
	}

	@RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		Customer customer = this.customerService.findById(id);
		model.addAttribute("customer", customer);
		return "/accounting/customers/edit";
	}

	@RequestMapping(value = "{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(@ModelAttribute("customer") Customer customer,
			BindingResult result, SessionStatus status, Model model) {
		this.customerValidator.validate(customer, result);
		if (result.hasErrors()) {
			model.addAttribute("customer", customer);

			return "accounting/customers/edit";
		} else {
			customerService.update(customer);

			return "redirect:/accounting/customers/list";
		}
	}
}
