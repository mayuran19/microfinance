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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.suwadi.domain.BeneficiaryType;
import com.suwadi.domain.District;
import com.suwadi.domain.Division;
import com.suwadi.domain.GNDivision;
import com.suwadi.domain.Group;
import com.suwadi.domain.Society;
import com.suwadi.service.BeneficiaryService;
import com.suwadi.service.DistrictService;
import com.suwadi.service.DivisionService;
import com.suwadi.service.GNDivisionService;
import com.suwadi.service.GroupService;
import com.suwadi.service.SocietyService;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.validator.GroupValidator;

@Controller
@RequestMapping(value = "/admin/")
public class GroupsController {
	private DistrictService districtService;
	private DivisionService divisionService;
	private GNDivisionService gnDivisionService;
	private GroupService groupService;
	private SocietyService societyService;
	private GroupValidator groupValidator;
	private BeneficiaryService beneficiaryService;

	@Autowired
	public GroupsController(GroupService groupService,
			SocietyService societyService, DistrictService districtService,
			DivisionService divisionService,
			GNDivisionService gnDivisionService, GroupValidator groupValidator,
			BeneficiaryService beneficiaryService) {
		this.groupService = groupService;
		this.societyService = societyService;
		this.districtService = districtService;
		this.divisionService = divisionService;
		this.gnDivisionService = gnDivisionService;
		this.groupValidator = groupValidator;
		this.beneficiaryService = beneficiaryService;
	}

	@RequestMapping(value = "/groups/getAllGroupsJSONBySocietyId", method = RequestMethod.GET)
	public @ResponseBody
	List<com.suwadi.web.json.response.Group> getAllGroupsJSONBySocietyId(
			@RequestParam Long societyId) {
		return this.groupService.getAllGroupsJSONBySocietyId(societyId);
	}

	@RequestMapping(value = "/groups/list", method = RequestMethod.GET)
	public String list(Model model, Pager pager) {
		model.addAttribute(
				"pager",
				this.groupService.paginate(pager, new String[] { "society",
						"society.gnDivision", "society.gnDivision.division",
						"society.gnDivision.division.district" }));
		return "admin/groups/list";
	}

	@RequestMapping(value = "/groups/add", method = RequestMethod.GET)
	public String add(Model model) {
		List<District> districts = this.districtService.findAll();
		Group group = new Group();
		model.addAttribute("districts", districts);
		model.addAttribute("divisions", new ArrayList<Division>());
		model.addAttribute("gnDivisions", new ArrayList<GNDivision>());
		model.addAttribute("societies", new ArrayList<Society>());
		model.addAttribute("group", group);
		return "admin/groups/add";
	}

	@RequestMapping(value = "/groups/processAdd", method = RequestMethod.POST)
	public String processAdd(@ModelAttribute("group") Group group,
			BindingResult result, SessionStatus status, Model model) {
		this.groupValidator.validate(group, result);
		if (result.hasErrors()) {
			List<District> districts = this.districtService.findAll();
			model.addAttribute("districts", districts);
			if (group.getSociety() != null
					&& group.getSociety().getGnDivision() != null
					&& group.getSociety().getGnDivision().getDivision() != null
					&& group.getSociety().getGnDivision().getDivision()
							.getDistrict() != null
					&& group.getSociety().getGnDivision().getDivision()
							.getDistrict().getId() != null) {
				List<Division> divisions = this.divisionService
						.findAllByDistrictId(group.getSociety().getGnDivision()
								.getDivision().getDistrict().getId());
				model.addAttribute("divisions", divisions);
				if (group.getSociety().getGnDivision().getDivision().getId() != null) {
					model.addAttribute(
							"gnDivisions",
							this.gnDivisionService.findAllByDivisionId(group
									.getSociety().getGnDivision().getDivision()
									.getId()));
				}
				if (group.getSociety().getGnDivision().getId() != null) {
					model.addAttribute(
							"societies",
							this.societyService.findAllByGNDivisionId(group
									.getSociety().getGnDivision().getId()));
				}
			}
			return "admin/groups/add";
		} else {
			group.getPresident().setGroup(group);
			group.getPresident().setBeneficiaryType(
					BeneficiaryType.GROUP_PRESIDENT);
			group.getEccPresident().setGroup(group);
			group.getEccPresident().setBeneficiaryType(
					BeneficiaryType.GROUP_ECC_PRESIDENT);
			this.groupService.save(group);
			// redirect
			String redirectURL = String.format("redirect:/admin/groups/%d",
					group.getId());
			return redirectURL;
		}
	}

	@RequestMapping(value = "/groups/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model, Pager pager) {
		model.addAttribute("group", this.groupService.findById(id, "society",
				"society.gnDivision", "society.gnDivision.division",
				"society.gnDivision.division.district", "president",
				"eccPresident"));
		model.addAttribute("pager",
				this.beneficiaryService.findAllByGroupId(id, pager));
		return "admin/groups/show";
	}

	@RequestMapping(value = "/groups/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		Group group = this.groupService.findById(id, "society",
				"society.gnDivision", "society.gnDivision.division",
				"society.gnDivision.division.district", "president",
				"eccPresident");
		List<District> districts = this.districtService.findAll();
		List<Division> divisions = this.divisionService
				.findAllByDistrictId(group.getSociety().getGnDivision()
						.getDivision().getDistrict().getId());
		List<GNDivision> gnDivisions = this.gnDivisionService
				.findAllByDivisionId(group.getSociety().getGnDivision()
						.getDivision().getId());
		List<Society> societies = this.societyService
				.findAllByGNDivisionId(group.getSociety().getGnDivision()
						.getId());
		model.addAttribute("group", group);
		model.addAttribute("districts", districts);
		model.addAttribute("divisions", divisions);
		model.addAttribute("gnDivisions", gnDivisions);
		model.addAttribute("societies", societies);

		return "admin/groups/edit";
	}

	@RequestMapping(value = "/groups/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(@ModelAttribute("group") Group group,
			BindingResult result, Model model) {
		this.groupValidator.validate(group, result);
		if (result.hasErrors()) {
			List<District> districts = this.districtService.findAll();
			model.addAttribute("districts", districts);
			if (group.getSociety() != null
					&& group.getSociety().getGnDivision() != null
					&& group.getSociety().getGnDivision().getDivision() != null
					&& group.getSociety().getGnDivision().getDivision()
							.getDistrict() != null
					&& group.getSociety().getGnDivision().getDivision()
							.getDistrict().getId() != null) {
				List<Division> divisions = this.divisionService
						.findAllByDistrictId(group.getSociety().getGnDivision()
								.getDivision().getDistrict().getId());
				model.addAttribute("divisions", divisions);
				if (group.getSociety().getGnDivision().getDivision().getId() != null) {
					model.addAttribute(
							"gnDivisions",
							this.gnDivisionService.findAllByDivisionId(group
									.getSociety().getGnDivision().getDivision()
									.getId()));
				}
				if (group.getSociety().getGnDivision().getId() != null) {
					model.addAttribute(
							"societies",
							this.societyService.findAllByGNDivisionId(group
									.getSociety().getGnDivision().getId()));
				}
			}
			return "admin/groups/edit";
		} else {
			this.groupService.update(group);
			// redirect
			String redirectURL = String.format("redirect:/admin/groups/%d",
					group.getId());
			return redirectURL;
		}
	}
}
