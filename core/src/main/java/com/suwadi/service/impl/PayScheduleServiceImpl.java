package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.PayScheduleDAO;
import com.suwadi.domain.PaySchedule;
import com.suwadi.service.PayScheduleService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("payScheduleService")
public class PayScheduleServiceImpl implements PayScheduleService {
	private PayScheduleDAO payScheduleDAO;

	@Autowired
	public void setPayScheduleDAO(PayScheduleDAO payScheduleDAO) {
		this.payScheduleDAO = payScheduleDAO;
	}

	public PaySchedule save(PaySchedule t) {
		return this.payScheduleDAO.save(t);
	}

	public PaySchedule update(PaySchedule t) {
		return this.payScheduleDAO.update(t);
	}

	public PaySchedule findById(Long id) {
		return this.payScheduleDAO.findById(id);
	}

	public PaySchedule findById(Long id, String... include) {
		return this.payScheduleDAO.findById(id, include);
	}

	public PaySchedule delete(PaySchedule t) {
		return this.payScheduleDAO.delete(t);
	}

	public List<PaySchedule> findAll() {
		return this.payScheduleDAO.findAll();
	}

	public List<PaySchedule> findAll(Pager pager) {
		return this.payScheduleDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.payScheduleDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.payScheduleDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.payScheduleDAO.isUnique(id, fieldName, fieldValue);
	}

}
