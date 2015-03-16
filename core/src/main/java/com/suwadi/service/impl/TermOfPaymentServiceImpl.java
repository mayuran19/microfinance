package com.suwadi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suwadi.dao.TermOfPaymentDAO;
import com.suwadi.domain.TermOfPayment;
import com.suwadi.service.TermOfPaymentService;
import com.suwadi.web.pagination.PagedResultSet;
import com.suwadi.web.pagination.Pager;

@Service("termOfPaymentService")
public class TermOfPaymentServiceImpl implements TermOfPaymentService {
	private TermOfPaymentDAO termOfPaymentDAO;

	@Autowired
	public void setTermOfPaymentDAO(TermOfPaymentDAO termOfPaymentDAO) {
		this.termOfPaymentDAO = termOfPaymentDAO;
	}

	public TermOfPayment save(TermOfPayment t) {
		return this.termOfPaymentDAO.save(t);
	}

	public TermOfPayment update(TermOfPayment t) {
		return this.termOfPaymentDAO.update(t);
	}

	public TermOfPayment findById(Long id) {
		return this.termOfPaymentDAO.findById(id);
	}

	public TermOfPayment findById(Long id, String... include) {
		return this.termOfPaymentDAO.findById(id, include);
	}

	public TermOfPayment delete(TermOfPayment t) {
		return this.termOfPaymentDAO.delete(t);
	}

	public List<TermOfPayment> findAll() {
		return this.termOfPaymentDAO.findAll();
	}

	public List<TermOfPayment> findAll(Pager pager) {
		return this.termOfPaymentDAO.findAll(pager);
	}

	public PagedResultSet paginate(Pager pager) {
		return this.termOfPaymentDAO.paginate(pager);
	}

	public Integer getAllCount() {
		return this.termOfPaymentDAO.getAllCount();
	}

	public Boolean isUnique(Long id, String fieldName, String fieldValue) {
		return this.termOfPaymentDAO.isUnique(id, fieldName, fieldValue);
	}

}
