package com.suwadi.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.domain.SavingAccountWithdraw;

@Component("savingAccountWithdrawValidator")
public class SavingAccountWithdrawValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return SavingAccountWithdraw.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount",
				"savingAccountWithdraw.amount.required");
		if (errors.hasErrors()) {
			errors.reject("savingAccountWithdraw.common");
		}
	}

}
