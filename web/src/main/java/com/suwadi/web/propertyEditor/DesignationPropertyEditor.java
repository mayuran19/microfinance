package com.suwadi.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.suwadi.service.DesignationService;

public class DesignationPropertyEditor extends PropertyEditorSupport {
	private DesignationService designationService;

	public DesignationPropertyEditor(DesignationService designationService) {
		this.designationService = designationService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && !text.isEmpty()) {
			Long id = Long.parseLong(text);
			setValue(this.designationService.findById(id));
		} else {
			setValue(null);
		}
	}
}
