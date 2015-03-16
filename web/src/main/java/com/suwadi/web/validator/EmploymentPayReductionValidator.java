package com.suwadi.web.validator;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.suwadi.domain.EmploymentPayReduction;
import com.suwadi.web.model.payroll.EmploymentPayReductionDetailForm;
import com.suwadi.web.model.payroll.EmploymentPayReductionHeaderForm;

@Component("employmentPayReductionValidator")
public class EmploymentPayReductionValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return EmploymentPayReduction.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		EmploymentPayReductionHeaderForm employmentPayReductionHeaderForm = (EmploymentPayReductionHeaderForm) target;
		List<EmploymentPayReductionDetailForm> employmentPayReductionDetailForms = employmentPayReductionHeaderForm
				.getEmploymentPayReductionDetailForms();
		for (int i = 0; i < employmentPayReductionDetailForms.size(); i++) {
			EmploymentPayReductionDetailForm employmentPayReductionDetailForm = employmentPayReductionDetailForms
					.get(i);
			if (employmentPayReductionDetailForm.getPayReductionId() != null
					&& employmentPayReductionDetailForm.getPayReductionId()
							.longValue() != 0
					&& (employmentPayReductionDetailForm.getAmount() == null || employmentPayReductionDetailForm
							.getAmount().doubleValue() == 0)) {
				errors.rejectValue("employmentPayReductionDetailForms[" + i
						+ "].amount", "employmentPayReduction.amount.required");
			}
		}

		if (errors.hasErrors()) {
			errors.reject("employmentPayReductionHeaderForm.common");
		}
	}

}
