package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.EmploymentPayRateDAO;
import com.suwadi.domain.EmploymentPayRate;
import com.suwadi.service.EmploymentPayRateService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("employmentPayRateService")
public class EmploymentPayRateServiceImpl implements EmploymentPayRateService {
	private EmploymentPayRateDAO employmentPayRateDAO;

	@Autowired
	public void setEmploymentPayRateDAO(
			EmploymentPayRateDAO employmentPayRateDAO) {
		this.employmentPayRateDAO = employmentPayRateDAO;
	}

	public EmploymentPayRate save(EmploymentPayRate t) {
		return this.employmentPayRateDAO.save(t);
	}

	public EmploymentPayRate update(EmploymentPayRate t) {
		return this.employmentPayRateDAO.update(t);
	}

	public EmploymentPayRate findById(Long id) {
		return this.employmentPayRateDAO.findById(id);
	}

	public EmploymentPayRate findById(Long id, String... include) {
		return this.employmentPayRateDAO.findById(id, include);
	}

	public EmploymentPayRate delete(EmploymentPayRate t) {
		return this.employmentPayRateDAO.delete(t);
	}

	public List<EmploymentPayRate> findAll() {
		return this.employmentPayRateDAO.findAll();
	}

	public List<EmploymentPayRate> findAll(Pager pager) {
		return this.employmentPayRateDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.employmentPayRateDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.employmentPayRateDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.employmentPayRateDAO.isUnique(id, fieldName, fieldValue);
	}

}
