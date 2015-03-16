package com.suwadi.web.validator;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.suwadi.web.model.payroll.EmploymentPayTypeForm;
import com.suwadi.web.model.payroll.EmploymentPayTypesForm;

@Component("employmentPayTypeValidator")
public class EmploymentPayTypeValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return EmploymentPayTypesForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		EmploymentPayTypesForm employmentPayTypesForm = (EmploymentPayTypesForm) target;
		List<EmploymentPayTypeForm> employmentPayTypeForms = employmentPayTypesForm
				.getEmploymentPayTypeForm();
		for (int i = 0; i < employmentPayTypeForms.size(); i++) {
			EmploymentPayTypeForm employmentPayTypeForm = employmentPayTypeForms
					.get(i);
			if (employmentPayTypeForm.getPayTypeId() != null
					&& employmentPayTypeForm.getPayTypeId().longValue() != 0
					&& (employmentPayTypeForm.getAmount() == null || employmentPayTypeForm
							.getAmount().doubleValue() == 0)) {
				errors.rejectValue("employmentPayTypeForm[" + i + "].amount",
						"employmentPayType.amount.required");
			}
		}
	}
}
