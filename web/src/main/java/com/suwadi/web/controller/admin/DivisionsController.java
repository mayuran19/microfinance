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

import com.suwadi.domain.District;
import com.suwadi.domain.Division;
import com.suwadi.domain.Status;
import com.suwadi.service.DistrictService;
import com.suwadi.service.DivisionService;
import com.suwadi.service.GNDivisionService;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.validator.DivisionValidator;

@Controller
@SessionAttributes("division")
public class DivisionsController {
	private DistrictService districtService;
	private DivisionValidator divisionValidator;
	private DivisionService divisionService;
	private GNDivisionService gnDivisionService;

	@Autowired
	public DivisionsController(DivisionService divisionService,
			DistrictService districtService,
			DivisionValidator divisionValidator,
			GNDivisionService gnDivisionService) {
		this.divisionService = divisionService;
		this.districtService = districtService;
		this.divisionValidator = divisionValidator;
		this.gnDivisionService = gnDivisionService;
	}

	@RequestMapping(value = "/admin/divisions/getAllDivisionsJSONByDistrictId", method = RequestMethod.GET)
	public @ResponseBody
	List<com.suwadi.web.json.response.Division> getAllDivisionsJSONByDistrictId(
			@RequestParam(value = "districtId", required = true) Long districtId) {
		return divisionService.findAllDivisionsJSONByDistrictId(districtId);
	}

	@RequestMapping(value = "/admin/districts/{districtId}/divisions/add", method = RequestMethod.GET)
	public String add(@PathVariable Long districtId, Model model) {
		District district = this.districtService.findById(districtId);
		Division division = new Division();
		division.setDistrict(district);

		model.addAttribute("division", division);

		return "admin/divisions/add";
	}

	@RequestMapping(value = "/admin/districts/{districtId}/divisions/processAdd", method = RequestMethod.POST)
	public String processAdd(@PathVariable Long districtId,
			@ModelAttribute("division") Division division,
			BindingResult result, SessionStatus status, Model model) {
		this.divisionValidator.validate(division, result);
		if (result.hasErrors()) {
			model.addAttribute("division", division);
			model.addAttribute("districts", districtService.findAll());

			return "admin/divisions/add";
		} else {
			divisionService.save(division);
			status.setComplete();

			// redirect
			String redirectURL = String.format("redirect:/admin/districts/%d",
					districtId);
			return redirectURL;
		}
	}

	@RequestMapping(value = "/admin/districts/{districtId}/divisions/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		Division division = this.divisionService.findById(id, "district");
		model.addAttribute("division", division);

		return "admin/divisions/edit";
	}


	@RequestMapping(value = "/admin/districts/{districtId}/divisions/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(@PathVariable Long districtId,
			@ModelAttribute("division") Division division,
			BindingResult result, SessionStatus status, Model model) {
		this.divisionValidator.validate(division, result);
		if (result.hasErrors()) {
			model.addAttribute("division", division);
			model.addAttribute("districts", districtService.findAll());

			return "admin/divisions/edit";
		} else {
			divisionService.update(division);
			status.setComplete();

			String redirectURL = String.format("redirect:/admin/districts/%d",
					districtId);
			return redirectURL;
		}
	}

	@RequestMapping(value = "/admin/districts/{districtId}/divisions/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model, Pager pager) {
		Division division = this.divisionService.findById(id, "district");

		model.addAttribute("pager",
				this.gnDivisionService.findAllByDivisionId(id, pager));
		model.addAttribute("division", division);

		return "admin/divisions/show";
	}
	
	@RequestMapping(value = "/admin/districts/{districtId}/divisions/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable Long id,@PathVariable Long districtId, Model model,SessionStatus status) {
		Division division = this.divisionService.findById(id);
		division.setStatus(Status.INACTIVE);
		divisionService.update(division);
		status.setComplete();

		String redirectURL = String.format("redirect:/admin/districts/%d",
				districtId);
		return redirectURL;
	}
}
