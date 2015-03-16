package com.suwadi.web.controller.microfinance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suwadi.dao.LoanDAO;
import com.suwadi.dao.LoanPaymentDAO;
import com.suwadi.dao.UserDAO;
import com.suwadi.domain.Loan;
import com.suwadi.domain.User;
import com.suwadi.service.LoanPaymentService;
import com.suwadi.web.model.microfinance.LoanPaymentForm;
import com.suwadi.web.validator.LoanPaymentValidator;

@Controller
public class LoanPaymentsController {
	private LoanDAO loanDAO;
	private LoanPaymentDAO loanPaymentDAO;
	private LoanPaymentService loanPaymentService;
	private UserDAO userDAO;
	private LoanPaymentValidator loanPaymentValidator;

	@Autowired
	public void setLoanDAO(LoanDAO loanDAO) {
		this.loanDAO = loanDAO;
	}

	@Autowired
	public void setLoanPaymentDAO(LoanPaymentDAO loanPaymentDAO) {
		this.loanPaymentDAO = loanPaymentDAO;
	}

	@Autowired
	public void setLoanPaymentService(LoanPaymentService loanPaymentService) {
		this.loanPaymentService = loanPaymentService;
	}

	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Autowired
	public void setLoanPaymentValidator(
			LoanPaymentValidator loanPaymentValidator) {
		this.loanPaymentValidator = loanPaymentValidator;
	}

	@RequestMapping(value = "/microfinance/loans/{loanId}/loanPayments/add", method = RequestMethod.GET)
	public String add(@PathVariable("loanId") Long loanId, Model model) {
		Loan loan = this.loanDAO.findById(loanId, "beneficiary",
				"beneficiary.group", "beneficiary.group.society",
				"beneficiary.group.society.gnDivision",
				"beneficiary.group.society.gnDivision.division",
				"beneficiary.group.society.gnDivision.division.district");
		LoanPaymentForm loanPaymentForm = new LoanPaymentForm();
		loanPaymentForm.setLoanId(loan.getId());
		model.addAttribute("loan", loan);
		model.addAttribute("loanPaymentForm", loanPaymentForm);
		List<User> users = this.userDAO
				.findAllWithEagerFetch(new String[] { "profile" });
		model.addAttribute("users", users);

		return "/microfinance/loanPayments/add";
	}

	@RequestMapping(value = "/microfinance/loans/{loanId}/loanPayments/processAdd", method = RequestMethod.POST)
	public String processAdd(@PathVariable("loanId") Long loanId,
			@ModelAttribute("loanPaymentForm") LoanPaymentForm loanPaymentForm,
			BindingResult result, Model model) {
		Loan loan = this.loanDAO.findById(loanId, "beneficiary",
				"beneficiary.group", "beneficiary.group.society",
				"beneficiary.group.society.gnDivision",
				"beneficiary.group.society.gnDivision.division",
				"beneficiary.group.society.gnDivision.division.district");
		this.loanPaymentValidator.validate(loanPaymentForm, result);
		if (result.hasErrors()) {
			loanPaymentForm.setLoanId(loan.getId());
			model.addAttribute("loan", loan);
			model.addAttribute("loanPaymentForm", loanPaymentForm);
			List<User> users = this.userDAO
					.findAllWithEagerFetch(new String[] { "profile" });
			model.addAttribute("users", users);

			return "/microfinance/loanPayments/add";
		} else {
			this.loanPaymentService.save(loanPaymentForm);
			String url = String.format(
					"redirect:/microfinance/beneficiaries/%s/loans/%s", loan
							.getBeneficiary().getId(), loanId);
			return url;
		}
	}

	@RequestMapping(value = "/microfinance/loans/{loanId}/loanPayments/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable("loanId") Long loanId,
			@PathVariable("id") Long id, Model model) {
		Loan loan = this.loanDAO.findById(loanId, "beneficiary",
				"beneficiary.group", "beneficiary.group.society",
				"beneficiary.group.society.gnDivision",
				"beneficiary.group.society.gnDivision.division",
				"beneficiary.group.society.gnDivision.division.district");
		LoanPaymentForm loanPaymentForm = this.loanPaymentService
				.getLoanPaymentForm(id);
		loanPaymentForm.setLoanId(loan.getId());
		model.addAttribute("loan", loan);
		model.addAttribute("loanPaymentForm", loanPaymentForm);
		List<User> users = this.userDAO
				.findAllWithEagerFetch(new String[] { "profile" });
		model.addAttribute("users", users);

		return "/microfinance/loanPayments/edit";
	}

	@RequestMapping(value = "/microfinance/loans/{loanId}/loanPayments/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(@PathVariable("loanId") Long loanId,
			@PathVariable("id") Long id,
			@ModelAttribute("loanPaymentForm") LoanPaymentForm loanPaymentForm,
			BindingResult result, Model model) {
		Loan loan = this.loanDAO.findById(loanId, "beneficiary",
				"beneficiary.group", "beneficiary.group.society",
				"beneficiary.group.society.gnDivision",
				"beneficiary.group.society.gnDivision.division",
				"beneficiary.group.society.gnDivision.division.district");
		this.loanPaymentValidator.validate(loanPaymentForm, result);
		if (result.hasErrors()) {
			loanPaymentForm.setLoanId(loan.getId());
			model.addAttribute("loan", loan);
			model.addAttribute("loanPaymentForm", loanPaymentForm);
			List<User> users = this.userDAO
					.findAllWithEagerFetch(new String[] { "profile" });
			model.addAttribute("users", users);

			return "/microfinance/loanPayments/edit";
		} else {
			this.loanPaymentService.update(loanPaymentForm);
			String url = String.format(
					"redirect:/microfinance/beneficiaries/%s/loans/%s", loan
							.getBeneficiary().getId(), loanId);
			return url;
		}
	}
}
