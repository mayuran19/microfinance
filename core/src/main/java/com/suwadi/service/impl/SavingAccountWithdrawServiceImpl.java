package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.SavingAccountWithdrawDAO;
import com.suwadi.domain.SavingAccountWithdraw;
import com.suwadi.service.SavingAccountWithdrawService;
import com.suwadi.web.model.microfinance.SavingAccountWithdrawForm;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("savingAccountWithdrawService")
public class SavingAccountWithdrawServiceImpl implements
		SavingAccountWithdrawService {
	private SavingAccountWithdrawDAO savingAccountWithdrawDAO;

	@Autowired
	public void setSavingAccountWithdrawDAO(
			SavingAccountWithdrawDAO savingAccountWithdrawDAO) {
		this.savingAccountWithdrawDAO = savingAccountWithdrawDAO;
	}

	public SavingAccountWithdraw save(SavingAccountWithdraw t) {
		return this.savingAccountWithdrawDAO.save(t);
	}

	public SavingAccountWithdraw update(SavingAccountWithdraw t) {
		return this.savingAccountWithdrawDAO.update(t);
	}

	public SavingAccountWithdraw findById(Long id) {
		return this.savingAccountWithdrawDAO.findById(id);
	}

	public SavingAccountWithdraw findById(Long id, String... include) {
		return this.savingAccountWithdrawDAO.findById(id, include);
	}

	public SavingAccountWithdraw delete(SavingAccountWithdraw t) {
		return this.savingAccountWithdrawDAO.delete(t);
	}

	public List<SavingAccountWithdraw> findAll() {
		return this.savingAccountWithdrawDAO.findAll();
	}

	public List<SavingAccountWithdraw> findAll(Pager pager) {
		return this.savingAccountWithdrawDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.savingAccountWithdrawDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.savingAccountWithdrawDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.savingAccountWithdrawDAO
				.isUnique(id, fieldName, fieldValue);
	}

	public PagedResultSet savingAccountWithdrawPaginateBySavingAccountId(
			Long savingAccountId, Pager pager) {
		return this.savingAccountWithdrawDAO
				.savingAccountWithdrawPaginateBySavingAccountId(
						savingAccountId, pager);
	}

	public SavingAccountWithdraw createSavingAccountWithdraw(
			SavingAccountWithdrawForm savingAccountWithdrawForm) {
		return this.savingAccountWithdrawDAO
				.createSavingAccountWithdraw(savingAccountWithdrawForm);
	}

	public SavingAccountWithdraw updateSavingAccountWithdraw(
			SavingAccountWithdrawForm savingAccountWithdrawForm) {
		return this.savingAccountWithdrawDAO
				.updateSavingAccountWithdraw(savingAccountWithdrawForm);
	}

	public SavingAccountWithdrawForm getSavingAccountWithdrawFormById(
			Long savingAccountWithdrawId) {
		return this.savingAccountWithdrawDAO
				.getSavingAccountWithdrawFormById(savingAccountWithdrawId);
	}

}
