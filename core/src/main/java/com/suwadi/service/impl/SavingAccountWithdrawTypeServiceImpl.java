package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.SavingAccountWithdrawTypeDAO;
import com.suwadi.domain.SavingAccountWithdrawType;
import com.suwadi.service.SavingAccountWithdrawTypeService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("savingAccountWithdrawTypeService")
public class SavingAccountWithdrawTypeServiceImpl implements
		SavingAccountWithdrawTypeService {
	private SavingAccountWithdrawTypeDAO savingAccountWithdrawTypeDAO;

	@Autowired
	public void setSavingAccountWithdrawTypeDAO(
			SavingAccountWithdrawTypeDAO savingAccountWithdrawTypeDAO) {
		this.savingAccountWithdrawTypeDAO = savingAccountWithdrawTypeDAO;
	}

	public SavingAccountWithdrawType save(SavingAccountWithdrawType t) {
		return this.savingAccountWithdrawTypeDAO.save(t);
	}

	public SavingAccountWithdrawType update(SavingAccountWithdrawType t) {
		return this.savingAccountWithdrawTypeDAO.update(t);
	}

	public SavingAccountWithdrawType findById(Long id) {
		return this.savingAccountWithdrawTypeDAO.findById(id);
	}

	public SavingAccountWithdrawType findById(Long id, String... include) {
		return this.savingAccountWithdrawTypeDAO.findById(id, include);
	}

	public SavingAccountWithdrawType delete(SavingAccountWithdrawType t) {
		return this.savingAccountWithdrawTypeDAO.delete(t);
	}

	public List<SavingAccountWithdrawType> findAll() {
		return this.savingAccountWithdrawTypeDAO.findAll();
	}

	public List<SavingAccountWithdrawType> findAll(Pager pager) {
		return this.savingAccountWithdrawTypeDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.savingAccountWithdrawTypeDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.savingAccountWithdrawTypeDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.savingAccountWithdrawTypeDAO.isUnique(id, fieldName,
				fieldValue);
	}

}
