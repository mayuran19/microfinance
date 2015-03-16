package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.ExpenseTypeDAO;
import com.suwadi.domain.ExpenseType;
import com.suwadi.service.ExpenseTypeService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("expenseTypeService")
public class ExpenseTypeServiceImpl implements ExpenseTypeService {
	private ExpenseTypeDAO expenseTypeDAO;

	@Autowired
	public void setExpenseTypeDAO(ExpenseTypeDAO expenseTypeDAO) {
		this.expenseTypeDAO = expenseTypeDAO;
	}

	public ExpenseType save(ExpenseType t) {
		return this.expenseTypeDAO.save(t);
	}

	public ExpenseType update(ExpenseType t) {
		return this.expenseTypeDAO.update(t);
	}

	public ExpenseType findById(Long id) {
		return this.expenseTypeDAO.findById(id);
	}

	public ExpenseType delete(ExpenseType t) {
		return this.expenseTypeDAO.delete(t);
	}

	public List<ExpenseType> findAll() {
		return this.expenseTypeDAO.findAll();
	}

	public List<ExpenseType> findAll(Pager pager) {
		return this.expenseTypeDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.expenseTypeDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.expenseTypeDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.expenseTypeDAO.isUnique(id, fieldName, fieldValue);
	}

	public ExpenseType findById(Long id, String... include) {
		return this.findById(id, include);
	}

}
