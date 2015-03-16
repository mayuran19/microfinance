package com.suwadi.web.controller.payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.suwadi.service.EmploymentSalaryDetailService;
import com.suwadi.service.EmploymentSalaryHeaderService;
import com.suwadi.web.pagination.Pager;

@Controller
public class EmploymentSalaryHeadersController {
	private EmploymentSalaryHeaderService employmentSalaryHeaderService;
	private EmploymentSalaryDetailService employmentSalaryDetailService;

	@Autowired
	public void setEmploymentSalaryHeaderService(
			EmploymentSalaryHeaderService employmentSalaryHeaderService) {
		this.employmentSalaryHeaderService = employmentSalaryHeaderService;
	}

	@Autowired
	public void setEmploymentSalaryDetailService(
			EmploymentSalaryDetailService employmentSalaryDetailService) {
		this.employmentSalaryDetailService = employmentSalaryDetailService;
	}

	@RequestMapping(value = "payroll/payrolls/{id}/employmentSalaryHeaders/list", method = RequestMethod.GET)
	public String payrollSalaryList(@PathVariable("id") Long id, Model model,
			Pager pager) {
		model.addAttribute("pager",
				employmentSalaryHeaderService.findByPayrollId(id, pager));
		return "/payroll/employmentSalaryHeaders/payrollSalaryList";
	}

	@RequestMapping(value = "payroll/payrolls/{payrollId}/employmentSalaryHeaders/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("payrollId") Long payrollId,
			@PathVariable("id") Long id, Model model, Pager pager) {
		model.addAttribute("employmentSalaryHeader",
				this.employmentSalaryHeaderService.findById(id, "payroll"));
		model.addAttribute("employmentSalaryDetailsEarnings",
				this.employmentSalaryDetailService
						.findAllEarningsByEmploymentSalaryHeaderId(id));
		model.addAttribute("employmentSalaryDetailsReductions",
				this.employmentSalaryDetailService
						.findAllReductionsByEmploymentSalaryHeaderId(id));
		return "/payroll/employmentSalaryHeaders/show";
	}
}
