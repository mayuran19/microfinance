package com.suwadi.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.web.model.microfinance.SavingAccountForm;

@Component("savingAccountValidator")
public class SavingAccountValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return SavingAccountForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		SavingAccountForm savingAccountForm = (SavingAccountForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountHolderName",
				"savingAccount.accountHolderName.required");
		if (errors.hasErrors()) {
			errors.reject("savingAccount.common");
		}
	}

}
