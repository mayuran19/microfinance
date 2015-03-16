package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.SalaryDetailDAO;
import com.suwadi.domain.SalaryDetail;
import com.suwadi.service.SalaryDetailService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("salaryDetailService")
public class SalaryDetailServiceImpl implements SalaryDetailService {
	private SalaryDetailDAO salaryDetailDAO;

	@Autowired
	public void setSalaryDetailDAO(SalaryDetailDAO salaryDetailDAO) {
		this.salaryDetailDAO = salaryDetailDAO;
	}

	public SalaryDetail save(SalaryDetail t) {
		return this.salaryDetailDAO.save(t);
	}

	public SalaryDetail update(SalaryDetail t) {
		return this.salaryDetailDAO.update(t);
	}

	public SalaryDetail findById(Long id) {
		return this.salaryDetailDAO.findById(id);
	}

	public SalaryDetail findById(Long id, String... include) {
		return this.salaryDetailDAO.findById(id, include);
	}

	public SalaryDetail delete(SalaryDetail t) {
		return this.salaryDetailDAO.delete(t);
	}

	public List<SalaryDetail> findAll() {
		return this.salaryDetailDAO.findAll();
	}

	public List<SalaryDetail> findAll(Pager pager) {
		return this.salaryDetailDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.salaryDetailDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.salaryDetailDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.salaryDetailDAO.isUnique(id, fieldName, fieldValue);
	}

}
