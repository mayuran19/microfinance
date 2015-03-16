package com.suwadi.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.domain.Society;
import com.suwadi.service.SocietyService;

@Component("societyValidator")
public class SocietyValidator implements Validator {
	private SocietyService societyService;

	@Autowired
	public SocietyValidator(SocietyService societyService) {
		this.societyService = societyService;
	}

	public boolean supports(Class<?> clazz) {
		return Society.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Society society = (Society) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"society.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
				"society.description.required");
		if (society.getGnDivision() == null
				|| society.getGnDivision().getId() == null) {
			errors.rejectValue("gnDivision", "society.gnDivision.required");
		}

		if (society.getPresident() == null
				|| society.getPresident().getFirstName() == null
				|| society.getPresident().getFirstName().isEmpty()) {
			errors.rejectValue("president.firstName",
					"society.president.firstName.required");
		}

		if (society.getSecretary() == null
				|| society.getSecretary().getFirstName() == null
				|| society.getSecretary().getFirstName().isEmpty()) {
			errors.rejectValue("secretary.firstName",
					"society.secretary.firstName.required");
		}

		if (society.getTreasurer() == null
				|| society.getTreasurer().getFirstName() == null
				|| society.getTreasurer().getFirstName().isEmpty()) {
			errors.rejectValue("treasurer.firstName",
					"society.treasurer.firstName.required");
		}

		if (society.getAllowedInterestRate() == null) {
			errors.rejectValue("allowedInterestRate",
					"society.allowedInterestRate.required");
		}

		if (society.getShareInterestRate() == null) {
			errors.rejectValue("shareInterestRate",
					"society.shareInterestRate.required");
		}

		if (!societyService
				.isUnique(society.getId(), "name", society.getName())) {
			errors.rejectValue("name", "society.name.unique",
					"Society has already been taken");
		}

		// input a general error message if any errors are preset
		if (errors.hasErrors()) {
			errors.reject("society.common");
		}
	}

}
