package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.EmploymentPayScheduleDAO;
import com.suwadi.domain.EmploymentPaySchedule;
import com.suwadi.service.EmploymentPayScheduleService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("employmentPayScheduleService")
public class EmploymentPayScheduleServiceImpl implements
		EmploymentPayScheduleService {
	private EmploymentPayScheduleDAO employmentPayScheduleDAO;

	@Autowired
	public void setEmploymentPayScheduleDAO(
			EmploymentPayScheduleDAO employmentPayScheduleDAO) {
		this.employmentPayScheduleDAO = employmentPayScheduleDAO;
	}

	public EmploymentPaySchedule save(EmploymentPaySchedule t) {
		return this.employmentPayScheduleDAO.save(t);
	}

	public EmploymentPaySchedule update(EmploymentPaySchedule t) {
		return this.employmentPayScheduleDAO.update(t);
	}

	public EmploymentPaySchedule findById(Long id) {
		return this.employmentPayScheduleDAO.findById(id);
	}

	public EmploymentPaySchedule findById(Long id, String... include) {
		return this.employmentPayScheduleDAO.findById(id, include);
	}

	public EmploymentPaySchedule delete(EmploymentPaySchedule t) {
		return this.employmentPayScheduleDAO.delete(t);
	}

	public List<EmploymentPaySchedule> findAll() {
		return this.employmentPayScheduleDAO.findAll();
	}

	public List<EmploymentPaySchedule> findAll(Pager pager) {
		return this.employmentPayScheduleDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.employmentPayScheduleDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.employmentPayScheduleDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.employmentPayScheduleDAO
				.isUnique(id, fieldName, fieldValue);
	}

}
