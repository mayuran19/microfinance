package com.suwadi.domain;

public enum SalaryType {
	BasicSalary("Basic salary"), NetSalary("Net salary"), GrossSalary(
			"Gross salary");
	private String text;

	private SalaryType(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public static SalaryType fromString(String text) {
		if (text != null) {
			for (SalaryType salaryType : SalaryType.values()) {
				if (text.equalsIgnoreCase(salaryType.text)) {
					return salaryType;
				}
			}
		}
		return null;
	}

}
