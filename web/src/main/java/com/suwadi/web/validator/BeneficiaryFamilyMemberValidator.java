package com.suwadi.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.suwadi.domain.BeneficiaryFamilyMember;

@Component("beneficiaryFamilyMemberValidator")
public class BeneficiaryFamilyMemberValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return BeneficiaryFamilyMember.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		BeneficiaryFamilyMember beneficiaryFamilyMember = (BeneficiaryFamilyMember) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
				"beneficiaryFamilyMember.firstName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
				"beneficiaryFamilyMember.lastName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nicNo",
				"beneficiaryFamilyMember.nicNo.required");
		ValidationUtils.rejectIfEmpty(errors, "familyRelationship",
				"beneficiaryFamilyMember.familyRelationship.required");
	}

}
