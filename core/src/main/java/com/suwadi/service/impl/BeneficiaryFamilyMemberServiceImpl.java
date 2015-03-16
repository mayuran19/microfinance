package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.BeneficiaryFamilyMemberDAO;
import com.suwadi.domain.BeneficiaryFamilyMember;
import com.suwadi.service.BeneficiaryFamilyMemberService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("beneficiaryFamilyMemberService")
public class BeneficiaryFamilyMemberServiceImpl implements
		BeneficiaryFamilyMemberService {
	private BeneficiaryFamilyMemberDAO beneficiaryFamilyMemberDAO;

	public BeneficiaryFamilyMemberDAO getBeneficiaryFamilyMemberDAO() {
		return beneficiaryFamilyMemberDAO;
	}

	@Autowired
	public void setBeneficiaryFamilyMemberDAO(
			BeneficiaryFamilyMemberDAO beneficiaryFamilyMemberDAO) {
		this.beneficiaryFamilyMemberDAO = beneficiaryFamilyMemberDAO;
	}

	public BeneficiaryFamilyMember save(BeneficiaryFamilyMember t) {
		return this.beneficiaryFamilyMemberDAO.save(t);
	}

	public BeneficiaryFamilyMember update(BeneficiaryFamilyMember t) {
		return this.beneficiaryFamilyMemberDAO.update(t);
	}

	public BeneficiaryFamilyMember findById(Long id) {
		return this.beneficiaryFamilyMemberDAO.findById(id);
	}

	public BeneficiaryFamilyMember delete(BeneficiaryFamilyMember t) {
		return this.beneficiaryFamilyMemberDAO.delete(t);
	}

	public List<BeneficiaryFamilyMember> findAll() {
		return this.beneficiaryFamilyMemberDAO.findAll();
	}

	public List<BeneficiaryFamilyMember> findAll(Pager pager) {
		return this.beneficiaryFamilyMemberDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.beneficiaryFamilyMemberDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.beneficiaryFamilyMemberDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.beneficiaryFamilyMemberDAO.isUnique(id, fieldName,
				fieldValue);
	}

	public List<BeneficiaryFamilyMember> getByBeneficiaryId(Long beneficiaryId) {
		return this.beneficiaryFamilyMemberDAO
				.getByBeneficiaryId(beneficiaryId);
	}

	public BeneficiaryFamilyMember findById(Long id, String... include) {
		return this.beneficiaryFamilyMemberDAO.findById(id, include);
	}

}
