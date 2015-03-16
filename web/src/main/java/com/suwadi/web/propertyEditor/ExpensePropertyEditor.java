package com.suwadi.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.suwadi.service.ExpenseService;

public class ExpensePropertyEditor extends PropertyEditorSupport {
	private ExpenseService expenseService;

	public ExpensePropertyEditor(ExpenseService expenseService) {
		this.expenseService = expenseService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && !text.isEmpty()) {
			Long id = Long.parseLong(text);
			setValue(this.expenseService.findById(id));
		} else {
			setValue(null);
		}
	}
}
