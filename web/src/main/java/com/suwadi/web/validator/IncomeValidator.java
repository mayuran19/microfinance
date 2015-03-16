package com.suwadi.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.web.model.accounting.IncomeDetailForm;
import com.suwadi.web.model.accounting.IncomeForm;

@Component("incomeValidator")
public class IncomeValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return IncomeForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		IncomeForm incomeForm = (IncomeForm) target;
		if (incomeForm.getCustomerId() == null) {
			errors.rejectValue("customerId", "incomeForm.customerId.required");
		}
		if (incomeForm.getTermOfPaymentId() == null) {
			errors.rejectValue("termOfPaymentId",
					"incomeForm.termOfPaymentId.required");
		}
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "notes",
		// "incomeForm.notes.required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "invoiceNo",
		// "incomeForm.invoiceNo.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date",
				"incomeForm.date.required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dueDate",
		// "incomeForm.dueDate.required");

		if (incomeForm.getIncomeDetailForms().size() == 0) {
			errors.rejectValue("incomeDetailForms",
					"incomeForm.incomeDetailForms.required");
		}

		for (int i = 0; i < incomeForm.getIncomeDetailForms().size(); i++) {
			IncomeDetailForm incomeDetailForm = incomeForm
					.getIncomeDetailForms().get(i);
			if (incomeDetailForm.getProductId() == null) {
				errors.rejectValue("incomeDetailForms[" + i + "].productId",
						"incomeForm.incomeDetailForms.productId.required");
			}

			// if (incomeDetailForm.getDescription() == null
			// || incomeDetailForm.getDescription().isEmpty()) {
			// errors.rejectValue("incomeDetailForms[" + i + "].description",
			// "incomeForm.incomeDetailForms.description.required");
			// }

			if (incomeDetailForm.getQuantity() == null
					|| incomeDetailForm.getQuantity().doubleValue() == 0) {
				errors.rejectValue("incomeDetailForms[" + i + "].quantity",
						"incomeForm.incomeDetailForms.quantity.required");
			}

			if (incomeDetailForm.getUnitPrice() == null
					|| incomeDetailForm.getUnitPrice().doubleValue() == 0) {
				errors.rejectValue("incomeDetailForms[" + i + "].unitPrice",
						"incomeForm.incomeDetailForms.unitPrice.required");
			}

			if (incomeDetailForm.getLineTotal() == null
					|| incomeDetailForm.getLineTotal().doubleValue() == 0) {
				errors.rejectValue("incomeDetailForms[" + i + "].lineTotal",
						"incomeForm.incomeDetailForms.lineTotal.required");
			}
		}
	}

}
