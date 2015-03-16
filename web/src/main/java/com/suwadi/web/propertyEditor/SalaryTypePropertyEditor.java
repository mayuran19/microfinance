package com.suwadi.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.suwadi.domain.SalaryType;

public class SalaryTypePropertyEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (SalaryType.BasicSalary.getText().equals(text)) {
			this.setValue(SalaryType.BasicSalary);
		} else if (SalaryType.GrossSalary.getText().equals(text)) {
			this.setValue(SalaryType.GrossSalary);
		} else if (SalaryType.NetSalary.getText().equals(text)) {
			this.setValue(SalaryType.NetSalary);
		} else {
			this.setValue(null);
		}
	}

	@Override
	public String getAsText() {
		return (getValue() == null ? "" : ((SalaryType) getValue()).getText());
	}
}
