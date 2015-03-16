package com.suwadi.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.domain.Loan;
import com.suwadi.web.model.microfinance.LoanForm;

@Component("loanValidator")
public class LoanValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Loan.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		LoanForm loan = (LoanForm) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loanName",
				"loan.loanName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loanAmount",
				"loan.loanAmount.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loanStartDate",
				"loan.loanStartDate.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loanEndDate",
				"loan.loanEndDate.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "loanPurpose",
				"loan.loanPurpose.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Remarks",
				"loan.Remarks.required");

		if (errors.hasErrors()) {
			errors.reject("loan.common",
					"Loan could not be saved because of the following errors");
		}
	}
}
