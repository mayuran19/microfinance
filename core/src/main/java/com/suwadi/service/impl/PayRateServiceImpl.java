package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.PayRateDAO;
import com.suwadi.domain.PayRate;
import com.suwadi.service.PayRateService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("payRateService")
public class PayRateServiceImpl implements PayRateService {
	private PayRateDAO payRateDAO;

	@Autowired
	public void setPayRateDAO(PayRateDAO payRateDAO) {
		this.payRateDAO = payRateDAO;
	}

	public PayRate save(PayRate t) {
		return this.payRateDAO.save(t);
	}

	public PayRate update(PayRate t) {
		return this.payRateDAO.update(t);
	}

	public PayRate findById(Long id) {
		return this.payRateDAO.findById(id);
	}

	public PayRate findById(Long id, String... include) {
		return this.payRateDAO.findById(id, include);
	}

	public PayRate delete(PayRate t) {
		return this.payRateDAO.delete(t);
	}

	public List<PayRate> findAll() {
		return this.payRateDAO.findAll();
	}

	public List<PayRate> findAll(Pager pager) {
		return this.payRateDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.payRateDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.payRateDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.payRateDAO.isUnique(id, fieldName, fieldValue);
	}

}
