package com.suwadi.web.propertyEditor;

import java.beans.PropertyEditorSupport;

import com.suwadi.service.ProductService;

public class ProductPropertyEditor extends PropertyEditorSupport {
	private ProductService productService;

	public ProductPropertyEditor(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && !text.isEmpty()) {
			Long id = Long.parseLong(text);
			setValue(this.productService.findById(id));
		} else {
			setValue(null);
		}
	}
}
