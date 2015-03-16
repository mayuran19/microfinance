package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.BeneficiaryDAO;
import com.suwadi.domain.Beneficiary;
import com.suwadi.service.BeneficiaryService;
import com.suwadi.web.model.BeneficiarySearch;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("beneficiaryService")
public class BeneficiaryServiceImpl implements BeneficiaryService {
	private BeneficiaryDAO beneficiaryDAO;

	public BeneficiaryDAO getBeneficiaryDAO() {
		return beneficiaryDAO;
	}

	@Autowired
	public void setBeneficiaryDAO(BeneficiaryDAO beneficiaryDAO) {
		this.beneficiaryDAO = beneficiaryDAO;
	}

	public Beneficiary save(Beneficiary t) {
		return this.beneficiaryDAO.save(t);
	}

	public Beneficiary update(Beneficiary t) {
		// exclude the properties from updating
		Beneficiary db = this.beneficiaryDAO.findById(t.getId());
		String[] ignoreProperties = { "beneficiaryType", "status" };
		BeanUtils.copyProperties(t, db, ignoreProperties);
		return this.beneficiaryDAO.update(db);
	}

	public Beneficiary findById(Long id) {
		return this.beneficiaryDAO.findById(id);
	}

	public Beneficiary delete(Beneficiary t) {
		return this.beneficiaryDAO.delete(t);
	}

	public List<Beneficiary> findAll() {
		return this.beneficiaryDAO.findAll();
	}

	public List<Beneficiary> findAll(Pager pager) {
		return this.beneficiaryDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.beneficiaryDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.beneficiaryDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.beneficiaryDAO.isUnique(id, fieldName, fieldValue);
	}

	public PagedResultSet findAllBeneficiaries(Pager pager) {
		return this.beneficiaryDAO.findAllBeneficiaries(pager);
	}

	public PagedResultSet findByBenificiarySearchCriteria(
			BeneficiarySearch beneficiarySearch, Pager pager) {
		return this.beneficiaryDAO.findByBenificiarySearchCriteria(
				beneficiarySearch, pager);
	}

	public PagedResultSet findAllByGroupId(Long groupId, Pager pager) {
		return this.beneficiaryDAO.findAllByGroupId(groupId, pager);
	}

	public Beneficiary findById(Long id, String... include) {
		return this.beneficiaryDAO.findById(id, include);
	}

}
