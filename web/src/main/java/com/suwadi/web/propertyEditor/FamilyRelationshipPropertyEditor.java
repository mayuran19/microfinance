package com.suwadi.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.suwadi.service.FamilyRelationshipService;

public class FamilyRelationshipPropertyEditor extends PropertyEditorSupport {
	private FamilyRelationshipService familyRelationshipService;

	public FamilyRelationshipPropertyEditor(
			FamilyRelationshipService familyRelationshipService) {
		this.familyRelationshipService = familyRelationshipService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && !text.isEmpty()) {
			Long id = Long.parseLong(text);
			setValue(this.familyRelationshipService.findById(id));
		} else {
			setValue(null);
		}
	}
}
