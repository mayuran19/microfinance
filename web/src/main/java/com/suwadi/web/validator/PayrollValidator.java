package com.suwadi.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.dao.PayrollDAO;
import com.suwadi.domain.PaySchedule;
import com.suwadi.service.PayScheduleService;
import com.suwadi.web.model.payroll.PayrollForm;

@Component("payrollValidator")
public class PayrollValidator implements Validator {
	private PayScheduleService payScheduleService;
	private PayrollDAO payrollDAO;

	@Autowired
	public void setPayScheduleService(PayScheduleService payScheduleService) {
		this.payScheduleService = payScheduleService;
	}

	@Autowired
	public void setPayrollDAO(PayrollDAO payrollDAO) {
		this.payrollDAO = payrollDAO;
	}

	public boolean supports(Class<?> clazz) {
		return PayrollForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		PayrollForm payrollForm = (PayrollForm) target;
		PaySchedule paySchedule = this.payScheduleService.findById(payrollForm
				.getPayScheduleId());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate",
				"payroll.fromDate.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate",
				"payroll.toDate.required");
		if (this.payrollDAO.isPayrollExist(paySchedule.getId(),
				payrollForm.getFromDate(), payrollForm.getToDate())) {
			errors.rejectValue("fromDate", "payroll.alreadyExist");
		}

		if (errors.hasErrors()) {
			errors.reject("payroll.common");
		}
	}

}
