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
import com.suwadi.domain.SavingAccountType;
import com.suwadi.domain.Society;
import com.suwadi.service.BeneficiaryService;
import com.suwadi.service.SavingAccountService;
import com.suwadi.service.SavingAccountTypeService;
import com.suwadi.service.SocietyService;
import com.suwadi.web.model.microfinance.SavingAccountForm;
import com.suwadi.web.model.microfinance.SavingAccountListDisplay;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.validator.SavingAccountValidator;

@Controller
public class SavingAccountsController {
	private SavingAccountService savingAccountService;
	private BeneficiaryService beneficiaryService;
	private SavingAccountTypeService savingAccountTypeService;
	private SavingAccountValidator savingAccountValidator;
	private SocietyService societyService;

	@Autowired
	public void setSavingAccountService(
			SavingAccountService savingAccountService) {
		this.savingAccountService = savingAccountService;
	}

	@Autowired
	public void setBeneficiaryService(BeneficiaryService beneficiaryService) {
		this.beneficiaryService = beneficiaryService;
	}

	@Autowired
	public void setSavingAccountTypeService(
			SavingAccountTypeService savingAccountTypeService) {
		this.savingAccountTypeService = savingAccountTypeService;
	}

	@Autowired
	public void setSavingAccountValidator(
			SavingAccountValidator savingAccountValidator) {
		this.savingAccountValidator = savingAccountValidator;
	}

	@Autowired
	public void setSocietyService(SocietyService societyService) {
		this.societyService = societyService;
	}

	@RequestMapping(value = "/microfinance/savingAccounts/list", method = RequestMethod.GET)
	public String list(Model model, Pager pager) {
		model.addAttribute("pager",
				this.savingAccountService.paginateSavingAccount(pager));
		return "/microfinance/savingAccounts/list";
	}

	@RequestMapping(value = "microfinance/beneficiaries/{id}/savingAccounts/benificiarySavingAccounts", method = RequestMethod.GET)
	public String benificiarySavingAccounts(@PathVariable("id") Long id,
			Model model) {
		Beneficiary beneficiary = this.beneficiaryService.findById(id, "group",
				"group.society", "group.society.gnDivision",
				"group.society.gnDivision.division",
				"group.society.gnDivision.division.district");
		List<SavingAccountListDisplay> accountListDisplays = this.savingAccountService
				.beneficiarySavingAccounts(id);
		model.addAttribute("savingAccounts", accountListDisplays);
		model.addAttribute("beneficiary", beneficiary);
		return "/microfinance/savingAccounts/benificiarySavingAccounts";
	}

	@RequestMapping(value = "microfinance/societies/{id}/savingAccounts/societySavingAccounts", method = RequestMethod.GET)
	public String societySavingAccounts(@PathVariable("id") Long id, Model model) {
		Society society = this.societyService.findById(id, "president",
				"secretary", "treasurer", "gnDivision", "gnDivision.division",
				"gnDivision.division.district");
		List<SavingAccountListDisplay> accountListDisplays = this.savingAccountService
				.societySavingAccounts(id);
		model.addAttribute("savingAccounts", accountListDisplays);
		model.addAttribute("society", society);
		return "/microfinance/savingAccounts/societySavingAccounts";
	}

	@RequestMapping(value = "microfinance/beneficiaries/{id}/savingAccounts/add", method = RequestMethod.GET)
	public String add(@PathVariable("id") Long id, Model model) {
		List<SavingAccountType> savingAccountTypes = this.savingAccountTypeService
				.findAll();
		Beneficiary beneficiary = this.beneficiaryService.findById(id);
		SavingAccountForm savingAccountForm = new SavingAccountForm();
		savingAccountForm.setBeneficiaryId(beneficiary.getId());
		savingAccountForm.setAccountHolderName(beneficiary.getFirstName() + " "
				+ beneficiary.getLastName());
		savingAccountForm.setAddress(beneficiary.getAddress());
		model.addAttribute("beneficiary", beneficiary);
		model.addAttribute("savingAccountForm", savingAccountForm);
		model.addAttribute("savingAccountTypes", savingAccountTypes);
		return "/microfinance/savingAccounts/add";
	}

