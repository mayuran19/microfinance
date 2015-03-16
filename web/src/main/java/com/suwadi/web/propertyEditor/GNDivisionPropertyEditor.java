package com.suwadi.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.suwadi.service.GNDivisionService;

public class GNDivisionPropertyEditor extends PropertyEditorSupport {
	private GNDivisionService gnDivisionService;

	public GNDivisionPropertyEditor(GNDivisionService gnDivisionService) {
		this.gnDivisionService = gnDivisionService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && !text.isEmpty()) {
			Long id = Long.parseLong(text);
			setValue(this.gnDivisionService.findById(id));
		} else {
			setValue(null);
		}
	}

}
