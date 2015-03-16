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
import com.suwadi.domain.PaySchedule;
import com.suwadi.domain.Payroll;
import com.suwadi.service.EmploymentService;
import com.suwadi.service.PayScheduleService;
import com.suwadi.service.PayrollService;
import com.suwadi.web.model.payroll.PayrollForm;
import com.suwadi.web.model.payroll.PayrollSelectEmployeesForm;
import com.suwadi.web.pagination.Pager;
import com.suwadi.web.validator.PayrollValidator;

@Controller
public class PayrollsController {
	private PayScheduleService payScheduleService;
	private EmploymentService employmentService;
	private PayrollService payrollService;
	private PayrollValidator payrollValidator;

	@Autowired
	public void setPayScheduleService(PayScheduleService payScheduleService) {
		this.payScheduleService = payScheduleService;
	}

	@Autowired
	public void setEmploymentService(EmploymentService employmentService) {
		this.employmentService = employmentService;
	}

	@Autowired
	public void setPayrollService(PayrollService payrollService) {
		this.payrollService = payrollService;
	}

	@Autowired
	public void setPayrollValidator(PayrollValidator payrollValidator) {
		this.payrollValidator = payrollValidator;
	}

	@RequestMapping(value = "payroll/payrolls/list", method = RequestMethod.GET)
	public String list(Model model, Pager pager) {
		model.addAttribute("pager", this.payrollService.paginate(pager,
				new String[] { "paySchedule" }));
		return "/payroll/payrolls/list";
	}

	@RequestMapping(value = "payroll/payrolls/add", method = RequestMethod.GET)
	public String add(Model model) {
		List<PaySchedule> paySchedules = this.payScheduleService.findAll();
		model.addAttribute("paySchedules", paySchedules);
		model.addAttribute("payrollForm", new PayrollForm());

		return "/payroll/payrolls/add";
	}

	@RequestMapping(value = "payroll/payrolls/processAdd", method = RequestMethod.POST)
	public String processAdd(
			@ModelAttribute("payrollForm") PayrollForm payrollForm,
			BindingResult result, Model model) {
		payrollValidator.validate(payrollForm, result);
		if (!result.hasErrors()) {
			this.payrollService.createPayroll(payrollForm);
			return "redirect:/payroll/payrolls/list";
		} else {
			List<PaySchedule> paySchedules = this.payScheduleService.findAll();
			model.addAttribute("paySchedules", paySchedules);
			return "/payroll/payrolls/add";
		}
	}

	@RequestMapping(value = "payroll/payrolls/selectPaySchedule", method = RequestMethod.GET)
	public String selectPaySchedule(Model model) {
		List<PaySchedule> paySchedules = this.payScheduleService.findAll();
		model.addAttribute("paySchedules", paySchedules);
		model.addAttribute("payrollForm", new PayrollForm());

		return "/payroll/payrolls/selectPaySchedule";
	}

	@RequestMapping(value = "payroll/payrolls/processSelectPaySchedule", method = RequestMethod.POST)
	public String processSelectPaySchedule(
			@ModelAttribute("payrollForm") PayrollForm payrollForm,
			BindingResult result, Model model) {
		payrollValidator.validate(payrollForm, result);
		if (!result.hasErrors()) {
			List<Employment> employments = this.employmentService
					.findAllByPayScheduleId(payrollForm.getPayScheduleId());
			Payroll payroll = this.payrollService.createPayroll(payrollForm);
			PayrollSelectEmployeesForm payrollSelectEmployeesForm = new PayrollSelectEmployeesForm();
			payrollSelectEmployeesForm.setFromDate(payrollForm.getFromDate());
			payrollSelectEmployeesForm.setToDate(payrollForm.getToDate());
			payrollSelectEmployeesForm.setPayScheduleId(payrollForm
					.getPayScheduleId());
			payrollSelectEmployeesForm.setPayrollId(payroll.getId());

			model.addAttribute("payrollForm", payrollForm);
			model.addAttribute("employments", employments);
			model.addAttribute("payrollSelectEmployeesForm",
					payrollSelectEmployeesForm);

			return "/payroll/payrolls/selectEmployees";
		} else {
			List<PaySchedule> paySchedules = this.payScheduleService.findAll();
			model.addAttribute("paySchedules", paySchedules);
			return "/payroll/payrolls/selectPaySchedule";
		}

	}

	@RequestMapping(value = "payroll/payrolls/processGeneratePayroll", method = RequestMethod.POST)
	public String processGeneratePayroll(
			@ModelAttribute("payrollSelectEmployeesForm") PayrollSelectEmployeesForm payrollSelectEmployeesForm,
			BindingResult result, Model model) {
		for (Long employmentId : payrollSelectEmployeesForm.getEmploymentIds()) {
			this.employmentService.createEmploymentSalaryHeader(null,
					payrollSelectEmployeesForm.getPayScheduleId(),
					payrollSelectEmployeesForm.getFromDate(),
					payrollSelectEmployeesForm.getToDate(), employmentId);
		}
		return "/payroll/payrolls/selectEmployees";
	}

	@RequestMapping(value = "payroll/payrolls/{id}/processPayroll", method = RequestMethod.POST)
	public String processPayroll(@PathVariable("id") Long id) {
		this.payrollService.processPayroll(id);
		return String.format(
				"redirect:/payroll/payrolls/%s/employmentSalaryHeaders/list",
				id);
	}
}
