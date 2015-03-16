package com.suwadi.web.controller.payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suwadi.service.EmployeeService;
import com.suwadi.web.model.payroll.EmployeeForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.validator.EmployeeValidator;

@Controller
public class EmployeesController {
	private EmployeeService employeeService;
	private EmployeeValidator employeeValidator;

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Autowired
	public void setEmployeeValidator(EmployeeValidator employeeValidator) {
		this.employeeValidator = employeeValidator;
	}

	@RequestMapping(value = "payroll/employees/list", method = RequestMethod.GET)
	public String list(Model model, Pager pager) {
		PagedResultSet pagedResultSet = this.employeeService
				.findAllEmployees(pager);
		model.addAttribute("pager", pagedResultSet);
		return "/payroll/employees/list";
	}

	@RequestMapping(value = "payroll/employees/add", method = RequestMethod.GET)
	public String add(Model model) {
		EmployeeForm employeeForm = new EmployeeForm();
		model.addAttribute("employeeForm", employeeForm);
		return "/payroll/employees/add";
	}

	@RequestMapping(value = "payroll/employees/processAdd", method = RequestMethod.POST)
	public String processAdd(
			@ModelAttribute("employeeForm") EmployeeForm employeeForm,
			BindingResult result, Model model) {
		this.employeeValidator.validate(employeeForm, result);
		if (result.hasErrors()) {
			return "/payroll/employees/add";
		} else {
			this.employeeService.save(employeeForm);
			String url = String.format("redirect:/payroll/employees/list");
			return url;
		}
	}

	@RequestMapping(value = "payroll/employees/{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		EmployeeForm employeeForm = this.employeeService.findByEmployeeId(id);
		model.addAttribute("employeeForm", employeeForm);
		return "/payroll/employees/edit";
	}

	@RequestMapping(value = "payroll/employees/{id}/processEdit", method = RequestMethod.POST)
	public String processEdit(
			@ModelAttribute("employeeForm") EmployeeForm employeeForm,
			BindingResult result, Model model) {
		this.employeeValidator.validate(employeeForm, result);
		if (result.hasErrors()) {
			return "/payroll/employees/edit";
		} else {
			this.employeeService.update(employeeForm);
			String url = String.format("redirect:/payroll/employees/list");
			return url;
		}
	}
}
