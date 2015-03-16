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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.suwadi.domain.District;
import com.suwadi.domain.Division;
import com.suwadi.domain.Status;
import com.suwadi.service.DistrictService;
import com.suwadi.service.DivisionService;
import com.suwadi.web.flash.Flash;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.validator.DistrictValidator;

@Controller
@SessionAttributes("district")
@RequestMapping(value = "/admin/districts")
public class DistrictsController {
	private DistrictService districtService;
	private DistrictValidator districtValidator;
	private DivisionService divisionService;
	private Flash flash;

	@Autowired
	public DistrictsController(DistrictService districtService,
			DistrictValidator districtValidator,
			DivisionService divisionService, Flash flash) {
		this.districtService = districtService;
		this.districtValidator = districtValidator;
		this.divisionService = divisionService;
		this.flash = flash;
	}

	@RequestMapping(value = "/getAllDistrictsJSON", method = RequestMethod.GET)
	public @ResponseBody
	List<com.suwadi.web.json.response.District> getAllActiveDistricts() {
		return this.districtService.getAllActiveDistricts();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Pager pager) {
		model.addAttribute("pager", this.districtService.findAllActiveDistrict(pager));
		return "admin/districts/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		District district = new District();

		model.addAttribute("district", district);

		return "admin/districts/add";
	}

	@RequestMapping(value = "processAdd", method = RequestMethod.POST)
	public String processAdd(@ModelAttribute("district") District district,
			BindingResult result, SessionStatus status, Model model) {
		this.districtValidator.validate(district, result);
		if (result.hasErrors()) {
			model.addAttribute("district", district);

			return "admin/districts/add";
		} else {
			districtService.save(district);
			status.setComplete();
			
			//flash.info("district.added.success");
			return "redirect:/admin/districts/list";
		}
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		District district = this.districtService.findById(id);
		model.addAttribute("district", district);

		return "admin/districts/edit";
	}

	@RequestMapping(value = "/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(@ModelAttribute("district") District district,
			BindingResult result, SessionStatus status, Model model) {
		this.districtValidator.validate(district, result);
		if (result.hasErrors()) {
			model.addAttribute("district", district);

			return "admin/districts/edit";
		} else {
			districtService.update(district);
			status.setComplete();
			flash.success("district.edit.success", "mayuran", "time");
			return "redirect:/admin/districts/list";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model, Pager pager) {
		District district = this.districtService.findById(id);

		model.addAttribute("pager",
				this.divisionService.findAllByDistrictId(id, pager));
		model.addAttribute("district", district);

		return "admin/districts/show";
	}
	
	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable Long id,SessionStatus status) {
		District district = this.districtService.findById(id);
		district.setStatus(Status.INACTIVE);
		districtService.update(district);
		status.setComplete();

		return "redirect:/admin/districts/list";
	}
}
