package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.EmploymentSalaryHeaderDAO;
import com.suwadi.domain.EmploymentSalaryHeader;
import com.suwadi.service.EmploymentSalaryHeaderService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("employmentSalaryHeaderService")
public class EmploymentSalaryHeaderServiceImpl implements
		EmploymentSalaryHeaderService {
	private EmploymentSalaryHeaderDAO employmentSalaryHeaderDAO;

	@Autowired
	public void setEmploymentSalaryHeaderDAO(
			EmploymentSalaryHeaderDAO employmentSalaryHeaderDAO) {
		this.employmentSalaryHeaderDAO = employmentSalaryHeaderDAO;
	}

	public EmploymentSalaryHeader save(EmploymentSalaryHeader t) {
		return this.employmentSalaryHeaderDAO.save(t);
	}

	public EmploymentSalaryHeader update(EmploymentSalaryHeader t) {
		return this.employmentSalaryHeaderDAO.update(t);
	}

	public EmploymentSalaryHeader findById(Long id) {
		return this.employmentSalaryHeaderDAO.findById(id);
	}

	public EmploymentSalaryHeader findById(Long id, String... include) {
		return this.employmentSalaryHeaderDAO.findById(id, include);
	}

	public EmploymentSalaryHeader delete(EmploymentSalaryHeader t) {
		return this.employmentSalaryHeaderDAO.delete(t);
	}

	public List<EmploymentSalaryHeader> findAll() {
		return this.employmentSalaryHeaderDAO.findAll();
	}

	public List<EmploymentSalaryHeader> findAll(Pager pager) {
		return this.employmentSalaryHeaderDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.employmentSalaryHeaderDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.employmentSalaryHeaderDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.employmentSalaryHeaderDAO.isUnique(id, fieldName,
				fieldValue);
	}

	public PagedResultSet findByPayrollId(final Long payrollId,
			final Pager pager) {
		return this.employmentSalaryHeaderDAO.findByPayrollId(payrollId, pager);
	}

}
