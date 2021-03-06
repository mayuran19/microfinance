package com.suwadi.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.suwadi.service.DistrictService;

public class UserDistrictPropertyEditor extends PropertyEditorSupport {
	private DistrictService districtService;

	public UserDistrictPropertyEditor(DistrictService districtService) {
		this.districtService = districtService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && !text.isEmpty()) {
			Long id = Long.parseLong(text);
			setValue(this.districtService.findById(id));
		} else {
			setValue(null);
		}
	}

}
