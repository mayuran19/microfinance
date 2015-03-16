package com.suwadi.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.suwadi.domain.Gender;

public class GenderPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		int c = 0;
		c = Integer.parseInt(text);

		switch (c) {
		case 0:
			this.setValue(Gender.MALE);
			break;
		case 1:
			this.setValue(Gender.FEMALE);
			break;
		default:
			this.setValue(null);
			break;
		}
	}

	@Override
	public String getAsText() {
		return (getValue() == null ? "" : Integer.valueOf(
				((Gender) getValue()).ordinal()).toString());
	}

}
