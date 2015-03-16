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
import com.suwadi.domain.SavingAccountDepositType;
import com.suwadi.service.BeneficiaryService;
import com.suwadi.service.EmployeeService;
import com.suwadi.service.SavingAccountDepositService;
import com.suwadi.service.SavingAccountDepositTypeService;
import com.suwadi.service.SavingAccountService;
import com.suwadi.web.model.microfinance.SavingAccountDepositForm;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.validator.SavingAccountDepositValidator;

@Controller
public class SavingAccountDepositsController {
	private SavingAccountDepositService savingAccountDepositService;
	private BeneficiaryService beneficiaryService;
	private SavingAccountService savingAccountService;
	private EmployeeService employeeService;
	private SavingAccountDepositValidator savingAccountDepositValidator;
	private SavingAccountDepositTypeService savingAccountDepositTypeService;

	@Autowired
	public void setSavingAccountDepositService(
			SavingAccountDepositService savingAccountDepositService) {
		this.savingAccountDepositService = savingAccountDepositService;
	}

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
	public void setSavingAccountDepositValidator(
			SavingAccountDepositValidator savingAccountDepositValidator) {
		this.savingAccountDepositValidator = savingAccountDepositValidator;
	}

	@Autowired
	public void setSavingAccountDepositTypeService(
			SavingAccountDepositTypeService savingAccountDepositTypeService) {
		this.savingAccountDepositTypeService = savingAccountDepositTypeService;
	}

	@RequestMapping(value = "/microfinance/savingAccounts/{id}/savingAccountDeposits/list")
	public String list(@PathVariable("id") Long id, Model model, Pager pager) {
		SavingAccount savingAccount = this.savingAccountService.findById(id,
				"beneficiary", "society");
		Beneficiary beneficiary = this.beneficiaryService.findById(
				savingAccount.getBeneficiary().getId(), "group",
				"group.society", "group.society.gnDivision",
				"group.society.gnDivision.division",
				"group.society.gnDivision.division.district");
		model.addAttribute("pager", this.savingAccountDepositService
				.savingAccountDepoistPaginateBySavingAccountId(id, pager));
		model.addAttribute("beneficiary", beneficiary);
		model.addAttribute("savingAccount", savingAccount);
		return "/microfinance/savingAccountDeposits/list";
	}

	@RequestMapping(value = "/microfinance/savingAccounts/{id}/savingAccountDeposits/add")
	public String add(@PathVariable("id") Long id, Model model) {
		SavingAccount savingAccount = this.savingAccountService.findById(id);
		SavingAccountDepositForm savingAccountDepositForm = new SavingAccountDepositForm();
		savingAccountDepositForm.setSavingAccountId(id);
		Beneficiary beneficiary = this.beneficiaryService.findById(
				savingAccount.getBeneficiary().getId(), "group",
				"group.society", "group.society.gnDivision",
				"group.society.gnDivision.division",
				"group.society.gnDivision.division.district");
		List<Employee> employees = this.employeeService.findAll();
		List<SavingAccountDepositType> savingAccountDepositTypes = this.savingAccountDepositTypeService
				.findAll();
		model.addAttribute("savingAccount", savingAccount);
		model.addAttribute("savingAccountDepositForm", savingAccountDepositForm);
		model.addAttribute("employees", employees);
		model.addAttribute("beneficiary", beneficiary);
		model.addAttribute("savingAccountDepositTypes",
				savingAccountDepositTypes);
		return "/microfinance/savingAccountDeposits/add";
	}

	@RequestMapping(value = "/microfinance/savingAccounts/{id}/savingAccountDeposits/processAdd", method = RequestMethod.POST)
	public String processAdd(
			@PathVariable("id") Long id,
			@ModelAttribute("savingAccountDepositForm") SavingAccountDepositForm savingAccountDepositForm,
			BindingResult result, Model model) {
		this.savingAccountDepositValidator.validate(savingAccountDepositForm,
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
			return "/microfinance/savingAccountDeposits/add";
		} else {
			this.savingAccountDepositService
					.createSavingAccountDeposit(savingAccountDepositForm);
			return String
					.format("redirect:/microfinance/savingAccounts/%d/savingAccountDeposits/list",
							id);
		}
	}

	@RequestMapping(value = "/microfinance/savingAccounts/{savingAccountId}/savingAccountDeposits/{id}/edit")
	public String edit(@PathVariable("savingAccountId") Long savingAccountId,
			@PathVariable("id") Long id, Model model) {
		SavingAccount savingAccount = this.savingAccountService
				.findById(savingAccountId);
		SavingAccountDepositForm savingAccountDepositForm = this.savingAccountDepositService
				.getSavingAccountDepositFormById(id);
		Beneficiary beneficiary = this.beneficiaryService.findById(
				savingAccount.getBeneficiary().getId(), "group",
				"group.society", "group.society.gnDivision",
				"group.society.gnDivision.division",
				"group.society.gnDivision.division.district");
		List<Employee> employees = this.employeeService.findAll();
		List<SavingAccountDepositType> savingAccountDepositTypes = this.savingAccountDepositTypeService
				.findAll();
		model.addAttribute("savingAccount", savingAccount);
		model.addAttribute("savingAccountDepositForm", savingAccountDepositForm);
		model.addAttribute("employees", employees);
		model.addAttribute("beneficiary", beneficiary);
		model.addAttribute("savingAccountDepositTypes",
				savingAccountDepositTypes);
		return "/microfinance/savingAccountDeposits/edit";
	}

	@RequestMapping(value = "/microfinance/savingAccounts/{savingAccountId}/savingAccountDeposits/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(
			@PathVariable("savingAccountId") Long savingAccountId,
			@PathVariable("id") Long id,
			@ModelAttribute("savingAccountDepositForm") SavingAccountDepositForm savingAccountDepositForm,
			BindingResult result, Model model) {
		this.savingAccountDepositValidator.validate(savingAccountDepositForm,
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
			return "/microfinance/savingAccountDeposits/edit";
		} else {
			this.savingAccountDepositService
					.updateSavingAccountDeposit(savingAccountDepositForm);
			return String
					.format("redirect:/microfinance/savingAccounts/%d/savingAccountDeposits/list",
							savingAccountId);
		}
	}
}
