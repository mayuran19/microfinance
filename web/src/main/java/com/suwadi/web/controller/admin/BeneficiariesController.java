package com.suwadi.web.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.suwadi.domain.Beneficiary;
import com.suwadi.domain.BeneficiaryType;
import com.suwadi.domain.District;
import com.suwadi.domain.Division;
import com.suwadi.domain.GNDivision;
import com.suwadi.domain.Group;
import com.suwadi.domain.Society;
import com.suwadi.domain.Status;
import com.suwadi.service.BeneficiaryService;
import com.suwadi.service.DistrictService;
import com.suwadi.service.DivisionService;
import com.suwadi.service.GNDivisionService;
import com.suwadi.service.GroupService;
import com.suwadi.service.SocietyService;
import com.suwadi.web.model.BeneficiarySearch;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.validator.BeneficiaryValidator;

@Controller
@RequestMapping(value = "/admin")
public class BeneficiariesController {
	private BeneficiaryService beneficiaryService;
	private BeneficiaryValidator beneficiaryValidator;
	private DistrictService districtService;
	private DivisionService divisionService;
	private GNDivisionService gnDivisionService;
	private SocietyService societyService;
	private GroupService groupService;

	@Autowired
	public BeneficiariesController(BeneficiaryService beneficiaryService,
			BeneficiaryValidator beneficiaryValidator,
			DistrictService districtService, DivisionService divisionService,
			GNDivisionService gnDivisionService, SocietyService societyService,
			GroupService groupService) {
		this.beneficiaryService = beneficiaryService;
		this.beneficiaryValidator = beneficiaryValidator;
		this.districtService = districtService;
		this.divisionService = divisionService;
		this.gnDivisionService = gnDivisionService;
		this.societyService = societyService;
		this.groupService = groupService;
	}

	@RequestMapping(value = "/beneficiaries/list")
	public String list(Model model, Pager pager) {
		model.addAttribute("pager",
				this.beneficiaryService.findAllBeneficiaries(pager));
		model.addAttribute("societies", this.societyService.findAll());
		model.addAttribute("districts", this.districtService.findAll());
		model.addAttribute("divisions", this.divisionService.findAll());
		model.addAttribute("gnDivisions", this.gnDivisionService.findAll());
		model.addAttribute("groups", this.groupService.findAll());
		BeneficiarySearch beneficiarySearch = new BeneficiarySearch();
		model.addAttribute("beneficiarySearch", beneficiarySearch);
		return "admin/beneficiaries/list";
	}

	@RequestMapping(value = "/beneficiaries/add", method = RequestMethod.GET)
	public String add(Model model) {
		Beneficiary beneficiary = new Beneficiary();
		List<District> districts = this.districtService.findAll();
		model.addAttribute("districts", districts);
		model.addAttribute("divisions", new ArrayList<Division>());
		model.addAttribute("gnDivisions", new ArrayList<GNDivision>());
		model.addAttribute("societies", new ArrayList<Society>());
		model.addAttribute("groups", new ArrayList<Group>());
		model.addAttribute("districts", districts);
		model.addAttribute("beneficiary", beneficiary);
		return "admin/beneficiaries/add";
	}

	@RequestMapping(value = "/beneficiaries/processAdd", method = RequestMethod.POST)
	public String processAdd(
			@ModelAttribute("beneficiary") Beneficiary beneficiary,
			BindingResult result, SessionStatus status, Model model) {
		this.beneficiaryValidator.validate(beneficiary, result);
		if (result.hasErrors()) {
			List<District> districts = this.districtService.findAll();
			model.addAttribute("districts", districts);
			if (beneficiary.getGroup() != null
					&& beneficiary.getGroup().getSociety() != null
					&& beneficiary.getGroup().getSociety().getGnDivision() != null
					&& beneficiary.getGroup().getSociety().getGnDivision()
							.getDivision() != null
					&& beneficiary.getGroup().getSociety().getGnDivision()
							.getDivision().getDistrict() != null
					&& beneficiary.getGroup().getSociety().getGnDivision()
							.getDivision().getDistrict().getId() != null) {
				List<Division> divisions = this.divisionService
						.findAllByDistrictId(beneficiary.getGroup()
								.getSociety().getGnDivision().getDivision()
								.getDistrict().getId());
				model.addAttribute("divisions", divisions);
				if (beneficiary.getGroup().getSociety().getGnDivision()
						.getDivision().getId() != null) {
					model.addAttribute("gnDivisions", this.gnDivisionService
							.findAllByDivisionId(beneficiary.getGroup()
									.getSociety().getGnDivision().getDivision()
									.getId()));
				}
				if (beneficiary.getGroup().getSociety().getGnDivision().getId() != null) {
					model.addAttribute("societies", this.societyService
							.findAllByGNDivisionId(beneficiary.getGroup()
									.getSociety().getGnDivision().getId()));
				}
				if (beneficiary.getGroup().getSociety().getId() != null) {
					model.addAttribute(
							"groups",
							this.groupService.findAllBySocietyId(beneficiary
									.getGroup().getSociety().getId()));
				}
			}
			return "admin/beneficiaries/add";
		} else {
			beneficiary.setBeneficiaryType(BeneficiaryType.GENERAL);
			this.beneficiaryService.save(beneficiary);
			// redirect
			String redirectURL = String.format(
					"redirect:/admin/beneficiaries/%d", beneficiary.getId());
			return redirectURL;
		}
	}

