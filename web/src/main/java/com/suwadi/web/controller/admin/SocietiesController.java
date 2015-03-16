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
import com.suwadi.domain.Society;
import com.suwadi.service.DistrictService;
import com.suwadi.service.DivisionService;
import com.suwadi.service.GNDivisionService;
import com.suwadi.service.GroupService;
import com.suwadi.service.SocietyService;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.validator.SocietyValidator;

@Controller
@RequestMapping(value = "/admin")
public class SocietiesController {
	private GNDivisionService gnDivisionService;
	private SocietyService societyService;
	private SocietyValidator societyValidator;
	private DistrictService districtService;
	private DivisionService divisionService;
	private GroupService groupService;

	@Autowired
	public SocietiesController(GNDivisionService gnDivisionService,
			SocietyService societyService, SocietyValidator societyValidator,
			DistrictService districtService, DivisionService divisionService,
			GroupService groupService) {
		this.gnDivisionService = gnDivisionService;
		this.societyService = societyService;
		this.societyValidator = societyValidator;
		this.districtService = districtService;
		this.divisionService = divisionService;
		this.groupService = groupService;
	}

	@RequestMapping(value = "/societies/list", method = RequestMethod.GET)
	public String list(Model model, Pager pager) {
		model.addAttribute("pager", this.societyService.getAllSocieties(pager));
		return "admin/societies/list";
	}

	@RequestMapping(value = "/societies/add", method = RequestMethod.GET)
	public String add(Model model) {
		List<District> districts = this.districtService.findAll();

		model.addAttribute("districts", districts);
		model.addAttribute("divisions", new ArrayList<Division>());
		model.addAttribute("gnDivisions", new ArrayList<GNDivision>());

		Society society = new Society();
		model.addAttribute("society", society);

		return "admin/societies/add";
	}

	@RequestMapping(value = "/societies/processAdd", method = RequestMethod.POST)
	public String processAdd(@ModelAttribute("society") Society society,
			BindingResult result, SessionStatus status, Model model) {
		this.societyValidator.validate(society, result);
		if (result.hasErrors()) {
			List<District> districts = this.districtService.findAll();
			if (society.getGnDivision() != null
					&& society.getGnDivision().getDivision() != null
					&& society.getGnDivision().getDivision().getDistrict() != null
					&& society.getGnDivision().getDivision().getDistrict()
							.getId() != null) {
				model.addAttribute(
						"divisions",
						this.divisionService.findAllByDistrictId(society
								.getGnDivision().getDivision().getDistrict()
								.getId()));
			}
			if (society.getGnDivision() != null
					&& society.getGnDivision().getDivision() != null
					&& society.getGnDivision().getDivision().getId() != null) {
				model.addAttribute(
						"gnDivisions",
						this.gnDivisionService.findAllByDivisionId(society
								.getGnDivision().getDivision().getId()));
			}
			model.addAttribute("districts", districts);
			return "admin/societies/add";
		} else {
			society.getPresident().setSociety(society);
			society.getSecretary().setSociety(society);
			society.getTreasurer().setSociety(society);

			society.getPresident()
					.setBeneficiaryType(BeneficiaryType.PRESIDENT);
			society.getSecretary()
					.setBeneficiaryType(BeneficiaryType.SECRETARY);
			society.getTreasurer()
					.setBeneficiaryType(BeneficiaryType.TREASURER);

			societyService.save(society);
			status.setComplete();

			// redirect
			String redirectURL = String.format("redirect:/admin/societies/%d",
					society.getId());
			return redirectURL;
		}
	}

	@RequestMapping(value = "/societies/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model, Pager pager) {
		model.addAttribute("society", this.societyService.findById(id,
				"gnDivision", "gnDivision.division",
				"gnDivision.division.district", "president", "secretary",
				"treasurer"));
		model.addAttribute("pager",
				this.groupService.findAllBySocietyId(id, pager));
		return "admin/societies/show";
	}

	@RequestMapping(value = "/societies/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		Society society = this.societyService.findById(id, "gnDivision",
				"gnDivision.division", "gnDivision.division.district",
				"president", "secretary", "treasurer");

		List<District> districts = this.districtService.findAll();
		List<Division> divisions = this.divisionService
				.findAllByDistrictId(society.getGnDivision().getDivision()
						.getDistrict().getId());
		List<GNDivision> gnDivisions = this.gnDivisionService
				.findAllByDivisionId(society.getGnDivision().getDivision()
						.getId());

		model.addAttribute("districts", districts);
		model.addAttribute("divisions", divisions);
		model.addAttribute("gnDivisions", gnDivisions);
		model.addAttribute("society", society);

		return "admin/societies/edit";
	}

	@RequestMapping(value = "/societies/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(@PathVariable Long id,
			@ModelAttribute("society") Society society, BindingResult result,
			SessionStatus status, Model model) {
		// validation
		this.societyValidator.validate(society, result);
		if (result.hasErrors()) {
			List<District> districts = this.districtService.findAll();
			if (society.getGnDivision() != null
					&& society.getGnDivision().getDivision() != null
					&& society.getGnDivision().getDivision().getDistrict() != null
					&& society.getGnDivision().getDivision().getDistrict()
							.getId() != null) {
				model.addAttribute(
						"divisions",
						this.divisionService.findAllByDistrictId(society
								.getGnDivision().getDivision().getDistrict()
								.getId()));
			}
			if (society.getGnDivision() != null
					&& society.getGnDivision().getDivision() != null
					&& society.getGnDivision().getDivision().getId() != null) {
				model.addAttribute(
						"gnDivisions",
						this.gnDivisionService.findAllByDivisionId(society
								.getGnDivision().getDivision().getId()));
			}
			model.addAttribute("districts", districts);
			return "admin/societies/edit";
		} else {
			societyService.update(society);
		}
		// redirect
		String redirectURL = String.format("redirect:/admin/societies/%d", id);
		return redirectURL;
	}

	@RequestMapping(value = "/societies/getAllSocietiesJSONByGNDivisionId", method = RequestMethod.GET)
	public @ResponseBody
	List<com.suwadi.web.json.response.Society> getAllSocietiesJSONByGNDivisionId(
			@RequestParam Long gnDivisionId) {
		return this.societyService
				.getAllSocietiesJSONByGNDivisionId(gnDivisionId);
	}
}
