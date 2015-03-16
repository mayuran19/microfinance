package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.EmploymentStatusDAO;
import com.suwadi.domain.EmploymentStatus;
import com.suwadi.service.EmploymentStatusService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("employmentStatusService")
public class EmploymentStatusServiceImpl implements EmploymentStatusService {
	private EmploymentStatusDAO employmentStatusDAO;

	@Autowired
	public void setEmploymentStatusDAO(EmploymentStatusDAO employmentStatusDAO) {
		this.employmentStatusDAO = employmentStatusDAO;
	}

	public EmploymentStatus save(EmploymentStatus t) {
		return this.employmentStatusDAO.save(t);
	}

	public EmploymentStatus update(EmploymentStatus t) {
		return this.employmentStatusDAO.update(t);
	}

	public EmploymentStatus findById(Long id) {
		return this.employmentStatusDAO.findById(id);
	}

	public EmploymentStatus findById(Long id, String... include) {
		return this.employmentStatusDAO.findById(id);
	}

	public EmploymentStatus delete(EmploymentStatus t) {
		return this.employmentStatusDAO.delete(t);
	}

	public List<EmploymentStatus> findAll() {
		return this.employmentStatusDAO.findAll();
	}

	public List<EmploymentStatus> findAll(Pager pager) {
		return this.employmentStatusDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.employmentStatusDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.employmentStatusDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.employmentStatusDAO.isUnique(id, fieldName, fieldValue);
	}

}
