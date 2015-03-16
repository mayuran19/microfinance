package com.suwadi.service;

import java.util.List;

import com.suwadi.domain.BeneficiaryFamilyMember;

public interface BeneficiaryFamilyMemberService extends
		GenericService<BeneficiaryFamilyMember> {
	public List<BeneficiaryFamilyMember> getByBeneficiaryId(Long beneficiaryId);
}
