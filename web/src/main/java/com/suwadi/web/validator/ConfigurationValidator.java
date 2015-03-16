package com.suwadi.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.domain.Configuration;
import com.suwadi.service.ConfigurationService;
import com.suwadi.web.model.common.ConfigurationForm;

@Component("configurationValidator")
public class ConfigurationValidator implements Validator {
	private ConfigurationService configurationService;

	@Autowired
	public void setConfigurationService(
			ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public boolean supports(Class<?> clazz) {
		return Configuration.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ConfigurationForm configuration = (ConfigurationForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "key",
				"configuraion.confKey.required", "Key is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value",
				"configuraion.confValue.required", "Value is required");

		if (!configurationService.isUnique(configuration.getId(), "confKey",
				configuration.getKey())) {
			errors.rejectValue("key", "configuraion.confKey.unique",
					"A key with same name already exists");
		}

		if (errors.hasErrors()) {
			errors.reject("configuration.common",
					"Configuration could not be saved because of the following errors");
		}
	}

}
