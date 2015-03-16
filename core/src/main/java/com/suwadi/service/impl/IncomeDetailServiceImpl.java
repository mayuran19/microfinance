package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.IncomeDetailDAO;
import com.suwadi.domain.IncomeDetail;
import com.suwadi.service.IncomeDetailService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("incomeDetailService")
public class IncomeDetailServiceImpl implements IncomeDetailService {
	private IncomeDetailDAO incomeDetailDAO;

	@Autowired
	public void setIncomeDetailDAO(IncomeDetailDAO incomeDetailDAO) {
		this.incomeDetailDAO = incomeDetailDAO;
	}

	public IncomeDetail save(IncomeDetail t) {
		return this.incomeDetailDAO.save(t);
	}

	public IncomeDetail update(IncomeDetail t) {
		return this.incomeDetailDAO.update(t);
	}

	public IncomeDetail findById(Long id) {
		return this.incomeDetailDAO.findById(id);
	}

	public IncomeDetail findById(Long id, String... include) {
		return this.incomeDetailDAO.findById(id, include);
	}

	public IncomeDetail delete(IncomeDetail t) {
		return this.incomeDetailDAO.delete(t);
	}

	public List<IncomeDetail> findAll() {
		return this.incomeDetailDAO.findAll();
	}

	public List<IncomeDetail> findAll(Pager pager) {
		return this.incomeDetailDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.incomeDetailDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.incomeDetailDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.incomeDetailDAO.isUnique(id, fieldName, fieldValue);
	}

	public List<IncomeDetail> findByPropertyWithEagerFetch(String propertyName,
			Object propertyValue, String[] joins) {
		return this.incomeDetailDAO.findByPropertyWithEagerFetch(propertyName,
				propertyValue, joins);
	}

}
