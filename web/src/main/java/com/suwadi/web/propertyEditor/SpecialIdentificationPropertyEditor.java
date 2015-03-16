package com.suwadi.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.suwadi.domain.SpecialIdentification;

public class SpecialIdentificationPropertyEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		int c = 0;
		c = Integer.parseInt(text);

		switch (c) {
		case 0:
			this.setValue(SpecialIdentification.NONE);
			break;
		case 1:
			this.setValue(SpecialIdentification.HANDICAP);
			break;
		case 2:
			this.setValue(SpecialIdentification.DISABLED);
			break;
		default:
			this.setValue(null);
			break;
		}
	}

	@Override
	public String getAsText() {
		return (getValue() == null ? "" : Integer.valueOf(
				((SpecialIdentification) getValue()).ordinal()).toString());
	}
}
