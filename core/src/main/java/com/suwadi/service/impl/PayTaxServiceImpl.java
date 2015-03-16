package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.PayTaxDAO;
import com.suwadi.domain.PayTax;
import com.suwadi.service.PayTaxService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("payTaxService")
public class PayTaxServiceImpl implements PayTaxService {
	private PayTaxDAO payTaxDAO;

	@Autowired
	public void setPayTaxDAO(PayTaxDAO payTaxDAO) {
		this.payTaxDAO = payTaxDAO;
	}

	public PayTax save(PayTax t) {
		return this.payTaxDAO.save(t);
	}

	public PayTax update(PayTax t) {
		return this.payTaxDAO.update(t);
	}

	public PayTax findById(Long id) {
		return this.payTaxDAO.findById(id);
	}

	public PayTax findById(Long id, String... include) {
		return this.payTaxDAO.findById(id, include);
	}

	public PayTax delete(PayTax t) {
		return this.payTaxDAO.delete(t);
	}

	public List<PayTax> findAll() {
		return this.payTaxDAO.findAll();
	}

	public List<PayTax> findAll(Pager pager) {
		return this.payTaxDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.payTaxDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.payTaxDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.payTaxDAO.isUnique(id, fieldName, fieldValue);
	}

	public List<PayTax> findAllFixedPayTaxes() {
		return this.payTaxDAO.findAllFixedPayTaxes();
	}

}
