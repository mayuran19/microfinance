package com.suwadi.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.web.model.accounting.ExpenseDetailForm;
import com.suwadi.web.model.accounting.ExpenseForm;

@Component("expenseValidator")
public class ExpenseValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return ExpenseForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ExpenseForm expenseForm = (ExpenseForm) target;

		if (expenseForm.getVendorId() == null) {
			errors.rejectValue("vendorId", "expenseForm.vendorId.required");
		}
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "notes",
		// "expenseForm.notes.required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "invoiceNumber",
		// "expenseForm.invoiceNumber.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date",
				"expenseForm.date.required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dueDate",
		// "expenseForm.dueDate.required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "purchaseOrder",
		// "expenseForm.purchaseOrder.required");

		if (expenseForm.getExpenseDetails().size() == 0) {
			errors.rejectValue("expenseDetails",
					"expenseForm.expenseDetails.required");
		}
		for (int i = 0; i < expenseForm.getExpenseDetails().size(); i++) {
			ExpenseDetailForm expenseDetail = expenseForm.getExpenseDetails()
					.get(i);
			if (expenseDetail.getProductId() == null) {
				errors.rejectValue("expenseDetails[" + i + "].productId",
						"expenseForm.expenseDetail.productId.required");
			}

			if (expenseDetail.getExpenseAccountId() == null) {
				errors.rejectValue(
						"expenseDetails[" + i + "].expenseAccountId",
						"expenseForm.expenseDetail.expenseAccountId.required");
			}

			if (expenseDetail.getDescription() == null
					|| expenseDetail.getDescription().isEmpty()) {
				errors.rejectValue("expenseDetails[" + i + "].description",
						"expenseForm.expenseDetail.description.required");
			}

			if (expenseDetail.getQuantity() == null
					|| expenseDetail.getQuantity().doubleValue() == 0) {
				errors.rejectValue("expenseDetails[" + i + "].quantity",
						"expenseForm.expenseDetail.quantity.required");
			}

			if (expenseDetail.getUnitPrice() == null
					|| expenseDetail.getUnitPrice().doubleValue() == 0) {
				errors.rejectValue("expenseDetails[" + i + "].unitPrice",
						"expenseForm.expenseDetail.unitPrice.required");
			}

			if (expenseDetail.getTotal() == null
					|| expenseDetail.getTotal().doubleValue() == 0) {
				errors.rejectValue("expenseDetails[" + i + "].total",
						"expenseForm.expenseDetail.total.required");
			}
		}

		if (errors.hasErrors()) {
			errors.reject("expense.common");
		}
	}

}
