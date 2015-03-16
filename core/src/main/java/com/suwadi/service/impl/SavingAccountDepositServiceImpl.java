package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.SavingAccountDepositDAO;
import com.suwadi.domain.SavingAccountDeposit;
import com.suwadi.service.SavingAccountDepositService;
import com.suwadi.web.model.microfinance.SavingAccountDepositForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("savingAccountDepositService")
public class SavingAccountDepositServiceImpl implements
		SavingAccountDepositService {
	private SavingAccountDepositDAO savingAccountDepositDAO;

	@Autowired
	public void setSavingAccountDepositDAO(
			SavingAccountDepositDAO savingAccountDepositDAO) {
		this.savingAccountDepositDAO = savingAccountDepositDAO;
	}

	public SavingAccountDeposit save(SavingAccountDeposit t) {
		return this.savingAccountDepositDAO.save(t);
	}

	public SavingAccountDeposit update(SavingAccountDeposit t) {
		return this.savingAccountDepositDAO.update(t);
	}

	public SavingAccountDeposit findById(Long id) {
		return this.savingAccountDepositDAO.findById(id);
	}

	public SavingAccountDeposit findById(Long id, String... include) {
		return this.savingAccountDepositDAO.findById(id, include);
	}

	public SavingAccountDeposit delete(SavingAccountDeposit t) {
		return this.savingAccountDepositDAO.delete(t);
	}

	public List<SavingAccountDeposit> findAll() {
		return this.savingAccountDepositDAO.findAll();
	}

	public List<SavingAccountDeposit> findAll(Pager pager) {
		return this.savingAccountDepositDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.savingAccountDepositDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.savingAccountDepositDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.savingAccountDepositDAO.isUnique(id, fieldName, fieldValue);
	}

	public PagedResultSet savingAccountDepoistPaginateBySavingAccountId(
			Long savingAccountId, Pager pager) {
		return this.savingAccountDepositDAO
				.savingAccountDepoistPaginateBySavingAccountId(savingAccountId,
						pager);
	}

	public SavingAccountDeposit createSavingAccountDeposit(
			SavingAccountDepositForm savingAccountDepositForm) {
		return this.savingAccountDepositDAO
				.createSavingAccountDeposit(savingAccountDepositForm);
	}

	public SavingAccountDeposit updateSavingAccountDeposit(
			SavingAccountDepositForm savingAccountDepositForm) {
		return this.savingAccountDepositDAO
				.updateSavingAccountDeposit(savingAccountDepositForm);
	}

	public SavingAccountDepositForm getSavingAccountDepositFormById(
			Long savingAccountDepositId) {
		return this.savingAccountDepositDAO
				.getSavingAccountDepositFormById(savingAccountDepositId);
	}

}
