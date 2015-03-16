package com.suwadi.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.suwadi.service.DivisionService;

public class DivisionPropertyEditor extends PropertyEditorSupport {
	private DivisionService divisionService;

	public DivisionPropertyEditor(DivisionService divisionService) {
		this.divisionService = divisionService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && !text.isEmpty()) {
			Long id = Long.parseLong(text);
			setValue(this.divisionService.findById(id));
		} else {
			setValue(null);
		}
	}
}
