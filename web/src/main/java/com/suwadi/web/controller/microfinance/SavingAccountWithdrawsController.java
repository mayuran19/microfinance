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

import com.suwadi.domain.Beneficiary;
import com.suwadi.domain.Employee;
import com.suwadi.domain.SavingAccount;
import com.suwadi.domain.SavingAccountWithdrawType;
import com.suwadi.service.BeneficiaryService;
import com.suwadi.service.EmployeeService;
import com.suwadi.service.SavingAccountService;
import com.suwadi.service.SavingAccountWithdrawService;
import com.suwadi.service.SavingAccountWithdrawTypeService;
import com.suwadi.web.model.microfinance.SavingAccountWithdrawForm;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.validator.SavingAccountWithdrawValidator;

@Controller
public class SavingAccountWithdrawsController {
	private BeneficiaryService beneficiaryService;
	private SavingAccountService savingAccountService;
	private EmployeeService employeeService;
	private SavingAccountWithdrawService savingAccountWithdrawService;
	private SavingAccountWithdrawValidator savingAccountWithdrawValidator;
	private SavingAccountWithdrawTypeService savingAccountWithdrawTypeService;

	@Autowired
	public void setBeneficiaryService(BeneficiaryService beneficiaryService) {
		this.beneficiaryService = beneficiaryService;
	}

