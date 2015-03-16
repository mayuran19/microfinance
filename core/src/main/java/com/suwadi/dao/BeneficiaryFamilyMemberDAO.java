package com.suwadi.dao;

import java.util.List;

import com.suwadi.domain.BeneficiaryFamilyMember;

public interface BeneficiaryFamilyMemberDAO extends
		GenericDAO<BeneficiaryFamilyMember> {
	public List<BeneficiaryFamilyMember> getByBeneficiaryId(Long beneficiaryId);
}
