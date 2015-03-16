package com.suwadi.web.controller.payroll;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
import com.suwadi.domain.PayReduction;
import com.suwadi.domain.PaySchedule;
import com.suwadi.domain.PayTax;
import com.suwadi.domain.PayType;
import com.suwadi.domain.SalaryType;
import com.suwadi.service.EmployeeService;
import com.suwadi.service.EmploymentPayReductionService;
import com.suwadi.service.EmploymentPayTaxService;
import com.suwadi.service.EmploymentPayTypeService;
import com.suwadi.service.EmploymentService;
import com.suwadi.service.EmploymentStatusService;
import com.suwadi.service.PayRateService;
import com.suwadi.service.PayReductionService;
import com.suwadi.service.PayScheduleService;
import com.suwadi.service.PayTaxService;
import com.suwadi.service.PayTypeService;
import com.suwadi.web.model.payroll.EmploymentForm;
import com.suwadi.web.model.payroll.EmploymentPayReductionDetailForm;
import com.suwadi.web.model.payroll.EmploymentPayReductionHeaderForm;
import com.suwadi.web.model.payroll.EmploymentPayTaxDetail;
import com.suwadi.web.model.payroll.EmploymentPayTaxHeader;
import com.suwadi.web.model.payroll.EmploymentPayTypeForm;
import com.suwadi.web.model.payroll.EmploymentPayTypesForm;
import com.suwadi.web.model.payroll.EmploymentPayrollSetupForm;
import com.suwadi.web.validator.EmploymentPayReductionValidator;
import com.suwadi.web.validator.EmploymentPayTypeValidator;
import com.suwadi.web.validator.EmploymentValidator;

@Controller
public class EmploymentPayrollSetupsController {
	private PayTypeService payTypeService;
	private PayTaxService payTaxService;
	private EmploymentService employmentService;
	private EmployeeService employeeService;
	private PayRateService payRateService;
	private PayReductionService payReductionService;
	private EmploymentValidator employmentValidator;
	private EmploymentStatusService employmentStatusService;
	private ActiveTab activeTab = ActiveTab.EMPLOYMENT;
	private EmploymentPayTypeService employmentPayTypeService;
	private EmploymentPayTaxService employmentPayTaxService;
	private EmploymentPayReductionService employmentPayReductionService;
	private EmploymentPayTypeValidator employmentPayTypeValidator;
	private EmploymentPayReductionValidator employmentPayReductionValidator;
	private PayScheduleService payScheduleService;

	public enum ActiveTab {
		EMPLOYMENT, PAYTYPE, PAYTAX, PAYREDUCTION;
	};

	@Autowired
	public void setPayTypeService(PayTypeService payTypeService) {
		this.payTypeService = payTypeService;
	}

	@Autowired
	public void setPayTaxService(PayTaxService payTaxService) {
		this.payTaxService = payTaxService;
	}

