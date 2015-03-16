package com.suwadi.web.controller.accounting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.suwadi.domain.Vendor;
import com.suwadi.domain.Status;
import com.suwadi.service.VendorService;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.validator.VendorValidator;



@Controller
@SessionAttributes("vendor")
@RequestMapping(value = "/accounting/vendors")
public class VendorsController {
	private VendorService vendorService;
	private VendorValidator vendorValidator;
	
	@Autowired
	public void setVendorService(VendorService vendorServices,
			VendorValidator vendorValidator) {
		this.vendorService = vendorServices;
		this.vendorValidator = vendorValidator;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Pager pager) {
		model.addAttribute("pager", this.vendorService.findAllActiveVendors(pager));
		return "/accounting/vendors/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		Vendor vendor = new Vendor();
		model.addAttribute("vendor", vendor);

		return "/accounting/vendors/add";
	}
	
	@RequestMapping(value = "processAdd", method = RequestMethod.POST)
	public String processAdd(@ModelAttribute("vendor") Vendor vendor,
			BindingResult result, SessionStatus status, Model model) {
		this.vendorValidator.validate(vendor, result);
		if (result.hasErrors()) {
			model.addAttribute("vendor", vendor);

			return "accounting/vendors/add";
		} else {
			vendorService.save(vendor);
			status.setComplete();
			
			//flash.info("district.added.success");
			return "redirect:/accounting/vendors/list";
		}
	}
	
	@RequestMapping(value = "/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(@ModelAttribute("vendor") Vendor vendor,
			BindingResult result, SessionStatus status, Model model) {
		this.vendorValidator.validate(vendor, result);
		if (result.hasErrors()) {
			model.addAttribute("vendor", vendor);
			return "accounting/vendors/edit";
		} else {
			vendorService.update(vendor);
			status.setComplete();
			
			//flash.info("district.added.success");
			return "redirect:/accounting/vendors/list";
		}
	}
	
	@RequestMapping(value = "/{id}")
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("vendor", this.vendorService.findById(id));
		return "accounting/vendors/show";
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		Vendor vendor = this.vendorService.findById(id);
		model.addAttribute("vendor", vendor);

		return "accounting/vendors/edit";
	}
	
	@RequestMapping(value = "/{id}/deactivate", method = RequestMethod.GET)
	public String deActivate(@PathVariable Long id,SessionStatus status) {
		Vendor vendor = this.vendorService.findById(id);
		vendor.setStatus(Status.INACTIVE);
		this.vendorService.update(vendor);
		status.setComplete();
		return "redirect:/accounting/vendors/list";
	}
	
}