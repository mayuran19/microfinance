package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.SavingAccountTypeDAO;
import com.suwadi.domain.SavingAccountType;
import com.suwadi.service.SavingAccountTypeService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("savingAccountTypeService")
public class SavingAccountTypeServiceImpl implements SavingAccountTypeService {
	private SavingAccountTypeDAO savingAccountTypeDAO;

	@Autowired
	public void setSavingAccountTypeDAO(
			SavingAccountTypeDAO savingAccountTypeDAO) {
		this.savingAccountTypeDAO = savingAccountTypeDAO;
	}

	public SavingAccountType save(SavingAccountType t) {
		return this.savingAccountTypeDAO.save(t);
	}

	public SavingAccountType update(SavingAccountType t) {
		return this.savingAccountTypeDAO.update(t);
	}

	public SavingAccountType findById(Long id) {
		return this.savingAccountTypeDAO.findById(id);
	}

	public SavingAccountType findById(Long id, String... include) {
		return this.savingAccountTypeDAO.findById(id, include);
	}

	public SavingAccountType delete(SavingAccountType t) {
		return this.savingAccountTypeDAO.delete(t);
	}

	public List<SavingAccountType> findAll() {
		return this.savingAccountTypeDAO.findAll();
	}

	public List<SavingAccountType> findAll(Pager pager) {
		return this.savingAccountTypeDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.savingAccountTypeDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.savingAccountTypeDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.savingAccountTypeDAO.isUnique(id, fieldName, fieldValue);
	}

}
