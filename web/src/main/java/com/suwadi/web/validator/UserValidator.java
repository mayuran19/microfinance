package com.suwadi.web.validator;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.domain.User;
import com.suwadi.service.UserService;

@Component("userValidator")
public class UserValidator implements Validator {
	private UserService userService;

	@Autowired
	public UserValidator(UserService userService) {
		this.userService = userService;
	}

	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		User user = (User) target;
		if (user.getId() == null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
					"user.userName.required", "Username is required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
					"user.password.required", "Password is reqired");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"confirmPassword", "user.confirmPassword.required",
					"Confirm password is required");
		}

		// remove the null objects from roles list
		user.getRoles().removeAll(Collections.singleton(null));

		if (user != null
				&& !user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("password", "user.password.match",
					"Password and confirm password should match");
		}

		if (!userService.isUnique(user.getId(), "userName", user.getUserName())) {
			errors.rejectValue("userName", "user.userName.unique",
					"Username has already been taken");
		}

		if (user.getRoles().size() < 1) {
			errors.rejectValue("roles", "user.roles.required",
					"User role is required");
		}

		// input a general error message if any errors are preset
		if (errors.hasErrors()) {
			errors.reject("user.common",
					"User could not be saved because of the following errors");
		}

	}

}
