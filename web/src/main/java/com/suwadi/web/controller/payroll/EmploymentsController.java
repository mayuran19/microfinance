package com.suwadi.web.controller.payroll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suwadi.domain.Employment;
import com.suwadi.domain.EmploymentStatus;
import com.suwadi.service.EmployeeService;
import com.suwadi.service.EmploymentService;
import com.suwadi.service.EmploymentStatusService;
import com.suwadi.web.model.payroll.EmploymentForm;
import com.suwadi.web.validator.EmploymentValidator;

@Controller
public class EmploymentsController {
	private EmploymentService employmentService;
	private EmployeeService employeeService;
	private EmploymentStatusService employmentStatusService;
	private EmploymentValidator employmentValidator;

	@Autowired
	public void setEmploymentService(EmploymentService employmentService) {
		this.employmentService = employmentService;
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Autowired
	public void setEmploymentStatusService(
			EmploymentStatusService employmentStatusService) {
		this.employmentStatusService = employmentStatusService;
	}

	@Autowired
	public void setEmploymentValidator(EmploymentValidator employmentValidator) {
		this.employmentValidator = employmentValidator;
	}

	@RequestMapping(value = "payroll/employees/{employeeId}/employments/list", method = RequestMethod.GET)
	public String list(@PathVariable("employeeId") Long employeeId, Model model) {
		List<Employment> employments = this.employmentService
				.findByEmployeeId(employeeId);
		model.addAttribute("employments", employments);
		return "/payroll/employments/list";
	}

	@RequestMapping(value = "payroll/employees/{employeeId}/employments/add", method = RequestMethod.GET)
	public String add(@PathVariable("employeeId") Long employeeId, Model model) {
		EmploymentForm employmentForm = new EmploymentForm();
		employmentForm.setEmployeeId(employeeId);
		List<EmploymentStatus> employmentStatus = this.employmentStatusService
				.findAll();
		model.addAttribute("employmentForm", employmentForm);
		model.addAttribute("employmentStatus", employmentStatus);
		return "/payroll/employments/add";
	}

	@RequestMapping(value = "payroll/employees/{employeeId}/employments/processAdd", method = RequestMethod.POST)
	public String processAdd(
			@ModelAttribute("employmentForm") EmploymentForm employmentForm,
			BindingResult result, Model model) {
		this.employmentValidator.validate(employmentForm, result);
		if (result.hasErrors()) {
			List<EmploymentStatus> employmentStatus = this.employmentStatusService
					.findAll();
			model.addAttribute("employmentStatus", employmentStatus);
			return String.format("payroll/employees/%s/employments/add",
					employmentForm.getEmployeeId());
		} else {
			this.employmentService.save(employmentForm);
			String url = String.format(
					"redirect:/payroll/employees/%s/employments/list",
					employmentForm.getEmployeeId());
			return url;
		}
	}
}
