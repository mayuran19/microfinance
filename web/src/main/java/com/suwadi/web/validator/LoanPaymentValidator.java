package com.suwadi.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.web.model.microfinance.LoanPaymentForm;

@Component("loanPaymentValidator")
public class LoanPaymentValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return LoanPaymentForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		LoanPaymentForm loanPaymentForm = (LoanPaymentForm) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount",
				"loanPayment.amount.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "paymentDate",
				"loanPayment.paymentDate.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "collectedById",
				"loanPayment.collectedById.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "remarks",
				"loanPayment.remarks.required");

		if (errors.hasErrors()) {
			errors.reject("loanPayment.common",
					"Loan payment could not be saved because of the following errors");
		}
	}

}
