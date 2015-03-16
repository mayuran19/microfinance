package com.suwadi.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.domain.Vendor;
import com.suwadi.domain.User;
import com.suwadi.service.VendorService;

@Component("vendorValidator")
public class VendorValidator implements Validator {
	private VendorService vendorService;

	@Autowired
	public VendorValidator(VendorService vendorService) {
		this.vendorService = vendorService;
	}

	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Vendor vendor = (Vendor) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"vendor.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
				"vendor.description.required");

		if (!vendorService.isUnique(vendor.getId(), "name",
				vendor.getName())) {
			errors.rejectValue("name", "vendor.name.unique");
		}

		// input a general error message if any errors are preset
		if (errors.hasErrors()) {
			errors.reject("vendor.common");
		}
	}

}
