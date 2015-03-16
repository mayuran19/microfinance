package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.ExpenseAccountDAO;
import com.suwadi.domain.ExpenseAccount;
import com.suwadi.service.ExpenseAccountService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("expenseAccountService")
public class ExpenseAccountServiceImpl implements ExpenseAccountService {
	private ExpenseAccountDAO expenseAccountDAO;

	@Autowired
	public void setExpenseAccountDAO(ExpenseAccountDAO expenseAccountDAO) {
		this.expenseAccountDAO = expenseAccountDAO;
	}

	public ExpenseAccount save(ExpenseAccount t) {
		return this.expenseAccountDAO.save(t);
	}

	public ExpenseAccount update(ExpenseAccount t) {
		return this.expenseAccountDAO.update(t);
	}

	public ExpenseAccount findById(Long id) {
		return this.expenseAccountDAO.findById(id);
	}

	public ExpenseAccount delete(ExpenseAccount t) {
		return this.expenseAccountDAO.delete(t);
	}

	public List<ExpenseAccount> findAll() {
		return this.expenseAccountDAO.findAll();
	}

	public List<ExpenseAccount> findAll(Pager pager) {
		return this.expenseAccountDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.expenseAccountDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.expenseAccountDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.expenseAccountDAO.isUnique(id, fieldName, fieldValue);
	}

	public ExpenseAccount findById(Long id, String... include) {
		return this.expenseAccountDAO.findById(id, include);
	}

}