	@RequestMapping(value = "/beneficiaries/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		Beneficiary beneficiary = this.beneficiaryService.findById(id, "group",
				"group.society", "group.society.gnDivision",
				"group.society.gnDivision.division",
				"group.society.gnDivision.division.district");
		List<District> districts = this.districtService.findAll();
		model.addAttribute("districts", districts);
		model.addAttribute(
				"divisions",
				this.divisionService.findAllByDistrictId(beneficiary.getGroup()
						.getSociety().getGnDivision().getDivision()
						.getDistrict().getId()));
		model.addAttribute(
				"gnDivisions",
				this.gnDivisionService.findAllByDivisionId(beneficiary
						.getGroup().getSociety().getGnDivision().getDivision()
						.getId()));
		model.addAttribute(
				"societies",
				this.societyService.findAllByGNDivisionId(beneficiary
						.getGroup().getSociety().getGnDivision().getId()));
		model.addAttribute(
				"groups",
				this.groupService.findAllBySocietyId(beneficiary.getGroup()
						.getSociety().getId()));
		model.addAttribute("districts", districts);
		model.addAttribute("beneficiary", beneficiary);

		return "admin/beneficiaries/edit";
	}

	@RequestMapping(value = "/beneficiaries/{id}/deactivate", method = RequestMethod.GET)
	public String deActivate(@PathVariable Long id, SessionStatus status) {
		Beneficiary beneficiary = this.beneficiaryService.findById(id);
		beneficiary.setStatus(Status.INACTIVE);
		beneficiaryService.update(beneficiary);
		status.setComplete();
		return "redirect:/admin/beneficiaries/list";
	}

	@RequestMapping(value = "/beneficiaries/{id}/activate", method = RequestMethod.GET)
	public String activate(@PathVariable Long id, SessionStatus status) {
		Beneficiary beneficiary = this.beneficiaryService.findById(id);
		beneficiary.setStatus(Status.ACTIVE);
		beneficiaryService.update(beneficiary);
		status.setComplete();
		return "redirect:/admin/beneficiaries/list";
	}

	@RequestMapping(value = "/beneficiaries/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(@ModelAttribute Beneficiary beneficiary,
			BindingResult result, Model model) {
		this.beneficiaryValidator.validate(beneficiary, result);
		if (result.hasErrors()) {
			List<District> districts = this.districtService.findAll();
			model.addAttribute("districts", districts);
			if (beneficiary.getGroup() != null
					&& beneficiary.getGroup().getSociety() != null
					&& beneficiary.getGroup().getSociety().getGnDivision() != null
					&& beneficiary.getGroup().getSociety().getGnDivision()
							.getDivision() != null
					&& beneficiary.getGroup().getSociety().getGnDivision()
							.getDivision().getDistrict() != null
					&& beneficiary.getGroup().getSociety().getGnDivision()
							.getDivision().getDistrict().getId() != null) {
				List<Division> divisions = this.divisionService
						.findAllByDistrictId(beneficiary.getGroup()
								.getSociety().getGnDivision().getDivision()
								.getDistrict().getId());
				model.addAttribute("divisions", divisions);
				if (beneficiary.getGroup().getSociety().getGnDivision()
						.getDivision().getId() != null) {
					model.addAttribute("gnDivisions", this.gnDivisionService
							.findAllByDivisionId(beneficiary.getGroup()
									.getSociety().getGnDivision().getDivision()
									.getId()));
				}
				if (beneficiary.getGroup().getSociety().getGnDivision().getId() != null) {
					model.addAttribute("societies", this.societyService
							.findAllByGNDivisionId(beneficiary.getGroup()
									.getSociety().getGnDivision().getId()));
				}
				if (beneficiary.getGroup().getSociety().getId() != null) {
					model.addAttribute(
							"groups",
							this.groupService.findAllBySocietyId(beneficiary
									.getGroup().getSociety().getId()));
				}
			}
			return "admin/beneficiaries/edit";
		} else {
			beneficiary.setBeneficiaryType(BeneficiaryType.GENERAL);
			this.beneficiaryService.update(beneficiary);
			// redirect
			String redirectURL = String.format(
					"redirect:/admin/beneficiaries/%d", beneficiary.getId());
			return redirectURL;
		}
	}

	@RequestMapping(value = "/beneficiaries/{id}")
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("beneficiary", this.beneficiaryService.findById(id,
				"group", "group.society", "group.society.gnDivision",
				"group.society.gnDivision.division",
				"group.society.gnDivision.division.district", "familyMembers"));
		return "admin/beneficiaries/show";
	}

	@RequestMapping(value = "/beneficiaries/search", method = RequestMethod.GET)
	public String search(
			@ModelAttribute("beneficiarySearch") BeneficiarySearch beneficiarySearch,
			Model model, Pager pager) {
		model.addAttribute("pager", this.beneficiaryService
				.findByBenificiarySearchCriteria(beneficiarySearch, pager));
		model.addAttribute("societies", this.societyService.findAll());
		model.addAttribute("districts", this.districtService.findAll());
		model.addAttribute("divisions", this.divisionService.findAll());
		model.addAttribute("gnDivisions", this.gnDivisionService.findAll());
		model.addAttribute("groups", this.groupService.findAll());
		return "admin/beneficiaries/list";
	}
}
