package com.suwadi.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.domain.Customer;

@Component("customerValidator")
public class CustomerValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Customer.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Customer customer = (Customer) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerName",
				"customer.customerName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address",
				"customer.address.required");
		// input a general error message if any errors are preset
		if (errors.hasErrors()) {
			errors.reject("customer.common");
		}
	}

}
