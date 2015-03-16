package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.SavingAccountInterestDAO;
import com.suwadi.domain.SavingAccountInterest;
import com.suwadi.service.SavingAccountInterestService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("savingAccountInterestService")
public class SavingAccountInterestServiceImpl implements
		SavingAccountInterestService {
	private SavingAccountInterestDAO savingAccountInterestDAO;

	@Autowired
	public void setSavingAccountInterestDAO(
			SavingAccountInterestDAO savingAccountInterestDAO) {
		this.savingAccountInterestDAO = savingAccountInterestDAO;
	}

	public SavingAccountInterest save(SavingAccountInterest t) {
		return this.savingAccountInterestDAO.save(t);
	}

	public SavingAccountInterest update(SavingAccountInterest t) {
		return this.savingAccountInterestDAO.update(t);
	}

	public SavingAccountInterest findById(Long id) {
		return this.savingAccountInterestDAO.findById(id);
	}

	public SavingAccountInterest findById(Long id, String... include) {
		return this.savingAccountInterestDAO.findById(id, include);
	}

	public SavingAccountInterest delete(SavingAccountInterest t) {
		return this.savingAccountInterestDAO.delete(t);
	}

	public List<SavingAccountInterest> findAll() {
		return this.savingAccountInterestDAO.findAll();
	}

	public List<SavingAccountInterest> findAll(Pager pager) {
		return this.savingAccountInterestDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.savingAccountInterestDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.savingAccountInterestDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.savingAccountInterestDAO
				.isUnique(id, fieldName, fieldValue);
	}

}
