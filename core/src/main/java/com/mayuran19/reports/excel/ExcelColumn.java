/**
 * ExcelColumn.java created by mayuran on Dec 24, 2012
 * Copyright (C) 2012 reserved by SESAMi (Singapore) Pte Ltd.
 */
package com.mayuran19.reports.excel;

/**
 * @author mayuran
 * 
 */
public class ExcelColumn {
	private Integer columnIndex;
	private String columnName;

	public ExcelColumn(Integer columnIndex, String columnName) {
		this.columnIndex = columnIndex;
		this.columnName = columnName;
	}

	public Integer getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(Integer columnIndex) {
		this.columnIndex = columnIndex;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

}
