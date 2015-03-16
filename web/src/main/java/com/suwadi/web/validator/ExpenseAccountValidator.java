package com.suwadi.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.domain.ExpenseAccount;
import com.suwadi.domain.Product;
import com.suwadi.domain.User;
import com.suwadi.service.ExpenseAccountService;

@Component("expenseAccountValidator")
public class ExpenseAccountValidator implements Validator {
	private ExpenseAccountService expenseAccountService;

	@Autowired
	public void setExpenseAccountService(
			ExpenseAccountService expenseAccountService) {
		this.expenseAccountService = expenseAccountService;
	}

	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ExpenseAccount expenseAccount = (ExpenseAccount) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"expenseAccount.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
				"expenseAccount.description.required");

		if (!expenseAccountService.isUnique(expenseAccount.getId(), "name",
				expenseAccount.getName())) {
			errors.rejectValue("name", "expenseAccount.name.unique");
		}

		// input a general error message if any errors are preset
		if (errors.hasErrors()) {
			errors.reject("expenseAccount.common");
		}
	}
}
