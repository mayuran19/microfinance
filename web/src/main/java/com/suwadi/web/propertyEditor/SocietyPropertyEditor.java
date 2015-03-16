package com.suwadi.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.suwadi.service.SocietyService;

public class SocietyPropertyEditor extends PropertyEditorSupport {
	private SocietyService societyService;

	public SocietyPropertyEditor(SocietyService societyService) {
		this.societyService = societyService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && !text.isEmpty()) {
			Long id = Long.parseLong(text);
			setValue(this.societyService.findById(id));
		} else {
			setValue(null);
		}
	}

}
