package com.suwadi.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.suwadi.service.VendorService;

public class VendorPropertyEditor extends PropertyEditorSupport {
	private VendorService vendorService;

	public VendorPropertyEditor(VendorService vendorService) {
		this.vendorService = vendorService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && !text.isEmpty()) {
			Long id = Long.parseLong(text);
			setValue(this.vendorService.findById(id));
		} else {
			setValue(null);
		}
	}
}
