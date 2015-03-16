package com.suwadi.web.controller.admin;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.suwadi.domain.Division;
import com.suwadi.domain.GNDivision;
import com.suwadi.service.DivisionService;
import com.suwadi.service.GNDivisionService;
import com.suwadi.service.SocietyService;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.validator.GNDivisionValidator;

@Controller
@SessionAttributes("gnDivision")
@RequestMapping(value = "/admin/")
public class GNDivisionsController {
	private GNDivisionValidator gnDivisionValidator;
	private DivisionService divisionService;
	private GNDivisionService gnDivisionService;
	private SocietyService societyService;

	@Autowired
	public GNDivisionsController(DivisionService divisionService,
			GNDivisionValidator gnDivisionValidator,
			GNDivisionService gnDivisionService, SocietyService societyService) {
		this.divisionService = divisionService;
		this.gnDivisionValidator = gnDivisionValidator;
		this.gnDivisionService = gnDivisionService;
		this.societyService = societyService;
	}

	@RequestMapping(value = "divisions/getAllGNDivisionsJSONByDivisionId")
	public @ResponseBody
	List<com.suwadi.web.json.response.GNDivision> getAllGNDivisionsJSONByDivisionId(
			@RequestParam(value = "divisionId", required = true) Long divisionId) {
		return this.gnDivisionService
				.getAllGNDivisionsJSONByDivisionId(divisionId);
	}

	@RequestMapping(value = "divisions/{divisionId}/gnDivisions/add", method = RequestMethod.GET)
	public String add(@PathVariable Long divisionId, Model model) {
		Division division = this.divisionService.findById(divisionId, "district");
		GNDivision gnDivision = new GNDivision();
		gnDivision.setDivision(division);

		model.addAttribute("gnDivision", gnDivision);

		return "admin/gnDivisions/add";
	}

	@RequestMapping(value = "divisions/{divisionId}/gnDivisions/processAdd", method = RequestMethod.POST)
	public String processAdd(@PathVariable Long divisionId,
			@ModelAttribute("gnDivision") GNDivision gnDivision,
			BindingResult result, SessionStatus status, Model model) {
		this.gnDivisionValidator.validate(gnDivision, result);
		if (result.hasErrors()) {
			model.addAttribute("gnDivision", gnDivision);

			return "admin/gnDivisions/add";
		} else {
			gnDivisionService.save(gnDivision);
			status.setComplete();

			// redirect
			String redirectURL = String.format(
					"redirect:/admin/districts/%d/divisions/%d", gnDivision
							.getDivision().getDistrict().getId(), divisionId);
			return redirectURL;
		}
	}

	@RequestMapping(value = "divisions/{divisionId}/gnDivisions/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		GNDivision gnDivision = this.gnDivisionService.findById(id, "division", "division.district");
		model.addAttribute("gnDivision", gnDivision);

		return "admin/gnDivisions/edit";
	}

	@RequestMapping(value = "divisions/{divisionId}/gnDivisions/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(@PathVariable Long divisionId,
			@ModelAttribute("gnDivision") GNDivision gnDivision,
			BindingResult result, SessionStatus status, Model model) {
		this.gnDivisionValidator.validate(gnDivision, result);
		if (result.hasErrors()) {
			model.addAttribute("gnDivision", gnDivision);

			return "admin/gnDivisions/edit";
		} else {
			gnDivisionService.update(gnDivision);
			status.setComplete();

			String redirectURL = String.format(
					"redirect:/admin/districts/%d/divisions/%d", gnDivision
							.getDivision().getDistrict().getId(), divisionId);
			return redirectURL;
		}
	}

	@RequestMapping(value = "divisions/{divisionId}/gnDivisions/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model, Pager pager) {
		GNDivision gnDivision = this.gnDivisionService.findById(id, "division", "division.district");
		model.addAttribute("gnDivision", gnDivision);
		model.addAttribute("pager",
				this.societyService.getSocietiesByGNDivisionId(id, pager));

		return "admin/gnDivisions/show";
	}

	@RequestMapping(value = "gnDivisions/allGNDivisions", method = RequestMethod.GET)
	public String allGNDivisions(Model model, Pager pager) {
		model.addAttribute("pager", gnDivisionService.paginate(pager));
		return "admin/gnDivisions/allGNDivisions";
	}
}
