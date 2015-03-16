package com.suwadi.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.domain.Product;
import com.suwadi.domain.User;
import com.suwadi.service.ProductService;

@Component("productValidator")
public class ProductValidator implements Validator {
	private ProductService productService;

	@Autowired
	public ProductValidator(ProductService productService) {
		this.productService = productService;
	}

	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Product product = (Product) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"product.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
				"product.description.required");

		if (!productService.isUnique(product.getId(), "name",
				product.getName())) {
			errors.rejectValue("name", "product.name.unique");
		}

		// input a general error message if any errors are preset
		if (errors.hasErrors()) {
			errors.reject("product.common");
		}
	}

}
