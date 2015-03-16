package com.suwadi.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.domain.GNDivision;
import com.suwadi.service.GNDivisionService;

@Component("gnDivisionValidator")
public class GNDivisionValidator implements Validator {
	private GNDivisionService gnDivisionService;

	@Autowired
	public GNDivisionValidator(GNDivisionService gnDivisionService) {
		this.gnDivisionService = gnDivisionService;
	}

	public boolean supports(Class<?> clazz) {
		return GNDivision.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		GNDivision gnDivision = (GNDivision) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"gndivision.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
				"gndivision.description.required");
		if (gnDivision.getDivision() == null) {
			errors.rejectValue("division", "gndivision.division.required");
		}
		if (!gnDivisionService.isUnique(gnDivision.getId(), "name",
				gnDivision.getName())) {
			errors.rejectValue("name", "gndivision.name.unique");
		}

		// input a general error message if any errors are preset
		if (errors.hasErrors()) {
			errors.reject("gndivision.common");
		}
	}

}
