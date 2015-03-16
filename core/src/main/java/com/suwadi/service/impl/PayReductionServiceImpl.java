package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.PayReductionDAO;
import com.suwadi.domain.PayReduction;
import com.suwadi.service.PayReductionService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("payReductionService")
public class PayReductionServiceImpl implements PayReductionService {
	private PayReductionDAO payReductionDAO;

	@Autowired
	public void setPayReductionDAO(PayReductionDAO payReductionDAO) {
		this.payReductionDAO = payReductionDAO;
	}

	public PayReduction save(PayReduction t) {
		return this.payReductionDAO.save(t);
	}

	public PayReduction update(PayReduction t) {
		return this.payReductionDAO.update(t);
	}

	public PayReduction findById(Long id) {
		return this.payReductionDAO.findById(id);
	}

	public PayReduction findById(Long id, String... include) {
		return this.payReductionDAO.findById(id, include);
	}

	public PayReduction delete(PayReduction t) {
		return this.payReductionDAO.delete(t);
	}

	public List<PayReduction> findAll() {
		return this.payReductionDAO.findAll();
	}

	public List<PayReduction> findAll(Pager pager) {
		return this.payReductionDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.payReductionDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.payReductionDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.payReductionDAO.isUnique(id, fieldName, fieldValue);
	}

	public List<PayReduction> findAllFixedPayReductions() {
		return this.payReductionDAO.findAllFixedPayReductions();
	}

}
