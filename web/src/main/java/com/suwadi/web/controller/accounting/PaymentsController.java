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
import com.suwadi.domain.Payment;
import com.suwadi.service.ExpenseService;
import com.suwadi.service.PaymentService;
import com.suwadi.service.VendorService;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.validator.PaymentValidator;

@Controller
public class PaymentsController {
	private PaymentService paymentService;
	private ExpenseService expenseService;
	private VendorService vendorService;
	private PaymentValidator paymentValidator;

	@Autowired
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@Autowired
	public void setExpenseService(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	@Autowired
	public void setVendorService(VendorService vendorService) {
		this.vendorService = vendorService;
	}

	@Autowired
	public void setPaymentValidator(PaymentValidator paymentValidator) {
		this.paymentValidator = paymentValidator;
	}

	@RequestMapping(value = "/accounting/expenses/{id}/payments/list")
	public String list(@PathVariable("id") Long id, Model model, Pager pager) {
		List<Payment> payments = this.paymentService.findByExpenseId(id);
		Expense expense = this.expenseService.findById(id);

		model.addAttribute("payments", payments);
		model.addAttribute("expense", expense);

		return "/accounting/payments/list";
	}

	@RequestMapping(value = "/accounting/expenses/{id}/payments/add", method = RequestMethod.GET)
	public String add(@PathVariable("id") Long id, Model model) {
		Expense expense = this.expenseService.findById(id);
		Payment payment = new Payment();
		payment.setExpense(expense);
		model.addAttribute("vendors", vendorService.findAll());
		model.addAttribute("payment", payment);

		return "/accounting/payments/add";
	}

	@RequestMapping(value = "/accounting/expenses/{id}/payments/processAdd", method = RequestMethod.POST)
	public String processAdd(@PathVariable("id") Long id,
			@ModelAttribute("payment") Payment payment, BindingResult result,
			Model model) {
		this.paymentValidator.validate(payment, result);
		System.out.println(this.paymentService.getTotalPaymentByExpenseId(id));
		if (result.hasErrors()) {
			model.addAttribute("payment", payment);
			return "/accounting/payments/add";
		} else {
			this.paymentService.addPayment(payment);
			return "redirect:/accounting/expenses/" + id;
		}

	}

	@RequestMapping(value = "/accounting/expenses/{expenseId}/payments/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("expenseId") Long expenseId,
			@PathVariable("id") Long id, Model model) {
		Payment payment = this.paymentService.findById(id, "expense");
		Expense expense = this.expenseService.findById(expenseId);
		model.addAttribute("payment", payment);
		model.addAttribute("expense", expense);
		model.addAttribute("vendors", vendorService.findAll());
		return "/accounting/payments/edit";
	}

	@RequestMapping(value = "/accounting/expenses/{expenseId}/payments/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(@PathVariable("expenseId") Long expenseId,
			@PathVariable("id") Long id,
			@ModelAttribute("payment") Payment payment, BindingResult result,
			Model model) {
		this.paymentValidator.validate(payment, result);
		if (result.hasErrors()) {
			model.addAttribute("payment", payment);
			return "/accounting/payments/add";
		} else {
			this.paymentService.updatePayment(payment);
			return "redirect:/accounting/expenses/" + id;
		}

	}
}
