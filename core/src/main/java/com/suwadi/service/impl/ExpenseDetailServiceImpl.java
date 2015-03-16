package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.ExpenseDetailDAO;
import com.suwadi.domain.ExpenseDetail;
import com.suwadi.service.ExpenseDetailService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("expenseDetailService")
public class ExpenseDetailServiceImpl implements ExpenseDetailService {
	private ExpenseDetailDAO expenseDetailDAO;

	@Autowired
	public void setExpenseDetailDAO(ExpenseDetailDAO expenseDetailDAO) {
		this.expenseDetailDAO = expenseDetailDAO;
	}

	public ExpenseDetail save(ExpenseDetail t) {
		return this.expenseDetailDAO.save(t);
	}

	public ExpenseDetail update(ExpenseDetail t) {
		return this.expenseDetailDAO.update(t);
	}

	public ExpenseDetail findById(Long id) {
		return this.expenseDetailDAO.findById(id);
	}

	public ExpenseDetail delete(ExpenseDetail t) {
		return this.expenseDetailDAO.delete(t);
	}

	public List<ExpenseDetail> findAll() {
		return this.expenseDetailDAO.findAll();
	}

	public List<ExpenseDetail> findAll(Pager pager) {
		return this.expenseDetailDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.expenseDetailDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.expenseDetailDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.expenseDetailDAO.isUnique(id, fieldName, fieldValue);
	}

	public ExpenseDetail findById(Long id, String... include) {
		return this.expenseDetailDAO.findById(id, include);
	}

	public void save(List<ExpenseDetail> expenseDetails) {
		this.expenseDetailDAO.save(expenseDetails);
	}

	public void delete(List<ExpenseDetail> expenseDetails) {
		this.expenseDetailDAO.delete(expenseDetails);
	}

	public List<ExpenseDetail> findByExpenseId(Long expenseId) {
		return this.expenseDetailDAO.findByExpenseId(expenseId);
	}

	public List<ExpenseDetail> findByPropertyWithEagerFetch(
			String propertyName, Object propertyValue, String[] joins) {
		return this.expenseDetailDAO.findByPropertyWithEagerFetch(propertyName,
				propertyValue, joins);
	}

}
