package com.suwadi.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.suwadi.domain.MaritalStatus;

public class MaritalStatusPropertyEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		int c = 0;
		c = Integer.parseInt(text);

		switch (c) {
		case 0:
			this.setValue(MaritalStatus.SINGLE);
			break;
		case 1:
			this.setValue(MaritalStatus.MARRIED);
			break;
		case 2:
			this.setValue(MaritalStatus.WIDOW);
			break;
		default:
			this.setValue(null);
			break;
		}
	}

	@Override
	public String getAsText() {
		return (getValue() == null ? "" : Integer.valueOf(
				((MaritalStatus) getValue()).ordinal()).toString());
	}
}
