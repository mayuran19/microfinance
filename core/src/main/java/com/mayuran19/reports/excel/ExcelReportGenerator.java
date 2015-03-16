/**
 * ExcelReportGenerator.java created by mayuran on Dec 24, 2012
 * Copyright (C) 2012 reserved by SESAMi (Singapore) Pte Ltd.
 */
package com.mayuran19.reports.excel;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author mayuran
 * 
 */
public class ExcelReportGenerator {
	private Map<String, ExcelColumn> columnKeyNameMap = new HashMap<String, ExcelColumn>();

	public Map<String, ExcelColumn> getColumnKeyNameMap() {
		return columnKeyNameMap;
	}

	public void setColumnKeyNameMap(Map<String, ExcelColumn> columnKeyNameMap) {
		this.columnKeyNameMap = columnKeyNameMap;
	}

	public ByteArrayOutputStream getExcelAsOutPutStream(
			List<? extends Object> list) {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet hssfSheet = hssfWorkbook.createSheet();
		HSSFRow hssfRow = null;
		HSSFCell hssfCell = null;
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			hssfRow = hssfSheet.createRow(i);
			for (String columnKey : columnKeyNameMap.keySet()) {
				this.getReadMethod(obj, columnKey);
				ExcelColumn excelColumn = columnKeyNameMap.get(columnKey);
				try {
					Method method = this.getReadMethod(obj, columnKey);
					Object object = method.invoke(obj, new Object[] {});
					hssfCell = hssfRow.createCell(excelColumn.getColumnIndex());
					if (object instanceof String) {
						hssfCell.setCellValue(object.toString());
					} else if (object instanceof Double) {
						hssfCell.setCellValue(((Double) object).doubleValue());
					} else if (object instanceof Boolean) {
						hssfCell.setCellValue(((Boolean) object).booleanValue());
					} else {
						hssfCell.setCellValue(object.toString());
					}
				} catch (Exception e) {
					e.printStackTrace(System.out);
				}
			}
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			hssfWorkbook.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

		return outputStream;
	}

	public void saveAsExcelFile(List<? extends Object> list, String fileName) {
		try {
			ByteArrayOutputStream outputStream = this
					.getExcelAsOutPutStream(list);
			FileOutputStream fos = new FileOutputStream(fileName);
			outputStream.writeTo(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Method getReadMethod(Object object, String fieldName) {
		try {
			BeanInfo info = Introspector.getBeanInfo(object.getClass(),
					Object.class);
			PropertyDescriptor[] propertyDescriptors = info
					.getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				String tmpFieldName = propertyDescriptor.getName();
				if (tmpFieldName.equals(fieldName)) {
					return propertyDescriptor.getReadMethod();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
