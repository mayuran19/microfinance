package com.suwadi.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.domain.Group;

@Component("groupValidator")
public class GroupValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Group.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Group group = (Group) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"group.name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "eccName",
				"group.eccName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactNo",
				"group.contactNo.required");
		if (group.getSociety() == null || group.getSociety().getId() == null) {
			errors.rejectValue("society", "group.society.required");
		}
		if (group.getPresident() == null
				|| group.getPresident().getFirstName() == null
				|| group.getPresident().getFirstName().isEmpty()) {
			errors.rejectValue("president.firstName",
					"group.president.firstName.required");
		}
		if (group.getEccPresident() == null
				|| group.getEccPresident().getFirstName() == null
				|| group.getEccPresident().getFirstName().isEmpty()) {
			errors.rejectValue("eccPresident.firstName",
					"group.eccPresident.firstName.required");
		}
	}

}
