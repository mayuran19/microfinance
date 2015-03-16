package com.suwadi.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.domain.Beneficiary;
import com.suwadi.service.BeneficiaryService;

@Component("beneficiaryValidator")
public class BeneficiaryValidator implements Validator {
	private BeneficiaryService beneficiaryService;

	@Autowired
	public BeneficiaryValidator(BeneficiaryService beneficiaryService) {
		this.beneficiaryService = beneficiaryService;
	}

	public boolean supports(Class<?> clazz) {
		return Beneficiary.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Beneficiary beneficiary = (Beneficiary) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "memberNo",
				"beneficiary.memberNo.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
				"beneficiary.firstName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
				"beneficiary.lastName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nicNo",
				"beneficiary.nicNo.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address",
				"beneficiary.address.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth",
				"beneficiary.dateOfBirth.required");

		if (errors.hasErrors()) {
			errors.reject("beneficiary.common");
		}
	}

}
