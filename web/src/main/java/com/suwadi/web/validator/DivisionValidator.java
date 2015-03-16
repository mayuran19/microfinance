package com.suwadi.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.domain.Division;
import com.suwadi.service.DivisionService;

@Component("divisionValidator")
public class DivisionValidator implements Validator {
	private DivisionService divisionService;

	@Autowired
	public DivisionValidator(DivisionService divisionService) {
		this.divisionService = divisionService;
	}

	public boolean supports(Class<?> clazz) {
		return Division.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Division division = (Division) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"division.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
				"division.description.required");
		if (division.getDistrict() == null) {
			errors.rejectValue("district", "division.district.required");
		}
		if (!divisionService.isUnique(division.getId(), "name",
				division.getName())) {
			errors.rejectValue("name", "division.name.unique");
		}

		// input a general error message if any errors are preset
		if (errors.hasErrors()) {
			errors.reject("division.common");
		}
	}

}
