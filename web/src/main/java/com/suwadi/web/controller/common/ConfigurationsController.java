package com.suwadi.web.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suwadi.service.ConfigurationService;
import com.suwadi.web.model.common.ConfigurationForm;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.validator.ConfigurationValidator;

@Controller
public class ConfigurationsController {
	private ConfigurationService configurationService;
	private ConfigurationValidator configurationValidator;

	@Autowired
	public void setConfigurationService(
			ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	@Autowired
	public void setConfigurationValidator(
			ConfigurationValidator configurationValidator) {
		this.configurationValidator = configurationValidator;
	}

	@RequestMapping(value = "/common/configurations/list", method = RequestMethod.GET)
	public String list(Model model, Pager pager) {
		model.addAttribute("pager", this.configurationService.paginate(pager));
		return "common/configurations/list";
	}

	@RequestMapping(value = "/common/configurations/add", method = RequestMethod.GET)
	public String add(Model model) {
		ConfigurationForm configuration = new ConfigurationForm();
		model.addAttribute("configuration", configuration);
		return "common/configurations/add";
	}

	@RequestMapping(value = "/common/configurations/processAdd", method = RequestMethod.POST)
	public String processAdd(
			@ModelAttribute("configuration") ConfigurationForm configuration,
			BindingResult result, Model model) {
		this.configurationValidator.validate(configuration, result);
		if (result.hasErrors()) {
			return "common/configurations/add";
		} else {
			this.configurationService.save(configuration);
			return "redirect:/common/configurations/list";
		}
	}

	@RequestMapping(value = "/common/configurations/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, Model model) {
		ConfigurationForm configuration = this.configurationService
				.findByConfigurationId(id);
		model.addAttribute("configuration", configuration);

		return "common/configurations/edit";
	}

	@RequestMapping(value = "/common/configurations/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(
			@ModelAttribute("configuration") ConfigurationForm configuration,
			BindingResult result, Model model) {
		this.configurationValidator.validate(configuration, result);
		if (result.hasErrors()) {
			return "common/configurations/edit";
		} else {
			this.configurationService.update(configuration);
			return "redirect:/common/configurations/list";
		}
	}
}
