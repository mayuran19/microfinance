package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.EmploymentSalaryDetailDAO;
import com.suwadi.domain.EmploymentSalaryDetail;
import com.suwadi.service.EmploymentSalaryDetailService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("employmentSalaryDetailService")
public class EmploymentSalaryDetailServiceImpl implements
		EmploymentSalaryDetailService {
	private EmploymentSalaryDetailDAO employmentSalaryDetailDAO;

	@Autowired
	public void setEmploymentSalaryDetailDAO(
			EmploymentSalaryDetailDAO employmentSalaryDetailDAO) {
		this.employmentSalaryDetailDAO = employmentSalaryDetailDAO;
	}

	public EmploymentSalaryDetail save(EmploymentSalaryDetail t) {
		return this.employmentSalaryDetailDAO.save(t);
	}

	public EmploymentSalaryDetail update(EmploymentSalaryDetail t) {
		return this.employmentSalaryDetailDAO.update(t);
	}

	public EmploymentSalaryDetail findById(Long id) {
		return this.employmentSalaryDetailDAO.findById(id);
	}

	public EmploymentSalaryDetail findById(Long id, String... include) {
		return this.employmentSalaryDetailDAO.findById(id, include);
	}

	public EmploymentSalaryDetail delete(EmploymentSalaryDetail t) {
		return this.employmentSalaryDetailDAO.delete(t);
	}

	public List<EmploymentSalaryDetail> findAll() {
		return this.employmentSalaryDetailDAO.findAll();
	}

	public List<EmploymentSalaryDetail> findAll(Pager pager) {
		return this.employmentSalaryDetailDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.employmentSalaryDetailDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.employmentSalaryDetailDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.employmentSalaryDetailDAO.isUnique(id, fieldName,
				fieldValue);
	}

	public List<EmploymentSalaryDetail> findByEmploymentSalaryHeaderId(
			Long employmentSalaryHeaderId) {
		return this.employmentSalaryDetailDAO
				.findByEmploymentSalaryHeaderId(employmentSalaryHeaderId);
	}

	public List<EmploymentSalaryDetail> findAllEarningsByEmploymentSalaryHeaderId(
			Long employmentSalaryHeaderId) {
		return this.employmentSalaryDetailDAO
				.findAllEarningsByEmploymentSalaryHeaderId(employmentSalaryHeaderId);
	}

	public List<EmploymentSalaryDetail> findAllReductionsByEmploymentSalaryHeaderId(
			Long employmentSalaryHeaderId) {
		return this.employmentSalaryDetailDAO
				.findAllReductionsByEmploymentSalaryHeaderId(employmentSalaryHeaderId);
	}

}
