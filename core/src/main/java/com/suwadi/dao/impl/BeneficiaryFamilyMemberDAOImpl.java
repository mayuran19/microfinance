package com.suwadi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.suwadi.dao.BeneficiaryFamilyMemberDAO;
import com.suwadi.dao.common.GenericHibernateDAOSupport;
import com.suwadi.domain.BeneficiaryFamilyMember;

@Repository("beneficiaryFamilyMemberDAO")
public class BeneficiaryFamilyMemberDAOImpl extends
		GenericHibernateDAOSupport<BeneficiaryFamilyMember> implements
		BeneficiaryFamilyMemberDAO {
	public BeneficiaryFamilyMemberDAOImpl() {
		super(BeneficiaryFamilyMember.class);
	}

	public List<BeneficiaryFamilyMember> getByBeneficiaryId(Long beneficiaryId) {
		String hqlQuery = String.format(
				"select distinct obj from %s where obj.beneficiary.id = ?",
				BeneficiaryFamilyMember.class.getName());
		@SuppressWarnings("unchecked")
		List<BeneficiaryFamilyMember> list = this.getHibernateTemplate().find(
				hqlQuery, beneficiaryId);
		return list;
	}
}
