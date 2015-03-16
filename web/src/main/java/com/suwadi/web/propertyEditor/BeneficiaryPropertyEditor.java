package com.suwadi.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.suwadi.service.BeneficiaryService;

public class BeneficiaryPropertyEditor extends PropertyEditorSupport {
	private BeneficiaryService beneficiaryService;

	public BeneficiaryPropertyEditor(BeneficiaryService beneficiaryService) {
		this.beneficiaryService = beneficiaryService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && !text.isEmpty()) {
			Long id = Long.parseLong(text);
			setValue(this.beneficiaryService.findById(id));
		} else {
			setValue(null);
		}
	}
}
