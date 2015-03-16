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
import com.suwadi.dao.LoanRecoveryMethodDAO;
import com.suwadi.domain.Beneficiary;
import com.suwadi.domain.Loan;
import com.suwadi.domain.LoanPayment;
import com.suwadi.service.BeneficiaryService;
import com.suwadi.service.LoanService;
import com.suwadi.web.model.microfinance.LoanForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.validator.LoanValidator;

@Controller
public class LoansController {
	private BeneficiaryService beneficiaryService;
	private LoanService loanService;
	private LoanValidator loanValidator;
	private LoanDAO loanDAO;
	private LoanPaymentDAO loanPaymentDAO;
	private LoanRecoveryMethodDAO loanRecoveryMethodDAO;

	@Autowired
	public void setBeneficiaryService(BeneficiaryService beneficiaryService) {
		this.beneficiaryService = beneficiaryService;
	}

	@Autowired
	public void setLoanService(LoanService loanService) {
		this.loanService = loanService;
	}

	@Autowired
	public void setLoanValidator(LoanValidator loanValidator) {
		this.loanValidator = loanValidator;
	}

	@Autowired
	public void setLoanDAO(LoanDAO loanDAO) {
		this.loanDAO = loanDAO;
	}

	@Autowired
	public void setLoanPaymentDAO(LoanPaymentDAO loanPaymentDAO) {
		this.loanPaymentDAO = loanPaymentDAO;
	}

	@Autowired
	public void setLoanRecoveryMethodDAO(
			LoanRecoveryMethodDAO loanRecoveryMethodDAO) {
		this.loanRecoveryMethodDAO = loanRecoveryMethodDAO;
	}

	@RequestMapping(value = "microfinance/loans/list", method = RequestMethod.GET)
	public String list(Model model, Pager pager) {
		PagedResultSet pagedResultSet = this.loanDAO.paginate(pager,
				new String[] { "beneficiary" });
		model.addAttribute("pager", pagedResultSet);

		return "/microfinance/loans/list";
	}

	@RequestMapping(value = "/microfinance/beneficiaries/{benificiaryId}/loans/beneficiaryLoans", method = RequestMethod.GET)
	public String beneficiaryLoans(
			@PathVariable("benificiaryId") Long benificiaryId, Model model) {
		List<Loan> loans = this.loanDAO
				.findByPropertyWithEagerFetch("beneficiary.id", benificiaryId,
						new String[] { "beneficiary" });
		Beneficiary beneficiary = this.beneficiaryService.findById(
				benificiaryId, "group", "group.society",
				"group.society.gnDivision",
				"group.society.gnDivision.division",
				"group.society.gnDivision.division.district");
		model.addAttribute("loans", loans);
		model.addAttribute("beneficiary", beneficiary);
		return "/microfinance/loans/beneficiaryLoans";
	}

	@RequestMapping(value = "/microfinance/beneficiaries/{benificiaryId}/loans/add", method = RequestMethod.GET)
	public String add(@PathVariable("benificiaryId") Long benificiaryId,
			Model model) {
		LoanForm loanForm = new LoanForm();
		Beneficiary beneficiary = this.beneficiaryService.findById(
				benificiaryId, "group", "group.society",
				"group.society.gnDivision",
				"group.society.gnDivision.division",
				"group.society.gnDivision.division.district");
		loanForm.setBeneficiaryId(benificiaryId);
		model.addAttribute("loanForm", loanForm);
		model.addAttribute("beneficiary", beneficiary);
		model.addAttribute("loanRecoveryMethods",
				this.loanRecoveryMethodDAO.findAll());
		return "/microfinance/loans/add";
	}

	@RequestMapping(value = "/microfinance/beneficiaries/{benificiaryId}/loans/processAdd", method = RequestMethod.POST)
	public String processAdd(@PathVariable("benificiaryId") Long benificiaryId,
			@ModelAttribute("loanForm") LoanForm loanForm,
			BindingResult result, Model model) {
		this.loanValidator.validate(loanForm, result);
		if (result.hasErrors()) {
			Beneficiary beneficiary = this.beneficiaryService.findById(
					benificiaryId, "group", "group.society",
					"group.society.gnDivision",
					"group.society.gnDivision.division",
					"group.society.gnDivision.division.district");
			model.addAttribute("loanForm", loanForm);
			model.addAttribute("beneficiary", beneficiary);
			return "/microfinance/loans/add";
		} else {
			this.loanService.save(loanForm);
			String url = String
					.format("redirect:/microfinance/beneficiaries/%s/loans/beneficiaryLoans",
							benificiaryId);
			return url;
		}
	}

	@RequestMapping(value = "/microfinance/beneficiaries/{benificiaryId}/loans/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable("benificiaryId") Long benificiaryId,
			@PathVariable("id") Long id, Model model) {
		LoanForm loanForm = this.loanService.getLoanForm(id);
		Beneficiary beneficiary = this.beneficiaryService.findById(
				benificiaryId, "group", "group.society",
				"group.society.gnDivision",
				"group.society.gnDivision.division",
				"group.society.gnDivision.division.district");
		loanForm.setBeneficiaryId(benificiaryId);
		model.addAttribute("loanForm", loanForm);
		model.addAttribute("beneficiary", beneficiary);
		model.addAttribute("loanRecoveryMethods",
				this.loanRecoveryMethodDAO.findAll());
		return "/microfinance/loans/edit";
	}

	@RequestMapping(value = "/microfinance/beneficiaries/{benificiaryId}/loans/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(
			@PathVariable("benificiaryId") Long benificiaryId,
			@ModelAttribute("loanForm") LoanForm loanForm,
			BindingResult result, Model model) {
		this.loanValidator.validate(loanForm, result);
		if (result.hasErrors()) {
			Beneficiary beneficiary = this.beneficiaryService.findById(
					benificiaryId, "group", "group.society",
					"group.society.gnDivision",
					"group.society.gnDivision.division",
					"group.society.gnDivision.division.district");
			model.addAttribute("loanForm", loanForm);
			model.addAttribute("beneficiary", beneficiary);
			return "/microfinance/loans/add";
		} else {
			this.loanService.update(loanForm);
			String url = String
					.format("redirect:/microfinance/beneficiaries/%s/loans/beneficiaryLoans",
							benificiaryId);
			return url;
		}
	}

	@RequestMapping(value = "/microfinance/beneficiaries/{benificiaryId}/loans/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("benificiaryId") Long benificiaryId,
			@PathVariable("id") Long id, Model model) {
		Beneficiary beneficiary = this.beneficiaryService.findById(
				benificiaryId, "group", "group.society",
				"group.society.gnDivision",
				"group.society.gnDivision.division",
				"group.society.gnDivision.division.district");
		Loan loan = this.loanService.findById(id);

		List<LoanPayment> loanPayments = this.loanPaymentDAO
				.findByPropertyWithEagerFetch("loan.id", id, new String[] {
						"collectedBy", "collectedBy.profile" });

		model.addAttribute("loan", loan);
		model.addAttribute("beneficiary", beneficiary);
		model.addAttribute("loanPayments", loanPayments);

		return "/microfinance/loans/show";
	}
}
