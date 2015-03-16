package com.suwadi.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.domain.District;
import com.suwadi.domain.User;
import com.suwadi.service.DistrictService;

@Component("districtValidator")
public class DistrictValidator implements Validator {
	private DistrictService districtService;

	@Autowired
	public DistrictValidator(DistrictService districtService) {
		this.districtService = districtService;
	}

	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		District district = (District) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"district.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
				"district.description.required");

		if (!districtService.isUnique(district.getId(), "name",
				district.getName())) {
			errors.rejectValue("name", "district.name.unique");
		}

		// input a general error message if any errors are preset
		if (errors.hasErrors()) {
			errors.reject("district.common");
		}
	}

}
