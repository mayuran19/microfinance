package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.SavingAccountDepositTypeDAO;
import com.suwadi.domain.SavingAccountDepositType;
import com.suwadi.service.SavingAccountDepositTypeService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("savingAccountDepositTypeService")
public class SavingAccountDepositTypeServiceImpl implements
		SavingAccountDepositTypeService {
	private SavingAccountDepositTypeDAO savingAccountDepositTypeDAO;

	@Autowired
	public void setSavingAccountDepositTypeDAO(
			SavingAccountDepositTypeDAO savingAccountDepositTypeDAO) {
		this.savingAccountDepositTypeDAO = savingAccountDepositTypeDAO;
	}

	public SavingAccountDepositType save(SavingAccountDepositType t) {
		return this.savingAccountDepositTypeDAO.save(t);
	}

	public SavingAccountDepositType update(SavingAccountDepositType t) {
		return this.savingAccountDepositTypeDAO.update(t);
	}

	public SavingAccountDepositType findById(Long id) {
		return this.savingAccountDepositTypeDAO.findById(id);
	}

	public SavingAccountDepositType findById(Long id, String... include) {
		return this.savingAccountDepositTypeDAO.findById(id, include);
	}

	public SavingAccountDepositType delete(SavingAccountDepositType t) {
		return this.savingAccountDepositTypeDAO.delete(t);
	}

	public List<SavingAccountDepositType> findAll() {
		return this.savingAccountDepositTypeDAO.findAll();
	}

	public List<SavingAccountDepositType> findAll(Pager pager) {
		return this.savingAccountDepositTypeDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.savingAccountDepositTypeDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.savingAccountDepositTypeDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.savingAccountDepositTypeDAO.isUnique(id, fieldName,
				fieldValue);
	}

}
