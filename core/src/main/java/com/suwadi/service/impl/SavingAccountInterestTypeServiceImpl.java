package com.suwadi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.suwadi.dao.SavingAccountInterestTypeDAO;
import com.suwadi.domain.SavingAccountInterestType;
import com.suwadi.service.SavingAccountInterestTypeService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("savingAccountInterestTypeService")
public class SavingAccountInterestTypeServiceImpl implements
		SavingAccountInterestTypeService {
	private SavingAccountInterestTypeDAO savingAccountInterestTypeDAO;

	public void setSavingAccountInterestTypeDAO(
			SavingAccountInterestTypeDAO savingAccountInterestTypeDAO) {
		this.savingAccountInterestTypeDAO = savingAccountInterestTypeDAO;
	}

	public SavingAccountInterestType save(SavingAccountInterestType t) {
		return this.savingAccountInterestTypeDAO.save(t);
	}

	public SavingAccountInterestType update(SavingAccountInterestType t) {
		return this.savingAccountInterestTypeDAO.update(t);
	}

	public SavingAccountInterestType findById(Long id) {
		return this.savingAccountInterestTypeDAO.findById(id);
	}

	public SavingAccountInterestType findById(Long id, String... include) {
		return this.savingAccountInterestTypeDAO.findById(id, include);
	}

	public SavingAccountInterestType delete(SavingAccountInterestType t) {
		return this.savingAccountInterestTypeDAO.delete(t);
	}

	public List<SavingAccountInterestType> findAll() {
		return this.savingAccountInterestTypeDAO.findAll();
	}

	public List<SavingAccountInterestType> findAll(Pager pager) {
		return this.savingAccountInterestTypeDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.savingAccountInterestTypeDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.savingAccountInterestTypeDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.savingAccountInterestTypeDAO.isUnique(id, fieldName,
				fieldValue);
	}

}