	@RequestMapping(value = "microfinance/beneficiaries/{id}/savingAccounts/processAdd", method = RequestMethod.POST)
	public String processAdd(
			@PathVariable("id") Long id,
			@ModelAttribute("savingAccountForm") SavingAccountForm savingAccountForm,
			BindingResult result, Model model) {
		this.savingAccountValidator.validate(savingAccountForm, result);
		if (result.hasErrors()) {
			Beneficiary beneficiary = this.beneficiaryService.findById(id);
			List<SavingAccountType> savingAccountTypes = this.savingAccountTypeService
					.findAll();
			model.addAttribute("beneficiary", beneficiary);
			model.addAttribute("savingAccountForm", savingAccountForm);
			model.addAttribute("savingAccountTypes", savingAccountTypes);
			return "/microfinance/savingAccounts/add";
		} else {
			this.savingAccountService.createSavingAccount(savingAccountForm);
			return String
					.format("redirect:/microfinance/beneficiaries/%d/savingAccounts/benificiarySavingAccounts",
							id);
		}
	}

	@RequestMapping(value = "microfinance/beneficiaries/{beneficiaryId}/savingAccounts/{id}/edit")
	public String edit(@PathVariable("beneficiaryId") Long beneficiaryId,
			@PathVariable("id") Long id, Model model) {
		List<SavingAccountType> savingAccountTypes = this.savingAccountTypeService
				.findAll();
		SavingAccountForm savingAccountForm = this.savingAccountService
				.getServiceAccountFormById(id);
		Beneficiary beneficiary = this.beneficiaryService
				.findById(beneficiaryId);
		model.addAttribute("beneficiary", beneficiary);
		model.addAttribute("savingAccountForm", savingAccountForm);
		model.addAttribute("savingAccountTypes", savingAccountTypes);
		return "/microfinance/savingAccounts/edit";
	}

	@RequestMapping(value = "microfinance/beneficiaries/{beneficiaryId}/savingAccounts/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(
			@PathVariable("beneficiaryId") Long beneficiaryId,
			@PathVariable("id") Long id,
			@ModelAttribute("savingAccountForm") SavingAccountForm savingAccountForm,
			BindingResult result, Model model) {
		this.savingAccountValidator.validate(savingAccountForm, result);
		if (result.hasErrors()) {
			Beneficiary beneficiary = this.beneficiaryService
					.findById(beneficiaryId);
			List<SavingAccountType> savingAccountTypes = this.savingAccountTypeService
					.findAll();
			model.addAttribute("beneficiary", beneficiary);
			model.addAttribute("savingAccountForm", savingAccountForm);
			model.addAttribute("savingAccountTypes", savingAccountTypes);
			return "/microfinance/savingAccounts/add";
		} else {
			this.savingAccountService.updateSavingAccount(savingAccountForm);
			return String
					.format("redirect:/microfinance/beneficiaries/%d/savingAccounts/benificiarySavingAccounts",
							beneficiaryId);
		}
	}

	@RequestMapping(value = "microfinance/societies/{id}/savingAccounts/add", method = RequestMethod.GET)
	public String societyAdd(@PathVariable("id") Long id, Model model) {
		List<SavingAccountType> savingAccountTypes = this.savingAccountTypeService
				.findAll();
		Society society = this.societyService.findById(id);
		SavingAccountForm savingAccountForm = new SavingAccountForm();
		savingAccountForm.setSocietyId(society.getId());
		savingAccountForm.setAccountHolderName(society.getName());
		savingAccountForm.setAddress(society.getAddress());
		model.addAttribute("society", society);
		model.addAttribute("savingAccountForm", savingAccountForm);
		model.addAttribute("savingAccountTypes", savingAccountTypes);
		return "/microfinance/savingAccounts/societyAdd";
	}

	@RequestMapping(value = "microfinance/societies/{id}/savingAccounts/processSocietyAdd", method = RequestMethod.POST)
	public String processSocietyAdd(
			@PathVariable("id") Long id,
			@ModelAttribute("savingAccountForm") SavingAccountForm savingAccountForm,
			BindingResult result, Model model) {
		this.savingAccountValidator.validate(savingAccountForm, result);
		if (result.hasErrors()) {
			Society society = this.societyService.findById(id);
			List<SavingAccountType> savingAccountTypes = this.savingAccountTypeService
					.findAll();
			model.addAttribute("society", society);
			model.addAttribute("savingAccountForm", savingAccountForm);
			model.addAttribute("savingAccountTypes", savingAccountTypes);
			return "/microfinance/savingAccounts/societyAdd";
		} else {
			this.savingAccountService.createSavingAccount(savingAccountForm);
			return String
					.format("redirect:/microfinance/societies/%d/savingAccounts/societySavingAccounts",
							id);
		}
	}
}