	@Autowired
	public void setSavingAccountService(
			SavingAccountService savingAccountService) {
		this.savingAccountService = savingAccountService;
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Autowired
	public void setSavingAccountWithdrawService(
			SavingAccountWithdrawService savingAccountWithdrawService) {
		this.savingAccountWithdrawService = savingAccountWithdrawService;
	}

	@Autowired
	public void setSavingAccountWithdrawValidator(
			SavingAccountWithdrawValidator savingAccountWithdrawValidator) {
		this.savingAccountWithdrawValidator = savingAccountWithdrawValidator;
	}

	@Autowired
	public void setSavingAccountWithdrawTypeService(
			SavingAccountWithdrawTypeService savingAccountWithdrawTypeService) {
		this.savingAccountWithdrawTypeService = savingAccountWithdrawTypeService;
	}

	@RequestMapping(value = "/microfinance/savingAccounts/{id}/savingAccountWithdraws/list")
	public String list(@PathVariable("id") Long id, Model model, Pager pager) {
		SavingAccount savingAccount = this.savingAccountService.findById(id,
				"beneficiary");
		Beneficiary beneficiary = this.beneficiaryService.findById(
				savingAccount.getBeneficiary().getId(), "group",
				"group.society", "group.society.gnDivision",
				"group.society.gnDivision.division",
				"group.society.gnDivision.division.district");
		model.addAttribute("pager", this.savingAccountWithdrawService
				.savingAccountWithdrawPaginateBySavingAccountId(id, pager));
		model.addAttribute("beneficiary", beneficiary);
		model.addAttribute("savingAccount", savingAccount);
		return "/microfinance/savingAccountWithdraws/list";
	}

	@RequestMapping(value = "/microfinance/savingAccounts/{id}/savingAccountWithdraws/add")
	public String add(@PathVariable("id") Long id, Model model) {
		SavingAccount savingAccount = this.savingAccountService.findById(id);
		SavingAccountWithdrawForm savingAccountWithdrawForm = new SavingAccountWithdrawForm();
		savingAccountWithdrawForm.setSavingAccountId(id);
		List<Employee> employees = this.employeeService.findAll();
		List<SavingAccountWithdrawType> savingAccountWithdrawTypes = this.savingAccountWithdrawTypeService
				.findAll();
		model.addAttribute("savingAccount", savingAccount);
		model.addAttribute("savingAccountWithdrawForm",
				savingAccountWithdrawForm);
		model.addAttribute("employees", employees);
		Beneficiary beneficiary = this.beneficiaryService.findById(
				savingAccount.getBeneficiary().getId(), "group",
				"group.society", "group.society.gnDivision",
				"group.society.gnDivision.division",
				"group.society.gnDivision.division.district");
		model.addAttribute("beneficiary", beneficiary);
		model.addAttribute("savingAccountWithdrawTypes",
				savingAccountWithdrawTypes);
		return "/microfinance/savingAccountWithdraws/add";
	}

	@RequestMapping(value = "/microfinance/savingAccounts/{id}/savingAccountWithdraws/processAdd", method = RequestMethod.POST)
	public String processAdd(
			@PathVariable("id") Long id,
			@ModelAttribute("savingAccountWithdrawForm") SavingAccountWithdrawForm savingAccountWithdrawForm,
			BindingResult result, Model model) {
		this.savingAccountWithdrawValidator.validate(savingAccountWithdrawForm,
				result);
		if (result.hasErrors()) {
			SavingAccount savingAccount = this.savingAccountService
					.findById(id);
			List<Employee> employees = this.employeeService.findAll();
			model.addAttribute("savingAccount", savingAccount);
			model.addAttribute("employees", employees);
			Beneficiary beneficiary = this.beneficiaryService.findById(
					savingAccount.getBeneficiary().getId(), "group",
					"group.society", "group.society.gnDivision",
					"group.society.gnDivision.division",
					"group.society.gnDivision.division.district");
			model.addAttribute("beneficiary", beneficiary);
			return "/microfinance/savingAccountWithdraws/add";
		} else {
			this.savingAccountWithdrawService
					.createSavingAccountWithdraw(savingAccountWithdrawForm);
			return String
					.format("redirect:/microfinance/savingAccounts/%d/savingAccountWithdraws/list",
							id);
		}
	}

	@RequestMapping(value = "/microfinance/savingAccounts/{savingAccountId}/savingAccountWithdraws/{id}/edit")
	public String edit(@PathVariable("savingAccountId") Long savingAccountId,
			@PathVariable("id") Long id, Model model) {
		SavingAccount savingAccount = this.savingAccountService.findById(id);
		SavingAccountWithdrawForm savingAccountWithdrawForm = this.savingAccountWithdrawService
				.getSavingAccountWithdrawFormById(id);
		List<Employee> employees = this.employeeService.findAll();
		List<SavingAccountWithdrawType> savingAccountWithdrawTypes = this.savingAccountWithdrawTypeService
				.findAll();
		model.addAttribute("savingAccount", savingAccount);
		model.addAttribute("savingAccountWithdrawForm",
				savingAccountWithdrawForm);
		model.addAttribute("employees", employees);
		Beneficiary beneficiary = this.beneficiaryService.findById(
				savingAccount.getBeneficiary().getId(), "group",
				"group.society", "group.society.gnDivision",
				"group.society.gnDivision.division",
				"group.society.gnDivision.division.district");
		model.addAttribute("beneficiary", beneficiary);
		model.addAttribute("savingAccountWithdrawTypes",
				savingAccountWithdrawTypes);
		return "/microfinance/savingAccountWithdraws/edit";
	}

	@RequestMapping(value = "/microfinance/savingAccounts/{savingAccountId}/savingAccountWithdraws/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(
			@PathVariable("savingAccountId") Long savingAccountId,
			@PathVariable("id") Long id,
			@ModelAttribute("savingAccountWithdrawForm") SavingAccountWithdrawForm savingAccountWithdrawForm,
			BindingResult result, Model model) {
		this.savingAccountWithdrawValidator.validate(savingAccountWithdrawForm,
				result);
		if (result.hasErrors()) {
			SavingAccount savingAccount = this.savingAccountService
					.findById(id);
			List<Employee> employees = this.employeeService.findAll();
			model.addAttribute("savingAccount", savingAccount);
			model.addAttribute("employees", employees);
			Beneficiary beneficiary = this.beneficiaryService.findById(
					savingAccount.getBeneficiary().getId(), "group",
					"group.society", "group.society.gnDivision",
					"group.society.gnDivision.division",
					"group.society.gnDivision.division.district");
			model.addAttribute("beneficiary", beneficiary);
			return "/microfinance/savingAccountWithdraws/edit";
		} else {
			this.savingAccountWithdrawService
					.updateSavingAccountWithdraw(savingAccountWithdrawForm);
			return String
					.format("redirect:/microfinance/savingAccounts/%d/savingAccountWithdraws/list",
							savingAccountId);
		}
	}
}
