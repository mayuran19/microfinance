package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.SalaryHeaderDAO;
import com.suwadi.domain.SalaryHeader;
import com.suwadi.service.SalaryHeaderService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("salaryHeaderService")
public class SalaryHeaderServiceImpl implements SalaryHeaderService {
	private SalaryHeaderDAO salaryHeaderDAO;

	@Autowired
	public void setSalaryHeaderDAO(SalaryHeaderDAO salaryHeaderDAO) {
		this.salaryHeaderDAO = salaryHeaderDAO;
	}

	public SalaryHeader save(SalaryHeader t) {
		return this.salaryHeaderDAO.save(t);
	}

	public SalaryHeader update(SalaryHeader t) {
		return this.salaryHeaderDAO.update(t);
	}

	public SalaryHeader findById(Long id) {
		return this.salaryHeaderDAO.findById(id);
	}

	public SalaryHeader findById(Long id, String... include) {
		return this.salaryHeaderDAO.findById(id, include);
	}

	public SalaryHeader delete(SalaryHeader t) {
		return this.salaryHeaderDAO.delete(t);
	}

	public List<SalaryHeader> findAll() {
		return this.salaryHeaderDAO.findAll();
	}

	public List<SalaryHeader> findAll(Pager pager) {
		return this.salaryHeaderDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.salaryHeaderDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.salaryHeaderDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.salaryHeaderDAO.isUnique(id, fieldName, fieldValue);
	}

}
