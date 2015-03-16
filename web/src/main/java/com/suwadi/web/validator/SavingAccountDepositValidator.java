package com.suwadi.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.web.model.microfinance.SavingAccountDepositForm;

@Component("savingAccountDepositValidator")
public class SavingAccountDepositValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return SavingAccountDepositForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount",
				"savingAccountDeposit.amount.required");
		if (errors.hasErrors()) {
			errors.reject("savingAccountDeposit.common");
		}
	}

}
