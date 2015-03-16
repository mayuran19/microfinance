package com.suwadi.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.suwadi.service.ExpenseAccountService;

public class ExpenseAccountPropertyEditor extends PropertyEditorSupport {
	private ExpenseAccountService expenseAccountService;

	public ExpenseAccountPropertyEditor(
			ExpenseAccountService expenseAccountService) {
		this.expenseAccountService = expenseAccountService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && !text.isEmpty()) {
			Long id = Long.parseLong(text);
			setValue(this.expenseAccountService.findById(id));
		} else {
			setValue(null);
		}
	}
}