	@Autowired
	public void setEmploymentService(EmploymentService employmentService) {
		this.employmentService = employmentService;
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Autowired
	public void setPayRateService(PayRateService payRateService) {
		this.payRateService = payRateService;
	}

	@Autowired
	public void setPayReductionService(PayReductionService payReductionService) {
		this.payReductionService = payReductionService;
	}

	@Autowired
	public void setEmploymentValidator(EmploymentValidator employmentValidator) {
		this.employmentValidator = employmentValidator;
	}

	@Autowired
	public void setEmploymentStatusService(
			EmploymentStatusService employmentStatusService) {
		this.employmentStatusService = employmentStatusService;
	}

	@Autowired
	public void setEmploymentPayTypeService(
			EmploymentPayTypeService employmentPayTypeService) {
		this.employmentPayTypeService = employmentPayTypeService;
	}

	@Autowired
	public void setEmploymentPayTaxService(
			EmploymentPayTaxService employmentPayTaxService) {
		this.employmentPayTaxService = employmentPayTaxService;
	}

	@Autowired
	public void setEmploymentPayReductionService(
			EmploymentPayReductionService employmentPayReductionService) {
		this.employmentPayReductionService = employmentPayReductionService;
	}

	@Autowired
	public void setEmploymentPayTypeValidator(
			EmploymentPayTypeValidator employmentPayTypeValidator) {
		this.employmentPayTypeValidator = employmentPayTypeValidator;
	}

	@Autowired
	public void setEmploymentPayReductionValidator(
			EmploymentPayReductionValidator employmentPayReductionValidator) {
		this.employmentPayReductionValidator = employmentPayReductionValidator;
	}

	@Autowired
	public void setPayScheduleService(PayScheduleService payScheduleService) {
		this.payScheduleService = payScheduleService;
	}

	@RequestMapping(value = "payroll/employments/{employmentId}/employmentPayrollSetups/add", method = RequestMethod.GET)
	public String add(@PathVariable("employmentId") Long employmentId,
			Model model) {
		Employment employment = this.employmentService.findById(employmentId,
				"employee");
		List<PayType> payTypes = this.payTypeService.findAllFixedPayTypes();
		List<PayTax> payTaxs = this.payTaxService.findAll();
		List<PayReduction> payReductions = this.payReductionService
				.findAllFixedPayReductions();

		EmploymentPayrollSetupForm employmentPayrollSetupForm = new EmploymentPayrollSetupForm();
		employmentPayrollSetupForm.setEmploymentId(employmentId);
		employmentPayrollSetupForm.setEmployeeId(employment.getEmployee()
				.getId());

		model.addAttribute("employment", employment);
		model.addAttribute("payTypes", payTypes);
		model.addAttribute("payTaxs", payTaxs);
		model.addAttribute("payReductions", payReductions);
		model.addAttribute("employmentPayrollSetupForm",
				employmentPayrollSetupForm);

		return "/payroll/employmentPayrollSetups/add";
	}

	@RequestMapping(value = "payroll/employees/{employeeId}/employmentPayrollSetups/setupEmployment", method = RequestMethod.GET)
	public String setupEmployment(@PathVariable("employeeId") Long employeeId,
			Model model) {
		this.activeTab = ActiveTab.EMPLOYMENT;
		Employment employment = this.employmentService
				.findActiveEmploymentByEmployeeId(employeeId);
		List<EmploymentStatus> employmentStatus = this.employmentStatusService
				.findAll();
		List<PaySchedule> paySchedules = this.payScheduleService.findAll();
		EmploymentForm employmentForm = new EmploymentForm();
		employmentForm.setEmployeeId(employeeId);
		if (employment != null) {
			employmentForm.setEmploymentId(employment.getId());
			employmentForm.setEmploymentStatusId(employment
					.getEmploymentStatus().getId());
			employmentForm.setEndDate(employment.getTerminationDate());
			employmentForm.setRemarks(employment.getRemarks());
			employmentForm.setStartDate(employment.getHiredDate());
			employmentForm.setWorkLocation(employment.getWorkLocation());
			employmentForm
					.setPayScheduleId(employment.getPaySchedule().getId());
		}
		model.addAttribute("employmentForm", employmentForm);
		model.addAttribute("employmentStatus", employmentStatus);
		model.addAttribute("paySchedules", paySchedules);
		return "/payroll/employmentPayrollSetups/setupEmployment";
	}

	@RequestMapping(value = "payroll/employees/{employeeId}/employmentPayrollSetups/processSetupEmployment", method = RequestMethod.POST)
	public String processSetupEmployment(
			@ModelAttribute("employmentForm") EmploymentForm employmentForm,
			BindingResult result, Model model) {
		this.employmentValidator.validate(employmentForm, result);
		if (result.hasErrors()) {
			this.activeTab = ActiveTab.EMPLOYMENT;
			List<EmploymentStatus> employmentStatus = this.employmentStatusService
					.findAll();
			List<PaySchedule> paySchedules = this.payScheduleService.findAll();
			model.addAttribute("employmentStatus", employmentStatus);
			model.addAttribute("paySchedules", paySchedules);
			return "/payroll/employmentPayrollSetups/setupEmployment";
		} else {
			Employment employment = this.employmentService.save(employmentForm);
			String url = String
					.format("redirect:/payroll/employments/%s/employmentPayrollSetups/setupPayTypes",
							employment.getId());
			return url;
		}
	}

	@RequestMapping(value = "payroll/employments/{employmentId}/employmentPayrollSetups/setupPayTypes", method = RequestMethod.GET)
	public String setupPayTypes(
			@PathVariable("employmentId") Long employmentId, Model model) {
		Employment employment = this.employmentService.findById(employmentId,
				"employee");
		List<PayType> allFixedPayTypes = this.payTypeService
				.findAllFixedPayTypes();
		EmploymentPayTypesForm employmentPayTypesForm = new EmploymentPayTypesForm();
		employmentPayTypesForm.setEmploymentId(employment.getId());
		employmentPayTypesForm.setEmployeeId(employment.getEmployee().getId());
		/*
		 * Select the added paytypes for employment
		 */
		Map<Long, BigDecimal> employmentPayTypeAmountMap = this.employmentPayTypeService
				.getFixedPayTypeAmountMapByEmploymentId(employmentId);
		Map<Long, SalaryType> employmentPayTypeSalaryTypeMap = this.employmentPayTypeService
				.getFixedPayTypeSalaryTypeMapByEmploymentId(employmentId);
		for (int i = 0; i < allFixedPayTypes.size(); i++) {
			PayType p = allFixedPayTypes.get(i);
			BigDecimal amount = employmentPayTypeAmountMap.get(p.getId());
			SalaryType salaryType = employmentPayTypeSalaryTypeMap.get(p
					.getId());
			EmploymentPayTypeForm employmentPayTypeForm = new EmploymentPayTypeForm();
			employmentPayTypeForm.setIsMandatory(p.getIsMandatory());
			if (p.getIsMandatory() != null && p.getIsMandatory()) {
				employmentPayTypeForm.setPayTypeId(p.getId());
			}
			employmentPayTypeForm.setIsUserEnteredAmount(p
					.getIsUserEnteredAmount());
			employmentPayTypesForm.getEmploymentPayTypeForm().add(
					employmentPayTypeForm);
			if (amount != null) {
				employmentPayTypeForm.setPayTypeId(p.getId());
				employmentPayTypeForm.setAmount(amount);
				employmentPayTypeForm.setSalaryType(salaryType);
			}
		}

		model.addAttribute("employment", employment);
		model.addAttribute("allFixedPayTypes", allFixedPayTypes);
		model.addAttribute("employmentPayTypesForm", employmentPayTypesForm);
		return "/payroll/employmentPayrollSetups/setupPayTypes";
	}

	@RequestMapping(value = "payroll/employments/{employmentId}/employmentPayrollSetups/processSetupPayTypes", method = RequestMethod.POST)
	public String processSetupPayTypes(
			@PathVariable("employmentId") Long employmentId,
			@ModelAttribute("employmentPayTypesForm") EmploymentPayTypesForm employmentPayTypesForm,
			BindingResult result, Model model) {
		this.employmentPayTypeValidator
				.validate(employmentPayTypesForm, result);
		if (result.hasErrors()) {
			Employment employment = this.employmentService.findById(
					employmentId, "employee");
			List<PayType> allFixedPayTypes = this.payTypeService
					.findAllFixedPayTypes();
			model.addAttribute("employment", employment);
			model.addAttribute("allFixedPayTypes", allFixedPayTypes);
			return "/payroll/employmentPayrollSetups/setupPayTypes";
		} else {
			this.employmentPayTypeService
					.addPayTypesToEmployment(employmentPayTypesForm);
			return String
					.format("redirect:/payroll/employments/%s/employmentPayrollSetups/setupPayTypes",
							employmentId);
		}

	}

	@RequestMapping(value = "payroll/employments/{employmentId}/employmentPayrollSetups/setupPayTax", method = RequestMethod.GET)
	public String setupPayTax(@PathVariable("employmentId") Long employmentId,
			Model model) {
		Employment employment = this.employmentService.findById(employmentId,
				"employee");
		List<PayTax> allFixedPayTaxes = this.payTaxService
				.findAllFixedPayTaxes();
		EmploymentPayTaxHeader employmentPayTaxHeader = new EmploymentPayTaxHeader();
		employmentPayTaxHeader.setEmploymentId(employmentId);
		employmentPayTaxHeader.setEmployeeId(employment.getEmployee().getId());
		Map<Long, BigDecimal> employmentPayTaxAmountMap = this.employmentPayTaxService
				.getFixedPayTaxAmountMapByEmploymentId(employmentId);
		for (int i = 0; i < allFixedPayTaxes.size(); i++) {
			PayTax payTax = allFixedPayTaxes.get(i);
			BigDecimal amount = employmentPayTaxAmountMap.get(payTax.getId());
			EmploymentPayTaxDetail employmentPayTaxDetail = new EmploymentPayTaxDetail();
			employmentPayTaxHeader.getEmploymentPayTaxDetails().add(
					employmentPayTaxDetail);
			if (amount != null) {
				employmentPayTaxDetail.setAmount(amount);
				employmentPayTaxDetail.setPayTaxId(payTax.getId());
			}
		}

		model.addAttribute("employment", employment);
		model.addAttribute("allFixedPayTaxes", allFixedPayTaxes);
		model.addAttribute("employmentPayTaxHeader", employmentPayTaxHeader);
		return "/payroll/employmentPayrollSetups/setupPayTaxes";
	}

	@RequestMapping(value = "payroll/employments/{employmentId}/employmentPayrollSetups/procesSetupPayTax", method = RequestMethod.POST)
	public String procesSetupPayTax(
			@PathVariable("employmentId") Long employmentId,
			@ModelAttribute("employmentPayTaxHeader") EmploymentPayTaxHeader employmentPayTaxHeader,
			Model model) {
		this.employmentPayTaxService
				.addPayTaxesToEmployment(employmentPayTaxHeader);
		return String
				.format("redirect:/payroll/employments/%s/employmentPayrollSetups/setupPayTax",
						employmentId);
	}

	@RequestMapping(value = "payroll/employments/{employmentId}/employmentPayrollSetups/setupPayReductions", method = RequestMethod.GET)
	public String setupPayReductions(
			@PathVariable("employmentId") Long employmentId, Model model) {
		Employment employment = this.employmentService.findById(employmentId,
				"employee");
		List<PayReduction> allFixedPayReductions = this.payReductionService
				.findAllFixedPayReductions();
		EmploymentPayReductionHeaderForm employmentPayReductionHeaderForm = new EmploymentPayReductionHeaderForm();
		employmentPayReductionHeaderForm.setEmploymentId(employmentId);
		employmentPayReductionHeaderForm.setEmployeeId(employment.getEmployee()
				.getId());
		Map<Long, BigDecimal> employmentPayReductionAmountMap = this.employmentPayReductionService
				.getFixedPayReductionAmountMapByEmploymentId(employmentId);
		Map<Long, SalaryType> employmentPayReductionSalaryTypeMap = this.employmentPayReductionService
				.getFixedPayReductionSalaryTypeMapByEmploymentId(employmentId);

		for (int i = 0; i < allFixedPayReductions.size(); i++) {
			PayReduction payReduction = allFixedPayReductions.get(i);
			BigDecimal amount = employmentPayReductionAmountMap
					.get(payReduction.getId());
			SalaryType salaryType = employmentPayReductionSalaryTypeMap
					.get(payReduction.getId());
			EmploymentPayReductionDetailForm employmentPayRedutionDetailForm = new EmploymentPayReductionDetailForm();
			employmentPayRedutionDetailForm.setIsMandatory(payReduction
					.getIsMandatory());
			employmentPayRedutionDetailForm.setIsUserEnteredAmount(payReduction
					.getIsUserEnteredAmount());
			employmentPayReductionHeaderForm
					.getEmploymentPayReductionDetailForms().add(
							employmentPayRedutionDetailForm);
			if (amount != null) {
				employmentPayRedutionDetailForm.setAmount(amount);
				employmentPayRedutionDetailForm.setPayReductionId(payReduction
						.getId());
				employmentPayRedutionDetailForm.setSalaryType(salaryType);
			}
		}

		model.addAttribute("employment", employment);
		model.addAttribute("allFixedPayReductions", allFixedPayReductions);
		model.addAttribute("employmentPayReductionHeaderForm",
				employmentPayReductionHeaderForm);
		return "/payroll/employmentPayrollSetups/setupPayReductions";
	}

	@RequestMapping(value = "payroll/employments/{employmentId}/employmentPayrollSetups/processSetupPayReductions", method = RequestMethod.POST)
	public String processSetupPayReductions(
			@PathVariable("employmentId") Long employmentId,
			@ModelAttribute("employmentPayReductionHeaderForm") EmploymentPayReductionHeaderForm employmentPayReductionHeaderForm,
			BindingResult result, Model model) {
		this.employmentPayReductionValidator.validate(
				employmentPayReductionHeaderForm, result);
		if (result.hasErrors()) {
			Employment employment = this.employmentService.findById(
					employmentId, "employee");
			List<PayReduction> allFixedPayReductions = this.payReductionService
					.findAllFixedPayReductions();
			model.addAttribute("employment", employment);
			model.addAttribute("allFixedPayReductions", allFixedPayReductions);
			return "/payroll/employmentPayrollSetups/setupPayReductions";
		} else {
			this.employmentPayReductionService
					.addPayReductionsToEmployment(employmentPayReductionHeaderForm);
			return String
					.format("redirect:/payroll/employments/%s/employmentPayrollSetups/setupPayReductions",
							employmentId);
		}

	}
}
