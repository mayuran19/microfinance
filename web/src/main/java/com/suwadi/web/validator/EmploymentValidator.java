package com.suwadi.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.web.model.payroll.EmploymentForm;

@Component("employmentValidator")
public class EmploymentValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return EmploymentForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate",
				"employment.startDate.required");

		// input a general error message if any errors are preset
		if (errors.hasErrors()) {
			errors.reject("employment.common");
		}
	}

}
