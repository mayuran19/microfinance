package com.suwadi.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.web.model.payroll.EmployeeForm;

@Component("employeeValidator")
public class EmployeeValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return EmployeeForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"employee.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address",
				"employee.address.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth",
				"employee.dateOfBirth.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nicNo",
				"employee.nicNo.required");
	}

}
