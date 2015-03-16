package com.suwadi.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suwadi.domain.Beneficiary;
import com.suwadi.domain.BeneficiaryFamilyMember;
import com.suwadi.service.BeneficiaryFamilyMemberService;
import com.suwadi.service.BeneficiaryService;
import com.suwadi.service.FamilyRelationshipService;
import com.suwadi.web.validator.BeneficiaryFamilyMemberValidator;

@Controller
@RequestMapping(value = "/admin")
public class BeneficiaryFamilyMembersController {
	private FamilyRelationshipService familyRelationshipService;
	private BeneficiaryFamilyMemberService beneficiaryFamilyMemberService;
	private BeneficiaryService beneficiaryService;
	private BeneficiaryFamilyMemberValidator beneficiaryFamilyMemberValidator;

	@Autowired
	public BeneficiaryFamilyMembersController(
			FamilyRelationshipService familyRelationshipService,
			BeneficiaryFamilyMemberService beneficiaryFamilyMemberService,
			BeneficiaryService beneficiaryService,
			BeneficiaryFamilyMemberValidator beneficiaryFamilyMemberValidator) {
		this.familyRelationshipService = familyRelationshipService;
		this.beneficiaryFamilyMemberService = beneficiaryFamilyMemberService;
		this.beneficiaryService = beneficiaryService;
		this.beneficiaryFamilyMemberValidator = beneficiaryFamilyMemberValidator;
	}

	@RequestMapping(value = "/beneficiaries/{beneficiaryId}/beneficiaryFamilies/{id}")
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("beneficiaryFamilyMember",
				this.beneficiaryFamilyMemberService.findById(id, "beneficiary", "familyRelationship"));
		return "admin/beneficiaryFamilies/show";
	}

	@RequestMapping(value = "/beneficiaries/{beneficiaryId}/beneficiaryFamilies/add", method = RequestMethod.GET)
	public String add(@PathVariable Long beneficiaryId, Model model) {
		Beneficiary beneficiary = this.beneficiaryService
				.findById(beneficiaryId);
		model.addAttribute("familyRelationships",
				this.familyRelationshipService.findAll());
		BeneficiaryFamilyMember beneficiaryFamilyMember = new BeneficiaryFamilyMember();
		beneficiaryFamilyMember.setBeneficiary(beneficiary);
		model.addAttribute("beneficiaryFamilyMember", beneficiaryFamilyMember);
		return "admin/beneficiaryFamilies/add";
	}

	@RequestMapping(value = "/beneficiaries/{beneficiaryId}/beneficiaryFamilies/processAdd", method = RequestMethod.POST)
	public String processAdd(@PathVariable Long beneficiaryId,
			@ModelAttribute BeneficiaryFamilyMember beneficiaryFamilyMember,
			BindingResult result, Model model) {
		this.beneficiaryFamilyMemberValidator.validate(beneficiaryFamilyMember,
				result);
		if (result.hasErrors()) {
			model.addAttribute("familyRelationships",
					this.familyRelationshipService.findAll());
			return "admin/beneficiaryFamilies/add";
		} else {
			this.beneficiaryFamilyMemberService.save(beneficiaryFamilyMember);
			String redirectUrl = String.format(
					"redirect:/admin/beneficiaries/%d/beneficiaryFamilies/%d",
					beneficiaryId, beneficiaryFamilyMember.getId());
			return redirectUrl;
		}

	}

	@RequestMapping(value = "/beneficiaries/{beneficiaryId}/beneficiaryFamilies/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("beneficiaryFamilyMember",
				this.beneficiaryFamilyMemberService.findById(id));
		model.addAttribute("familyRelationships",
				this.familyRelationshipService.findAll());
		return "admin/beneficiaryFamilies/edit";
	}

	@RequestMapping(value = "/beneficiaries/{beneficiaryId}/beneficiaryFamilies/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(@PathVariable Long beneficiaryId,
			@ModelAttribute BeneficiaryFamilyMember beneficiaryFamilyMember,
			BindingResult result, Model model) {
		this.beneficiaryFamilyMemberValidator.validate(beneficiaryFamilyMember,
				result);
		if (result.hasErrors()) {
			model.addAttribute("familyRelationships",
					this.familyRelationshipService.findAll());
			return "admin/beneficiaryFamilies/edit";
		} else {
			this.beneficiaryFamilyMemberService.update(beneficiaryFamilyMember);
			String redirectUrl = String.format(
					"redirect:/admin/beneficiaries/%d/beneficiaryFamilies/%d",
					beneficiaryId, beneficiaryFamilyMember.getId());
			return redirectUrl;
		}
	}
}
